/*
 * Modal 操作工具類，協助處理動態載入 modal 的元素查找與事件綁定
 */
class DynamicModalHelper {
    /*
     * @param {string|jQuery} modalRoot - Modal 根元素或 selector
     */
    constructor(modalRoot) {
        this.$modal = (modalRoot instanceof jQuery) ? modalRoot : $(modalRoot);
    }

    /*
     * 依據 id 結尾來查找 modal 內部的元素
     * @param {string} idSuffix - ID 結尾（如 'btnSubmit'）
     * @returns {jQuery} 找到的元素
     */
    find(idSuffix) {
        return this.$modal.find(`[id$='${idSuffix}']`);
    }

    /*
     * 綁定按鈕 click 事件
     * @param {string} idSuffix - ID 結尾
     * @param {Function} handler - 處理函式
     */
    onClick(idSuffix, handler) {
        this.find(idSuffix).on('click', handler);
    }

    /*
     * 顯示 modal
     */
    show() {
        this.$modal.modal('show');
    }

    /*
     * 隱藏 modal
     */
    hide() {
        this.$modal.modal('hide');
    }

    /*
     * 取得某個欄位的值
     * @param {string} idSuffix - 欄位 id 的結尾
     */
    getValue(idSuffix) {
        return this.find(idSuffix).val();
    }

    /*
     * 設定欄位的值
     * @param {string} idSuffix - 欄位 id 的結尾
     * @param {string} value - 欲設定的值
     */
    setValue(idSuffix, value) {
        this.find(idSuffix).val(value);
    }

    /*
     * 回傳 modal jQuery 實體（必要時可用）
     */
    getRoot() {
        return this.$modal;
    }
}
