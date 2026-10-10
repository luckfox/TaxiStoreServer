package com.utils;

/**
 * ImgurUploader.java
 * 負責將圖片上傳至 Imgur 並回傳連結
 */

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImgurUploader {

    private static final String IMGUR_CLIENT_ID = PropertiesUtil.load("application.properties")
            .getProperty("imgur.client.id");

    /**
     * 上傳圖片至 Imgur 並取得 URL
     */
    public static String upload(String imagePath) {
        try {
            File file = new File(imagePath);
            if (!file.exists()) return "錯誤: 檔案不存在";

            byte[] bytes = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            String encoded = Base64.getEncoder().encodeToString(bytes);

            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.imgur.com/3/image").openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String payload = "image=" + URLEncoder.encode(encoded, "UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            if (conn.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    return json.getAsJsonObject("data").get("link").getAsString();
                }
            } else {
                return "錯誤: 上傳 Imgur 失敗, HTTP Code: " + conn.getResponseCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "錯誤: " + e.getMessage();
        }
    }
}