package com.taxistore.dto;

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
public class PostcodeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long postcodeId;
    private String postcodeCode;
    private String regionName;
    private String districtName;
    private String str_postcode;

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
        updateStrPostcode();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
        updateStrPostcode();
    }

    public String getStr_postcode() {
        return str_postcode;
    }

    private void updateStrPostcode() {
        // 防止 null 導致的空字串錯誤，改成空字串相加
        String region = (regionName != null) ? regionName : "";
        String district = (districtName != null) ? districtName : "";
        this.str_postcode = region + district;
    }

    @Override
    public String toString() {
        return "PostcodeDTO{" +
                "postcodeId=" + postcodeId +
                ", postcodeCode='" + postcodeCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", str_postcode='" + str_postcode + '\'' +
                '}';
    }
}
