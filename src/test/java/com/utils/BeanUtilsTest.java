package com.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BeanUtilsTest {

    public static class TestBean {
        private Timestamp timestamp;

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        try {
            // 測試資料
            TestBean bean = new TestBean();
            LocalDateTime localDateTime = LocalDateTime.of(2008, 10, 27, 0, 0);

            // **嘗試直接用 BeanUtils 設定 LocalDateTime**
            System.out.println("【Before】timestamp = " + bean.getTimestamp());
            try {
                BeanUtils.setProperty(bean, "timestamp", localDateTime);
            } catch (Exception e) {
                System.out.println("❌ BeanUtils.setProperty() 失敗: " + e.getMessage());
            }

            // **正確做法: 轉換成 Timestamp**
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            BeanUtils.setProperty(bean, "timestamp", timestamp);

            // 檢查結果
            System.out.println("【After】timestamp = " + bean.getTimestamp());

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
