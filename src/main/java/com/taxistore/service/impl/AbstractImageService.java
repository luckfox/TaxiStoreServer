package com.taxistore.service.impl;

import com.taxistore.dao.impl.AbstractImageDao;
import com.taxistore.pojo.BaseImage;
import com.taxistore.pojo.Result;
import com.utils.FastThumbGenerator;
import com.utils.ImageRotator;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static com.utils.Sha256Hex.ComputeSha256Hex;

/**
 * 通用圖片服務基底類別，適用於 FineImage 與 DriverImage
 * 負責 hash、縮圖、刪檔、儲存等共用邏輯
 */
public abstract class AbstractImageService<T extends BaseImage> {

    protected final AbstractImageDao<T> dao;

    public AbstractImageService(AbstractImageDao<T> dao) {
        this.dao = dao;
    }

    /**
     * 根據主鍵 ID 查詢單筆資料
     */
    public Result<T> getImageMetasByImageId(T image) {
        return dao.getEntity(getImageId(image));
    }

    /**
     * 依據所有者欄位（如 fineNo / driverSeq）查多筆資料
     */
    public Result<List<T>> getImageMetasByOwner(Object ownerValue) {
        return dao.getImagesByOwner(ownerValue);
    }

    /**
     * 依據所有者 + hash 查單筆資料
     */
    public Result<T> getImageMetaByOwnerAndHash(T image) {
        return dao.getImageByOwnerAndHash(image);
    }

    /**
     * 刪除圖片（含檔案實體）
     */
    public Result<Integer> deleteImageByImageId(T image) {
        Long id = getImageId(image);
        Result<T> old = dao.getEntity(id);
        if (!old.isSuccess()) return Result.failure("參數錯誤。");
        Result<Integer> deleted = dao.deleteEntity(id);
        if (!deleted.isSuccess()) return Result.failure("資料庫處理錯誤。\n" + deleted.getErrorMessage());

        deleteFile(getImagePath(old.getData()));
        deleteFile(getThumbPath(old.getData()));
        return Result.success(1);
    }
    public Result<T> RotationImageByImageId(T image,int angle) {
        Long id = getImageId(image);
        Result<T> meta = dao.getEntity(id);

        T data=meta.getData();
        String imagePath=getImagePath(data);
        String imageThumbPath=getThumbPath(data);
        if(!meta.isSuccess()||meta.getData()==null) return Result.failure("旋轉後更新資料庫失敗：取得meta失敗");
        try {
            //step 1: 旋轉原圖與縮圖
            ImageRotator.rotateImage(imagePath, angle);
            ImageRotator.rotateImage(imageThumbPath, angle);
            // Step 2: 重新計算 hash（針對旋轉後的檔案）
            File imageFile = new File(imagePath);
            String newHash = ComputeSha256Hex(imageFile);
            setHashCode(data, newHash);
            // Step 3: 更新建立時間
            setCreateDate(data, new java.sql.Timestamp(System.currentTimeMillis()));
            // Step 4: 更新資料庫
            Result<Integer> updateResult = dao.updateEntity(data);
            if(!updateResult.isSuccess()) return Result.failure("旋轉後更新資料庫失敗：" + updateResult.getErrorMessage());
        } catch (Exception e) {
            return Result.failure("旋轉後更新資料庫失敗：" + e.getMessage());
        }
        return Result.success(data);
    }
    /**
     * 更新或新增圖片資料，並處理縮圖與 hash
     */
    public Result<T> updateImage(T image, String thumbFolder, File file) {
        Result<T> existing = dao.getImageByOwnerAndHash(image);
        if (existing.isSuccess() && existing.getData() != null) {
            setImageId(image, getImageId(existing.getData()));
            Result<Integer> updated = dao.updateEntity(image);
            return updated.isSuccess() ? Result.success(image) : Result.failure("fail to update image。");
        } else {
            try {
                String hashHex = ComputeSha256Hex(file);
                setHashCode(image, hashHex);
                String thumbPath = FastThumbGenerator.generateThumb(file.getAbsolutePath(), thumbFolder, 20, 200, 200);
                setThumbPath(image, thumbPath);
                setCreateDate(image, new java.sql.Timestamp(System.currentTimeMillis()));
                Result<Long> insert = dao.insertEntity(image);
                setImageId(image, insert.getData());
                return Result.success(image);
            } catch (Exception e) {
                return Result.failure("fail to add image: " + e.getMessage());
            }
        }
    }

    public Result<Long> saveImage(T image, File file) {
        try {
            String hashHex = ComputeSha256Hex(file);
            setHashCode(image, hashHex);
            Result<Long> insert = dao.insertEntity(image);
            return insert.isSuccess() ? insert : Result.failure("failed to save file: " + insert.getErrorMessage());
        } catch (Exception e) {
            return Result.failure("圖片儲存或 hash 計算失敗：" + e.getMessage());
        }
    }

    public boolean checkImageExistsByHash(Object owner, int hashCode) {
        Result<List<T>> result = dao.getImagesByOwner(owner);
        if (!result.isSuccess()) return false;
        List<T> list = result.getData();
        return list.stream().anyMatch(img -> Objects.equals(getHashCode(img), hashCode));
    }

    protected void deleteFile(String path) {
        if (path == null || path.isEmpty()) return;
        File f = new File(path);
        if (f.exists()) f.delete();
    }

    protected abstract Long getImageId(T image);
    protected abstract void setImageId(T image, Long id);
    protected abstract String getImagePath(T image);
    protected abstract String getThumbPath(T image);
    protected abstract void setThumbPath(T image, String path);
    protected abstract void setCreateDate(T image, java.sql.Timestamp timestamp);
    protected abstract void setHashCode(T image, String hash);
    protected abstract Object getHashCode(T image);
    protected abstract Result<List<T>> getImagesByKey(Object key);
    protected abstract Result<T> getEntityByHash(T image);
//
//    protected abstract T getImageMetaById(Long imageId);
//    protected abstract String resolvePath(T imageMeta, boolean isThumb);

}
