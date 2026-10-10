package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE ImageMetadata (
    imageId BIGINT AUTO_INCREMENT PRIMARY KEY,
    hashCode VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
    cloudinaryPublicId VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    cloudinarySecureUrl VARCHAR(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 */
/**
 * ImageMetadata POJO - 儲存 Cloudinary 圖像上傳資料
 */
public class ImageMetadata {
    private Long imageId;
    private String hashCode;
    private String cloudinaryPublicId;
    private String cloudinarySecureUrl;
    private Timestamp createTime;
    private Long bytes;

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getCloudinaryPublicId() {
        return cloudinaryPublicId;
    }

    public void setCloudinaryPublicId(String cloudinaryPublicId) {
        this.cloudinaryPublicId = cloudinaryPublicId;
    }

    public String getCloudinarySecureUrl() {
        return cloudinarySecureUrl;
    }

    public void setCloudinarySecureUrl(String cloudinarySecureUrl) {
        this.cloudinarySecureUrl = cloudinarySecureUrl;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ImageMetadata{" +
                "imageId=" + imageId +
                ", hashCode='" + hashCode + '\'' +
                ", cloudinaryPublicId='" + cloudinaryPublicId + '\'' +
                ", cloudinarySecureUrl='" + cloudinarySecureUrl + '\'' +
                ", createTime=" + createTime +
                ", bytes=" + bytes +
                '}';
    }
}
