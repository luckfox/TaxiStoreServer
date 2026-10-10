/**
 * 通用函式：透過 AJAX 呼叫 lineUserServlet 發送多張圖片 + 文字訊息
 *
 * @param {string} userId - 目標使用者 LINE ID
 * @param {string} message - 欲傳送的文字訊息
 * @param {Map<string, Object>} metaMap - Map 結構，key 為 hashCode，value 需包含 imageId, hashCode
 * @param {boolean} useCloudinary - 是否使用 Cloudinary 模式（對應不同 servlet action）
 */
function pushMultiImageMessageUnified(userId, message, metaMap, useCloudinary = false) {
    if (!(metaMap instanceof Map)) {
        alert("圖片資料格式錯誤，請聯絡工程師喝咖啡！");
        return;
    }

    const metasArray = Array.from(metaMap.values());

    const payload = {
        userId: userId,
        message: message,
        imagePaths: metasArray
    };

    const action = useCloudinary
        ? "ajaxPushMultiImageMessageByCloudinaryMetadata"
        : "ajaxPushMultiImageMessage";

    fetch(`lineUserServlet?action=${action}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                alert("訊息發送成功！");
                console.log("訊息已紀錄，訊息ID:", result.data);
            } else {
                alert("訊息發送失敗：" + result.errorMessage);
            }
        })
        .catch(error => {
            console.error("發送時發生錯誤: ", error);
            alert("發送時發生錯誤，請稍後重試！");
        });
}


/**
 * 透過 AJAX 呼叫 lineUserServlet 發送多張圖片 + 文字訊息
 *
 * @param {string} userId - 目標使用者 LINE ID
 * @param {string} message - 欲傳送的文字訊息
 * @param {Array} metas - 圖片 meta 資訊陣列，每個元素具有 imageId, hashCode
 */
function pushMultiImageMessageT(userId, message, metas) {

    const payload = {
        userId: userId,
        message: message,
        imagePaths: metas
    };

    fetch('lineUserServlet?action=ajaxPushMultiImageMessage', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                alert("訊息發送成功！");
                console.log("訊息已紀錄，訊息ID:", result.data);
            } else {
                alert("訊息發送失敗：" + result.errorMessage);
            }
        })
        .catch(error => {
            console.error("發送時發生錯誤: ", error);
            alert("發送時發生錯誤，請稍後重試！");
        });
}
/**
 * 透過 AJAX 呼叫 lineUserServlet 發送多張圖片 + 文字訊息
 *
 * @param {string} userId - 目標使用者 LINE ID
 * @param {string} message - 欲傳送的文字訊息
 * @param {Array} metas - 圖片 meta 資訊陣列，每個元素具有 imageId, hashCode
 */
function pushMultiImageMessage(userId, message, metas) {

    const payload = {
        userId: userId,
        message: message,
        imagePaths: metas
    };

    fetch('lineUserServlet?action=ajaxPushMultiImageMessageByCloudinaryMetadata', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                alert("訊息發送成功！");
                console.log("訊息已紀錄，訊息ID:", result.data);
            } else {
                alert("訊息發送失敗：" + result.errorMessage);
            }
        })
        .catch(error => {
            console.error("發送時發生錯誤: ", error);
            alert("發送時發生錯誤，請稍後重試！");
        });
}