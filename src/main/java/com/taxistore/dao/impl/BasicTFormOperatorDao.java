package com.taxistore.dao.impl;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;


/**
 * @author luckf
 */
public class BasicTFormOperatorDao<T> extends BaseTDao
{
    private final Class<T> type;
    private final String TableName;
    public BasicTFormOperatorDao(Class<T> type, String TableName)
    {
        this.TableName=TableName;
        this.type=type;
    }
    public Result<List<T>> getItems()
    {
        String sql="SELECT * FROM "+TableName+";";
        Result<List<T>> result = queryForList(type, sql);
        return result;
    }

    public Result<T> getFirst(String KeyName)
    {
        String sql="SELECT * FROM "+TableName+" ORDER BY "+ KeyName+ " LIMIT 1;";
        Result<T> result=queryForOne(type,sql);

        return result;
    }
    public Result<T> getLast(String KeyName)
    {
        String sql="SELECT * FROM "+TableName+" ORDER BY "+KeyName+" DESC LIMIT 0 , 1";
        //String sql ="select *  from t_car_list where id = ?";
        Result<T> result=queryForOne(type,sql);
        return result;
    }
    public Result<T> getNext(String KeyName,long serialNo)
    {
        String sql="SELECT * FROM "+TableName+" where "+KeyName+" > ? order by "+KeyName+" ASC limit 1";
        Result<T> result= queryForOne(type,sql,serialNo);

        /**
         * 執行SQL成功，但是沒有獲得資料。可以得知為最後一筆資料。
         */
        if (result.isSuccess() && result.getData() == null) {
            return new Result<T>()
                    .setSuccess(false)
                    .setErrorMessage("database.query.lastrecord","No next record found");
        }

        return result;
    }
    public Result<T> getPrevious(String KeyName,long serialNo)
    {
        String sql="SELECT * FROM "+TableName+" where "+ KeyName+" < ? order by "+KeyName+" DESC limit 1";
        Result<T> result= queryForOne(type,sql,serialNo);

        return result;
    }


    public Result<T> getItem(String KeyName,long serialNo)
    {
        String sql="SELECT * FROM "+TableName+" where "+KeyName+" = ?";
        Result<T> result= queryForOne(type,sql,serialNo);

        return result;
    }
    public Result<T> getItem(String KeyName,String KeyValue)
    {
        String sql="SELECT * FROM "+TableName+" where "+KeyName+" = ?";
        Result<T> result= queryForOne(type,sql,KeyValue);

        return result;
    }

    public Result<Integer> deleteByIndex(String KeyName,long serialNo)
    {
        String sql="DELETE FROM "+TableName+" where "+KeyName+" = ?";
        Result<Integer> result=  update(sql, serialNo);

        return result;
    }
}
