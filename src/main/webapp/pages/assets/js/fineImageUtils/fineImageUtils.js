

/**
 * 根據 hash 對照是否存在於後端
 * @param {string} fineNo - 罰單編號
 * @returns {Promise<Set<string>>} - 回傳存在於後端的 hashCode Set
 */
async function fetchExistingHashes(fineNo) {
    const response = await $.getJSON(`fineImageServlet?action=ajaxGetImageMetasByFineNo&fineNo=${fineNo}`);
    if (response.success && Array.isArray(response.data)) {
        return new Set(response.data.map(img => img.hashCode));
    }
    return new Set();
}

function fetchFineImageHashes(fineNo, callback) {
    const url = 'fineImageServlet?action=ajaxGetImageMetasByFineNo&fineNo=' + encodeURIComponent(fineNo);
    $.getJSON(url, function (res) {
        if (res && res.data) {
            const serverHashes = res.data.map(img => img.hashCode);
            callback(serverHashes);
        } else {
            console.warn('⚠️ 無回傳 hash 資料');
            callback([]);
        }
    }).fail(function (err) {
        console.error('❌ 取得 hash 失敗:', err);
        callback([]);
    });
}
/**
 * 向後端取得某罰單號碼的所有圖片 Hash 清單
 * @param {string} fineNo - 罰單編號
 * @returns {Promise<Set<string>>} - 回傳已存在圖片的 hashCode Set
 */
async function fetchFineImageHashes(fineNo) {
    const url = `fineImageServlet?action=ajaxGetImageMetasByFineNo&fineNo=${encodeURIComponent(fineNo)}`;
    const response = await $.getJSON(url);
    if (response.success && Array.isArray(response.data)) {
        return new Set(response.data.map(img => img.hashCode));
    }
    return new Set();
}
/**
 * 根據現有 Hash 清單標記圖片是否已存在
 * @param {Array<Object>} imageList - 使用者已選的圖片清單，含 id 與 hash
 * @param {Set<string>} existingHashes - 後端回傳的已存在 hashCode Set
 */
function markExistingImages(imageList, existingHashes) {
    imageList.forEach(image => {
        image.exists = existingHashes.has(image.hash);
        const wrapper = document.querySelector(`[data-id="${image.id}"]`);
        if (wrapper) {
            wrapper.classList.toggle('border-danger', image.exists);
            wrapper.classList.toggle('border', image.exists);
        }
    });
}