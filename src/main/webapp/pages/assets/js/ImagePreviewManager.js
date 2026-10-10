// import {BasePreviewManager} from './BasePreviewManager.js'
// import {CarouselRotationManager} from "./CarouselRotationManager.js";
// import {AutoAlert} from "./alert/autoAlert";

class ImagePreviewManager extends BasePreviewManager
{
    #previewTag;        // 預覽區容器 selector
    #carouselTag;       // Carousel 元件 selector
    #carouselModalTag
    #imagesMeta
    #imageData
    #imageDelete
    #imageUpload
    #imageRotation
    #alertBox
    constructor(config)
    {
        super(config);
        console.log("📦 進來的 config：", config);
        console.log("🧨 imageDelete:", config.imageDelete);
        this.#previewTag=config.previewTag;
        this.#carouselTag=config.carouselTag;
        this.#carouselModalTag=config.carouselModalTag;
        this.#imagesMeta=config.imagesMeta;
        this.#imageData=config.imageData;
        this.#imageDelete=config.imageDelete;
        this.#imageUpload=config.imageUpload;
        this.#imageRotation=config.imageRotation;
        this.#alertBox=new AutoAlert();
        // 因 Base 的私有欄位無法直接讀取，子類自行保存需要用到的 selector
        this._carouselModalTag = config.carouselModalTag;
        this._carouselTag = config.carouselTag;
        this._rotateBtnSelector = config.rotateBtnSelector || '#rotateImageBtn';
        this._imageRotation = config.imageRotation; // { servlet, action, parameter }
        this._imageData = config.imageData;         // { servlet, action, parameter? }

        // 建立「事件委派版」的 rotation manager
        this.rotationManager = new CarouselRotationManager({
            modalSelector: this._carouselModalTag,
            carouselSelector: this._carouselTag,
            rotateBtnSelector: this._rotateBtnSelector,
            previewInfoMap: this.getPreviewInfoMap(), // 🔹 傳入 Map
            onRotationChanged: (key, angle, imgEl) => {
                // 🔹 更新 previewInfoMap rotation
                const map = this.getPreviewInfoMap();
                const info = map.get(key) || {};
                map.set(key, { ...info, rotation: angle });
                console.log(`previewInfoMap roation: key=${key}, rotation=${angle}`);
            },
            onUpdateImage: async(key, angle, imgEl) => {
                await this.#rotateImage(key,angle);
                console.log(`previewInfoMap updated: key=${key}, rotation=${angle}`);
            }
        });

    }
    /**
     * 🚀 後端旋轉（僅限已上傳的圖）
     * 👉 如果要支援「本地旋轉」，請另外用 CSS transform。
     */
    async #rotateImage(key,angle) {
        const previewInfoMap=this.getPreviewInfoMap();
        const info = previewInfoMap.get(key.toString());
        if (!info || !info.extraId)
        {
            console.log("#rotateImage ...");
            return;
        }

