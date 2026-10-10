package com.taxistore.dto;

import java.sql.Timestamp;

public class VehicleInspDTO {
    public String carno;                //車號
    public String driverSeq;            //司機序號
    public String driverName;           //司機姓名
    public Timestamp caryear;           //車輛生產年月
    public String companyName;          //掛牌車行
    public Timestamp shingleDat;        //掛牌日期
    public Timestamp insp_date;         //驗車日期
    public Timestamp insp_deadline;     //驗車最後期限




    public VehicleInspDTO(String carno, String driverSeq, String driverName, Timestamp caryear, String companyName, Timestamp shingleDat, Timestamp insp_date, Timestamp insp_deadline) {
        this.carno = carno;
        this.driverSeq = driverSeq;
        this.driverName = driverName;
        this.caryear = caryear;
        this.companyName = companyName;
        this.shingleDat = shingleDat;
        this.insp_date = insp_date;
        this.insp_deadline = insp_deadline;
    }

    public VehicleInspDTO() {
    }


    public VehicleInspDTO(String carno, String driverSeq, String driverName, Timestamp caryear, String companyName, Timestamp shingleDat) {
        this.carno = carno;
        this.driverSeq=driverSeq;
        this.driverName = driverName;
        this.caryear = caryear;
        this.companyName = companyName;
        this.shingleDat = shingleDat;
    }
    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Timestamp getCaryear() {
        return caryear;
    }

    public void setCaryear(Timestamp caryear) {
        this.caryear = caryear;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Timestamp getShingleDat() {
        return shingleDat;
    }

    public void setShingleDat(Timestamp shingleDat) {
        this.shingleDat = shingleDat;
    }
    public String getDriverSeq() {
        return driverSeq;
    }

    public void setDriverSeq(String driverSeq) {
        this.driverSeq = driverSeq;
    }

    public Timestamp getInsp_date() {
        return insp_date;
    }

    public void setInsp_date(Timestamp insp_date) {
        this.insp_date = insp_date;
    }

    public Timestamp getInsp_deadline() {
        return insp_deadline;
    }

    public void setInsp_deadline(Timestamp insp_deadline) {
        this.insp_deadline = insp_deadline;
    }
    @Override
    public String toString() {
        return "VehicleInspDTO{" +
                "carno='" + carno + '\'' +
                ", driverSeq='" + driverSeq + '\'' +
                ", driverName='" + driverName + '\'' +
                ", caryear=" + caryear +
                ", companyName='" + companyName + '\'' +
                ", shingleDat=" + shingleDat +
                ", insp_date=" + insp_date +
                ", insp_deadline=" + insp_deadline +
                '}';
    }
}
