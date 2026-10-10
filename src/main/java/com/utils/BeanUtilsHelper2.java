package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.utils.ReflectUtils.*;

/*
 * 協助類別：從物件中取得欄位值，排除主鍵與額外欄位（如 createdate）
 */
public class BeanUtilsHelper2
{


    /*
     * 取得實體中特定欄位的值，依照欄位名稱清單順序。
     *
     * @param obj        實體物件
     * @param fieldNames 欲取值的欄位名稱
     * @return 欄位值清單
     * @throws IllegalAccessException 欄位存取錯誤
     * @throws NoSuchFieldException   欄位不存在
     */
    public static <T> List<Object> getSpecifiedFieldValues(T obj, List<String> fieldNames) throws IllegalAccessException, NoSuchFieldException {
        List<Object> values = new ArrayList<>();
        for (String fieldName : fieldNames) {
            Field field = getField(obj.getClass(), fieldName); // ✅ 改這裡
            field.setAccessible(true);
            values.add(field.get(obj));
        }
        return values;
    }

    /*
     * 根據指定欄位名稱清單取得欄位值。
     *
     * @param bean        欲提取欄位的物件
     * @param includeFields 欲提取的欄位名稱清單
     * @return 欄位值清單
     */
    public static List<Object> getFieldValuesInclude(Object bean, List<String> includeFields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        Class<?> clazz = bean.getClass();
        for (String fieldName : includeFields) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                values.add(field.get(bean));
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("找不到欄位: " + fieldName, e);
            }
        }
        return values;
    }
    /*
     * 將 ResultSet 資料自動映射成 Java 物件列表。
     *
     * @param rs    資料庫查詢結果集
     * @param clazz 欲映射的目標類別
     * @param <T>   類型參數
     * @return 映射後的物件清單
     */
    public static <T> List<T> mapResultSetToList(ResultSet rs, Class<T> clazz) {
        List<T> list = new ArrayList<>();

        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i); // 使用 Label，支援 AS
                    Object value = rs.getObject(i);

                    try {
                        Field field = clazz.getDeclaredField(columnName);
                        field.setAccessible(true);
                        if (value != null) {

                            // 🛠 **修正重點**：手動處理 LocalDateTime -> Timestamp
                            if (value instanceof LocalDateTime) {
                                value = Timestamp.valueOf((LocalDateTime) value);
                            }
                            // 🛠 其他日期型別處理（防止 NULL 造成錯誤）
                            else if (value instanceof Date) {
                                value = new Timestamp(((Date) value).getTime());
                            }
                            field.set(obj, value);
                        }
                    } catch (NoSuchFieldException ignored) {
                        // 如果找不到對應欄位，可忽略（例如 DB 有 extra 欄位但 Java 類別無）
                    }
                }

                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
