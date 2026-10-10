package com.taxistore.dao.impl;
import com.utils.Log;

import java.util.List;


/**
 * @author luckf
 */
public class BasicFormOperatorDao<T> extends BaseDao
{
    private final Class<T> type;
    private final String TableName;
    public BasicFormOperatorDao(Class<T> type, String TableName)
    {
        this.TableName=TableName;
        this.type=type;
    }
    public List<T> getItems()
    {
        String sql="SELECT * FROM "+TableName+";";
        return queryForList(type,sql);
    }

    public T getFirst(String KeyName)
    {
        String sql="SELECT * FROM "+TableName+" ORDER BY "+ KeyName+ " LIMIT 1;";
        T t=queryForOne(type,sql);

        if(t==null)
        {
            System.out.println("資料無法取得");
            return  null;
        }
        System.out.println(t);
        return t;
    }
    public T getLast(String KeyName)
    {
        String sql="SELECT * FROM "+TableName+" ORDER BY "+KeyName+" DESC LIMIT 0 , 1";
        //String sql ="select *  from t_car_list where id = ?";
        T t=queryForOne(type,sql);

        if(t==null)
        {
            System.out.println("資料無法取得");
            return  null;
        }
        System.out.println(t);
        return t;
    }
    public T getNext(String KeyName,long serialNo)
    {
        String sql="SELECT * FROM "+TableName+" where "+KeyName+" > ? order by "+KeyName+" ASC limit 1";
        T t= queryForOne(type,sql,serialNo);

        if(t!=null) return t;

        Log.normal("It is the last entry");
        return null;
    }
    public T getPrevious(String KeyName,long serialNo)
    {
        String sql="SELECT * FROM "+TableName+" where "+ KeyName+" < ? order by "+KeyName+" DESC limit 1";
        T t= queryForOne(type,sql,serialNo);

        if(t!=null) return t;

        System.out.println("已經到第一筆");
        return null;
    }


    public T getItem(String KeyName,long serialNo)
    {
        String sql="SELECT * FROM "+TableName+" where "+KeyName+" = ?";
        return  queryForOne(type,sql,serialNo);
    }
    public T getItem(String KeyName,String KeyValue)
    {
        String sql="SELECT * FROM "+TableName+" where "+KeyName+" = ?";
        return  queryForOne(type,sql,KeyValue);
    }

    public Boolean deleteByIndex(String KeyName,long serialNo)
    {
        String sql="DELETE FROM "+TableName+" where "+KeyName+" = ?";
        return update(sql, serialNo) > 0;
    }
}
