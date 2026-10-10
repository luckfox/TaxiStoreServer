package com.taxistore.pojo;
/*
CREATE TABLE `line_message` (
  `serialNo` int(10) NOT NULL AUTO_INCREMENT COMMENT '序列號',
  `userId` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '訊息發送對象',
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '內文種類',
  `timeStamp` timestamp NULL DEFAULT NULL COMMENT '發送時間',
  `messageId` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '訊息ID',
  `content` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '內文種類',
  `directory` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '發送/接收',
  PRIMARY KEY (`serialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci


 */

import java.sql.Timestamp;

public class LineMessage {

    private Long serialNo;        // 序列號
    private String userId;       // 訊息發送對象
    private String type;         // 內文種類
    private Timestamp timeStamp; // 發送時間
    private String messageId;    // 訊息ID
    private String content;      // 內文內容
    private String directory;    // 發送/接收

    // 無參數構造函數
    public LineMessage() {}

    // 帶參數構造函數
    public LineMessage(Long serialNo, String userId, String type, Timestamp timeStamp,
                       String messageId, String content, String directory) {
        this.serialNo = serialNo;
        this.userId = userId;
        this.type = type;
        this.timeStamp = timeStamp;
        this.messageId = messageId;
        this.content = content;
        this.directory = directory;
    }

    // Getter 和 Setter 方法
    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return "LineMessage{" +
                "serialNo=" + serialNo +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", timeStamp=" + timeStamp +
                ", messageId='" + messageId + '\'' +
                ", content='" + content + '\'' +
                ", directory='" + directory + '\'' +
                '}';
    }
}
