package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.pojo.Result;
import com.utils.BeanUtilsHelper2;
import com.utils.Log;
import com.utils.ReflectUtils;
import com.utils.SqlHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.utils.ReflectUtils.*;

/*
 * 抽象 DAO 類別，提供通用的表單資料庫操作邏輯。
 *
 * @param <T> Entity 類型
 */
public abstract class AbstractFormDao<T> extends BasicTFormOperatorDao<T> {

    protected final Class<T> clazz;
    protected final String KEY_NAME;
    protected final String TABLE_NAME;
    protected final Map<String, Supplier<Object>> extraFields;


    /*
     * 建構子 - 建立 DAO 實例，包含額外欄位處理。
     *
     * @param clazz        欲操作的實體類別
     * @param TABLE_NAME   資料庫表格名稱
     * @param KEY_NAME     主鍵欄位名稱
     * @param extraFields  額外欄位，排除的欄位 (通常為自動補值，例如 createDate)
     */
    public AbstractFormDao(Class<T> clazz, String TABLE_NAME, String KEY_NAME,Map<String, Supplier<Object>> extraFields) {
        super(clazz, TABLE_NAME);
        this.clazz = clazz;
        this.KEY_NAME = KEY_NAME;
        this.TABLE_NAME = TABLE_NAME;
        this.extraFields = (extraFields != null) ? extraFields : Collections.emptyMap();
       // this.insertSQL = SqlHelper.buildInsertSQL(clazz, TABLE_NAME, new ArrayList<>(this.extraFields.keySet()), KEY_NAME);
    }
    /**
     * 不提供額外欄位的建構子（預設 extraFields 為空）
     */
    public AbstractFormDao(Class<T> clazz, String TABLE_NAME, String KEY_NAME) {
        this(clazz, TABLE_NAME, KEY_NAME, null);
    }


    /*
     * 根據指定欄位條件，統計符合條件的資料筆數。
     *
     * @param entity      查詢條件所對應的資料物件
     * @param columnNames 欲作為 WHERE 條件的欄位名稱清單
     * @return 封裝筆數結果的 Result 物件
     */
    public Result<Integer> getCountByColumns(T entity, List<String> columnNames) {
        // 檢查必要參數
        if (entity == null || columnNames == null || columnNames.isEmpty()) {
            return Result.failure("Entity 物件與欄位名稱清單不可為空");
        }

        try {
            // 1. 產生 WHERE 子句
            String whereClause = SqlHelper.buildWhereClauseByColumns(columnNames);

            // 2. 產生完整 SQL
            String sql = SqlHelper.buildCountSQL(TABLE_NAME, "", whereClause);

            // 3. 從 entity 中取出對應欄位的值
            List<Object> params = BeanUtilsHelper2.getFieldValuesInclude(entity, columnNames);

            // 4. 執行 SQL
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // 設定參數
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Result.success(rs.getInt(1));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("countByColumns.error: " + e.getMessage());
        }

        return Result.failure("No data found");
    }

    /*
     * 根據指定欄位 LIKE 條件，查詢符合條件的資料筆數。
     * 可選擇加入 JOIN 子句（不需指定 table alias）。
     *
     * @param tableName         要查詢的主資料表名稱
     * @param searchColumn      欲查詢的欄位名稱
     * @param searchValue       查詢關鍵字（會以 LIKE %value% 比對）
     * @param joinClauseSupplier  JOIN 子句的 callback，可為 null（無 JOIN）
     * @return 封裝筆數結果的 Result 物件
     */
    public Result<Integer> getCountByLikeColumn(
            String tableName,
            String searchColumn,
            String searchValue,
            Supplier<String> joinClauseSupplier
    ) {
        if (searchColumn == null || searchValue == null || searchValue.trim().isEmpty()) {
            return Result.failure("searchColumn or searchValue cannot be empty");
        }

        String joinClause = joinClauseSupplier != null ? joinClauseSupplier.get() : "";
        String whereClause = String.format(" WHERE %s LIKE ?", searchColumn);

        String sql = SqlHelper.buildCountSQL(tableName, joinClause, whereClause);

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchValue + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Result.success(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            return Result.failure("Database error: " + e.getMessage());
        }

        return Result.failure("No data found");
    }



