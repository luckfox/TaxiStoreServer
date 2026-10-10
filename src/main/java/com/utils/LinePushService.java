package com.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * LinePushService
 * <p>
 * 提供 LINE Bot 推播服務，支援文字、圖片訊息推送，以及多圖上傳與推送功能。
 * 同時整合圖片上傳服務 (ex: CloudinaryUploader)。
 */
public class LinePushService {

    private static final String APPLICATION_PROPERTIES_FILE="application.properties";
    private static Properties applicationProperties;
    private static  String CHANNEL_ACCESS_TOKEN;
    // 讀取 LINE Channel Access Token
//    private static final String CHANNEL_ACCESS_TOKEN = PropertiesUtil.load("application.properties")
//            .getProperty("channel.access.token");

    static CloudinaryHelper helper;

    static {
        Properties applicationProperties = PropertiesUtil.load(APPLICATION_PROPERTIES_FILE);
        helper = new CloudinaryHelper();
    }

    public LinePushService() throws IOException {
    }

    /**
     * 傳送單純的文字訊息給指定使用者
     *
     * @param userId 目標使用者 LINE ID
     * @param message 欲傳送的文字內容
     * @return HTTP 回應碼
     */
    public static int pushText(String userId, String message) {
        String json = "{" +
                "\"to\": \"" + userId + "\"," +
                "\"messages\": [{\"type\": \"text\", \"text\": \"" + escapeJson(message) + "\"}]" +
                "}";
        return sendLinePush(json);
    }

    /**
     * 傳送單張圖片訊息
     *
     * @param userId 目標使用者 LINE ID
     * @param imageUrl 圖片網址 (需為 https URL)
     * @return HTTP 回應碼
     */
    public static int pushImage(String userId, String imageUrl) {
        String json = "{" +
                "\"to\": \"" + userId + "\"," +
                "\"messages\": [{" +
                "\"type\": \"image\", " +
                "\"originalContentUrl\": \"" + imageUrl + "\"," +
                "\"previewImageUrl\": \"" + imageUrl + "\"" +
                "}]" +
                "}";
        return sendLinePush(json);
    }

    /**
     * 傳送含有多張圖片與文字的訊息，可以允許沒有圖片。但是一定要有文字。
     *
     * @param userId 目標使用者 LINE ID
     * @param imageUrls 圖片網址列表 (最多 4 張，超出自動截斷)
     * @param message 文字訊息內容
     * @return HTTP 回應碼
     */
    public static int pushMessageWithMultipleImages(String userId, List<String> imageUrls, String message) {
        if (imageUrls.size() > 4) {
            System.out.println("超過 4 張圖片，將自動截斷！");
            imageUrls = imageUrls.subList(0, 4);
        }

        List<String> messages = new ArrayList<>();

        // 加入文字訊息
        if (message != null && !message.isEmpty()) {
            messages.add("{\"type\": \"text\", \"text\": \"" + escapeJson(message) + "\"}");
        }

        // 加入圖片訊息
        for (String imgUrl : imageUrls) {
            messages.add("{"
                    + "\"type\": \"image\","
                    + "\"originalContentUrl\": \"" + imgUrl + "\","
                    + "\"previewImageUrl\": \"" + imgUrl + "\""
                    + "}");
        }

        // 如果沒有訊息要送，直接跳出
        if (messages.isEmpty()) {
            System.out.println("沒東西好傳啦！🫠");
            return -1;
        }

        // 組合 JSON
        String json = "{\"to\": \"" + userId + "\",\"messages\": [" + String.join(",", messages) + "]}";

        return sendLinePush(json);
    }


    /**
     * 上傳本地單張圖片並推送至 LINE
     *
     * @param userId 目標使用者 LINE ID
     * @param imagePath 本地圖片路徑
     * @param message 可附加文字訊息
     */
    public static void sendLocalImageWithMessage(String userId, String imagePath, String message) {
        String imageUrl = uploadImage(imagePath);
        if (imageUrl == null || imageUrl.startsWith("錯誤")) {
            System.out.println("圖片上傳失敗：" + imageUrl);
            return;
        }

        // 先送文字（可選）
        if (message != null && !message.isEmpty()) {
            pushText(userId, message);
        }
        int response = pushImage(userId, imageUrl);
        if (response == 200) {
            System.out.println("圖片訊息推送成功！");
        } else {
            System.out.println("圖片訊息推送失敗，HTTP Code: " + response);
        }
    }

    /**
     * 多執行緒上傳多張圖片 (支援加速上傳)
     *
     * @param imagePaths 本地圖片路徑列表
     * @return 上傳後的圖片網址列表 (與原順序相同，失敗則為 null)
     */
    public static List<String> uploadImagesByThread(List<String> imagePaths) {
        List<String> resultUrls = new ArrayList<>(imagePaths.size());
        List<Thread> threads = new ArrayList<>();

        // 預先填充 null 方便用 index 存放結果
        for (int i = 0; i < imagePaths.size(); i++) {
            resultUrls.add(null);
        }

        for (int i = 0; i < imagePaths.size(); i++) {
            final int index = i;
            String path = imagePaths.get(i);
            Thread thread = new Thread(() -> {
                String url = uploadImage(path);
                if (url != null && !url.startsWith("錯誤")) {
                    resultUrls.set(index, url);
                    System.out.println("# " + (index + 1) + " uploaded successly.");
                } else {
                    System.out.println("# " + (index + 1) + " uploaded fail.");
                }
            });
            threads.add(thread);
            thread.start();
        }

        // 等待所有執行緒完成
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultUrls;
    }

    /**
     * 實際送出 HTTP POST 請求到 LINE push API
     */
    private static int sendLinePush(String payload) {
        try {
            URL url = new URL("https://api.line.me/v2/bot/message/push");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }
            return conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

    /**
     * 圖片上傳 (整合 CloudinaryUploader)
     */
    private static String uploadImage(String imagePath) {
        //return CloudinaryHelper. upload(imagePath);
        return helper.uploadImage(imagePath);

    }

    /**
     * 避免 JSON 格式錯誤 (很簡單的轉義防呆)
     */
    private static String escapeJson(String text) {
        return text.replace("\"", "\\\"");
    }

    /**
     * 測試入口
     */
    public static void main(String[] args) {
        String userId = "Uc5f2f40eebcbff5ad54e21a6f411a97e";
        String img1 = "G:/我的雲端硬碟/照片/太空/pexels-pixabay-2150.jpg";
        String img2 = "G:/我的雲端硬碟/照片/昆蟲/istockphoto-108221306-1024x1024.jpg";
        String img3 = "G:/我的雲端硬碟/照片/昆蟲/istockphoto-1007438592-1024x1024.jpg";
        String message = "你好！這是測試訊息。";

        pushText(userId,"hello  222!");
        String refImage1=helper.uploadImage(img1);
        pushImage(userId,refImage1);
        // 先進行多圖上傳
        List<String> localPaths = List.of(img1, img2, img3);
        List<String> uploadedUrls = uploadImagesByThread(localPaths);

        // 傳送圖+文字
        pushMessageWithMultipleImages(userId, uploadedUrls, message);
        helper.printAllResources();

    }
}
