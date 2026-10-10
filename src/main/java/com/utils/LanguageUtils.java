package com.utils;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LanguageUtils {
    private static final String CONFIG_FILE = "system.properties";
    private static final String LANGUAGE_DIR = "language/";

    public static Properties getLanguageProperties() throws IOException {
        // 讀取 system.properties 文件
        Properties systemProps = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            systemProps.load(input);
        }

        // 根據語言加載對應的語言資源文件
        String language = systemProps.getProperty("language", "en");
        Properties languageProps = new Properties();
        try (InputStream input = new FileInputStream(LANGUAGE_DIR + language + ".properties")) {
            languageProps.load(input);
        }

        return languageProps;
    }

    public static String getTranslation(String key) throws IOException {
        Properties props = getLanguageProperties();
        return props.getProperty(key, "???");
    }
    /**
     * 根據語言代碼加載對應的語言文件。
     *
     * @param language 語言代碼，例如 "en" 或 "zh"
     * @param context  ServletContext，用於讀取資源文件
     * @return 包含鍵值對的 Map
     * @throws IOException 當文件加載失敗時拋出
     */
    public static Map<String, String> loadLanguageProperties(String language, ServletContext context) throws IOException {
        String propertiesPath = "/language/" + (language == null || language.isEmpty() ? "default" : language) + ".properties";

        InputStream input = context.getResourceAsStream(propertiesPath);
        if (input == null) {
            throw new FileNotFoundException("Properties file not found: " + propertiesPath);
        }

        Properties properties = new Properties();
        properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));

        Map<String, String> messages = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            messages.put(key, properties.getProperty(key));
        }
        return messages;
    }



}
