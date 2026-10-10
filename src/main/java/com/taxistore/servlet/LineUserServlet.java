package com.taxistore.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.*;
import com.taxistore.service.impl.LineMessageService;
import com.taxistore.service.impl.LineUserTService;

import com.utils.LinePushService;
import com.utils.PropertiesUtil;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * LineUserServlet 負責處理與 Line 使用者相關的 API 請求。開法者應該透過這個serlvet，將LineID與個人資料綁定，以便發送LINE訊息。
 */
@WebServlet("/lineUserServlet")
public class LineUserServlet extends BaseServlet
{

    /**
     * 儲存前端傳來的欄位名稱列表（全域共享，請確保執行緒安全）
     */
    private static List<String> columnList = new ArrayList<>();
    private static final String Application_PROPERTIES_FILE="application.properties";
    private static final long serialVersionUID = 1L;
    private static final LineUserTService lineUserTService=new LineUserTService();
    private static final LineMessageService lineMessageService = new LineMessageService();
    Properties applicationProperties=PropertiesUtil.load(Application_PROPERTIES_FILE);
    /**
     * 測試用，檢視此servlet是否運作。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
    http://localhost:8088/lineUserServlet?action=ajaxGetWelcome
     */
    protected void ajaxGetWelcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"success\": true, \"message\": \"歡迎光臨我們的網站！\"}");

    }

    /**
     * http://localhost:8088/TaxiStoreServer/lineUserServlet?action=ajaxGetList
     *
     取得 Line 使用者列表，支援 DataTables 伺服器端查詢
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxGetLineUsersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int draw= request.getParameter("draw")!=null?Integer.parseInt(request.getParameter("draw")):0;
        int start = request.getParameter("start")!=null?Integer.parseInt(request.getParameter("start")):0;
        int length = request.getParameter("length")!=null?Integer.parseInt(request.getParameter("length")):0;
        String searchColumn = request.getParameter("searchColumn");
        String searchValue = request.getParameter("searchValue");
        if("all".equalsIgnoreCase(searchColumn)) searchValue="";

        String columnName=request.getParameter("orderColumnName");

        String orderDir = request.getParameter("orderDir");

        Result<DataTableResponse<LineUserDTO>> result = lineUserTService.getLineUsersBySearch(draw, start, length, searchColumn, searchValue, orderDir,columnName );

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(result.isSuccess() ? result.getData() : Map.of("error", result.getErrorMessage())));

    }

    /**
     * 透過 SerialId 取得單一使用者資訊
     * @param request
     * @param response
     * @throws IOException
     */
    protected void ajaxGetUserBySerialId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String serialId = request.getParameter("serialId");
        if (serialId == null || serialId.trim().isEmpty()) {
            response.getWriter().write("{\"success\": false, \"errorMessage\": \"缺少 serialID\"}");
            return;
        }

        Result<LineUser> result = lineUserTService.getLineUserBySerialId(serialId);
        response.getWriter().write(new Gson().toJson(result));
    }

    protected void ajaxGetUserWithDriverByDriverSeq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String DriverSeq = request.getParameter("DriverSeq");
        Result<LineUserDTO> result = lineUserTService.getLineUserWithDriverByDriverSeq(DriverSeq);

        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetUserWithDriverByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UserId = request.getParameter("UserId");
        Result<LineUserDTO> result = lineUserTService.getLineUserWithDriverByUserId(UserId);

        response.getWriter().write(result.toJSON());

    }
    /*
        透過serialId取得table line_users與DriverId關聯的資料。
     */
    protected void ajaxGetUserWithDriverBySerialId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String SerialId = request.getParameter("serialId");
        PrintWriter out = response.getWriter();
        Result<LineUserDTO> result = lineUserTService.getLineUserWithDriverBySerialId(SerialId);

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(result));

    }
    protected void ajaxGetUserByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UserId = request.getParameter("UserId");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        if (UserId == null || UserId.trim().isEmpty()) {
            out.print("{\"success\": false, \"errorMessage\": \"缺少 UserId\"}");
            return;
        }

        Result<LineUser> result = lineUserTService.getLineUserByUserId(UserId);
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(result.isSuccess() ? result.getData() : Map.of("error", result.getErrorMessage())));

    }
    protected void ajaxUpdateByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String serialID = request.getParameter("serialID");
        String userId = request.getParameter("userId");
        String displayName = request.getParameter("displayName");
        String driverseq = request.getParameter("driverseq");
        LineUser lineUser=WebUtils.copyParamToBean(request.getParameterMap(),new LineUser());
        if (lineUser.getUserId() == null || lineUser.getUserId().trim().isEmpty())
        {
            response.getWriter().write(Result.failure("缺少 UserId").toJSON());
            return;
        }

        Result <Integer> result=lineUserTService.updateLineUserByUserId(lineUser);

        response.setContentType("application/json");

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(result));

    }


    /**
     * 處理傳送純文字訊息
     */
    protected void ajaxPushMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String userId = request.getParameter("userId");
        String message = request.getParameter("message");

        if (userId == null || message == null || userId.trim().isEmpty() || message.trim().isEmpty()) {
            response.getWriter().write(Result.failure("缺少 UserId/Message").toJSON());
            return;
        }

        try {
            LinePushService.pushText(userId, message);
            saveLineMessage(userId, message, "text", response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(Result.failure("發送訊息失敗：" + e.getMessage()).toJSON());
        }
    }
    /**
     * 處理傳送多圖訊息 (本地 imagePath 模式)
     */
    protected void ajaxPushMultiImageMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ajaxPushMultiImageMessageTemplate(
                request,
                response,
                new TypeToken<List<FineImage>>() {}.getType(),
                FineImage::getImagePath,
                true
        );
    }
    /**
     *
     * 處理傳送多圖訊息 (Cloudinary secureUrl 模式)
     */
    protected void ajaxPushMultiImageMessageByCloudinaryMetadata(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ajaxPushMultiImageMessageTemplate(
                request,
                response,
                new TypeToken<List<CloudinaryMetadata>>() {}.getType(),
                CloudinaryMetadata::getCloudinarySecureUrl,
                false
        );
    }

    protected <T> void ajaxPushMultiImageMessageTemplate(
            HttpServletRequest request,
            HttpServletResponse response,
            Type listType,
            Function<T, String> urlExtractor,
            boolean needUpload
    ) throws IOException {
        response.setContentType("application/json");

        try {
            JsonObject jsonObject = parseRequestJson(request, response);
            if (jsonObject == null) return;

            String userId = jsonObject.get("userId").getAsString();
            String message = jsonObject.get("message").getAsString();
            String imagePathsJson = jsonObject.get("imagePaths").toString();

            if (!validateParams(userId, imagePathsJson, response)) return;

            List<T> imageMetas = new Gson().fromJson(imagePathsJson, listType);

            if (message == null || message.isEmpty()) {
                response.getWriter().write(Result.failure("Image metas parse fail (message is empty)").toJSON());
                return;
            }

            List<String> urls = imageMetas.stream()
                    .map(urlExtractor)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (urls.isEmpty()) {
                response.getWriter().write(Result.failure("No valid image URLs found").toJSON());
                return;
            }

            if (needUpload) {
                urls = LinePushService.uploadImagesByThread(urls);
                if (urls.contains(null)) {
                    response.getWriter().write(Result.failure("Some images uploaded fail").toJSON());
                    return;
                }
            }

            int responseCode = LinePushService.pushMessageWithMultipleImages(userId, urls, message);
            if (responseCode != 200) {
                response.getWriter().write(Result.failure("Push LINE Message fail, HTTP code: " + responseCode).toJSON());
                return;
            }

            saveLineMessage(userId, message, "image", response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(Result.failure("It failed to send multi-images: " + e.getMessage()).toJSON());
        }
    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
    /*
     * 簡單版：接收欄位名稱字串陣列，存進 static List
     * 前端需用類似 columns=欄位1&columns=欄位2 的方式傳遞
     */
    protected void ajaxUpdateColumnList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        // 👇 這一行直接抓出你傳來的欄位名稱陣列
        String[] columns = request.getParameterValues("columns");

        if (columns == null || columns.length == 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(Result.failure("No columns provided").toJSON());
            return;
        }

        // 存起來
        synchronized (LineUserServlet.class) {
            columnList = Arrays.asList(columns);
        }

        // 回傳成功訊息
        response.getWriter().write(Result.success(columnList).toJSON());
    }


    /**
     * 讀取並解析 JSON 內容
     */
    private JsonObject parseRequestJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        try {
            return JsonParser.parseString(jsonBuilder.toString()).getAsJsonObject();
        } catch (Exception e) {
            response.getWriter().write(Result.failure("Invalid JSON format").toJSON());
            return null;
        }
    }

    /**
     * 檢查參數
     */
    private boolean validateParams(String userId, String imagePathsJson, HttpServletResponse response) throws IOException {
        if (userId == null || userId.trim().isEmpty() || imagePathsJson == null || imagePathsJson.trim().isEmpty()) {
            response.getWriter().write(Result.failure("Required parameters userId or imagePaths are missing").toJSON());
            return false;
        }
        return true;
    }

    /**
     * 共用紀錄發送訊息的邏輯
     */
    private void saveLineMessage(String userId, String message, String type, HttpServletResponse response) throws IOException {
        LineMessage lineMessage = new LineMessage();
        lineMessage.setContent(message);
        lineMessage.setUserId(userId);
        lineMessage.setType(type);
        lineMessage.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        lineMessage.setDirectory("sent");
        lineMessage.setTimeStamp(new Timestamp(System.currentTimeMillis()));

        Result<Long> result = lineMessageService.addMessage(lineMessage);
        response.getWriter().write(result.toJSON());
    }

}
