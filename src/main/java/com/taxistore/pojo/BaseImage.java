package com.taxistore.pojo;
import java.sql.Timestamp;

/**
 * BaseImage - 所有圖片的共同祖先，包含共通屬性。
 */
public abstract class BaseImage {
    protected Long imageId;
    protected String imagePath;
    protected String thumbPath;
    protected Timestamp createDate;
    protected String hashCode;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public String toString() {
        return "BaseImage{" +
                "imageId=" + imageId +
                ", imagePath='" + imagePath + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", createDate=" + createDate +
                ", hashCode='" + hashCode + '\'' +
                '}';
    }
}
