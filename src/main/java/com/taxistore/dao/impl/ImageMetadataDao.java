package com.taxistore.dao.impl;


import com.taxistore.config.DatabaseManager;
import com.taxistore.dao.IImageMetadataDao;
import com.taxistore.pojo.ImageMeta;
import com.taxistore.pojo.ImageMetadata;
import com.taxistore.pojo.Result;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageMetadataDao extends AbstractFormDao<ImageMeta> implements IImageMetadataDao {

    private static final String TABLE_NAME = "imagemetadata";
    private static final String KEY_NAME = "imageId";

    public ImageMetadataDao() {
        super(ImageMeta.class, TABLE_NAME,KEY_NAME);
    }
    @Override
    public Result<List<ImageMetadata>> findAll()
    {
        List<ImageMetadata> list = new ArrayList<>();
        String sql = "SELECT * FROM ImageMetadata ORDER BY createTime DESC";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ImageMetadata meta = new ImageMetadata();
                meta.setImageId(rs.getLong("imageId"));
                meta.setHashCode(rs.getString("hashCode"));
                meta.setCloudinaryPublicId(rs.getString("cloudinaryPublicId"));
                meta.setCloudinarySecureUrl(rs.getString("cloudinarySecureUrl"));
                meta.setCreateTime(rs.getTimestamp("createTime"));
                meta.setBytes(rs.getLong("bytes"));
                list.add(meta);
            }
            return Result.success(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result.failure("error");
    }
    @Override
    public void deleteByImageId(Long imageId) {
        String sql = "DELETE FROM ImageMetadata WHERE imageId = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, imageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Result<ImageMetadata> findByHash(String hashCode) {


        String sql = "SELECT * FROM ImageMetadata WHERE hashCode = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hashCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ImageMetadata meta = new ImageMetadata();
                meta.setImageId(rs.getLong("imageId"));
                meta.setHashCode(rs.getString("hashCode"));
                meta.setCloudinaryPublicId(rs.getString("cloudinaryPublicId"));
                meta.setCloudinarySecureUrl(rs.getString("cloudinarySecureUrl"));
                meta.setCreateTime(rs.getTimestamp("createTime"));
                return Result.success(meta);
            }
            return Result.success(null);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Result.failure("error");
    }

    @Override
    public void insert(ImageMetadata metadata) {
        String sql = "INSERT INTO ImageMetadata (hashCode, cloudinaryPublicId, cloudinarySecureUrl,bytes) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, metadata.getHashCode());
            ps.setString(2, metadata.getCloudinaryPublicId());
            ps.setString(3, metadata.getCloudinarySecureUrl());
            ps.setLong(4,metadata.getBytes());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
