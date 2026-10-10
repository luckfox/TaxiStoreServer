package com.taxistore.service.impl;

import com.taxistore.dao.impl.FineImageDao;
import com.taxistore.pojo.FineImage;
import com.taxistore.pojo.Result;
import com.utils.FastThumbGenerator;
import com.utils.Log;
import com.utils.PropertiesUtil;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static com.utils.Sha256Hex.ComputeSha256Hex;

public class _FineImageService
{
    /*
    罰單完整路徑 d:/FineImage/image/FN12345
    相關工具程式
    1.com.tools/FineImageHashUpdater：對FineImage的罰單產生HashCode
    2.com.tools/FastThumbGenerator：產生縮圖。
 */
    private static final String APPLICATION_PROPERTIES_FILE="application.properties";
    static {
        Properties applicationProperties = PropertiesUtil.load(APPLICATION_PROPERTIES_FILE);
        String FineImagePath = applicationProperties.getProperty("fineiamge.image");
        String FineThumbPath = applicationProperties.getProperty("fineimage.thumb");
    }

    //private static String FineImagePath="d:/FineImage/image/";
    //private static String FineThumbPath="d:/FineImage/thumb/";
    FineImageDao fineImageDao=new FineImageDao();
    /* 取得某張罰單號碼下的所有罰單照片的meta資料。
     */
    public Result<FineImage> getImageMetasByImageId(FineImage fineImage)
    {
        return fineImageDao.getImageMetasByImageId(fineImage);
    }
    public Result<List<FineImage>> getFineImageMetasByFineNo(String fineNo)
    {
        return fineImageDao.getImagesMetasByFineNo(fineNo);
    }
    public Result<FineImage> getFineImageMetaByFineNoAndHashCode(FineImage fineImage)
    {
        return fineImageDao.getImageMetasByFineNoAndHashCode(fineImage);
    }
    public Result<Integer> deleteFineImageByImageId(FineImage fineImage)
    {
        Result<FineImage> result0=fineImageDao.getEntity(fineImage.getImageId());
        if(!result0.isSuccess())  return Result.failure("參數錯誤。");
        Result<Integer>result= fineImageDao.deleteEntity(fineImage.getImageId());
        if(!result.isSuccess())  return Result.failure("資料庫處理錯誤。");
        FineImage fineImage1=result0.getData();
        RemoveImage(fineImage1.getImagePath());//刪除Image的實體圖檔
        RemoveImage(fineImage1.getThumbPath());//刪除Thumb的實體圖檔。
        return Result.success(1);
    }
    /*
     * 新增或更新 FineImage 資料。
     * <p>
     * 此方法會先透過 fineNo 與 hashCode 判斷該筆資料是否已存在：
     * <ul>
     *     <li>若已存在，則進行更新並回傳更新後的 imageId。</li>
     *     <li>若不存在，則執行新增並回傳新資料的 imageId。</li>
     * </ul>
     *
     * @param fineImage 傳入的 FineImage 實例，需包含 fineNo 與 hashCode 等關鍵欄位。
     * @return 包含 imageId 的 Result。若新增或更新失敗，回傳失敗結果。
     */
    public Result<FineImage> updateFineImage(FineImage fineImage,String thumbFolder,File file) {
        // 先查詢是否已存在相同 fineNo 與 hashCode 的資料
        Result<FineImage> existingResult = fineImageDao.getImageMetasByFineNoAndHashCode(fineImage);

        if (existingResult.isSuccess() && existingResult.getData() != null) {
            // 資料已存在，進行更新
            fineImage.setImageId(existingResult.getData().getImageId());
            Result<Integer> updateResult = fineImageDao.updateEntity(fineImage);

            if (updateResult.isSuccess() && updateResult.getData() != null)
            {

                //回傳imageID
                return Result.success(fineImage);
            } else {
                return Result.failure("fail to update image。");
            }

        } else {
            // 資料不存在，執行新增
            try {
                // 1. 計算 SHA-256 hash（hex string）
                String hashHex = ComputeSha256Hex(file);
                // 2. 寫入資料庫
                fineImage.setHashCode(hashHex);
                // 3. 產生縮圖
                fineImage.setThumbPath(FastThumbGenerator.generateThumb(file.getAbsolutePath(), thumbFolder, 20, 200, 200));
                // 4. 設置時間
                fineImage.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
                // 5. 寫回資料庫
                Result<Long>r=fineImageDao.insertEntity(fineImage);
                fineImage.setImageId(r.getData());
                return Result.success(fineImage);
            }catch (Exception e) {
                return Result.failure("fail to add image");
            }
        }

    }
    /*
     * 儲存罰單圖片並計算其 SHA-256 Hash 字串，並且回傳ImageId。
     *
     * @param image.fineNo   罰單編號
     * @param image.filePath 實際圖片儲存路徑（建議包含完整路徑）
     * @param file     實際圖片檔案（由前端傳入）
     * @return 回傳圖片的 ImageId
     */
    public Result<Long> saveImage(FineImage image, File file) {
        try {


            // 1. 計算 SHA-256 hash（hex string）

            String hashHex =ComputeSha256Hex(file);
            // 2. 寫入資料庫
            image.setHashCode(hashHex);

            Result<Long> insertResult = fineImageDao.insertEntity(image);
            if (!insertResult.isSuccess()) {
                return Result.failure("failed to save file" + insertResult.getErrorMessage());
            }

            // 3. 成功後回傳 hash 給前端
            return insertResult;

        } catch (Exception e) {
            Log.error("儲存圖片並計算 hash 發生錯誤");
            return Result.failure("圖片儲存或 hash 計算失敗：" + e.getMessage());
        }
    }

    /*
     * 根據罰單編號與圖片 hashCode 判斷是否已有相同圖片存在。
     */
    public boolean checkImageExistsByHash(String fineNo, int hashCode)
    {
        List<FineImage> images = fineImageDao.getImagesMetasByFineNo(fineNo).getData();
        if (images == null || images.isEmpty()) return false;

        for (FineImage img : images)
        {
            if (Objects.equals(img.getHashCode(), hashCode))
            {
                return true; // 找到相同 hashCode
            }
        }
        return false;
    }

    /**
     * 刪除指定路徑的圖片檔案。
     *
     * @param path 檔案完整路徑
     */
    private void RemoveImage(String path) {
        if (path == null || path.isEmpty()) {
            Log.debug("Path is empty");
            return;
        }

        File file = new File(path);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                Log.debug("removed file successly：" + path);
            } else {
                Log.debug("failed to remove file：" + path);
            }
        } else {
            Log.debug("can't find file：" + path);
        }
    }

    //==========================================================================================================
    public static void main(String[] args)
    {

    }
}
===================
    public static void main(String[] args)
    {

    }
}
