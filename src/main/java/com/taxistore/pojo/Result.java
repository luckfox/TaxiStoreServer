package com.taxistore.pojo;

import java.util.Locale;
import java.util.ResourceBundle;

public class Result<T> {
    private T data;           // 成功時的數據
    private String error;     // 原始錯誤訊息
    private String errorCode; // 錯誤碼

    public Result() {}

    public Result(T data) {
        this.data = data;
    }

    public Result(String error, String errorCode) {
        this.error = error;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 根據當前語系解析錯誤訊息
     * @param language 指定語系（如 "en", "zh_TW"）
     * @return 多國語言錯誤訊息
     */
    public String getLocalizedErrorMessage(String language) {
        if (errorCode == null) return error;

        // 使用 Locale.forLanguageTag() 來處理語言標識符
        Locale locale = Locale.forLanguageTag(language);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        return bundle.getString(errorCode);
    }

    @Override
    public String toString() {
        return isSuccess()
                ? "Result{data=" + data + "}"
                : "Result{errorCode='" + errorCode + "', errorMessage='" + getLocalizedErrorMessage("en") + "'}";
    }
}
