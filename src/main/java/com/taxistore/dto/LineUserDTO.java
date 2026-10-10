package com.taxistore.dto;

import java.sql.Timestamp;

/**
 * @author luckf
 */
public class LineUserDTO {
    private long serialID;              //Long，LineUser表格
    private String userId;              //userId，LINE上面的實際ID
    private String displayName;         //varchar(255)，LINE上所使用的名稱
    private String tag;               //varchar(255)，備註
    private Timestamp createDate;      //TimeStamp，此筆資料建立時間
    private Long driverSeq;         //Long，司機序號
    private String driverSC;     //varchar(255),特別碼
    private String driverName;      //varchar(255),司機姓名
    private String driverId;              //varchar(255),身分證號
    private String driverPortable;        //varchar(255),行動電話

    public LineUserDTO()
    {

    }

    @Override
    public String toString()
    {
        return "LineUserDTO{" +
                "serialID=" + serialID +
                ", userId='" + userId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", tag='" + tag + '\'' +
                ", createDate=" + createDate +
                ", driverSeq=" + driverSeq +
                ", driverSC='" + driverSC + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverId='" + driverId + '\'' +
                ", driverPortable='" + driverPortable + '\'' +
                '}';
    }

    public LineUserDTO(long serialID, String userId, String displayName, String tag, Timestamp createDate, String driverSC, Long driverSeq, String driverName, String driverId, String driverPortable)
    {
        this.serialID = serialID;
        this.userId = userId;
        this.displayName = displayName;
        this.tag = tag;
        this.createDate = createDate;
        this.driverSC = driverSC;
        this.driverSeq = driverSeq;
        this.driverName = driverName;
        this.driverId = driverId;
        this.driverPortable = driverPortable;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getSerialID() {
        return serialID;
    }

    public void setSerialID(long serialID) {
        this.serialID = serialID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getDriverSC() {
        return driverSC;
    }

    public void setDriverSC(String driverSC) {
        this.driverSC = driverSC;
    }

    public Long getDriverSeq() {
        return driverSeq;
    }

    public void setDriverSeq(Long driverSeq) {
        this.driverSeq = driverSeq;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverPortable() {
        return driverPortable;
    }

    public void setDriverPortable(String driverPortable) {
        this.driverPortable = driverPortable;
    }
}
