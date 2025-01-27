package com.utils;

import javax.servlet.ServletContext;
import java.util.*;

public class Language2Utils {
    private static final String CONFIG_FILE = "system";
    private static final String LANGUAGE_BUNDLE_DIR = "messages";

    // 默認的語言環境
    private static Locale currentLocale = Locale.getDefault();
    /**
     * 讀取 system.properties 文件中的語言設置，並加載對應的語言資源。
     *
     * @return 包含鍵值對的 ResourceBundle
     */
    public static ResourceBundle getLanguageBundle() {
        // 加載 system.properties 配置文件
        ResourceBundle systemBundle = ResourceBundle.getBundle(CONFIG_FILE);

        // 從配置中獲取語言代碼，默認為 "en"
        String language = systemBundle.getString("language");
        if (language == null || language.isEmpty()) {
            language = "en";
        }

        // 加載對應語言的資源文件
        Locale locale = new Locale(language);
        return ResourceBundle.getBundle(LANGUAGE_BUNDLE_DIR , locale);
    }

    /**
     * 根據 key 獲取翻譯。
     *
     * @param key 翻譯的鍵
     * @return 翻譯內容，找不到則返回 "unknown message"
     */
    public static String getTranslation(String key) {
        try {
            ResourceBundle bundle = getLanguageBundle();
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return "unknown message";
        }
    }
    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }
    public static void setLocale(String language) {
        currentLocale = Locale.forLanguageTag(language);
        if(currentLocale==null)
        {
            currentLocale=Locale.getDefault();
        }
    }
    public static String getTranslation(String key, Object... args)
    {
        try
        {
            ResourceBundle bundle = getLanguageBundle();
            String pattern = bundle.getString(key);
            return String.format(pattern, args);
        } catch (Exception e)
        {
            // 其他异常的处理
            return "Error processing key: " + key + ", " + e.getMessage();
        }

    }
    /**
     * 根據語言代碼取得對應的語言文件。
     *
     * @param language 語言代碼，例如 "en" 或 "zh"
     * @param context  ServletContext，用於獲取語言資源目錄路徑
     * @return 包含鍵值對的 Map
     */
    public static Map<String, String> loadLanguageProperties(String language, ServletContext context) {

        try {
            // 如果語言代碼為空，使用默認語言
            if (language == null || language.isEmpty()) {
                language = "default";
            }
            Locale locale= new Locale(language);
            // 創建語言的 Locale 並加載資源
             if(locale==null) locale=Locale.getDefault();

            ResourceBundle bundle = ResourceBundle.getBundle(LANGUAGE_BUNDLE_DIR , locale);

            // 將 ResourceBundle 轉換為 Map
            Map<String, String> messages = new HashMap<>();
            for (String key : bundle.keySet()) {
                messages.put(key, bundle.getString(key));
            }

            return messages;
        } catch (MissingResourceException e) {
            throw new RuntimeException("Properties file not found for language: " + language, e);
        }
    }
}
