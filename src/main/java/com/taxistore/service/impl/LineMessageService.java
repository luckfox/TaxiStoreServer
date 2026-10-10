package com.taxistore.service.impl;

import com.taxistore.dao.impl.LineMessageDao;
import com.taxistore.pojo.LineMessage;
import com.taxistore.pojo.Result;
import com.taxistore.service.ILineMessageService;
import com.utils.Log;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class LineMessageService implements ILineMessageService {
    private final LineMessageDao lineMessageDao=new LineMessageDao();

    /**
     * 建構子，初始化 DAO 層
     */
    public LineMessageService() {

    }

    /**
     * 新增訊息
     * @param message 要插入的 LineMessage 物件
     * @return Result 物件，包含新訊息的 serialNo 或錯誤訊息
     */
    public Result<Long> addMessage(LineMessage message)
    {
        //把帶有ES6的字元處理掉。
        message.setContent(message.getContent().replaceAll("[^\\u0000-\\uFFFF]", "?"));
        return lineMessageDao.insertEntity(message);
    }
    /**
     * 更新訊息內容
     * @param message 要更新的 LineMessage 物件
     * @return Result 物件，包含受影響的行數或錯誤訊息
     */
    @Override
    public Result<Integer> updateMessage(LineMessage message)
    {
        return lineMessageDao.updateEntity(message);
    }
    /**
     * 刪除指定訊息
     * @param serialNo 要刪除的 LineMessage 的Id
     * @return Result 物件，包含刪除結果或錯誤訊息
     */
    @Override
    public Result<Integer> deleteMessage(Long serialNo)
    {
        return lineMessageDao.deleteEntity(serialNo);
    }
    /**
     * 取得單筆訊息
     * @param serialNo 訊息的序列號
     * @return Result 物件，包含對應的 LineMessage 或錯誤訊息
     */
    @Override
    public Result<LineMessage> getMessageById(Long serialNo)
    {
        return lineMessageDao.getEntity(serialNo);
    }



    /**
     * 取得所有訊息
     * @return Result 物件，包含所有訊息列表或錯誤訊息
     */
    public Result<List<LineMessage>> getAllMessages() {
        return lineMessageDao.getEntities();
    }

    /**
     * 依 userId 查詢該使用者的所有訊息
     * @param userId 使用者 ID
     * @return Result 物件，包含訊息列表或錯誤訊息
     */
    public Result<List<LineMessage>> getMessagesByUserId(String userId) {
        return lineMessageDao.getEntitiesByField("userId",userId);
    }
    public static void main(String[] args)
    {
        LineMessage lineMessage=new LineMessage();
        LineMessageService lineMessageService=new LineMessageService();
        lineMessage.setContent("123456");
        lineMessage.setType("message");
        lineMessage.setDirectory("");
        lineMessage.setUserId("Uc5f2f40eebcbff5ad54e21a6f411a97e");
        lineMessage.setMessageId("550314710348857729");
        Result<Long> result0=lineMessageService.addMessage(lineMessage);
        if(result0.isSuccess())
        {
            lineMessage.setSerialNo(result0.getData());
            lineMessage.setMessageId("550314710348857730");
            lineMessage.setContent("Hello baby!");
            lineMessage.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            Result<Integer>result2=lineMessageService.updateMessage(lineMessage);
            //Result<Integer>result3=lineMessageService.deleteMessage(result0.getData());
            //Log.normal(result3.toJSON());
        }
        Result<List<LineMessage>>result4=lineMessageService.getAllMessages();
    }
}
