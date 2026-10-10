package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.pojo.Result;
import com.utils.BeanUtilsHelper2;
import com.utils.Log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class BaseTDao
{
    private final QueryRunner queryRunner = new QueryRunner();


    /**
     * 執行 SELECT MAX(...) 查詢，並回傳 max 值（整數）。
     *
     * @param tableName     資料表名稱
     * @param targetField   欲查詢最大值的欄位
     * @param whereField    條件欄位
     * @param whereValue    條件值
     * @return 最大值（int）
     */
    public Result<Integer> getMaxValue(String sql,String tableName, String targetField, String whereField, Object whereValue) {

        int maxValue = 0;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, whereValue);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                maxValue = rs.getInt("max_value");
            }

            return Result.success(maxValue);
        } catch (SQLException e) {
            Log.error("getMaxValue SQL Error: " + e.getMessage());
            return Result.failure("getMaxValue.sqlError"+ e.getMessage());
        }
    }

    /**
     * 執行 DELETE
     *
     * @param sql  SQL 語句
     * @param args 參數列表
     * @return 受影響的行數
     */
    public Result<Integer> delete(String sql, Object... args)
    {
        try (Connection conn = DatabaseManager.getConnection())
        {
            int affectedRows = queryRunner.update(conn, sql, args);
            return new Result<>(affectedRows); // 表示有幾筆被刪除
        } catch (SQLException e)
        {
            handleException(e);
            return new Result<>("database.delete.error", e.getMessage());
        }
    }
    /**
     * 執行 INSERT
     *
     * @param sql  SQL 語句
     * @param args 參數列表
     * @return 受影響的行數
     */
    public Result<Long> insert(String sql, Object... args)
    {
        Long generatedKey;
        try (Connection conn = DatabaseManager.getConnection())
        {
            ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

            /*
                注意；mySQL回傳值為bigInteger，所以不能直接賦值給Long
             */
            Object keyObj = queryRunner.insert(conn, sql, scalarHandler, args);
            if (keyObj instanceof BigInteger) {
                generatedKey = ((BigInteger) keyObj).longValue();
            } else if (keyObj instanceof Number) {
                generatedKey = ((Number) keyObj).longValue();
            } else {
                return new Result<>("database.insert.error", "無法解析主鍵型別：" + keyObj.getClass());
            }

        } catch (SQLException e)
        {
            handleException(e);
            return new Result<Long>("database.update.error.",e.getMessage());
        }
        return new Result<>(generatedKey);
    }
    //UPDATE
    public Result<Integer> update(String sql, Object... args)
    {
        Integer row;
         try (Connection conn = DatabaseManager.getConnection())
        {
            //ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
            row = queryRunner.update(conn, sql, args);

        }catch (SQLException e)
        {
            handleException(e);
            return new Result<>("database.update.error.",e.getMessage());
         }
        return new Result<>(row);
}
    /*
     * 查詢單一 JavaBean 的方法
     *
     * @param <T>   返回類型的泛型
     * @param type  返回類型對象
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢單筆資料
     */
    public <T> Result<T> queryForOne(Class<T> type, String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            T result = queryRunner.query(conn, sql, new BeanHandler<>(type), args);
            return new Result<>(result);
        } catch (SQLException e) {
            return new Result<>("database.query.error", e.getMessage());
        }
    }
    protected <T> Result<T> queryForObject(Class<T> type,String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            T data = new QueryRunner().query(conn, sql, new BeanHandler<>(type), args);
            return data != null ? Result.success(data) : Result.failure("查無資料");
        } catch (SQLException e) {
            Log.error("queryForObject 失敗: " + e.getMessage());
            return Result.failure("查詢失敗: " + e.getMessage());
        }
    }
    /*
     * 查詢多條記錄返回列表的方法
     *
     * @param <T>   返回類型的泛型
     * @param type  返回類型對象
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢多筆資料
     */
    public <T> Result<List<T>> queryForList(Class<T> type, String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            List<T> result = queryRunner.query(conn, sql, new BeanListHandler<>(type), args);
            return new Result<>(result);
        } catch (SQLException e) {
            return new Result<>("database.query.list.error",e.getMessage());
        }
    }


//    protected <T2> Result<List<T2>> queryForList(String sql, Class<T2> type, Object... args) {
//        try (Connection conn = DatabaseManager.getConnection()) {
//            List<T2> list = new QueryRunner().query(conn, sql, new BeanListHandler<>(type), args);
//            return Result.success(list);
//        } catch (SQLException e) {
//            Log.error("queryForList 失敗: " + e.getMessage());
//            return Result.failure("查詢失敗: " + e.getMessage());
//        }
//    }

    protected <T> Result<List<T>> queryForScalarList(Class<T> scalarType,String sql,  Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            List<T> list = new QueryRunner().query(conn, sql, new ColumnListHandler<>(), args);
            return Result.success(list);
        } catch (SQLException e) {
            Log.error("queryForScalarList 失敗: " + e.getMessage());
            return Result.failure("查詢失敗: " + e.getMessage());
        }
    }
    /**
     * 通用的 SQL 查詢工具，回傳物件清單。
     *
     * @param sql    SQL 查詢語句
     * @param clazz  欲映射的類別
     * @param params 查詢參數
     * @return 結果封裝類，包含查詢成功與否與資料
     */
    protected<T> Result<List<T>> query(String sql, Class<T> clazz, Object... params) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = pstmt.executeQuery();
            List<T> list = BeanUtilsHelper2.mapResultSetToList(rs, clazz);
            return new Result<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("BasicDao.query.error", "");
        }
    }
    /**
     * 執行返回單行單列的查詢
     *
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢結果
     */
    public Result<Object> queryForSingleValue(String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            Object result = queryRunner.query(conn, sql, new ScalarHandler<>(), args);
            return new Result<>(result);
        } catch (SQLException e) {
            return new Result<>("database.query.scalar.error",e.getMessage());
        }
    }

    /**
     * 查詢多行數據並以 Map 結構返回
     *
     * @param sql   SQL 語句
     * @param args  SQL 參數
     * @return 查詢結果
     */
    public Result<List<Map<String, Object>>> mapListHandleQuery(String sql, Object... args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            List<Map<String, Object>> result = queryRunner.query(conn, sql, new MapListHandler(), args);
            return new Result<>(result);
        } catch (SQLException e) {
            return new Result<>("database.query.maplist.error",e.getMessage());
        }
    }


    /**
     * 處理 SQL 異常的通用方法
     *
     * @param e 異常對象
     */
    private void handleException(Exception e) {
        Log.error("Datasource exception: " + e.getMessage());
    }
}