    /**
     * 根據指定欄位條件，刪除符合條件的資料。
     *
     * @param entity        欲刪除的資料物件，用於取得條件欄位值
     * @param includeFields 欲作為條件的欄位名稱清單（組成 WHERE 子句）
     * @return 刪除筆數的結果封裝
     */
    public Result<Integer> deleteEntitiesByFields(T entity, List<String> includeFields) {
        try {
            // 1. 產生 DELETE SQL
            String deleteSQL = SqlHelper.buildDeleteSQL(TABLE_NAME, includeFields);

            // 2. 直接透過工具方法取得條件欄位值
            List<Object> params = ReflectUtils.getFieldValuesByFields(entity, includeFields);

            // 3. 執行刪除
            return delete(deleteSQL, params.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("BeanUtils.getFieldValuesInclude.error", "");
        }
    }


    /*
     * 根據指定欄位的值，查詢多筆資料。
     *
     * @param field 欄位名稱
     * @param value 欲查詢的值
     * @return 符合條件的結果清單
     */
    public Result<List<T>> getEntitiesByFieldLike(String field, Object value) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + field + " LIKE CONCAT(?, '%')";
        try {
            return query(sql, clazz, value);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("Dao.query.error", "");
        }
    }

    /**
     * 根據實體對象的某些欄位生成 SELECT 查詢語句，並執行查詢操作，返回多筆結果。
     *
     * @param entity        查詢條件來源的實體對象
     * @param includeFields 欲作為查詢條件的欄位清單
     * @return 查詢結果，Result<List<T>> 包含符合條件的資料
     */
    public Result<List<T>> getEntitiesByFields(T entity, List<String> includeFields) {
        String sql = SqlHelper.buildSelectByColumns(TABLE_NAME, includeFields);

        try {
            // 使用 ReflectUtils 取得欄位值
            List<Object> params = ReflectUtils.getFieldValuesByFields(entity, includeFields);
            return query(sql, clazz, params.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("getEntitiesByFields.error: " + e.getMessage());
        }
    }

    /**
     * 根據實體對象的某些欄位生成 SELECT 查詢語句，並執行查詢操作，返回單一結果。
     *
     * @param entity        查詢條件來源的實體對象
     * @param includeFields 欲作為查詢條件的欄位清單
     * @return 查詢結果，Result<T> 包含符合條件的單一資料
     */
    public Result<T> getEntityByFields(T entity, List<String> includeFields) {
        String sql = SqlHelper.buildSelectByColumns(TABLE_NAME, includeFields);

        try {
            // 使用 ReflectUtils 取得欄位值
            List<Object> params = ReflectUtils.getFieldValuesByFields(entity, includeFields);
            return queryForOne(clazz, sql, params.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("getEntityByFields.error: " + e.getMessage());
        }
    }

    /*
     * 根據指定欄位的值查詢多筆資料。
     *
     * @param field 欄位名稱
     * @param value 欲查詢的值
     * @return 符合條件的結果清單
     */
    public Result<List<T>> getEntitiesByField(String field, Object value) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + field + " = ?";
        try {
            return query(sql, clazz, value);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("Dao.query.error", "");
        }
    }
    /*
     * 根據指定欄位的值查詢多筆資料。
     *
     * @param field 欄位名稱
     * @param value 欲查詢的值
     * @param OrderColumn 要排序的欄位名稱
     * @param OrderDir 要排序的方向(true=ASC,false=DESC)
     * @return 符合條件的結果清單
     */
    public Result<List<T>> getEntitiesByField(String field, Object value,String OrderColumn,boolean OrderDir) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + field + " = ? ORDER BY "+OrderColumn +(OrderDir?" ASC":" DESC");

        try {
            return query(sql, clazz, value);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("Dao.query.error", "");
        }
    }

