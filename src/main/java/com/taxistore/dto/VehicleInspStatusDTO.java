package com.taxistore.dto;

import java.sql.Timestamp;

public class VehicleInspStatusDTO {
    private String carno;                //車號
    private String driverSeq;            //司機序號
    private String driverName;           //司機姓名
    private Timestamp caryear;           //車輛生產年月
    private String companyName;          //掛牌車行
    private Timestamp shingleDat;        //掛牌日期
    private Timestamp terminateDate;     //繳銷日期
    private Timestamp newRegistration;  //新掛牌日期
    private Timestamp terminatedStatus; //繳銷日期

    public VehicleInspStatusDTO(String carno, String driverSeq, String driverName, Timestamp caryear, String companyName, Timestamp shingleDat, Timestamp terminateDate, Timestamp newRegistration, Timestamp terminatedStatus) {
        this.carno = carno;
        this.driverSeq = driverSeq;
        this.driverName = driverName;
        this.caryear = caryear;
        this.companyName = companyName;
        this.shingleDat = shingleDat;
        this.terminateDate = terminateDate;
        this.newRegistration = newRegistration;
        this.terminatedStatus = terminatedStatus;
    }

    public VehicleInspStatusDTO() {
    }
    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getDriverSeq() {
        return driverSeq;
    }

    public void setDriverSeq(String driverSeq) {
        this.driverSeq = driverSeq;
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

    public Timestamp getTerminateDate() {
        return terminateDate;
    }

    public void setTerminateDate(Timestamp terminateDate) {
        this.terminateDate = terminateDate;
    }

    public Timestamp getNewRegistration() {
        return newRegistration;
    }

    public void setNewRegistration(Timestamp newRegistration) {
        this.newRegistration = newRegistration;
    }

    public Timestamp getTerminatedStatus() {
        return terminatedStatus;
    }

    public void setTerminatedStatus(Timestamp terminatedStatus) {
        this.terminatedStatus = terminatedStatus;
    }

    @Override
    public String toString() {
        return "VehicleInspStatusDTO{" +
                "carno='" + carno + '\'' +
                ", driverSeq='" + driverSeq + '\'' +
                ", driverName='" + driverName + '\'' +
                ", caryear=" + caryear +
                ", companyName='" + companyName + '\'' +
                ", shingleDat=" + shingleDat +
                ", terminateDate=" + terminateDate +
                ", newRegistration=" + newRegistration +
                ", terminatedStatus=" + terminatedStatus +
                '}';
    }
}

