package com.taxistore.pojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utils.Language2Utils;


public class Result<T> {
    private T data;
    private String errorMessage;
    private boolean success;
    // 默認的語言環境
    //private static Locale currentLocale = Locale.getDefault();

    // 預設資源包名稱
    //private static final String RESOURCE_BUNDLE_NAME = "messages";

    // 靜態方法快速創建成功的 Result
    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setSuccess(true)
                .setData(data);
    }

    // 靜態方法快速創建失敗的 Result
    // for example： return Result.failure(r.getErrorMessage());
    public static <T> Result<T> failure(String errorMessage) {
        return new Result<T>()
                .setSuccess(false)
                .setErrorMessage(errorMessage);
    }

    // 成功時的構造方法
    public Result(T data)
    {
        this.data = data;
        this.success = true;
    }

    // 失敗時的構造方法，支持多國語言
    public Result(String errorKey, Object... args)
    {
        this.errorMessage = Language2Utils.getTranslation(errorKey, args);
        this.success = false;
    }
    public Result()
    {
        this.errorMessage = Language2Utils.getTranslation("default.error","unknown error");
        this.success = false;
    }
    // 判斷是否成功
    public boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success)
    {
        this.success = success;
        return this;
    }
    // 獲取數據
    public T getData() {
        return data;
    }
    public Result<T> setData(T data)
    {
        this.data = data;
        return this; // 返回當前實例以支持鏈式調用
    }
    // 獲取錯誤訊息
    public String getErrorMessage() {
        return errorMessage;
    }
    public Result<T> setErrorMessage(String errorKey, Object... args)
    {
        this.errorMessage = Language2Utils.getTranslation(errorKey, args);
        return this;
    }



    @Override
    public String toString()
    {
        if (success)
        {
            return "Result{success=true, data=" + data + "}";
        } else {
            return "Result{success=false, errorMessage='" + errorMessage + "'}";
        }
    }
    /**
     * 將 Result 物件轉換為 JSON 字串
     * @return JSON 格式的字串
     */
    public String toJSON() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this);

    }
    public static void main(String[] args) {
        Result<String> result = new Result<>("Test error message");
        System.out.println(result.toJSON());
        System.out.println(new Result<>(null));
    }

}
