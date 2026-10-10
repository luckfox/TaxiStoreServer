package com.taxistore.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taxistore.pojo.LineMessage;
import com.taxistore.pojo.LineUser;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.LineMessageService;
import com.taxistore.service.impl.LineUserTService;
import com.utils.ConfigLoader;
import com.utils.Log;
import com.utils.PropertiesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

/*
 * Servlet 處理來自 LINE Webhook 的請求，也就是當LINE Server端，如果有接受到屬於我們的訊息，則會發到這個servlet，並由這個
 * servlet進行處理。
 * <p>
 * 主要功能：
 * <ul>
 *     <li>接收 LINE Webhook 事件</li>
 *     <li>從 LINE API 取得使用者資訊</li>
 *     <li>將使用者資訊寫入資料庫</li>
 *     <li>本地快取 userProfiles 以減少 API 請求</li>
 * </ul>
 */
@WebServlet("/webhook")
public class LineWebhookServlet extends HttpServlet {
    private static final String Application_PROPERTIES_FILE="application.properties";
    private static final String LINE_USER_PROFILE="lineUser.properties";
    private static  String CHANNEL_ACCESS_TOKEN;

    private static final LineUserTService lineUserTService = new LineUserTService();
    private static final LineMessageService lineMessageService = new LineMessageService();

    private  Properties applicationProperties;
    private  Properties userProfiles;
    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        System.out.println("JVM Default Charset: " + java.nio.charset.Charset.defaultCharset());
        System.out.println("JVM File Encoding: " + System.getProperty("file.encoding"));
        Log.normal("@@@@ 測試中文輸出");
        super.init();
        applicationProperties=PropertiesUtil.load(Application_PROPERTIES_FILE);
        CHANNEL_ACCESS_TOKEN= applicationProperties.getProperty("channel.access.token");
        userProfiles=PropertiesUtil.load(LINE_USER_PROFILE);
    }

    /**
     * 處理 GET 請求，提供 Webhook 健康檢查。
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException 若寫入 response 失敗
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Log.normal("收到 GET Webhook 請求");
        resp.setContentType("text/plain");
        resp.getWriter().write("Webhook is working");
    }

    /**
     * 處理來自 LINE Webhook 的 POST 請求。
     * 解析 Webhook 事件並根據使用者 ID 取得 profile 資訊。
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException 若讀取請求或寫入回應時發生錯誤
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
        Map<String, Object> payload = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {}.getType());

        Log.normal("收到 Webhook: " + payload);

        List<Map<String, Object>> events = (List<Map<String, Object>>) payload.get("events");
        Log.debug("get Webhook event!");
        if (events == null || events.isEmpty())
        {

            resp.getWriter().write("{\"status\":\"no events\"}");
        }
        for (Map<String, Object> event : events)
        {
            // 取得事件的來源資訊
            Map<String, Object> source = (Map<String, Object>) event.get("source");
            // 只處理 type 為 "user" 的來源
            if (source == null || !"user".equals(source.get("type")))
            {
                continue;
            }

            String userId = (String) source.get("userId");
            if (userId == null) continue;
            // 若 userId 存在，則透過 LINE API 取得 displayName
            //檢查此userID是否存在於user properties的快取中
            // 使用 computeIfAbsent 簡化快取邏輯

            String displayName = userProfiles.getProperty(userId);
            if (displayName == null)
            {
                displayName = fetchUserProfile(userId);
                //saveUserProfile(userId, displayName);
                userProfiles.setProperty(userId,displayName);
                PropertiesUtil.storeProperties(userProfiles,LINE_USER_PROFILE);
            }

            Log.normal("displayName="+displayName);

            // 建立一個新的 LineUser 物件並設定必要的欄位
            // memo、driverID 可先留空，createDate 由資料庫自動處理
            LineUser lineUser = new LineUser(0,userId,displayName,"",null,0L);
            // 透過service layer 將使用者寫入資料庫
            Result<Long> result = lineUserTService.InsertOrUpdateUser(lineUser);
            if (result.isSuccess())
            {
                Log.normal("使用者成功寫入資料庫，userId: " + userId);
            } else
            {
                Log.error("寫入資料庫失敗，userId: " + userId + ", Error: " + result.getErrorMessage());
            }


            String eventType = (String) event.get("type");
            /*
            event有很多種，如加入/退出聊天室、訊息、加入好友...
            此處處理訊息類的event.
             */
            if ("message".equals(eventType))
            {
                LineMessage lineMessage = new LineMessage();
                Map<String, Object> message = (Map<String, Object>) event.get("message");
                String messageType=(String) message.get("type");
                lineMessage.setType(messageType);
                lineMessage.setMessageId((String) message.get("id"));
                lineMessage.setUserId(userId);
                Long eventTimestamp = ((Number) event.get("timestamp")).longValue();//收到訊息的時間
                lineMessage.setTimeStamp(new Timestamp(eventTimestamp));
                lineMessage.setDirectory("received");
                switch (messageType) {
                    case "text":
                        lineMessage.setContent((String) message.get("text"));
                        break;
                    case "image":
                    case "video":
                    case "audio":
                        Map<String, Object> contentProvider = (Map<String, Object>) message.get("contentProvider");
                        if (contentProvider != null && "line".equals(contentProvider.get("type")))
                        {
                            lineMessage.setContent("需透過 Message API 下載媒體內容");
                        } else {
                            lineMessage.setContent((String) contentProvider.get("originalContentUrl"));
                        }
                        break;
                    case "sticker":
                        String stickerId = (String) message.get("stickerId");
                        String packageId = (String) message.get("packageId");
                        lineMessage.setContent("Sticker ID: " + stickerId + ", Package ID: " + packageId);
                        break;
                    default:
                        lineMessage.setContent("未知類型: " + messageType);
                }

                lineMessageService.addMessage(lineMessage);
            }
        }
        resp.getWriter().write("{\"status\":\"ok\"}");
    }

    /**
     * 透過 LINE API 取得使用者的 displayName。
     *
     * @param userId 使用者 ID
     * @return displayName 若成功，則返回使用者名稱；否則返回 null。
     */
    private String fetchUserProfile(String userId) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://api.line.me/v2/bot/profile/" + userId);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                Map<String, Object> response = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {}.getType());
                return (String) response.get("displayName");
            } else {
                Log.error("Failed to fetch user profile: HTTP " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }



    /**
     * 儲存使用者 ID 和 displayName 到 userProfiles.properties。
     *
     * @param userId       使用者 ID
     * @param displayName  使用者名稱
     */
    private void saveUserProfile(String userId, String displayName)
    {
        synchronized (userProfiles) { // 避免多執行緒寫入問題
            userProfiles.setProperty(userId, displayName);

            File file = new File(getServletContext().getRealPath(LINE_USER_PROFILE));
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // 確保目錄存在
            }

            // 使用暫存檔案來避免寫入錯誤時破壞原始檔案
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            try (OutputStream output = new FileOutputStream(tempFile)) {
                userProfiles.store(output, "Updated User Profiles");
                output.flush();

                // 寫入成功後，使用 rename 確保更新是原子性操作
                if (!tempFile.renameTo(file)) {
                    throw new IOException("Failed to replace the original properties file");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 回傳 JSON 回應給客戶端。
     *
     * @param resp     HTTP Response 物件
     * @param response 要回傳的 JSON 內容
     * @throws IOException 當回應時發生錯誤
     */
    private void sendJsonResponse(HttpServletResponse resp, Map<String, String> response) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(response));
    }
}