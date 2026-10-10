package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static com.utils.ReflectUtils.getAllFields;

/**
 * SQL 組裝工具類，提供靜態方法協助產生常見 SQL 語句（SELECT、INSERT、UPDATE、DELETE 等）。
 * <p>
 * 支援基於欄位清單、類別結構等資訊快速生成 SQL 字串，降低重複程式碼。
 */
public class SqlHelper {

    /*
     * 組合 WHERE 子句（格式為 col1 = ? AND col2 = ? ...）。
     *
     * @param columnNames 欲組成條件的欄位名稱清單
     * @return WHERE 子句字串，若為空則回傳空字串
     */
    public static String buildWhereClauseByColumns(List<String> columnNames) {
        if (columnNames == null || columnNames.isEmpty()) return "";
        return " WHERE " + columnNames.stream()
                .map(col -> col + " = ?")
                .collect(Collectors.joining(" AND "));
    }

    /*
     * 建立 SELECT * 查詢語句（含欄位條件）。
     *
     * @param tableName   表格名稱
     * @param columnNames 欲比對的欄位名稱列表
     * @return SELECT SQL 語句
     */
    public static String buildSelectByColumns(String tableName, List<String> columnNames) {
        return "SELECT * FROM " + tableName + buildWhereClauseByColumns(columnNames);
    }

    /**
     * 建立 SELECT COUNT(*) 查詢語句（含欄位條件）。
     *
     * @param tableName   表格名稱
     * @param columnNames 欲比對的欄位名稱列表
     * @return COUNT SQL 語句
     */
    public static String buildCountSQLByColumns(String tableName, List<String> columnNames) {
        return "SELECT COUNT(*) FROM " + tableName + buildWhereClauseByColumns(columnNames);
    }

    /*
     * 建立檢查欄位是否存在值的 SQL（SELECT 1 FROM ... WHERE ... LIMIT 1）。
     *
     * @param tableName 資料表名稱
     * @param column    欄位名稱
     * @return SQL 語句
     */
    public static String buildExistsSQL(String tableName, String column) {
        return String.format("SELECT 1 FROM %s WHERE %s = ? LIMIT 1", tableName, column);
    }

    /*
     * 建立 SELECT MAX(...) 語句。
     *
     * @param tableName   表格名稱
     * @param targetField 欲取最大值的欄位
     * @param whereField  WHERE 條件欄位
     * @return SELECT MAX SQL 語句
     */
    public static String buildSelectMaxSQL(String tableName, String targetField, String whereField) {
        return String.format("SELECT MAX(%s) AS max_value FROM %s WHERE %s = ?", targetField, tableName, whereField);
    }

    /*
     * 建立 UPDATE 語句（排除某些欄位）。
     *
     * @param clazz         類別（欄位來源）
     * @param tableName     表格名稱
     * @param keyField      主鍵欄位
     * @param excludeFields 欲排除的欄位
     * @return UPDATE SQL 語句
     */
    public static <T> String buildUpdateSQLExcludingFields(Class<T> clazz, String tableName, String keyField, List<String> excludeFields) {
        return buildUpdateSQLInternal(clazz, tableName, keyField,
                f -> !excludeFields.contains(f.getName()) && !f.getName().equals(keyField));
    }

    /**
     * 建立 UPDATE 語句（僅包含指定欄位）。
     *
     * @param clazz         類別（欄位來源）
     * @param tableName     表格名稱
     * @param keyField      主鍵欄位
     * @param includeFields 欲更新的欄位清單
     * @return UPDATE SQL 語句
     */
    public static <T> String buildUpdateSQLWithOnlyFields(Class<T> clazz, String tableName, String keyField, List<String> includeFields) {
        return buildUpdateSQLInternal(clazz, tableName, keyField,
                f -> includeFields.contains(f.getName()) && !f.getName().equals(keyField));
    }

    // 抽取共用 UPDATE SQL 組裝邏輯
    private static <T> String buildUpdateSQLInternal(Class<T> clazz, String tableName, String keyField, java.util.function.Predicate<Field> predicate) {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        List<String> sets = new ArrayList<>();

        for (Field field : getAllFields(clazz)) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            if (predicate.test(field)) {
                sets.add(field.getName() + " = ?");
            }
        }

