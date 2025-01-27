package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.utils.Log;
import com.utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao
{
    private final QueryRunner queryRunner = new QueryRunner();

    /**
     * 執行 INSERT/UPDATE/DELETE 的通用方法
     *
     * @param sql  SQL 語句
     * @param args 參數列表
     * @return 受影響的行數
     */
    public int update(String sql, Object... args)
    {
        try (Connection conn = DatabaseManager.getConnection())
        {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e)
        {
            handleException(e);
            return -1;
        }
    }

    /**
     * 查詢單一 JavaBean 的方法
     *
     * @param <T>   返回類型的泛型
     * @param type  返回類型對象
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢結果
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            return queryRunner.query(conn, sql, new BeanHandler<>(type), args);
        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    /**
     * 查詢多條記錄返回列表的方法
     *
     * @param <T>   返回類型的泛型
     * @param type  返回類型對象
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢結果的列表
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            return queryRunner.query(conn, sql, new BeanListHandler<>(type), args);
        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    /**
     * 執行返回單行單列的查詢
     *
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢結果
     */
    public Object queryForSingleValue(String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            return queryRunner.query(conn, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    /**
     * 查詢多行數據並以 Map 結構返回
     *
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢結果
     */
    public Object mapListHandleQuery(String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            return queryRunner.query(conn, sql, new MapListHandler(), args);
        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    /**
     * 處理 SQL 異常的通用方法
     *
     * @param e 異常對象
     */
    private void handleException(Exception e) {
        Log.error("Datasource exception: " + e.getMessage());
        e.printStackTrace();
    }

}