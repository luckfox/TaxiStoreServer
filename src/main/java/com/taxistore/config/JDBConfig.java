package com.taxistore.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.utils.Log;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 資料庫配置和連接管理類，使用 Druid 連接池。
 * 支援多執行緒安全的連接操作。
 * @author luckf
 */
public class JDBConfig {
    private static final DruidDataSource dataSource;
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        try (InputStream inputStream = JDBConfig.class.getClassLoader().getResourceAsStream("jdbc.properties"))
        {
            if (inputStream == null)
            {
                throw new RuntimeException("jdbc.properties file not found！");
            }

            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            Log.normal("Druid datasource 初始化成功");

        } catch (Exception e)
        {
            Log.error("Druid datasource 初始化失敗");
            throw new RuntimeException("Druid datasource init fail", e);
        }
    }

    /**
     * 獲取資料庫連接。
     * 如果當前執行緒尚未綁定連接，則從連接池中取得新連接。
     *
     * @return 資料庫連接
     */
    public static Connection getConnection() {
        Connection conn = connectionHolder.get();
        try {
            // 检查连接是否有效
            if (conn == null || conn.isClosed()) {
                conn = dataSource.getConnection();
                connectionHolder.set(conn);
                Log.normal("Acquired new connection: " + conn);
            }
        } catch (SQLException e) {
            Log.error("Failed to get a valid connection!");
            throw new RuntimeException("Failed to get a valid connection!", e);
        }
        return conn;
    }


    /**
     * 關閉當前執行緒綁定的資料庫連接。
     */
    public static void closeConnection()
    {
        Connection conn = connectionHolder.get();
        if (conn != null)
        {
            try {
                if (!conn.isClosed())
                {
                    conn.close();
                    Log.normal("Closed connection: " + conn);
                }
            } catch (SQLException e)
            {
                Log.error("Failed to close connection: " + conn);
            } finally
            {
                connectionHolder.remove();
            }
        }
    }


    /**
     * 關閉資料來源（在應用程式結束時調用）。
     */
    public static void shutdownDataSource()
    {
        if (dataSource != null)
        {
            dataSource.close();
            Log.warning("Datasource closed successly.");
        }
    }

    /**
     * 測試方法，用於檢查資料庫連接池是否配置正確，並嘗試建立連接。
     * 在開發或部署階段可用於快速驗證配置。
     */
    public static void testConnection() {
        System.out.println("正在測試資料庫連接...");
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("資料庫連接成功: " + conn);
            }
        } catch (Exception e) {
            System.out.println("資料庫連接測試失敗: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                try (Connection conn = JDBConfig.getConnection()) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Connection acquired");
                    // 模拟 SQL 查询
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

}
