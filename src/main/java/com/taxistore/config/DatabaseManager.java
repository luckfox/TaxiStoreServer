package com.taxistore.config;

import com.taxistore.config.DBConfig;
import com.taxistore.config.JDBConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author luckf
 */
public class DatabaseManager {

    /**
     * 資料庫提供者類型
     */
    public enum ProviderType {
        DBCONFIG, JDBCONFIG
    }

    /**
     * 預設為 DBConfig
      */

    //private static ProviderType providerType = ProviderType.DBCONFIG;
    private static ProviderType providerType = ProviderType.JDBCONFIG;
    /**
     * 設定資料庫提供者類型
     * @param type 提供者類型
     */
    public static void setProviderType(ProviderType type) {
        providerType = type;
    }

    /**
     * 獲取資料庫連接
     * @return Connection 資料庫連接
     * @throws SQLException 資料庫連接異常
     */
    public static Connection getConnection() throws SQLException {
        switch (providerType) {
            case JDBCONFIG:
                return JDBConfig.getConnection();
            case DBCONFIG:
            default:
                return DBConfig.getInstance().getConnection();
        }
    }

    /**
     * 測試資料庫連接
     */
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("資料庫連接成功，使用提供者: " + providerType);
            }
        } catch (SQLException e) {
            System.err.println("資料庫連接失敗，提供者: " + providerType);
            e.printStackTrace();
        }
    }
}
