package com.taxistore.pojo;

/**
 * CloudinaryMetadata
 *
 * 用來封裝從 Cloudinary 上傳後回傳的 metadata 資訊
 * 包含：
 * - hashCode：本地端對檔案計算的 hash
 * - cloudinaryPublicId：Cloudinary 內部的 public ID
 * - cloudinarySecureUrl：Cloudinary 對外可存取的 https URL
 */
public class CloudinaryMetadata {

    private String hashCode;
    private String cloudinaryPublicId;
    private String cloudinarySecureUrl;
    private long bytes;

    public CloudinaryMetadata() {
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
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

    @Override
    public String toString() {
        return "CloudinaryMetadata{" +
                "hashCode='" + hashCode + '\'' +
                ", cloudinaryPublicId='" + cloudinaryPublicId + '\'' +
                ", cloudinarySecureUrl='" + cloudinarySecureUrl + '\'' +
                ", bytes=" + bytes +
                '}';
    }
}
