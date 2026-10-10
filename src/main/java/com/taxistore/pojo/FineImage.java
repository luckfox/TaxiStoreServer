package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE FineImage (
imageId     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '相片的序號',
fineNo      VARCHAR(50) NOT NULL COMMENT '罰單編號',
imagePath   VARCHAR(255) NOT NULL COMMENT '原圖路徑',
thumbPath   VARCHAR(255) COMMENT '縮圖路徑',
createDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '建檔時間',
hashCode    VARCHAR(64) COMMENT '圖片 hash 值',

        -- 為了查圖快速，幫你加上索引
INDEX idx_fineNo (fineNo),
INDEX idx_hashCode (hashCode)
) COMMENT='罰單相片資料表';
*/
/**
 * FineImage - 用來封裝單筆罰單圖片資訊的資料物件。
 * 支援鍊式呼叫風格（Fluent-style）。
 */

public class FineImage extends BaseImage {
    private String fineNo;

    public String getFineNo() {
        return fineNo;
    }

    public void setFineNo(String fineNo) {
        this.fineNo = fineNo;
    }

    @Override
    public String toString() {
        return "FineImage{" +
                "imageId=" + imageId +
                ", fineNo='" + fineNo + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", createDate=" + createDate +
                ", hashCode='" + hashCode + '\'' +
                '}';
    }
}

/*
public class FineImage {
    private Long imageId;         // 相片的序號
    private String fineNo;        // 罰單編號
    private String imagePath;     // 原圖路徑
    private String thumbPath;     // 縮圖路徑
    private Timestamp createDate; // 建檔時間
    private String hashCode;      // 圖片 hash

    public FineImage() {}



    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }


    public String getFineNo() {
        return fineNo;
    }

    public void setFineNo(String fineNo) {
        this.fineNo = fineNo;
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
        return "FineImage{" +
                "imageId=" + imageId +
                ", fineNo='" + fineNo + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", createDate=" + createDate +
                ", hashCode='" + hashCode + '\'' +
                '}';
    }


}
*/