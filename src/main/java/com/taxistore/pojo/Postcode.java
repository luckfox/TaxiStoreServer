package com.taxistore.pojo;


//CREATE TABLE `postcode` (
//        `POSTCODEID` bigint(20) NOT NULL AUTO_INCREMENT,
//  `POSTCODECODE` varchar(255) DEFAULT NULL,
//  `regionName` varchar(255) DEFAULT NULL,
//  `districtName` varchar(255) DEFAULT NULL,
//PRIMARY KEY (`POSTCODEID`)
//) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8

import java.io.Serializable;

/**
 * 郵遞區號資料表對應的 POJO。
 * 對應資料表：postcode
 */
public class Postcode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 郵遞區號主鍵，自動遞增
     * 資料庫欄位：POSTCODEID (bigint)
     */
    private Long postcodeId;

    /**
     * 郵遞區號代碼
     * 資料庫欄位：POSTCODECODE (varchar)
     */
    private String postcodeCode;

    /**
     * 區域名稱（例如：新北市）
     * 資料庫欄位：regionName (varchar)
     */
    private String regionName;

    /**
     * 地區名稱（例如：新店區）
     * 資料庫欄位：districtName (varchar)
     */
    private String districtName;


    private String str_postcode;

    public String getStr_postcode() {
        return str_postcode;
    }

    public void setStr_postcode(String str_postcode) {
        this.str_postcode = str_postcode;
    }
// Getter & Setter

    public Long getPostcodeId() {
        return postcodeId;
    }

    public void setPostcodeId(Long postcodeId) {
        this.postcodeId = postcodeId;
    }

    public String getPostcodeCode() {
        return postcodeCode;
    }

    public void setPostcodeCode(String postcodeCode) {
        this.postcodeCode = postcodeCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
        this.str_postcode= this.regionName + this.districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
        this.str_postcode= this.regionName + this.districtName;
    }

    @Override
    public String toString() {
        return "Postcode{" +
                "postcodeId=" + postcodeId +
                ", postcodeCode='" + postcodeCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}
