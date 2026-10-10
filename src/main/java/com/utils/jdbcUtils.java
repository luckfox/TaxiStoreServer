package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by luckf on 2022/9/22.
 */
public class jdbcUtils
{
    private static DruidDataSource dataSource;
    private static Connection conn=null;
    static
    {
        Properties properties = new Properties();
        InputStream inputStream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try
        {
            properties.load(inputStream);
            //建立資料庫
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println("dataSource init OK!");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        try
        {
            if(conn==null)
            {
                Class.forName("com.mysql.jdbc.Driver");
                conn = dataSource.getConnection();
                System.out.println("create Connection="+conn);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        //如果conn已經存在,則直接使用
        return conn;

    }
    public static void close(Connection conn)
    {
        if(conn!=null)
        try
        {
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
