package com.utils.test;

import com.utils.PropertiesUtil;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesUtilTest {
    public static void main(String[] args) {
        try {
            String configFile1 = "application.properties";
            String configFile2 = "lineUser.properties";

            // 1. 載入多個 Properties 檔案，開發者自行管理
            Properties props1 = PropertiesUtil.load(configFile1);
            Properties props2 = PropertiesUtil.load(configFile2);

            // 2. 取得屬性值
            System.out.println("Config1 - channel.access.token: " + props1.getProperty("channel.access.token"));
            System.out.println("Config2 - Uc5f2f40eebcbff5ad54e21a6f411a97e: " + props2.getProperty("Uc5f2f40eebcbff5ad54e21a6f411a97e"));

            // 3. 設定新屬性
            props1.setProperty("channel.access.token", "2.0.1");
            props2.setProperty("Uc5f2f40eebcbff5ad54e21a6f411a97e", "true");

            // 4. 存回檔案
            PropertiesUtil.storeProperties(props1, configFile1);
            PropertiesUtil.storeProperties(props2, configFile2);

            System.out.println("屬性更新完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