        sql.append(String.join(", ", sets)).append(" WHERE ").append(keyField).append(" = ?");
        return sql.toString();
    }

    /*
     * 建立 INSERT 語句（依照傳入欄位 + 額外欄位）。
     *
     * @param clazz           類別
     * @param tableName       表格名稱
     * @param extraFieldNames 額外欄位（如 create_time）
     * @param fieldNames      欲插入欄位
     * @param keyName         主鍵欄位（排除用）
     * @return INSERT SQL 語句
     */
    public static String buildInsertSQL(Class<?> clazz, String tableName, List<String> extraFieldNames, List<String> fieldNames, String keyName) {
        List<String> allColumns = new ArrayList<>(extraFieldNames);
        allColumns.addAll(fieldNames);

        String columnsStr = String.join(", ", allColumns);
        String placeholders = allColumns.stream().map(c -> "?").collect(Collectors.joining(", "));
        return String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columnsStr, placeholders);
    }

    /*
     * 建立簡易 UPDATE 語句（不排除欄位）。
     *
     * @param clazz     類別
     * @param tableName 表格名稱
     * @param keyField  主鍵欄位
     * @return UPDATE SQL 語句
     */
    public static String buildUpdateSQL(Class<?> clazz, String tableName, String keyField) {
        return buildUpdateSQLInternal(clazz, tableName, keyField,
                f -> !f.getName().equalsIgnoreCase(keyField));
    }

    /*
     * 建立 DELETE SQL 語句（支援多欄位條件）。
     *
     * @param tableName      表格名稱
     * @param conditionFields 欲比對的欄位清單
     * @return DELETE SQL 語句
     */
    public static String buildDeleteSQL(String tableName, List<String> conditionFields) {
        if (conditionFields == null || conditionFields.isEmpty()) {
            throw new IllegalArgumentException("條件欄位不可為空");
        }
        String whereClause = conditionFields.stream()
                .map(col -> col + " = ?")
                .collect(Collectors.joining(" AND "));
        return "DELETE FROM " + tableName + " WHERE " + whereClause;
    }

    /*
     * 建立 COUNT 查詢語句（支援 JOIN、WHERE）。
     *
     * @param tableName   主資料表
     * @param joinClause  JOIN 子句（可為 null）
     * @param whereClause WHERE 子句（可為 null）
     * @return COUNT SQL 語句
     */
    public static String buildCountSQL(String tableName, String joinClause, String whereClause) {
        return String.format("SELECT COUNT(*) FROM %s %s %s",
                tableName,
                Optional.ofNullable(joinClause).orElse(""),
                Optional.ofNullable(whereClause).orElse("")).trim();
    }

    /*
     * 建立 LIKE 搜尋用的 COUNT 語句。
     *
     * @param tableName  表格名稱
     * @param joinClause JOIN 子句
     * @param likeColumn 欲搜尋的欄位（含 alias，如 l.displayName）
     * @return COUNT SQL 語句
     */
    public static String buildLikeCountSQL(String tableName, String joinClause, String likeColumn) {
        String base = "SELECT COUNT(*) FROM " + tableName + " ";
        if (joinClause != null && !joinClause.isEmpty()) base += joinClause + " ";
        return base + "WHERE " + likeColumn + " LIKE ?";
    }

    /*
     * 自動產生 INSERT 語句（排除 static/transient 欄位與指定欄位）。
     *
     * @param clazz         類別
     * @param tableName     表格名稱
     * @param extraFields   額外欄位（如 create_time）
     * @param excludeFields 欲排除欄位
     * @return INSERT SQL 語句
     */
    public static String generateInsertSQL(Class<?> clazz, String tableName, String[] extraFields, String[] excludeFields) {
        List<String> columns = new ArrayList<>();
        List<String> placeholders = new ArrayList<>();

        if (extraFields != null) {
            for (String field : extraFields) {
                columns.add(field);
                placeholders.add("?");
            }
        }

        for (Field field : getAllFields(clazz)) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) continue;
            if (excludeFields != null && Arrays.asList(excludeFields).contains(field.getName())) continue;

            columns.add(field.getName());
            placeholders.add("?");
        }

        return String.format("INSERT INTO %s(%s) VALUES (%s);",
                tableName,
                String.join(", ", columns),
                String.join(", ", placeholders));
    }
    /*
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }*/
    public static Builder select(String... columns)
    {
        return new Builder().select(columns);
    }

    public static class Builder
    {
        private final List<String> selectColumns = new ArrayList<>();
        private String fromTable;
        private final List<String> whereConditions = new ArrayList<>();

        /*
         * 指定 SELECT 欄位
         */
        public Builder select(String... columns) {
            selectColumns.addAll(Arrays.asList(columns));
            return this;
        }

        /*
         * 指定 FROM 表格
         */
        public Builder from(String table) {
            this.fromTable = table;
            return this;
        }

        /*
         * 新增 WHERE 相等條件（格式：column = ?）
         */
        public Builder whereEq(String column) {
            whereConditions.add(column + " = ?");
            return this;
        }

        /*
         * 組裝成完整 SQL 語句
         */
        public String build() {
            if (selectColumns.isEmpty()) throw new IllegalStateException("No columns selected.");
            if (fromTable == null) throw new IllegalStateException("No table specified.");

            StringBuilder sb = new StringBuilder("SELECT ");
            sb.append(String.join(", ", selectColumns));
            sb.append(" FROM ").append(fromTable);

            if (!whereConditions.isEmpty()) {
                sb.append(" WHERE ").append(String.join(" AND ", whereConditions));
            }

            return sb.toString();
        }
    }
}
