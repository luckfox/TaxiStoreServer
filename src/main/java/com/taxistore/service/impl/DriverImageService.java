package com.taxistore.service.impl;

import com.taxistore.dao.impl.DriverImageDao;
import com.taxistore.pojo.DriverImage;
import com.taxistore.pojo.Result;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


public class DriverImageService  extends AbstractImageService<DriverImage>
{
    public DriverImageService()
    {
        super(new DriverImageDao());
    }

    @Override
    protected Long getImageId(DriverImage image) {
        return image.getImageId();
    }

    @Override
    protected void setImageId(DriverImage image, Long id) {
        image.setImageId(id);
    }

    @Override
    protected String getHashCode(DriverImage image) {
        return image.getHashCode();
    }

    @Override
    protected void setHashCode(DriverImage image, String hash) {
        image.setHashCode(hash);
    }

    @Override
    protected String getImagePath(DriverImage image) {


        return image.getImagePath();
    }


    @Override
    protected String getThumbPath(DriverImage image) {
        return image.getThumbPath();
    }

    @Override
    protected void setThumbPath(DriverImage image, String thumbPath) {
        image.setThumbPath(thumbPath);
    }

    @Override
    protected void setCreateDate(DriverImage image, Timestamp timestamp) {
        image.setCreateDate(timestamp);
    }

    @Override
    protected Result<DriverImage> getEntityByHash(DriverImage image) {
        return ((DriverImageDao) dao).getImageMetasByDriverSeqAndHashCode(image);
    }



    @Override
    protected Result<List<DriverImage>> getImagesByKey(Object key) {
        return ((DriverImageDao) dao).getImagesMetasByDriverSeq((Long) key);
    }

    public Result<DriverImage> getImageMetasByImageId(DriverImage image) {
        return ((DriverImageDao) dao).getImageMetasByImageId(image);
    }


    public boolean checkImageExistsByHash(Long driverSeq, int hashCode) {
        List<DriverImage> images = ((DriverImageDao) dao).getImagesMetasByDriverSeq(driverSeq).getData();
        if (images == null || images.isEmpty()) return false;

        for (DriverImage img : images) {
            if (Objects.equals(img.getHashCode(), hashCode)) {
                return true;
            }
        }
        return false;
    }
}
