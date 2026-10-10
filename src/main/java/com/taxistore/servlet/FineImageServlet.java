package com.taxistore.servlet;


import com.taxistore.pojo.FineImage;
import com.taxistore.service.impl.FineImageService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 處理罰單圖片的 Servlet
 */
@MultipartConfig
@WebServlet("/fineImageServlet")
public class FineImageServlet extends AbstractImageServlet<FineImage> {

    final private static String FineImagePath="d:/FineImage/image/";
    final private static String FineThumbPath="d:/FineImage/thumb/";
    final private static String OwnerKey="fineNo";

    private final FineImageService service = new FineImageService();

    @Override
    protected FineImageService getService() {
        return service;
    }

    @Override
    protected File getImageDir(String fineNo) {
        return new File(FineImagePath + fineNo);
    }

    @Override
    protected File getThumbDir(String fineNo) {
        return new File(FineThumbPath + fineNo );
    }

    @Override
    protected FineImage createImageEntity() {
        return new FineImage();
    }

    @Override
    protected void setOwnerKey(FineImage image, String fineNo) {
        image.setFineNo(fineNo);
    }
    //======================================================================================================================
    protected void ajaxGetImageMetasByFineNo(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        ajaxGetImageMetasByOwner(request, response, OwnerKey);
    }
    protected void ajaxGetImageMetaByFineNoAndHashCode(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ajaxGetImageMetaByOwnerAndHash(req, res, OwnerKey);
    }
    protected void ajaxGetImageByImageId(HttpServletRequest req, HttpServletResponse res) throws  IOException {
        super.ajaxGetImageByImageId(req, res);
    }
    protected void ajaxUploadFineImage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ajaxUploadImage(req, res, OwnerKey);
    }
    protected void ajaxDeleteImageByImageId(HttpServletRequest req, HttpServletResponse res) throws IOException {
        super.ajaxDeleteImageByImageId(req, res);
    }
    protected void ajaxRotationFineImageByImageId(HttpServletRequest req, HttpServletResponse res) throws Exception {
        super.ajaxRotationImageByImageId(req, res);
    }
    protected void ajaxUploadDriverImage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ajaxUploadImage(req, res, OwnerKey);
    }
}


