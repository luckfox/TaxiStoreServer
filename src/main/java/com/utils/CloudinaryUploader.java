package com.utils;

/**
 * CloudinaryUploader.java
 * 上傳圖片至 Cloudinary
 */
import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CloudinaryUploader {

    private static final Properties config = PropertiesUtil.load("application.properties");
    private static final String CLOUD_NAME = config.getProperty("cloudinary.cloudName");
    private static final String API_KEY = config.getProperty("cloudinary.api_key");
    private static final String API_SECRET = config.getProperty("cloudinary.api_secret");

    /**
     * 上傳本地圖片到 Cloudinary，回傳 HTTPS 圖片 URL
     */
    public static String upload(String imagePath) {
        try {
            File file = new File(imagePath);
            if (!file.exists()) return "錯誤: 檔案不存在";

            byte[] bytes = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }

            long timestamp = System.currentTimeMillis() / 1000;
            String signature = sha1("timestamp=" + timestamp + API_SECRET);

            String boundary = "Boundary-" + System.currentTimeMillis();
            String LINE_FEED = "\r\n";

            URL url = new URL("https://api.cloudinary.com/v1_1/" + CLOUD_NAME + "/image/upload");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (OutputStream output = conn.getOutputStream();
                 DataOutputStream writer = new DataOutputStream(output)) {

                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"api_key\"" + LINE_FEED + LINE_FEED);
                writer.writeBytes(API_KEY + LINE_FEED);

                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"timestamp\"" + LINE_FEED + LINE_FEED);
                writer.writeBytes(Long.toString(timestamp) + LINE_FEED);

                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"signature\"" + LINE_FEED + LINE_FEED);
                writer.writeBytes(signature + LINE_FEED);

                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + LINE_FEED);
                writer.writeBytes("Content-Type: application/octet-stream" + LINE_FEED + LINE_FEED);
                writer.write(bytes);
                writer.writeBytes(LINE_FEED);

                writer.writeBytes("--" + boundary + "--" + LINE_FEED);
                writer.flush();
            }

            if (conn.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    return json.get("secure_url").getAsString();
                }
            } else {
                return "錯誤: 上傳 Cloudinary 失敗, HTTP Code: " + conn.getResponseCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "錯誤: " + e.getMessage();
        }
    }

    /**
     * Cloudinary 簽名產生器
     */
    private static String sha1(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    // 以下為新增的監控功能

    /**
     * 取得目前所有資源清單
     */
    public static List<Map> listAllResources() {
//        List<Map> allResources = new ArrayList<>();
//        String nextCursor = null;
//        try {
//            do {
//                Map<String, Object> params = Map.of(
//                        "max_results", 100,
//                        "next_cursor", nextCursor
//                );
//                ApiResponse response = cloudinary.api().resources(params);
//                List<Map> resources = (List<Map>) response.get("resources");
//                allResources.addAll(resources);
//                nextCursor = (String) response.get("next_cursor");
//            } while (nextCursor != null);
//        } catch (ApiException e) {
//            System.err.println("API 錯誤: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return allResources;
        return null;
    }

    /**
     * 依照上傳日期篩選 (最近N天)
     */
    public static List<Map> filterResourcesByDays(List<Map> resources, int days) {
        List<Map> filtered = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -days);
        Date cutoffDate = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        for (Map res : resources) {
            try {
                String createdAt = (String) res.get("created_at");
                Date createdDate = sdf.parse(createdAt);
                if (createdDate.after(cutoffDate)) {
                    filtered.add(res);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return filtered;
    }

    /**
     * 總儲存統計
     */
    public static void printStorageStatistics(List<Map> resources) {
        long totalBytes = 0;
        for (Map res : resources) {
            totalBytes += (Long) res.get("bytes");
        }
        System.out.println("目前總檔案數: " + resources.size());
        System.out.println("目前總儲存空間: " + (totalBytes / 1024.0 / 1024.0) + " MB");
    }

    /**
     * 列出最大檔案排行
     */
    public static void printTopLargestFiles(List<Map> resources, int topN) {
        resources.sort((a, b) -> Long.compare((Long) b.get("bytes"), (Long) a.get("bytes")));

        System.out.println("【Top " + topN + " 最大檔案】");
        for (int i = 0; i < Math.min(topN, resources.size()); i++) {
            Map res = resources.get(i);
            System.out.println("檔名: " + res.get("public_id"));
            System.out.println("大小: " + ((Long) res.get("bytes") / 1024.0 / 1024.0) + " MB");
            System.out.println("上傳時間: " + res.get("created_at"));
            System.out.println("--------------------------------------");
        }
    }

    // 簡易測試 main
    public static void main(String[] args) {
        // 測試上傳
        String result = upload("測試用圖片路徑.jpg");
        System.out.println("上傳結果: " + result);

        // 盤點資源
        List<Map> allResources = listAllResources();
        printStorageStatistics(allResources);
        printTopLargestFiles(allResources, 5);

        // 列出最近 30 天
        System.out.println("最近 30 天內檔案：");
        List<Map> recentFiles = filterResourcesByDays(allResources, 30);
        for (Map res : recentFiles) {
            System.out.println(res.get("public_id") + " / " + res.get("created_at"));
        }
    }
}
