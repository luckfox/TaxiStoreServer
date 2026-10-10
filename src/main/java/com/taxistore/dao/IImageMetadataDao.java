package com.taxistore.dao;

import com.taxistore.pojo.ImageMetadata;
import com.taxistore.pojo.Result;

import java.util.List;

public interface IImageMetadataDao {
    public Result<ImageMetadata> findByHash(String hashCode);
    public Result<List<ImageMetadata>> findAll();
    public void deleteByImageId(Long imageId);
    public void insert(ImageMetadata metadata);
}