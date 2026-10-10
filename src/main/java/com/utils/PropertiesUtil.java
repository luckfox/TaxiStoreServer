package com.utils;
import java.io.*;
import java.util.Properties;
import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    /**
     * 載入指定的 Properties 檔案，並回傳 Properties 物件
     *
     * @param filePath 屬性檔案的路徑
     * @return Properties 物件，由呼叫者管理
     * @throws IOException 如果讀取檔案時發生錯誤
     */

    public static Properties load(String fileName)
    {
        Properties properties = new Properties();
        try (InputStream propertiesStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName))
        {
            if (propertiesStream != null)
            {
                properties.load(propertiesStream);
            } else
            {
                 Log.error("Properties file not found: " + fileName);
            }
        } catch (IOException e)
        {
            Log.error("Failed to load properties file: " + fileName);
        }
        return properties;
    }
    /**
     * 將 Properties 存回指定的檔案
     *
     * @param properties Properties 物件
     * @param filePath   存儲的檔案路徑
     * @throws IOException 如果存檔時發生錯誤
     */
    public static void storeProperties(Properties properties, String filePath) throws IOException
    {
        try (OutputStream output = new FileOutputStream(filePath))
        {
            properties.store(output, "Updated properties");
            output.flush();
        }
    }
}
