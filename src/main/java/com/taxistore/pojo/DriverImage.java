package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE driverImage (
    imageId     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '相片的序號',
    driverSeq   BIGINT NOT NULL COMMENT '司機序號',
    imagePath   VARCHAR(255) NOT NULL COMMENT '原圖路徑',
    thumbPath   VARCHAR(255) DEFAULT NULL COMMENT '縮圖路徑',
    createDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '建檔時間',
    hashCode    VARCHAR(64) NOT NULL COMMENT '圖片 hash',
    type        INT DEFAULT NULL COMMENT '圖片種類（例如：1=身分證、2=執業登記證、3=駕照 等）',

    INDEX idx_driverSeq (driverSeq),
    UNIQUE KEY uniq_driver_hash (driverSeq, hashCode)
) COMMENT='司機圖片資料表（欄位使用 camelCase 命名）';

 */
public class DriverImage extends BaseImage {
    private Long driverSeq;     // 司機序號
    private Integer type;         // 圖片種類 (身分證、執業登記證、駕照。。ETC)

    public Long getDriverSeq() {
        return driverSeq;
    }

    public void setDriverSeq(Long driverSeq) {
        this.driverSeq = driverSeq;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DriverImage{" +
                "driverSeq=" + driverSeq +
                ", type=" + type +
                ", imageId=" + imageId +
                ", imagePath='" + imagePath + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", createDate=" + createDate +
                ", hashCode='" + hashCode + '\'' +
                '}';
    }
}
/*
public class DriverImage   {
    private Long imageId;         // 相片的序號
    private Long driverSeq;     // 司機序號
    private String imagePath;     // 原圖路徑
    private String thumbPath;     // 縮圖路徑
    private Timestamp createDate; // 建檔時間
    private String hashCode;      // 圖片 hash
    private Integer type;         // 圖片種類 (身分證、執業登記證、駕照。。ETC)

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getDriverSeq() {
        return driverSeq;
    }

    public void setDriverSeq(Long driverSeq) {
        this.driverSeq = driverSeq;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DriverImage{" +
                "imageId=" + imageId +
                ", driverSeq=" + driverSeq +
                ", imagePath='" + imagePath + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", createDate=" + createDate +
                ", hashCode='" + hashCode + '\'' +
                ", type=" + type +
                '}';
    }
}
*/