package com.taxistore.pojo;

public class ImageMeta {
    private Long imageId;
    private String hashCode;

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

    @Override
    public String
    toString() {
        return "ImageMeta{" +
                "imageId=" + imageId +
                ", hashCode='" + hashCode + '\'' +
                '}';
    }
}