    /**
     * 只更新指定欄位（由 includeFields 指定）。
     *
     * @param entity        欲更新的實體物件
     * @param includeFields 欲更新的欄位名稱清單
     * @param ColumnName    WHERE 條件用欄位名稱
     * @return 更新筆數（1 成功 / 0 失敗）
     */
    public Result<Integer> updateEntityOnlyFieldsByColumn(T entity, List<String> includeFields, String ColumnName) {
        // 1. 產生相對應的 SQL 語句
        String updateSQL = SqlHelper.buildUpdateSQLWithOnlyFields(clazz, TABLE_NAME, ColumnName, includeFields);

        try {
            // 2. 取得更新欄位的值（排除 ColumnName，因為它是 WHERE 條件）
            List<String> fieldsForUpdate = new ArrayList<>();
            for (String field : includeFields) {
                if (!field.equals(ColumnName)) {
                    fieldsForUpdate.add(field);
                }
            }

            List<Object> params = new ArrayList<>(ReflectUtils.getFieldValuesByFields(entity, fieldsForUpdate));

            // 3. 最後加上條件欄位值
            params.add(ReflectUtils.getFieldValue(entity, ColumnName));

            // 4. 執行更新
            return update(updateSQL, params.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("BeanUtils.getFieldValues.error", "");
        }
    }

    /*
     * 僅更新指定欄位的資料。從includeFields清單中，選取entity中需要更新的成員。
     *
     * @param entity        欲更新的實體物件
     * @param includeFields 欲更新的欄位名稱清單
     * @return 更新筆數（1 成功 / 0 失敗）
     */
    public Result<Integer> updateEntityOnlyFields(T entity, List<String> includeFields) {
        return updateEntityOnlyFieldsByColumn(entity,  includeFields,KEY_NAME);
    }
    public Result<Integer> getMaxValueByField(T entity, String targetField, String whereField)
    {
        String sql = SqlHelper.buildSelectMaxSQL(TABLE_NAME, targetField, whereField);
        try {
            Object whereValue = getFieldValue(entity, whereField);
            return getMaxValue(sql,TABLE_NAME, targetField, whereField, whereValue);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("getMaxValueByField.reflectionError"+ e.getMessage());
        }
    }
    /**
     * 更新實體資料，排除指定欄位。
     *
     * @param entity        欲更新的實體物件
     * @param excludeFields 不欲更新的欄位名稱清單
     * @return 更新筆數（1 成功 / 0 失敗）
     */
    public Result<Integer> updateEntityExcludingFields(T entity, List<String> excludeFields) {
        String updateSQL = SqlHelper.buildUpdateSQLExcludingFields(clazz, TABLE_NAME, KEY_NAME, excludeFields);
        try {
            // 1. 取得更新欄位的值（排除 excludeFields）
            List<Object> params = new ArrayList<>(ReflectUtils.getFieldValuesExclude(entity, excludeFields));

            // 2. 取得主鍵值，作為 WHERE 條件
            params.add(ReflectUtils.getFieldValue(entity, KEY_NAME));

            // 3. 執行更新
            return update(updateSQL, params.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("BeanUtils.getFieldValues.error", "");
        }
    }



    /*
     * 檢查指定資料表中，某一欄位是否存在指定的值。
     *
     * @param column 欲檢查的欄位名稱
     * @param value 欲比對的值
     * @return 如果存在對應的資料，回傳 Result.success(true)，否則回傳 Result.success(false)。若發生錯誤則回傳 Result.failure
     */
    public Result<Boolean> existsByColumnValue(String column, Object value) {
        if (column == null || value == null) {
            return Result.failure("欄位名稱與查詢值不可為 null");
        }

        String sql = SqlHelper.buildExistsSQL(TABLE_NAME, column);

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, value);

            try (ResultSet rs = stmt.executeQuery()) {
                // 如果有任何資料回傳，代表該值存在
                return Result.success(rs.next());
            }

        } catch (SQLException e) {
            return Result.failure("資料庫查詢錯誤：" + e.getMessage());
        }
    }
    /**
     * 根據傳入的實體資料中非 null 欄位進行更新。
     * 會忽略主鍵欄位與 extraFields 內定義的欄位。
     *
     * @param entity 欲更新的實體物件
     * @return 更新筆數（1 成功 / 0 失敗）
     */
    public Result<Integer> updateEntityByNonNullFields(T entity) {
        try {
            // 1. 找出要更新的欄位 (非 null，排除 KEY_NAME 與 extraFields)
            List<String> updateFields = new ArrayList<>();
            Object keyValue = null;

            for (Field field : ReflectUtils.getAllFields(clazz)) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(entity);

                if (fieldName.equals(KEY_NAME)) {
                    keyValue = value; // 主鍵留著
                    continue;
                }

                // 排除 extraFields
                if (extraFields.containsKey(fieldName)) {
                    continue;
                }

                // 只有非 null 才更新
                if (value != null) {
                    updateFields.add(fieldName);
                }
            }

            if (keyValue == null) {
                return Result.failure("主鍵值不可為 null，無法更新");
            }

            if (updateFields.isEmpty()) {
                return Result.success(0);
            }

            // 2. 建立 SQL
            String updateSQL = SqlHelper.buildUpdateSQLWithOnlyFields(clazz, TABLE_NAME, KEY_NAME, updateFields);

            // 3. 組參數
            List<Object> params = new ArrayList<>();
            for (String fieldName : updateFields) {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                params.add(field.get(entity));
            }
            params.add(keyValue);

            return update(updateSQL, params.toArray());

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("updateEntityByNonNullFields.error: " + e.getMessage());
        }
    }

    /*
     * 更新整筆資料（不排除欄位）。
     *
     * @param entity 欲更新的實體資料
     * @return 更新筆數（1 成功 / 0 失敗）
     */
    public  Result<Integer> updateEntity(T entity)
    {       // 1. 產生SQL語法
        String updateSQL = SqlHelper.buildUpdateSQL(clazz, TABLE_NAME, KEY_NAME);
        try {
            // 2. 取得entity所有成員變數所攜帶的值(排除主鍵項目)
            List<Object> params = new ArrayList<>(getFieldValuesExclude(entity, List.of(KEY_NAME)));
            // 3. 再取得 key 值作為 WHERE 條件參數
            Object keyValue = ReflectUtils.getFieldValue(entity, KEY_NAME);
            params.add(keyValue);

            return update(updateSQL, params.toArray());
        }catch (Exception e) {
            e.printStackTrace();
            return new Result<>("BeanUtility.getDeclaredField.error", "");
        }

    }
    /**
     * 新增一筆資料到資料庫。
     *
     * - 會自動處理 extraFields (如 createdate)
     * - 會忽略 KEY_NAME (自動遞增欄位)
     *
     * @param entity 欲新增的實體物件
     * @return 新增後的主鍵值
     */
    public Result<Long> insertEntity(T entity) {
        try {
            // 1. 欄位清單處理
            List<String> extraFieldNames = new ArrayList<>(extraFields.keySet());
            List<String> entityFieldNames = ReflectUtils.getAllFieldNames(clazz);

            // 排除 extraFields 與 KEY_NAME
            entityFieldNames.removeAll(extraFieldNames);
            entityFieldNames.remove(KEY_NAME);

            // 2. 組 SQL
            String insertSQL = SqlHelper.buildInsertSQL(clazz, TABLE_NAME, extraFieldNames, entityFieldNames, KEY_NAME);

            // 3. 組參數
            List<Object> params = new ArrayList<>();

            // 3.1 extraFields 的值
            for (String key : extraFieldNames) {
                params.add(getExtraFieldValue(key));
            }

            // 3.2 entity 欄位的值
            params.addAll(ReflectUtils.getFieldValuesByFields(entity, entityFieldNames));

            // 4. 執行 insert
            return insert(insertSQL, params.toArray());

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("insertEntity.error: " + e.getMessage());
        }
    }

    public Result<List<T>> getEntities()
    {
        return getItems();
    }
    public Result<T> getEntity(Long serialNo)
    {
        return getItem(KEY_NAME,serialNo);
    }
    public Result<T>
    getFirstEntity()
    {
        return getFirst(KEY_NAME);
    }

    public Result<T> getLastEntity()
    {
        return getLast(KEY_NAME);
    }

    public Result<T> getNextEntity(long serialNo)
    {
        return getNext(KEY_NAME, serialNo);
    }

    public Result<T> getPreviousEntity(long serialNo)
    {
        return getPrevious(KEY_NAME, serialNo);
    }

    public Result<Integer> deleteEntity(long serialNo)
    {
        return deleteByIndex(KEY_NAME, serialNo);
    }
    /*
     * 提供子類別定義額外欄位的值（例如 createdate、modifiedate 等）
     */
    protected Object getExtraFieldValue(String fieldName) {
        // 預設傳回 null，由子類別覆寫
        return null;
    }
}