/*
import com.taxistore.pojo.DriverImage;
import com.taxistore.pojo.FineImage;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.FineImageService;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@MultipartConfig // ⬅⬅⬅ 這一行是關鍵！！
@WebServlet("/fineImageServlet")
public class FineImageServlet  extends BaseServlet
{
    // *
    //    罰單完整路徑 d:/FineImage/image/FN12345
    // *
    final private static String FineImagePath="d:/FineImage/image/";
    final private static String FineThumbPath="d:/FineImage/thumb/";
    FineImageService service=new FineImageService();

    // *
    // *  透過fineNo取得所有圖片的Meta，前端可以根據裡面的hashCode資料判斷圖片是否已經存在Server
    // *
    protected void ajaxGetImageMetasByFineNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fineNo = request.getParameter("fineNo");
        if(fineNo==null )
        {
            response.getWriter().write(Result.failure("paramters error").toJSON());
            return ;
        }
        Result<List<FineImage>> result=service.getImageMetasByOwner(fineNo);
        Log.debug(result.toJSON());
        response.getWriter().write(result.toJSON());
    }
    // *
    // *   透過HashCode和fineNo取得某張罰單圖片的Meta
    // *
    protected void ajaxGetImageMetaByFineNoAndHashCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        FineImage fineImage=new FineImage();
        String fineNo = request.getParameter("fineNo");
        String hashCode = request.getParameter("hash");
        if(fineNo==null || hashCode==null)
        {
            response.getWriter().write(Result.failure("paramters error").toJSON());
            return;
        }
        fineImage.setFineNo(fineNo);
        fineImage.setHashCode(hashCode);
        Result<FineImage> result = service.getImageMetaByOwnerAndHash(fineImage);
        if (!result.isSuccess()) {
            response.getWriter().write(Result.failure("fail").toJSON());
        }
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxDeleteImageByImageId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Long ImageId=WebUtils.stringToLong(request.getParameter("ImageId"));
        FineImage fineImage=new FineImage();
        fineImage.setImageId(ImageId);
        Result<Integer> result=service.deleteImageByImageId(fineImage);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxRotationFineImageByImageId(HttpServletRequest request, HttpServletResponse response) throws  IOException
    {
        Long imageId= WebUtils.stringToLong(request.getParameter("ImageId"));
        if (imageId == -1L) {
            response.getWriter().write(Result.failure("parameter error").toJSON());
            return;
        }
        FineImage fineImage=new FineImage();
        fineImage.setImageId(imageId);
        Result<FineImage> result=service.RotationImageByImageId(fineImage);
        response.getWriter().write(result.toJSON());
    }
    // *
    //    前端以ImageId，
    //  *
    protected void ajaxGetImageByImageId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ImageId_Str = request.getParameter("ImageId");
        String Thumb_str  =  request.getParameter("Thumb");
        FineImage fineImage=new FineImage();
        //step1.透過service取得ImageId的FineImage的meta資料(service.getImageMetasByImageId(fineImage)。
        //step2.根據取得的FineImage 結構，取得檔案路徑位置(如果type為thumb，則取FineImage.thumbPath，否則取FineImage.imagePath
        //step3.傳回前端
        if (ImageId_Str == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ImageId 未提供！");
            return;
        }
        Log.debug("ImageId:"+ImageId_Str+" Thumb:"+Thumb_str);
        fineImage.setImageId(Long.parseLong(ImageId_Str));
        boolean isThumb = "true".equalsIgnoreCase(Thumb_str);

        // Step 1: 透過 service 取得該 imageId 的 FineImage metadata
        Result<FineImage> result = service.getImageMetasByImageId(fineImage);

        if (!result.isSuccess() || result.getData() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到該圖片的 meta 資料！");
            return;
        }

        fineImage = result.getData();

        // Step 2: 根據是否縮圖，取得對應路徑
        String path = isThumb ? fineImage.getThumbPath() : fineImage.getImagePath();
        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "圖片檔案不存在！");
            return;
        }

        // Step 3: 設定檔案類型並回傳檔案給前端
        String contentType = getServletContext().getMimeType(file.getName());
        response.setContentType(contentType != null ? contentType : "application/octet-stream");
        response.setContentLengthLong(file.length());

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream())
        {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1)
            {
                os.write(buffer, 0, bytesRead);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "伺服器處理錯誤！");
        }
    }

    // *
    //    接受來自前端上傳的圖片。
    // *
    protected void ajaxUploadFineImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 罰單號碼
        String fineNo = request.getParameter("fineNo");
        // 罰單
        Part filePart = request.getPart("file");

        Result<FineImage> result=handleUploadFineImage(fineNo,filePart);
        response.getWriter().write(result.toJSON());

    }
    //===================================================================================================================
    private void handleGetImageByHash(FineImage fineImage){

    }
    private String randomFileName()
    {
        // 時間格式 yyyyMMdd_HHmmss，例如 20250531_235959
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // 取 UUID 的前 6 碼當作隨機碼
        String randomSuffix = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

        return timeStamp + "_" + randomSuffix + ".jpg";
    }
    // **
    // * 處理單一罰單圖片的上傳邏輯。
    // * <p>
    // * 主要流程如下：
    // * <ul>
    // *     <li>判斷上傳檔案是否已存在</li>
    // *     <li>若已存在，計算與現有檔案的 hash 值比對</li>
    // *     <li>若 hash 相同，略過寫入；若不同，將原始檔案改名備份後寫入新檔</li>
    // *     <li>無論檔案是否重寫，皆會呼叫 service 儲存 metadata 並產生縮圖</li>
    // * </ul>
    // *
    // * @param fineNo   罰單編號（作為儲存目錄名稱）
    // * @param filePart 使用者上傳的圖片檔案（透過 multipart 表單上傳）
    // * @return 成功時回傳包含 FineImage 主鍵的 Result<Long>
    // * @throws RuntimeException 若檔案寫入或搬移失敗
    // *
    private Result<FineImage> handleUploadFineImage(String fineNo, Part filePart) {
        // 嘗試取得上傳的原始檔名，若無則隨機產生
        String submitFilename = filePart.getSubmittedFileName();
        String fileName = (submitFilename == null) ? randomFileName() : submitFilename;

        // 準備圖片與縮圖的存放目錄
        File imageDir = new File(FineImagePath + fineNo);
        File thumbDir = new File(FineThumbPath + fineNo);
        if (!imageDir.exists()) imageDir.mkdirs();
        if (!thumbDir.exists()) thumbDir.mkdirs();

        // 產生圖片的最終儲存檔案路徑
        File file = new File(imageDir, fileName);
        if (file.exists())
        {
            // 如果檔案已存在，需比對 hash 判斷是否相同
            Log.debug(file.getAbsolutePath() + " already exists. Checking hash...");
            return Result.failure("fail.file is exists.");

        }
        try {
            // 檔案不存在，正常寫入
            filePart.write(file.getAbsolutePath());
            Log.debug("File saved: " + file.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 建立 FineImage 資料物件，儲存 metadata 並觸發縮圖產生
        FineImage fineImage = new FineImage();
        fineImage.setFineNo(fineNo);
        fineImage.setImagePath(file.getAbsolutePath());

        return service.updateImage(fineImage, thumbDir.getAbsolutePath(), file);
    }

    private Result<FineImage> handleUploadFineImage2(String fineNo,Part filePart)
    {
        // 取得原始的檔案名稱。
        String submitFilename=filePart.getSubmittedFileName();
        String fileName = submitFilename==null?randomFileName():submitFilename;

        // 設置儲存圖片檔案
        File imageDir = new File(FineImagePath + fineNo);
        File thumbDir = new File(FineThumbPath + fineNo);
        if (!imageDir.exists()) imageDir.mkdirs();
        if (!thumbDir.exists()) thumbDir.mkdirs();
        // 由path和檔名產生File handle
        File file = new File(imageDir, fileName);
        // 將檔案存到File handle
        try
        {
            if (file.exists())
            {
                Log.debug(file.getAbsolutePath() + " already exists. Skipping write.");
            }else
            {
                filePart.write(file.getAbsolutePath());
                Log.debug(file.getAbsolutePath() + " write done!");
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        FineImage fineImage=new FineImage();
        fineImage.setFineNo(fineNo);
        fineImage.setImagePath( file.getAbsolutePath());
        // 呼叫 Service 儲存並計算 hash
        return service.updateImage(fineImage,thumbDir.getAbsolutePath(),file);
    }
}
*/