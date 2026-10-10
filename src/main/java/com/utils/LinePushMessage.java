package com.utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

public class LinePushMessage {

    private static String Cloudinary_CloudName="dzfzvyno4";
    private static String Cloudinary_API_Key="471324441145343";
    private static String Cloudinary_API_Secret="QsU_uZQkA9pKRYHTWVpG_Uwdkm8";
    private static String Cloudinary_Environment_Variable="CLOUDINARY_URL=cloudinary://471324441145343:QsU_uZQkA9pKRYHTWVpG_Uwdkm8@dzfzvyno4";


    private static  String CHANNEL_ACCESS_TOKEN;
    private static String IMGUR_CLIENT_ID;
    private static final String APPLICATION_PROPERTIES_FILE="application.properties";
    private static Properties applicationProperties;
//=====================================CLOUDINARY===============================================
    private static String CLOUDINARY_CLOUDNAME;
    private static String CLOUDINARY_API_KEY;
    private static String CLOUDINARY_API_SECRET;
    static {
        Properties applicationProperties = PropertiesUtil.load(APPLICATION_PROPERTIES_FILE);
        CHANNEL_ACCESS_TOKEN = applicationProperties.getProperty("channel.access.token");
        IMGUR_CLIENT_ID = applicationProperties.getProperty("imgur.client.id");

        CLOUDINARY_CLOUDNAME= applicationProperties.getProperty("cloudinary.cloudName");
        CLOUDINARY_API_KEY = applicationProperties.getProperty("cloudinary.api_key");
        CLOUDINARY_API_SECRET = applicationProperties.getProperty("cloudinary.api_secret");
    }
    /**
     * 發送文字訊息到 LINE
     */
    public static int pushMessage(String userId, String message) {
        String jsonPayload = "{"
                + "\"to\": \"" + userId + "\","
                + "\"messages\": [{\"type\": \"text\", \"text\": \"" + message + "\"}]"
                + "}";
        return sendPushRequest(jsonPayload);
    }

    /**
     * 發送圖片訊息到 LINE
     */
    public static int pushImage(String userId, String imageUrl) {
        String jsonPayload = "{"
                + "\"to\": \"" + userId + "\","
                + "\"messages\": [{"
                + "\"type\": \"image\","
                + "\"originalContentUrl\": \"" + imageUrl + "\","
                + "\"previewImageUrl\": \"" + imageUrl + "\""
                + "}]"
                + "}";
        return sendPushRequest(jsonPayload);
    }
    /**
     * 發送 HTTP 請求到 LINE Push API
     */
    public static int sendPushRequest(String jsonPayload) {
        int responseCode = 200;
        try {
            URL url = new URL("https://api.line.me/v2/bot/message/push");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("UTF-8"));
            }

