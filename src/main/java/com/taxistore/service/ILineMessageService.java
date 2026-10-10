package com.taxistore.service;

import com.taxistore.pojo.LineMessage;
import com.taxistore.pojo.Result;

import java.util.List;

/**
 * ILineMessageService 介面，定義與 LineMessage 相關的業務邏輯。
 */
public interface ILineMessageService {

    /**
     * 新增一筆訊息。
     * @param message LineMessage 物件
     * @return 包含插入結果的 Result 物件
     */
    Result<Long> addMessage(LineMessage message);

    /**
     * 更新指定的訊息。
     * @param message LineMessage 物件
     * @return 包含更新結果的 Result 物件
     */
    Result<Integer> updateMessage(LineMessage message);

    /**
     * 刪除指定的訊息。
     * @param serialNo 訊息的序列號
     * @return 包含刪除結果的 Result 物件
     */
    Result<Integer> deleteMessage(Long serialNo);

    /**
     * 透過序列號查詢單筆訊息。
     * @param serialNo 訊息的序列號
     * @return 包含查詢結果的 Result 物件
     */
    Result<LineMessage> getMessageById(Long serialNo);

    /**
     * 查詢所有訊息。
     * @return 包含訊息列表的 Result 物件
     */
    Result<List<LineMessage>> getAllMessages();

    /**
     * 透過 userId 查詢該使用者的所有訊息。
     * @param userId 使用者 ID
     * @return 包含該使用者訊息列表的 Result 物件
     */
    Result<List<LineMessage>> getMessagesByUserId(String userId);
}
