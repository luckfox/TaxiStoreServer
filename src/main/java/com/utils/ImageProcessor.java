package com.utils;
import org.json.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import javax.imageio.ImageIO;

public class ImageProcessor
{
    private static  String CHANNEL_ACCESS_TOKEN;
    private static String IMGUR_CLIENT_ID;
    private static final String APPLICATION_PROPERTIES_FILE="application.properties";
    private static Properties applicationProperties;

    static {
        Properties applicationProperties = PropertiesUtil.load(APPLICATION_PROPERTIES_FILE);
        CHANNEL_ACCESS_TOKEN = applicationProperties.getProperty("channel.access.token");
        IMGUR_CLIENT_ID = applicationProperties.getProperty("imgur.client.id");
    }
    /**
     * 產生縮圖
     * @param inputPath  原始圖片路徑
     * @param outputPath 縮圖輸出路徑
     * @param width      目標寬度
     * @param height     目標高度
     * @return 是否成功
     */
    public static boolean generateThumbnail(String inputPath, String outputPath, int width, int height) {
        try {
            // 讀取原始圖片
            File inputFile = new File(inputPath);
            BufferedImage originalImage = ImageIO.read(inputFile);
            if (originalImage == null) {
                System.out.println("讀取圖片失敗: " + inputPath);
                return false;
            }

            // 產生縮圖
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();

            // 儲存縮圖
            File outputFile = new File(outputPath);
            ImageIO.write(resizedImage, "jpeg", outputFile);

            System.out.println("縮圖已儲存: " + outputPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 產生縮圖
     * @param inputPath  原始圖片路徑
     * @param outputPath 縮圖輸出路徑
     * @param scale      縮小比例
     * @return 是否成功
     */
    public static boolean generateThumbnailByScale(String inputPath, String outputPath, double scale) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(inputPath));
            if (originalImage == null) {
                System.out.println("讀取圖片失敗: " + inputPath);
                return false;
            }

            // 原圖尺寸
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // 按照 scale 計算新尺寸
            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            // 判斷最短邊是否小於 200
            if (Math.min(newWidth, newHeight) < 200) {
                // 計算最短邊縮小到 200 時的等比例縮放係數
                double minScale = 200.0 / Math.min(originalWidth, originalHeight);
                newWidth = (int) (originalWidth * minScale);
                newHeight = (int) (originalHeight * minScale);
            }

            // 產生縮圖
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            ImageIO.write(resizedImage, "jpeg", new File(outputPath));
            System.out.println("縮圖已儲存: " + outputPath + " (尺寸: " + newWidth + "x" + newHeight + ")");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
     *
     * @param base64Image  轉成base64的圖片資料。
     * @return 上傳圖片所在的url，若是失敗則回傳null
     */
    public static String uploadToImgur(String base64Image) {
        try {
            URL url = new URL("https://api.imgur.com/3/image");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // 將 base64 圖片格式化成 URL 編碼
            String payload = "image=" + URLEncoder.encode(base64Image, "UTF-8");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes("UTF-8"));
            }

            // 取得回應
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 解析 JSON 取得圖片 URL
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONObject("data").getString("link");

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    /*
     * 使用 LINE Messaging API 發送圖片訊息
     */
    public static int pushImage(String userId, String imageUrl) {
        int responseCode = 500;
        try {
            URL url = new URL("https://api.line.me/v2/bot/message/push");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);
            conn.setDoOutput(true);

            // 建立 JSON Payload
            String jsonPayload = "{"
                    + "\"to\": \"" + userId + "\","
                    + "\"messages\": [{"
                    + "  \"type\": \"image\","
                    + "  \"originalContentUrl\": \"" + imageUrl + "\","
                    + "  \"previewImageUrl\": \"" + imageUrl + "\""
                    + "}]"
                    + "}";

            // 送出請求
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("UTF-8"));
                os.flush();
            }

            responseCode = conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCode;
    }
    public static void main(String[] args) {
        // 測試縮圖
        String inputImage = "G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\resources\\image\\example.jpg";
        String outputImage = "G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\resources\\image\\thumbnail.jpg";
        String output0_2Image = "G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\resources\\image\\thumbnail0_2.jpg";
        int targetWidth = 200;
        int targetHeight = 200;

        boolean success = generateThumbnail(inputImage, outputImage, targetWidth, targetHeight);
        System.out.println("縮圖產生結果: " + success);

        generateThumbnailByScale(inputImage,output0_2Image,0.2);
        System.out.println("縮圖產生結果: " + success);
    }
}
