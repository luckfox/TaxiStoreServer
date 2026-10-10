package com.taxistore.service.impl;

import com.taxistore.dao.impl.FineImageDao;
import com.taxistore.pojo.FineImage;
import com.taxistore.pojo.Result;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


/**
 * FineImageService 負責處理 FineImage 資料的業務邏輯。
 */
public class FineImageService extends AbstractImageService<FineImage> {
    public FineImageService() {
        super(new FineImageDao());
    }

    @Override
    protected Long getImageId(FineImage image) {
        return image.getImageId();
    }

    @Override
    protected void setImageId(FineImage image, Long id) {
        image.setImageId(id);
    }

    @Override
    protected String getHashCode(FineImage image) {
        return image.getHashCode();
    }

    @Override
    protected void setHashCode(FineImage image, String hash) {
        image.setHashCode(hash);
    }

    @Override
    protected String getImagePath(FineImage image) {
        return image.getImagePath();
    }

    @Override
    protected String getThumbPath(FineImage image) {
        return image.getThumbPath();
    }

    @Override
    protected void setThumbPath(FineImage image, String thumbPath) {
        image.setThumbPath(thumbPath);
    }

    @Override
    protected void setCreateDate(FineImage image, Timestamp timestamp) {
        image.setCreateDate(timestamp);
    }

    @Override
    protected Result<FineImage> getEntityByHash(FineImage image) {
        return ((FineImageDao) dao).getImageMetasByFineNoAndHashCode(image);
    }

    @Override
    protected Result<List<FineImage>> getImagesByKey(Object key) {
        return ((FineImageDao) dao).getImagesMetasByFineNo((String) key);
    }

    public Result<FineImage> getImageMetasByImageId(FineImage fineImage) {
        return ((FineImageDao) dao).getImageMetasByImageId(fineImage);
    }


    public boolean checkImageExistsByHash(String fineNo, int hashCode) {
        List<FineImage> images = ((FineImageDao) dao).getImagesMetasByFineNo(fineNo).getData();
        if (images == null || images.isEmpty()) return false;

        for (FineImage img : images) {
            if (Objects.equals(img.getHashCode(), hashCode)) {
                return true;
            }
        }
        return false;
    }
}
