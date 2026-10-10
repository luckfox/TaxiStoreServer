package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/*
 * BeanUtilsHelper 是一個工具類，用於處理 Java Bean 欄位操作，例如欄位值提取、排除特定欄位、與 ResultSet 映射等。
 */
public class BeanUtilsHelper {

    /*
     * 取得指定類別（包含父類別）所有非 static 欄位名稱。
     *
     * @param clazz 欲處理的類別
     * @return 欄位名稱清單
     */
    public static List<String> getAllFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (seen.add(name)) {
                        fieldNames.add(name);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return fieldNames;
    }

    /*
     * 根據指定欄位名稱清單提取對應的欄位值。
     *
     * @param obj        欲提取的物件
     * @param fieldNames 欲提取的欄位名稱
     * @return 欄位值清單
     * @throws IllegalAccessException 欄位不可存取
     * @throws NoSuchFieldException   欄位不存在
     */
    public static <T> List<Object> getSpecifiedFieldValues(T obj, List<String> fieldNames)
            throws IllegalAccessException, NoSuchFieldException {
        List<Object> values = new ArrayList<>();
        for (String fieldName : fieldNames) {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            values.add(field.get(obj));
        }
        return values;
    }

    /*
     * 從物件中取得所有欄位的值（排除 static, transient, 指定欄位）。
     *
     * @param obj           欲提取欄位的物件
     * @param excludeFields 欲排除的欄位（可為 null）
     * @return 欄位值陣列
     */
    public static Object[] extractAllFieldValues(Object obj, String... excludeFields) {
        List<Object> values = new ArrayList<>();
        Set<String> exclude = excludeFields == null ? Collections.emptySet() : new HashSet<>(Arrays.asList(excludeFields));
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) continue;
                if (exclude.contains(field.getName())) continue;
                field.setAccessible(true);
                values.add(field.get(obj));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return values.toArray();
    }

    /*
     * 根據欄位名稱取得欄位值。
     *
     * @param bean      欲提取的物件
     * @param fieldName 欄位名稱
     * @return 欄位值
     * @throws NoSuchFieldException   欄位不存在
     * @throws IllegalAccessException 欄位不可存取
     */
    public static Object getFieldValue(Object bean, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = bean.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(bean);
    }

    /*
     * 取得物件中不包含特定欄位的所有欄位值。
     *
     * @param bean          欲提取的物件
     * @param excludeFields 欲排除的欄位
     * @return 欄位值清單
     * @throws Exception 欄位存取失敗
     */
    public static List<Object> getFieldValuesExclude(Object bean, List<String> excludeFields) throws Exception {
        List<Object> values = new ArrayList<>();
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!excludeFields.contains(field.getName())) {
                values.add(field.get(bean));
            }
        }
        return values;
    }

    /*
     * 取得物件中包含指定欄位清單的值（順序相同）。
     *
     * @param bean        欲提取的物件
     * @param fieldNames 欲提取的欄位名稱
     * @return 欄位值清單
     * @throws Exception 欄位讀取錯誤
     */
    public static List<Object> getFieldValuesByFields(Object bean, List<String> fieldNames) throws Exception {
        List<Object> values = new ArrayList<>();
        for (String name : fieldNames) {
            values.add(getFieldValue(bean, name));
        }
        return values;
    }

    /*
     * 取得欄位值（排除主鍵與其他額外欄位）。
     *
     * @param bean             欲提取的物件
     * @param excludeFieldName 欲排除的主鍵欄位
     * @param extraFields      欲額外排除的欄位
     * @return 欄位值清單
     * @throws IllegalAccessException 欄位存取錯誤
     */
    public static List<Object> getFieldValuesExclude(Object bean, String excludeFieldName, List<String> extraFields)
            throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (Field field : bean.getClass().getDeclaredFields()) {
            String name = field.getName();
            if ((excludeFieldName != null && name.equalsIgnoreCase(excludeFieldName)) ||
                    (extraFields != null && extraFields.contains(name))) {
                continue;
            }
            field.setAccessible(true);
            values.add(field.get(bean));
        }
        return values;
    }

    /*
     * 根據 ResultSet 映射成指定類型物件清單（支援欄位名稱別名）。
     *
     * @param rs    資料查詢結果
     * @param clazz 欲映射的類別
     * @param <T>   型別參數
     * @return 映射結果清單
     */
    public static <T> List<T> mapResultSetToList(ResultSet rs, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i); // 支援 AS
                    Object value = rs.getObject(i);
                    try {
                        Field field = clazz.getDeclaredField(columnName);
                        field.setAccessible(true);
                        if (value != null) {
                            if (value instanceof LocalDateTime) {
                                value = Timestamp.valueOf((LocalDateTime) value);
                            } else if (value instanceof Date) {
                                value = new Timestamp(((Date) value).getTime());
                            }
                            field.set(obj, value);
                        }
                    } catch (NoSuchFieldException ignored) {
                        // 欄位不存在就跳過
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 可改為 log 系統記錄
        }
        return list;
    }
}
