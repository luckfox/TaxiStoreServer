package com.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

public class ResultSetMapper<T> {

    /**
     * 使用 BeanUtils 將 ResultSet 轉換為 List<T>。
     *
     * @param rs    SQL 查詢結果集
     * @param clazz 目標 JavaBean 類型的 Class
     * @return List<T> 轉換後的 JavaBean 列表
     */
    public List<T> mapResultSetToList(ResultSet rs, Class<T> clazz) {
        List<T> resultList = new ArrayList<>();
        try {
            // 取得結果集的元資料
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 遍歷結果集的每一行資料
            while (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance(); // 創建 JavaBean 物件

                // 遍歷每一列資料並將其設置到 JavaBean 屬性中
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);  // 取得欄位名稱
                    Object value = rs.getObject(i);  // 取得欄位值

                    // 🛠 **修正重點**：手動處理 LocalDateTime -> Timestamp
                    if (value instanceof LocalDateTime) {
                        value = Timestamp.valueOf((LocalDateTime) value);
                    }
                    // 🛠 其他日期型別處理（防止 NULL 造成錯誤）
                    else if (value instanceof Date) {
                        value = new Timestamp(((Date) value).getTime());
                    }

                    // 設置屬性
                    if (value != null) {
                        BeanUtils.setProperty(obj, columnName, value);
                    }
                }
                resultList.add(obj); // 添加到結果列表中
            }
        } catch (SQLException e) {
            System.err.println("SQL 錯誤: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Bean 映射錯誤: " + e.getMessage());
            e.printStackTrace();
        }
        return resultList;
    }
}
