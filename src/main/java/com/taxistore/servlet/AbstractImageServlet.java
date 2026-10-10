package com.taxistore.servlet;

import com.taxistore.pojo.BaseImage;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.AbstractImageService;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.List;

/**
 * 超通用圖片 Servlet 抽象類別，支援基本 CRUD 與圖片存取。
 * 所有 FineImageServlet / DriverImageServlet / CarImageServlet 都能繼承它。
 */
@MultipartConfig
public abstract class AbstractImageServlet<T extends BaseImage> extends BaseServlet {

    protected abstract AbstractImageService<T> getService();
    protected abstract File getImageDir(String ownerKey);
    protected abstract File getThumbDir(String ownerKey);
    protected abstract T createImageEntity();
    protected abstract void setOwnerKey(T image, String ownerKey);

    protected void ajaxGetImageMetasByOwner(HttpServletRequest request, HttpServletResponse response, String paramName) throws IOException {
        String ownerKey = request.getParameter(paramName);
        if (ownerKey == null) {
            response.getWriter().write(Result.failure("parameters error").toJSON());
            return;
        }
        Result<List<T>> result = getService().getImageMetasByOwner(ownerKey);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxGetImageMetaByOwnerAndHash(HttpServletRequest request, HttpServletResponse response, String paramName) throws IOException {
        String ownerKey = request.getParameter(paramName);
        String hashCode = request.getParameter("hash");
        if (ownerKey == null || hashCode == null) {
            response.getWriter().write(Result.failure("parameters error").toJSON());
            return;
        }
        T image = createImageEntity();
        setOwnerKey(image, ownerKey);
        image.setHashCode(hashCode);
        Result<T> result = getService().getImageMetaByOwnerAndHash(image);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxDeleteImageByImageId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long imageId = WebUtils.stringToLong(request.getParameter("ImageId"));
        T image = createImageEntity();
        image.setImageId(imageId);
        Result<Integer> result = getService().deleteImageByImageId(image);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxRotationImageByImageId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long imageId = WebUtils.stringToLong(request.getParameter("ImageId"));
        int angle = WebUtils.stringToInt(request.getParameter("angle"));
        if (imageId == -1L || angle == -1) {
            response.getWriter().write(Result.failure("parameter error").toJSON());
            return;
        }
        T image = createImageEntity();
        image.setImageId(imageId);
        Result<T> result = getService().RotationImageByImageId(image,angle);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxGetImageByImageId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imageIdStr = request.getParameter("ImageId");
        String thumbStr = request.getParameter("Thumb");

        if (imageIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ImageId 未提供！");
            return;
        }

        T image = createImageEntity();
        image.setImageId(Long.parseLong(imageIdStr));
        boolean isThumb = "true".equalsIgnoreCase(thumbStr);

        Result<T> result = getService().getImageMetasByImageId(image);

        if (!result.isSuccess() || result.getData() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到該圖片的 meta 資料！");
            return;
        }

        image = result.getData();
        String path = isThumb ? image.getThumbPath() : image.getImagePath();
        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "圖片檔案不存在！");
            return;
        }

        String contentType = getServletContext().getMimeType(file.getName());
        response.setContentType(contentType != null ? contentType : "application/octet-stream");
        response.setContentLengthLong(file.length());

        try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "伺服器處理錯誤！");
        }
    }

    protected void ajaxUploadImage(HttpServletRequest request, HttpServletResponse response, String paramName) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String ownerKey = null;
        //String ownerKey = request.getParameter(paramName);
        //Part filePart = request.getPart("file");

        // ✅ 取得非檔案欄位
        Part paramPart = request.getPart(paramName);
        if (paramPart != null) {
            try (Scanner scanner = new Scanner(paramPart.getInputStream(), "UTF-8")) {
                ownerKey = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            }
        }

        // ✅ 取得檔案欄位
        Part filePart = request.getPart("file");

        if (filePart == null || ownerKey == null || ownerKey.isEmpty()) {
            response.getWriter().write(Result.failure("檔案或參數缺少").toJSON());
            return;
        }
        Result<T> result = handleUploadImage(ownerKey, filePart);
        response.getWriter().write(result.toJSON());
    }

    private String randomFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String randomSuffix = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        return timeStamp + "_" + randomSuffix + ".jpg";
    }

    private Result<T> handleUploadImage(String ownerKey, Part filePart) {
        String errMsg="";
        String submitFilename = filePart.getSubmittedFileName();
        String fileName = (submitFilename == null) ? randomFileName() : submitFilename;

        File imageDir = getImageDir(ownerKey);
        File thumbDir = getThumbDir(ownerKey);
        if (!imageDir.exists()) imageDir.mkdirs();
        if (!thumbDir.exists()) thumbDir.mkdirs();

        File file = new File(imageDir, fileName);
        if (file.exists()) {
            errMsg=fileName+"已經存在";
            Log.debug(errMsg);
            return Result.failure(errMsg);
        }

        try {
            filePart.write(file.getAbsolutePath());
            Log.debug("File saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        T image = createImageEntity();
        setOwnerKey(image, ownerKey);
        image.setImagePath(file.getAbsolutePath());

        return getService().updateImage(image, thumbDir.getAbsolutePath(), file);
    }
}
