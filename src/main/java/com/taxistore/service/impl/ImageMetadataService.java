package com.taxistore.service.impl;

import com.taxistore.dao.IImageMetadataDao;
import com.taxistore.dao.impl.ImageMetadataDao;
import com.taxistore.pojo.ImageMetadata;
import com.taxistore.pojo.Result;
import com.taxistore.service.IImageMetadataService;

import java.util.List;

public class ImageMetadataService implements IImageMetadataService {

    private IImageMetadataDao dao = new ImageMetadataDao();

    @Override
    public Result<ImageMetadata> isHashExist(String hashCode) {
        return dao.findByHash(hashCode);
    }

    @Override
    public void saveMetadata(ImageMetadata metadata) {
        dao.insert(metadata);
    }

    @Override
    public Result<List<ImageMetadata>> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteByImageId(Long imageId) {
        dao.deleteByImageId(imageId);
    }
}