            responseCode = conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCode;
    }
    public static int pushTextMessage(String userId, String message)
    {
        int respondCode = 500;
        try {
                // LINE Push Message API
                URL url = new URL("https://api.line.me/v2/bot/message/push");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);
                conn.setDoOutput(true);

                // 建立 JSON Body
                String jsonPayload = "{"
                        + "\"to\": \"" + userId + "\","
                        + "\"messages\": [{\"type\": \"text\", \"text\": \"" + message + "\"}]"
                        + "}";

                // 發送 Request
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
                }
                respondCode = conn.getResponseCode();
            } catch (Exception e)
        {
                e.printStackTrace();
        }
            // 取得 Response

        return respondCode;
    }

    /**
     * 上傳圖片到 Imgur 並回傳圖片 URL
     */
    public static String uploadImageToImgur(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                return "錯誤: 檔案不存在";
            }

            byte[] imageBytes = new byte[(int) imageFile.length()];
            try (FileInputStream fis = new FileInputStream(imageFile)) {
                fis.read(imageBytes);
            }
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println("clientId = " + IMGUR_CLIENT_ID);
            System.out.println("imagePath = " + imagePath);
            System.out.println("image exists = " + new File(imagePath).exists());
            System.out.println("image length = " + new File(imagePath).length());
            System.out.println("encoded length = " + encodedImage.length());
            URL url = new URL("https://api.imgur.com/3/image");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
            conn.setDoOutput(true);

            String payload = "image=" + URLEncoder.encode(encodedImage, "UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes("UTF-8"));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    JsonObject jsonResponse = JsonParser.parseReader(in).getAsJsonObject();
                    return jsonResponse.getAsJsonObject("data").get("link").getAsString();
                }
            } else {
                return "錯誤: 上傳 Imgur 失敗, HTTP Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "錯誤: " + e.getMessage();
        }
    }
    private static String uploadImage(String imagePath)
    {
        return uploadImageToImgur(imagePath);
        //return uploadImageToCloudinary(imagePath);
    }
    /*
     * 上傳本地圖片並發送 LINE 訊息（圖片 + 附加文字）
     * @param userId LINE 使用者 ID
     * @param imagePath 本地圖片檔案路徑
     * @param message 附加文字訊息
     */
    public static void sendLocalImageWithMessage(String userId, String imagePath, String message) {
        String imageUrl = uploadImage(imagePath);

        if (imageUrl.startsWith("錯誤")) {
            System.out.println("圖片上傳失敗：" + imageUrl);
            return;
        }

        // 先送文字訊息（可選）
        if (message != null && !message.isEmpty()) {
            pushTextMessage(userId, message);
        }

        // 送圖片訊息
        int responseCode = pushImage(userId, imageUrl);
        if (responseCode == 200) {
            System.out.println("圖片訊息已成功送出");
        } else {
            System.out.println("圖片訊息送出失敗，HTTP Code: " + responseCode);
        }
    }
    /*
     * 上傳圖片到 Cloudinary 並回傳圖片 URL
     */
    public static String uploadImageToCloudinary(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                return "錯誤: 檔案不存在";
            }

            byte[] imageBytes = new byte[(int) imageFile.length()];
            try (FileInputStream fis = new FileInputStream(imageFile)) {
                fis.read(imageBytes);
            }

            String boundary = "Boundary-" + System.currentTimeMillis();
            String LINE_FEED = "\r\n";

            URL url = new URL("https://api.cloudinary.com/v1_1/" + CLOUDINARY_CLOUDNAME + "/image/upload");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (OutputStream outputStream = conn.getOutputStream();
                 DataOutputStream writer = new DataOutputStream(outputStream)) {

                // api_key
                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"api_key\"" + LINE_FEED + LINE_FEED);
                writer.writeBytes(CLOUDINARY_API_KEY + LINE_FEED);

                // timestamp
                long timestamp = System.currentTimeMillis() / 1000;
                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"timestamp\"" + LINE_FEED + LINE_FEED);
                writer.writeBytes(Long.toString(timestamp) + LINE_FEED);

                // signature
                String paramsToSign = "timestamp=" + timestamp + CLOUDINARY_API_SECRET;
                String signature = sha1Hex(paramsToSign);
                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"signature\"" + LINE_FEED + LINE_FEED);
                writer.writeBytes(signature + LINE_FEED);

                // file
                writer.writeBytes("--" + boundary + LINE_FEED);
                writer.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + imageFile.getName() + "\"" + LINE_FEED);
                writer.writeBytes("Content-Type: application/octet-stream" + LINE_FEED + LINE_FEED);
                writer.write(imageBytes);
                writer.writeBytes(LINE_FEED);

                writer.writeBytes("--" + boundary + "--" + LINE_FEED);
                writer.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    JsonObject jsonResponse = JsonParser.parseReader(in).getAsJsonObject();
                    return jsonResponse.get("secure_url").getAsString();
                }
            } else {
                return "錯誤: 上傳 Cloudinary 失敗, HTTP Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "錯誤: " + e.getMessage();
        }
    }

    /*
     * 計算 SHA-1 簽名 (Cloudinary 用)
     */
    private static String sha1Hex(String input) throws Exception {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
        byte[] result = md.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }




















    // 測試發送
    public static void main(String[] args)
    {
      LinePushMessage linePushMessage=new LinePushMessage();
        String userId="Uc5f2f40eebcbff5ad54e21a6f411a97e";
        //String imageUrl = "https://i.imgur.com/zE2fql4.jpg"; // 原始圖片網址（HTTPS）
  //      String path2="G:/我的雲端硬碟/照片/太空/pexels-pixabay-2150.jpg";
 //       String path="D:/FineImage/thumb/CU3236052/istockphoto-512596224-1024x1024_thumb.jpg";
//        String previewImageUrl = "https://example.com/images/photo_preview.jpg"; // 預覽圖片網址

        pushTextMessage(userId, "你好！這是測試訊息。");

        //LinePushMessage.pushImage(userId,imageUrl);
 //       LinePushMessage.sendLocalImageWithMessage(userId,path2,"This message is for test");
//        int response = pushImageMessage(userId, imageUrl, previewImageUrl);
//        System.out.println("發送圖片回應碼: " + response);




//        String imageUrl = uploadImageToCloudinary(path2);
//        System.out.println("Cloudinary URL: " + imageUrl);
//
//        if (!imageUrl.startsWith("錯誤")) {
//            pushTextMessage(userId, "這張圖是從 Cloudinary 傳來的📸");
//            pushImage(userId, imageUrl);
//        } else {
//            System.out.println("Cloudinary 上傳失敗: " + imageUrl);
//        }
    }
}
