package com.taxistore.service.impl;

import com.taxistore.dao.impl.CarImageDao;
import com.taxistore.pojo.CarImage;
import com.taxistore.pojo.Result;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class CarImageService  extends AbstractImageService<CarImage>
{
    public CarImageService()
    {
        super(new CarImageDao());
    }

    @Override
    protected Long getImageId(CarImage image) {
        return image.getImageId();
    }

    @Override
    protected void setImageId(CarImage image, Long id) {
        image.setImageId(id);
    }

    @Override
    protected String getHashCode(CarImage image) {
        return image.getHashCode();
    }

    @Override
    protected void setHashCode(CarImage image, String hash) {
        image.setHashCode(hash);
    }

    @Override
    protected String getImagePath(CarImage image) {

        return image.getImagePath();
    }


    @Override
    protected String getThumbPath(CarImage image) {
        return image.getThumbPath();
    }

    @Override
    protected void setThumbPath(CarImage image, String thumbPath) {
        image.setThumbPath(thumbPath);
    }

    @Override
    protected void setCreateDate(CarImage image, Timestamp timestamp) {
        image.setCreateDate(timestamp);
    }

    @Override
    protected Result<CarImage> getEntityByHash(CarImage image) {
        return ((CarImageDao) dao).getImageMetasByDriverSeqAndHashCode(image);
    }



    @Override
    protected Result<List<CarImage>> getImagesByKey(Object key) {
        return ((CarImageDao) dao).getImagesMetasByDriverSeq((Long) key);
    }

    public Result<CarImage> getImageMetasByImageId(CarImage image) {
        return ((CarImageDao) dao).getImageMetasByImageId(image);
    }


    public boolean checkImageExistsByHash(Long driverSeq, int hashCode) {
        List<CarImage> images = ((CarImageDao) dao).getImagesMetasByDriverSeq(driverSeq).getData();
        if (images == null || images.isEmpty()) return false;

        for (CarImage img : images) {
            if (Objects.equals(img.getHashCode(), hashCode)) {
                return true;
            }
        }
        return false;
    }


}
