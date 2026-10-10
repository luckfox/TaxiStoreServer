/**
 * 負責圖片上傳邏輯（不處理任何 DOM）
 * - 可透過 setCallbacks() 注入進度、成功、錯誤的回呼處理
 */
class ImageUploader {
    #previewInfoMap;
    #uploadUrl;
    #onProgress;
    #onSuccess;
    #onError;
    /**
     * @param {Map<string, {File: File, IsExisting: boolean, hashCode: string}>} previewInfoMap - hash 對應的檔案資料
     * @param {string} [uploadUrl] - 圖片上傳 API URL
     */
    constructor(previewInfoMap, uploadUrl = 'fineImageServlet?action=ajaxUploadFineImage') {
        this.#previewInfoMap = previewInfoMap;
        this.#uploadUrl = uploadUrl;

        this.#onProgress = null;
        this.#onSuccess = null;
        this.#onError = null;
    }

    /**
     * 註冊外部回呼，用於處理進度、成功、錯誤事件
     * @param {Object} callbacks
     * @param {function(string, number):void} [callbacks.onProgress] - 進度更新，hash 與百分比
     * @param {function(string, string):void} [callbacks.onSuccess] - 成功，hash 與 imageId
     * @param {function(string, string):void} [callbacks.onError] - 錯誤，hash 與訊息
     */
    setCallbacks({onProgress, onSuccess, onError}) {
        this.#onProgress = onProgress;
        this.#onSuccess = onSuccess;
        this.#onError = onError;
    }

    /**
     * 取得尚未上傳的圖片 hash 陣列
     * @returns {string[]}
     */
    getPendingHashes() {
        return [...this.#previewInfoMap.entries()]
            .filter(([_, info]) => !info.IsExisting && info.File instanceof File)
            .map(([hash]) => hash);
    }

    /**
     * 上傳單一圖片檔案
     * @param {string} hash - 檔案的雜湊碼
     * @param {string} fineNo - 案件編號
     * @returns {Promise<{hash: string, imageId: string}>}
     */
    async uploadOne(hash, fineNo) {
        console.log(`[${hash}] 上傳開始 @ ${performance.now().toFixed(1)}ms`);
        const info = this.#previewInfoMap.get(hash);
        if (!info || !(info.File instanceof File)) {
            throw new Error(`無檔案可上傳：${hash}`);
        }

        const formData = new FormData();
        formData.append('fineNo', fineNo);
        formData.append('file', info.File);

        return new Promise((resolve, reject) => {
            const xhr = new XMLHttpRequest();
            xhr.open('POST', this.#uploadUrl, true);

            // 處理進度事件
            xhr.upload.onprogress = (e) => {
                if (e.lengthComputable && typeof this.#onProgress === 'function') {
                    const percent = Math.round((e.loaded / e.total) * 100);
                    this.#onProgress(hash, percent);
                }
            };

            // 回應處理
            xhr.onload = () => {
                try {
                    const json = JSON.parse(xhr.responseText);
                    if (json.success) {
                        this.#onSuccess?.(hash, json.data);
                        resolve({hash, metaMap: json.data});//修正為metaMap
                    } else {
                        this.#onError?.(hash, json.message);
                        reject(new Error(json.message));
                    }
                } catch (err) {
                    this.#onError?.(hash, '回應解析錯誤');
                    reject(new Error('回應解析錯誤'));
                }
            };

            xhr.onerror = () => {
                this.#onError?.(hash, '網路錯誤');
                reject(new Error('網路錯誤'));
            };

            xhr.send(formData);
        });
    }

    /**
     * 上傳所有圖片（可切換為串行或併發模式）
     * @param {string} fineNo - 案件編號
     * @param {boolean} [useParallel=false] - 是否使用併發（多緒）上傳
     * @returns {Promise<Array<{hash: string, status: string, imageId?: string, message?: string}>>}
     */
    async uploadAll(fineNo, useParallel = false) {
        const hashes = this.getPendingHashes();
        const results = [];

        if (useParallel) {
            // 🚀 多緒上傳（併發）
            const uploadPromises = hashes.map(hash =>
                this.uploadOne(hash, fineNo)
                    .then(res => ({hash: res.hash, status: 'success', ...res.metaMap}))
                    .catch(err => ({hash, status: 'error', message: err.message}))
            );

            return await Promise.all(uploadPromises);
        } else {
            // ✅ 串行上傳（逐張）
            for (const hash of hashes) {
                try {
                    const res = await this.uploadOne(hash, fineNo);
                    results.push({hash: res.hash, status: 'success',...res.metaMap});
                } catch (err) {
                    results.push({hash, status: 'error', message: err.message});
                }
            }
            return results;
        }
    }
}



