package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE carImage (
        imageId BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '圖片的唯一識別碼',
        carSeq BIGINT NOT NULL COMMENT '車輛的序號，對應車輛主表',
        imagePath VARCHAR(255) NOT NULL COMMENT '原圖的檔案路徑',
thumbPath VARCHAR(255) DEFAULT NULL COMMENT '縮圖的檔案路徑',
createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '建檔時間',
hashCode VARCHAR(64) NOT NULL COMMENT '圖片的 SHA256 雜湊值',
type INT DEFAULT NULL COMMENT '圖片種類（例如：1=牌照登記書，2=行照）',

        -- Optional: 加個 unique constraint 防止同車重複圖片
UNIQUE KEY uqCarHash (carSeq, hashCode)
) COMMENT='車輛圖片表：儲存牌照登記書、行照等影像資料';
*/
public class CarImage extends BaseImage {
        private Long carSeq;          // 車輛序號
        private Integer type;         // 圖片種類 (牌照登記書、行照。。)

        public Long getCarSeq() {
                return carSeq;
        }

        public void setCarSeq(Long carSeq) {
                this.carSeq = carSeq;
        }

        public Integer getType() {
                return type;
        }

        public void setType(Integer type) {
                this.type = type;
        }

        @Override
        public String toString() {
                return "CarImage{" +
                        "carSeq=" + carSeq +
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
public class CarImage {
        private Long imageId;         // 相片的序號
        private Long carSeq;          // 車輛序號
        private String imagePath;     // 原圖路徑
        private String thumbPath;     // 縮圖路徑
        private Timestamp createDate; // 建檔時間
        private String hashCode;      // 圖片 hash
        private Integer type;         // 圖片種類 (牌照登記書、行照。。)

        public Long getImageId() {
                return imageId;
        }

        public void setImageId(Long imageId) {
                this.imageId = imageId;
        }

        public Long getCarSeq() {
                return carSeq;
        }

        public void setCarSeq(Long carSeq) {
                this.carSeq = carSeq;
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
                return "CarImage{" +
                        "imageId=" + imageId +
                        ", carSeq=" + carSeq +
                        ", imagePath='" + imagePath + '\'' +
                        ", thumbPath='" + thumbPath + '\'' +
                        ", createDate=" + createDate +
                        ", hashCode='" + hashCode + '\'' +
                        ", type=" + type +
                        '}';
        }
}

 */
