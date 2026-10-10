package com.taxistore.dao.impl;
/**
 * DAO 類別負責操作 line_message 資料表。
 */

import com.taxistore.dao.ILineMessageDao;
import com.taxistore.pojo.LineMessage;


public class LineMessageDao extends AbstractFormDao<LineMessage> implements ILineMessageDao {

    private static final String TABLE_NAME = "line_message";
    private static final String KEY_NAME = "serialNo";
    /**
     * 建構子，接收資料庫連線。
     *
     */
    public LineMessageDao() {
        super(LineMessage.class,TABLE_NAME,KEY_NAME );
    }

}
