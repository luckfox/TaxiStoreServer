package com.taxistore.service.impl;

import com.taxistore.dao.impl.AbstractFormDao;
import com.taxistore.pojo.Result;
import com.taxistore.service.IBasicOperatorService;

import java.util.List;

/*
 * @author luckf
 * @date
 * function 泛型服務類，提供基本的資料操作服務，針對某個特定的 (TableName)
 *          操作 DAO（資料訪問層）， 也就是說可以根據TableName，去操作相對應的Table。
 */
public class BasicOperatorService<T> implements IBasicOperatorService<T>
{
    /**
     * 資料訪問層 DAO 的實例，負責與資料庫交互
      */
    private final AbstractFormDao<T> dao;
    /**
     * 資料操作的表格名稱，用於標識操作的資料範圍
     */
    private final String TableName;

    BasicOperatorService(AbstractFormDao<T> dao, String TableName)
    {
        this.dao = dao;
        this.TableName=TableName;

    }

    /**
    判斷是否存在項目，目前返回固定的 false（可根據需求擴展邏輯）
     */
    @Override
    public Boolean existItem()
    {
        return false;
    }

    /**
     * 獲取鍵值對應的最後一個項目
     *
     * @return
     */
    @Override
    public Result<T> getLastItem(String KeyName)
    {
        return dao.getLast(KeyName);
    }
    /**
     * 獲取序號（serialNo）之後的下一個項目
     */
    @Override
    public Result<T> getNextItem(String KeyName,Integer serialNo)
    {
        return dao.getNext(KeyName,serialNo);
    }
    /**
     * 根據序號獲取指定的項目
     */
    @Override
    public Result<T> getItem(String KeyName,Integer serialNo)
    {
        return dao.getItem(KeyName,serialNo);
    }

    @Override
    public Result<T> getItem(String KeyName,String keyValue)
    {
        return dao.getItem(KeyName,keyValue);
    }
    /**
     * 獲取序號之前的上一個項目
      */
    @Override
    public Result<T> getPreviousItem(String KeyName,Integer serialNo)
    {
        return dao.getPrevious(KeyName,serialNo);
    }
    /**
     *  刪除指定序號的項目，並返回鄰近的項目（優先返回上一個項目，否則返回下一個）
     */
    @Override
    public Result<T> deleteItem(String KeyName,Integer serialNo)
    {
        Result<T> t=null;
        /**
         * 嘗試刪除序號對應的項目
         */
        if(dao.deleteByIndex(KeyName,serialNo).isSuccess())
        {
            /**
             * 刪除成功，嘗試獲取上一個項目
             */
            if(null==(t=dao.getPrevious(KeyName,serialNo)))
            {
                /**
                 * 上一個項目不存在，則嘗試獲取下一個項目
                 */
                t=dao.getNext(KeyName,serialNo);
            }
        }
        return t;
    }
    /**
     * 獲取所有項目
     */
    @Override
    public Result<List<T>> getItems()
    {
        return dao.getItems();
    }
}
