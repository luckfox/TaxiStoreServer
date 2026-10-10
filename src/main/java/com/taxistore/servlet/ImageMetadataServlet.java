package com.taxistore.servlet;

import com.taxistore.pojo.ImageMetadata;
import com.taxistore.pojo.Result;
import com.taxistore.service.IImageMetadataService;
import com.taxistore.service.impl.ImageMetadataService;
import com.utils.CloudinaryHelper;
import com.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


/**
 * Servlet 負責處理前端呼叫的 hash 檢查與上傳 metadata 存檔
 */
@WebServlet("/imageMetadataServlet")
public class ImageMetadataServlet extends BaseServlet {

    private IImageMetadataService service = new ImageMetadataService();
    private CloudinaryHelper helper=new CloudinaryHelper();

    protected void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result<List<ImageMetadata>> result = service.findAll();
        response.getWriter().write(result.toJSON());
    }
    protected void deleteByImageId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long imageId = Long.parseLong(request.getParameter("imageId"));
        String publicId = request.getParameter("publicId");

        // 先刪除 Cloudinary
        helper.deleteImageByPublicId(publicId);
        // 再刪除資料庫
        service.deleteByImageId(imageId);
        response.getWriter().write(Result.success(null).toJSON());
    }


    protected void checkHash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String hashCode = request.getParameter("hashCode");
        Result<ImageMetadata> result = service.isHashExist(hashCode);

        response.getWriter().write(result.toJSON());
    }
    protected void saveMetadata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String metadataJson = request.getParameter("metadata");
        if (metadataJson == null || metadataJson.isEmpty()) {
            response.getWriter().write(Result.failure("metadata 參數缺失").toJSON());
            return;
        }

        try {
            JSONObject metadataObj = new JSONObject(metadataJson);
            String hashCode = metadataObj.getString("hashCode");
            String publicId = metadataObj.getString("cloudinaryPublicId");
            String secureUrl = metadataObj.getString("cloudinarySecureUrl");
            long bytes=metadataObj.getLong("bytes");
            ImageMetadata meta = new ImageMetadata();
            meta.setHashCode(hashCode);
            meta.setCloudinaryPublicId(publicId);
            meta.setCloudinarySecureUrl(secureUrl);
            meta.setBytes(bytes);
            service.saveMetadata(meta);

            response.getWriter().write(Result.success("").toJSON());
        } catch (JSONException e) {
            response.getWriter().write(Result.failure("JSON格式錯誤").toJSON());
        }
    }
}
