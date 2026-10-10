package com.taxistore.config;

import com.utils.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author luckf
 */
public class DBConfig {
    private static volatile DBConfig instance;
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static boolean initialized = false;

    // 私有構造函數，防止外部實例化
    private DBConfig() {
        // 啟用 DriverManager 調試模式
        System.setProperty("java.sql.DriverManager", "DEBUG");
    }

    /**
     * 獲取 DBConfig 單例
     *
     * @return DBConfig 單例
     */
    public static DBConfig getInstance() {
        if (instance == null) {
            synchronized (DBConfig.class) {
                if (instance == null) {
                    instance = new DBConfig();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化資料庫配置
     *
     * @param configFile 配置檔案名稱，預設為 "DBConfig.properties"
     */
    public synchronized void initializeFromConfig(String configFile) {
        if (initialized) return;

        Properties properties = new Properties();
        String fileName = (configFile == null || configFile.isEmpty()) ? "DBConfig.properties" : configFile;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException(fileName + " not found!");
            }

            properties.load(input);
            dbUrl = properties.getProperty("Url");
            dbUsername = properties.getProperty("Username");
            dbPassword = properties.getProperty("Password");

            // 显式加载驱动
            String driverClassName = properties.getProperty("DriverClassName");
            if (driverClassName != null && !driverClassName.isEmpty()) {
                try {
                    Class.forName(driverClassName);
                    Log.normal("Driver loaded: " + driverClassName);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Failed to load database driver: " + driverClassName, e);
                }
            }
            // 測試連接
            testConnection();

            initialized = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
    }

    /**
     * 獲取資料庫連接
     *
     * @return Connection 資料庫連接
     * @throws SQLException 資料庫連接錯誤
     */
    public Connection getConnection() throws SQLException {
        if (!initialized) initializeFromConfig(null);
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    /**
     * 測試資料庫連接
     */
    private void testConnection() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            System.out.println("Database connection test successful.");
        } catch (SQLException e) {
            throw new RuntimeException("Database connection test failed. Check your configuration.", e);
        }
    }
}
