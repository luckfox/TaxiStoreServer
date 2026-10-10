package com.taxistore.servlet;

import com.google.gson.Gson;
import com.taxistore.pojo.LineMessage;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.ImageService;
import com.taxistore.service.impl.LineMessageService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

@WebServlet("/UploadImageServlet")
public class UploadImageServlet extends BaseServlet
{

    ImageService imageService=new ImageService();
    LineMessageService lineMessageService=new LineMessageService();
    /**
     * Authorization: Client-ID aef81551ce27fad
     * Client secret: a0e6043650f32ee43840beb0f58b8d05f20c2b2d
     */
    /**
     * 測試用，檢視此servlet是否運作。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
    http://localhost:8088/fineUploadServlet?action=ajaxGetWelcome
     */
    protected void ajaxGetWelcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"success\": true, \"message\": \"歡迎光臨我們的網站！\"}");

    }

    protected void ajaxUploadToImgur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        BufferedReader reader=request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());

        String userId = json.getString("userId");  // 目標 LINE 用戶 ID
        String base64Image = json.getString("image");  // Base64 編碼的圖片


        // 上傳圖片至 Imgur
        Result<String> result = imageService.uploadToImgur(base64Image);
        if(!result.isSuccess())
        {
            response.getWriter().write(new Gson().toJson(result));
            return;
        }
        LineMessage lineMessage=new LineMessage();
        lineMessage.setDirectory("sent");
        lineMessage.setType("image");
        lineMessage.setUserId(userId);
        lineMessage.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        lineMessage.setTimeStamp(new java.sql.Timestamp(System.currentTimeMillis()));
        lineMessage.setContent(result.getData().toString());

        Result<Long> result2=lineMessageService.addMessage(lineMessage);

        // 發送圖片訊息給 LINE User
        Result<Integer> result3 = imageService.pushImage(userId, result.getData().toString());
        response.getWriter().write(new Gson().toJson(result2));

    }

}
