package com.taxistore.service;


import com.taxistore.pojo.ImageMetadata;
import com.taxistore.pojo.Result;

import java.util.List;

public interface IImageMetadataService {
    public Result<ImageMetadata> isHashExist(String hashCode);
    public void saveMetadata(ImageMetadata metadata);
    public Result<List<ImageMetadata>> findAll();
    public void deleteByImageId(Long imageId);
}
