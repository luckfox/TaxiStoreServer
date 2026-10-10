package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 通用反射工具類，支援欄位讀取、繼承欄位擷取、排除欄位等功能。
 */
public class ReflectUtils {

    /**
     * 取得類別（包含父類）所有欄位。
     *
     * @param clazz 欲取得欄位的類別
     * @return 所有非重複欄位（包含私有與繼承欄位）
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Set<String> fieldNames = new HashSet<>();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!fieldNames.contains(field.getName())) {
                    fields.add(field);
                    fieldNames.add(field.getName());
                }
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /**
     * 根據欄位名稱取得 Field（包含父類欄位）。
     *
     * @param clazz     類別
     * @param fieldName 欄位名稱
     * @return 欄位物件
     * @throws NoSuchFieldException 若找不到欄位
     */
    public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException("找不到欄位: " + fieldName);
    }

    /**
     * 取得所有欄位名稱（排除 static）。
     *
     * @param clazz 類別
     * @return 欄位名稱清單
     */
    public static List<String> getAllFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : getAllFields(clazz)) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    /**
     * 取得欄位值（支援繼承欄位）。
     */
    public static Object getFieldValue(Object bean, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = getField(bean.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(bean);
    }

    /**
     * 取得指定欄位清單的值（依照順序）。
     */
    public static List<Object> getFieldValuesByFields(Object obj, List<String> fieldNames) throws IllegalAccessException, NoSuchFieldException {
        List<Object> values = new ArrayList<>();
        for (String fieldName : fieldNames) {
            values.add(getFieldValue(obj, fieldName));
        }
        return values;
    }

    /**
     * 取得所有欄位值（排除 static/transient 與指定欄位）。
     */
    public static Object[] extractAllFieldValues(Object obj, String... excludeFields) {
        List<Object> values = new ArrayList<>();
        List<String> exclude = excludeFields != null ? Arrays.asList(excludeFields) : Collections.emptyList();

        try {
            for (Field field : getAllFields(obj.getClass())) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) continue;
                if (exclude.contains(field.getName())) continue;

                field.setAccessible(true);
                values.add(field.get(obj));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return values.toArray();
    }

    /**
     * 取得欄位值，排除單一欄位與額外欄位。
     */
    public static List<Object> getFieldValuesExclude(Object obj, String excludeFieldName, List<String> extraFields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (Field field : getAllFields(obj.getClass())) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) continue;

            String name = field.getName();
            if ((excludeFieldName != null && name.equalsIgnoreCase(excludeFieldName)) ||
                    (extraFields != null && extraFields.contains(name))) {
                continue;
            }

            field.setAccessible(true);
            values.add(field.get(obj));
        }
        return values;
    }

    /**
     * 取得欄位值，排除欄位清單。
     */
    public static List<Object> getFieldValuesExclude(Object obj, List<String> excludeFields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (Field field : getAllFields(obj.getClass())) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) continue;
            if (excludeFields != null && excludeFields.contains(field.getName())) continue;

            field.setAccessible(true);
            values.add(field.get(obj));
        }
        return values;
    }

    /**
     * 取得欄位值，僅包含指定欄位清單。
     */
    public static List<Object> getFieldValuesInclude(Object obj, List<String> includeFields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (String fieldName : includeFields) {
            try {
                Field field = getField(obj.getClass(), fieldName);
                field.setAccessible(true);
                values.add(field.get(obj));
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("找不到欄位: " + fieldName, e);
            }
        }
        return values;
    }
}