        try {
            const r=this.#imageRotation;
            const url = `${r.servlet}?action=${r.action}&${r.parameter}=${info.extraId}&${r.parameter1}=${angle}`;
            const result = await $.getJSON(url);
            if (!result.success) throw new Error("後端旋轉失敗");

            info.hash = result.data.hashCode;

            // 更新 preview & carousel 圖
            const newSrc = `${this.#imageData.servlet}?action=${this.#imageData.action}&ImageId=${info.extraId}&Thumb=false&t=${Date.now()}`;
            $(`${this.#previewTag} .preview-wrapper[data-id="${key}"] img`).attr("src", newSrc);
            //$(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item[data-id="${key}"] img`).attr("src", newSrc);

            this.callCallback("onImageRotated", key, info.hash);
        } catch (e) {
            this.#alertBox.show("圖片旋轉失敗 ☠️");
            console.error(e);
        }
    }
    /*
     * 呼叫後端旋轉並回傳「新的取圖 URL」（附上 timestamp 破快取）
     */
    async _rotateOnServer(extraId) {
        const { servlet, action, parameter } = this._imageRotation || {};
        if (!servlet || !action || !parameter) throw new Error('imageRotation config 不完整');

        const url = `${servlet}?action=${encodeURIComponent(action)}&${encodeURIComponent(parameter)}=${encodeURIComponent(extraId)}`;
        const result = await $.getJSON(url);
        if (!result || !result.success) throw new Error(result?.errorMessage || '後端旋轉失敗');
        console.log(`update rotation image id=${extraId} success!`);
        // 回傳新 URL 讓外層刷新（注意有些後端參數名稱不同，這裡用 parameter 當 key）
        const dataApi = this._imageData;
        const idParam = dataApi.parameter || 'ImageId';
        const freshUrl = `${dataApi.servlet}?action=${encodeURIComponent(dataApi.action)}&${encodeURIComponent(idParam)}=${encodeURIComponent(extraId)}&Thumb=false&t=${Date.now()}`;
        return freshUrl;
    }
    /**
     * 同步刷新 preview + carousel 兩邊的 <img src>
     */
    _refreshImagesSrc(key, freshUrl) {
        // 右側預覽區
        $(`${this._carouselModalTag}`).find(`${this._carouselTag} .carousel-item[data-id="${key}"] img`).attr('src', freshUrl);
        // 左側縮圖區（預覽牆）
        // 注意：BasePreviewManager 建 preview 時的容器 selector 來自 config.previewTag
        // 由於這裡拿不到 Base 的私有 selector，我們改用「DOM 搜尋」方式：
        $(`.preview-wrapper[data-id="${key}"] img`).attr('src', freshUrl);
    }
    /**
     * 與後端同步現有圖片資料，並建立對應的預覽元件與 carousel 項目
     * @param parameterValue 用來查詢的參數值，例如序號
     */
    async remoteSync(parameterValue) {

        const metaMap = new Map();
        try {
            const url = `${this.#imagesMeta.servlet}?action=${this.#imagesMeta.action}&${this.#imagesMeta.parameter}=${parameterValue}&Thumb=false&t=${Date.now()}`;
            console.log(`remoteSync: ${url}`);
            // 向後端拉資料
            const result = await $.getJSON(url);
            if (!result.success) return;
            result.data.forEach(meta => {
                const key=meta.imageId.toString();
                // 將圖片資訊存入 BasePreviewManager 的 Map
                this.getPreviewInfoMap().set(key, {
                    file: null,
                    hash: meta.hashCode,
                    existing: true,
                    extraId: meta.imageId
                });
                // 拼出圖片實體 URL
                const url = `${this.#imageData.servlet}?action=${this.#imageData.action}&${this.#imageData.parameter}=${meta.imageId}&Thumb=false`;
                // 建立預覽與 carousel 項目
                this.buildPreviewElement(key, url, true);  // <= 這裡新增 true 讓它直接顯示紅色 100% 已上傳
                this.buildCarouselItem(key, url);
                // 把 meta 存到 metaMap
                metaMap.set(key, meta);
            });
            // 通知 callback
            console.log(`metaMap=`,metaMap);
            this.callCallback("onRemoteSync", parameterValue, metaMap);
        }catch (e)
        {
            console.error("remoteSync error", e);
        }
    }
    /**
     * 批次上傳所有尚未存在的檔案
     * @param serialNo 序號或額外識別碼
     */
    async uploadAll(serialNo) {
        const uploadPromises = [...this.getPreviewInfoMap().entries()]
            .filter(([_, info]) => !info.existing)
            .map(([key, info]) => this.#uploadSingle(key, info.file, serialNo));
        await Promise.all(uploadPromises);
        this.callCallback("onUploadAllSuccess");
    }

    /**
     * 覆寫刪除邏輯：如果是已存在的檔案 -> 呼叫後端 API；否則直接刪除 UI
     * @param key 預覽元素的唯一 key
     */
    handleDeleteRequest(key) {
        console.log(`@@ servlet = ${this.#imageDelete.servlet}`);//<==發生錯誤。
        const info = this.getPreviewInfoMap().get(key);
        this.#debugLogMap(`handleDeleteRequest key=${key}`);
        if (!info)
        {
            console.log(`Can't get key=${key} info`);
            return;
        }

        if (info.existing && info.extraId)
        {
            const url=`${this.#imageDelete.servlet}?action=${this.#imageDelete.action}&${this.#imageDelete.parameter}=${info.extraId}`;
            console.log(`handleDeleteRequest: ${url}`);
            $.getJSON(url)
                .done(result => {
                    if (result.success) {
                        this.deleteFromUI(key);
                        this.callCallback("onRemoteDeleteSuccess", key);
                    } else {
                        this.callCallback("onDeleteFailed", key, result.errorMessage);
                    }
                })
                .fail(err => {
                    this.callCallback("onDeleteFailed", key, err);
                });
        } else {
            this.deleteFromUI(key);
            this.callCallback("onLocalDelete", key);
        }
    }
    /** 🕵️‍♂️ Debug 用：印出目前 Map 狀態 */
    #debugLogMap(action)
    {
        const previewInfoMap=this.getPreviewInfoMap();
        const snapshot = Array.from(previewInfoMap.entries())
            .map(([k, v]) => ({ key: k, ...v }));
        console.log(`📌 [${action}] previewInfoMap =`, snapshot);
    }
    /**
     * 單一檔案上傳邏輯，包含進度監聽、成功 / 失敗 callback
     * @param key 預覽元素的 key
     * @param file 準備上傳的檔案物件
     * @param serialNo 序號或識別碼
     */
    #uploadSingle(key, file, serialNo)
    {

        return new Promise((resolve, reject) => {
            const formData = new FormData();
            formData.append("file", file);
            formData.append(`${this.#imageUpload.parameter}`, serialNo);
            const url=`${this.#imageUpload.servlet}?action=${this.#imageUpload.action}`;

            console.log(`uploadSingle: ${url}  ${this.#imageUpload.parameter}:${serialNo} file: ${file}`);
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
                            this.callCallback("onProgress", key, percent);
                            this.updateProgress(key, percent);
                        }
                    });
                    return xhr;
                },
                success: result => {
                    if (result.success===true && result.data)
                    {
                        const imageId =result.data.imageId;
                        this.callCallback("onUploadSuccess", key,result.data);

                        this.markUploaded(key, imageId);
                        resolve();
                    } else {
                        this.callCallback("onUploadError", key, result.errorMessage);
                        reject(result.errorMessage);
                    }
                },
                error: err => {
                    this.callCallback("onUploadError", key, err);
                    reject(err);
                }
            });
        });
    }
}
