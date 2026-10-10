package com.taxistore.service.impl;



import com.taxistore.dao.impl.FineImageDao;
import com.taxistore.pojo.FineImage;
import com.taxistore.pojo.Result;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class _FineImageService/* extends AbstractImageService<FineImage, FineImageDao> */{

    public _FineImageService() {
       // super(new FineImageDao(), "d:/FineImage/image/", "d:/FineImage/thumb/");
    }
/*
    /** Servlet 舊用法：取得單筆 meta */
//    public Result<FineImage> getImageMetasByImageId(FineImage fineImage) {
//        return dao.getImageMetasByImageId(fineImage);
//    }

    /** Servlet 舊用法：查所有 meta */
//    public Result<List<FineImage>> getFineImageMetasByFineNo(String fineNo) {
//        return dao.getImagesMetasByFineNo(fineNo);
//    }

    /** Servlet 舊用法：查單筆 meta（by hash） */
//    public Result<FineImage> getFineImageMetaByFineNoAndHashCode(FineImage fineImage) {
//        return dao.getImageMetasByFineNoAndHashCode(fineImage);
//    }

    /** Servlet 舊用法：刪除圖片 */
//    public Result<Integer> deleteFineImageByImageId(FineImage fineImage) {
//        return deleteImage(fineImage);
//    }

    /** Servlet 舊用法：新增或更新圖片 */
//    public Result<FineImage> updateFineImage(FineImage fineImage, String thumbFolder, File file) {
//        return updateImage(fineImage, file);
//    }

    /** Servlet 舊用法：儲存圖片與 hash */
//    public Result<Long> saveImage(FineImage image, File file) {
//        return super.saveImage(image, file);
//    }

    /** Servlet 舊用法：hash 判重 */
//    public boolean checkImageExistsByHash(String fineNo, int hashCode) {
//        return super.checkImageExistsByHash(fineNo, hashCode);
//    }

    // 實作抽象方法
//    @Override public String getHashCode(FineImage image) { return image.getHashCode(); }
//    @Override public void setHashCode(FineImage image, String hash) { image.setHashCode(hash); }
//    @Override public void setThumbPath(FineImage image, String thumbPath) { image.setThumbPath(thumbPath); }
//    @Override public void setCreateDateNow(FineImage image) { image.setCreateDate(new Timestamp(System.currentTimeMillis())); }
//    @Override public Long getImageId(FineImage image) { return image.getImageId(); }
//    @Override public void setImageId(FineImage image, Long id) { image.setImageId(id); }
//    @Override public String getImagePath(FineImage image) { return image.getImagePath(); }
//    @Override public String getThumbPath(FineImage image) { return image.getThumbPath(); }
    //@Override public Result<FineImage> getByHash(FineImage image) { return dao.getImageMetasByFineNoAndHashCode(image); }
//    @Override public Result<Long> insert(FineImage image) { return dao.insertEntity(image); }
//    @Override public Result<Integer> update(FineImage image) { return dao.updateEntity(image); }
//    @Override public Result<FineImage> getEntity(Long id) { return dao.getEntity(id); }
//    @Override public Result<Integer> deleteEntity(Long id) { return dao.deleteEntity(id); }
    //@Override public List<FineImage> getImagesByOwner(Object ownerKey) { return dao.getImagesMetasByFineNo((String) ownerKey).getData(); }
}
