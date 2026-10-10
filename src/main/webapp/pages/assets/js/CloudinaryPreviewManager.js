class CloudinaryPreviewManager extends BasePreviewManager {

    #cloudName;
    #uploadPreset;
    #metadataList;
    constructor(config) {
        super(config);
        this.#cloudName = config.cloudName;
        this.#uploadPreset = config.uploadPreset;
        this.#metadataList = [];  // 初始化 metadata 清單

        this._carouselModalTag = config.carouselModalTag;
        this._carouselTag = config.carouselTag;
        this._rotateBtnSelector = config.rotateBtnSelector || '#rotateImageBtn';

        this.rotationManager = new CarouselRotationManager({
            modalSelector: this._carouselModalTag,
            carouselSelector: this._carouselTag,
            rotateBtnSelector: this._rotateBtnSelector,
            previewInfoMap: this.getPreviewInfoMap(), // 🔹 傳入 Map
            onRotate: async ({ key, imgEl }) => {
                // 先做視覺旋轉，給使用者立即回饋
                this.rotationManager.rotateElement(imgEl, key);

                // ⚙️（可選）若你想把「旋轉」永久化，可在這裡：
                // 1) 呼叫你自家後端，寫入該 key 的旋轉角度（方便下次還原）
                // 2) 或者重算 URL（插入 Cloudinary transformation，如 a_90），再更新 src
                try {
                    await this._syncRotationToBackend(key);
                } catch (err) {
                    console.warn('Cloudinary rotation sync failed:', err);
                }
            },
            onRotationChanged: (key, angle, imgEl) => {
                // 🔹 更新 previewInfoMap rotation
                const map = this.getPreviewInfoMap();
                const info = map.get(key) || {};
                map.set(key, { ...info, rotation: angle });
                console.log(`previewInfoMap roation: key=${key}, rotation=${angle}`);
            },
            onUpdateImage:(key,angle,imgEl)=>{
                console.log(`updateImage @@@@@@`);
            }
        });
    }
    /**
     * （可選）將旋轉狀態告訴後端做保存。這裡留空殼，依專案實作。
     * 例如：$.getJSON('/api/image/rotateMeta', { id: key, delta: 90 })
     */
    async _syncRotationToBackend(key) {
        // TODO: 依需求實作；這裡先打個日誌
        console.log(`[Cloudinary] sync rotation meta for key=${key}`);
    }
    /**
     * 從後端取得已存在 Cloudinary 之 metadata 並還原畫面
     */
    async remoteSync() {
        const result = await $.getJSON("imageMetadataServlet", { action: "getAllImageMetas" });
        if (!result.success) return;

        result.data.forEach(meta => {
            this.getPreviewInfoMap().set(meta.hashCode, { file: null, hash: meta.hashCode, existing: true, extraId: meta.imageId });
            const url = meta.cloudinarySecureUrl;
            this.buildPreviewElement(meta.hashCode, url, true);
            this.buildCarouselItem(meta.hashCode, url);
        });

        this.callCallback("onRemoteSync", result.data);
    }

    async uploadAll() {
        this.#metadataList = [];
        const uploadPromises = [...this.getPreviewInfoMap().entries()]
            .filter(([_, info]) => !info.existing)
            .map(([hash, info]) => this.#uploadSingle(hash, info.file));
        await Promise.all(uploadPromises);
        this.callCallback("onComplete", this.#metadataList);
    }

    // 完全接管刪除邏輯：Cloudinary刪除邏輯（注意: Cloudinary 可能不支援單純前端刪除）
    handleDeleteRequest(hash) {
        const info = this.getPreviewInfoMap().get(hash);
        if (!info) return;

        if (info.existing && info.extraId) {
            // 交由後端刪除 Cloudinary 內容
            $.getJSON(`imageMetadataServlet?action=deleteById&imageId=${info.extraId}`)
                .done(result => {
                    if (result.success) {
                        this.deleteFromUI(hash);
                        this.callCallback("onRemoteDeleteSuccess", hash);
                    } else {
                        this.callCallback("onDeleteFailed", hash, result.errorMessage);
                    }
                })
                .fail(err => {
                    this.callCallback("onDeleteFailed", hash, err);
                });
        } else {
            this.deleteFromUI(hash);
            this.callCallback("onLocalDelete", hash);
        }
    }

    #uploadSingle(hash, file) {
        return new Promise((resolve, reject) => {
            const url = `https://api.cloudinary.com/v1_1/${this.#cloudName}/upload`;
            const formData = new FormData();
            formData.append("file", file);
            formData.append("upload_preset", this.#uploadPreset);

            $.ajax({
                url: url,
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                xhr: () => {
                    const xhr = new XMLHttpRequest();
                    xhr.upload.addEventListener("progress", e => {
                        if (e.lengthComputable) {
                            let percent = Math.round(e.loaded * 100 / e.total);
                            this.callCallback("onProgress", hash, percent);
                            this.updateProgress(hash, percent);
                        }
                    });
                    return xhr;
                },
                success: (cloudResult) =>
                {
                    this.callCallback("onSuccess", hash, cloudResult);
                    const metadata = {
                        hashCode: hash,
                        cloudinaryPublicId: cloudResult.public_id,
                        cloudinarySecureUrl: cloudResult.secure_url,
                        bytes:cloudResult.bytes
                    };

                    // 成功後上傳 metadata 給後端儲存
                    $.getJSON("imageMetadataServlet", {
                        action: "saveMetadata",
                        metadata: JSON.stringify(metadata)
                    }).done(saveResult => {
                        if (saveResult.success) {
                            this.markUploaded(hash, saveResult.data);
                            this.#metadataList.push(metadata);
                            this.callCallback("onMetadataSaved", hash, metadata);
                            resolve();
                        } else {
                            this.callCallback("onMetadataSaveFailed", hash, saveResult.errorMessage);
                            reject(saveResult.errorMessage);
                        }
                    }).fail(err => {
                        this.callCallback("onMetadataSaveFailed", hash, err);
                        reject(err);
                    });
                },
                error: err => {
                    this.callCallback("onUploadError", hash, err);
                    reject(err);
                }
            });
        });
    }
}
