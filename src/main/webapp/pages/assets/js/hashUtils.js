/**
 * 計算圖片的 SHA-256 雜湊值並輸出十六進位字串（與 Java 相同）
 * @param {File} file - 上傳的圖片檔案
 * @returns {Promise<string>} 雜湊字串（hex）
 */
async function calculateImageHash(file) {
    const arrayBuffer = await file.arrayBuffer();
    const hashBuffer = await crypto.subtle.digest('SHA-256', arrayBuffer);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hexHash = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
    return hexHash;
}
/**
 * 計算 JPEG 圖檔的「快速比對用 hash」
 * 結合前 1KB bytes + file.size
 * @param {File} file
 * @returns {Promise<string>} hash 字串
 */
async function quickJpegHash(file) {
    const headerBlob = file.slice(0, 1024); // 抓前 1KB
    const buffer = await headerBlob.arrayBuffer();
    const headerBytes = new Uint8Array(buffer);

    // 加上檔案大小
    const sizeBytes = new TextEncoder().encode(file.size.toString());

    // 組合成新 ArrayBuffer
    const combined = new Uint8Array(headerBytes.length + sizeBytes.length);
    combined.set(headerBytes, 0);
    combined.set(sizeBytes, headerBytes.length);

    const digest = await crypto.subtle.digest("SHA-256", combined.buffer);
    return Array.from(new Uint8Array(digest)).map(b => b.toString(16).padStart(2, '0')).join('');
}