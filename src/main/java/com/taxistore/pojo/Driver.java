package com.taxistore.pojo;

/*
 Create Table

 CREATE TABLE `driver` (
        `driverseq` bigint(20) NOT NULL AUTO_INCREMENT,
        `createdate` datetime DEFAULT NULL,
        `serialno` varchar(255) DEFAULT NULL,
        `postcode` bigint(20) DEFAULT NULL,
        `drivername` varchar(255) DEFAULT NULL,
        `specialcode` varchar(255) DEFAULT NULL,
        `id` varchar(255) DEFAULT NULL,
        `birthday` datetime DEFAULT NULL,
        `portable` varchar(255) DEFAULT NULL,
        `email` varchar(255) DEFAULT NULL,
        `commadd` varchar(255) DEFAULT NULL,
        `commtel` varchar(255) DEFAULT NULL,
        `householda` varchar(255) DEFAULT NULL,
        `householdt` varchar(255) DEFAULT NULL,
        `indate` datetime DEFAULT NULL,
        `outdate` datetime DEFAULT NULL,
        `contact2` varchar(255) DEFAULT NULL,
        `contactel` varchar(255) DEFAULT NULL,
        `pfine` int(11) DEFAULT NULL,
        `pfineper` double DEFAULT NULL,
        `d10000` varchar(255) DEFAULT NULL,
        `pfineday` int(11) DEFAULT NULL,
        `remark` varchar(5000) DEFAULT NULL,
        `guarantor` varchar(255) DEFAULT NULL,
        `guarantorid` varchar(255) DEFAULT NULL,
        `relation` varchar(255) DEFAULT NULL,
        `guarantortel` varchar(255) DEFAULT NULL,
        `guarantoradd` varchar(255) DEFAULT NULL,
        `lastPostFineDate` datetime DEFAULT NULL,
 PRIMARY KEY (`driverseq`),
 KEY `FK78AF8C284BD1FA76` (`postcode`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1664 DEFAULT CHARSET=utf8

 */

import java.sql.Timestamp;

public class Driver
{
    private long driverseq;          //BigInteger,流水號
    private Timestamp createdate;   //datetime,建檔日期
    private String serialno;        //String,序號
    private long postcode;           //BigInteger,區域號碼
    private String drivername;      //varchar(255),司機姓名
    private String specialcode;     //varchar(255),特別碼
    private String id;              //varchar(255),身分證號
    private Timestamp birthday;     //datetime,出生日期
    private String portable;        //varchar(255),行動電話
    private String email;           //varchar(255),email
    private String commadd;         //varchar(255),通訊地址
    private String commtel;         //varchar(255),通訊電話
    private String householda;      //varchar(255),戶籍地址
    private String householdt;      //varchar(255),戶籍電話
    private Timestamp indate;       //datetime,受僱日期
    private Timestamp outdate;      //datetime,離行日期
    private String contact2;        //varchar(255),緊急聯絡人
    private String contactel;       //varchar(255),緊急聯絡人電話
    private Integer pfine;          //int(11)     滯納金底數
    private double pfineper;        //double     滯納金百分比
    private String d10000;          //varchar(255),全額計算/額度不計算
    private Integer pfineday;       //int(11)     ,催收天數
    private String remark;          //varchar(255),註解
    private String guarantor;       //varchar(255),保證人姓名
    private String relation;        //varchar(255),保證人關係
    private String guarantorid;     //varchar(255),保證人身分證號
    private String guarantortel;     //varchar(255),保證人電話
    private String guarantoradd;     //varchar(255) 保證人地址
    private Timestamp lastPostFineDate;// datetime


    public long getDriverseq() {
        return driverseq;
    }

    public void setDriverseq(long driverseq) {
        this.driverseq = driverseq;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public long getPostcode() {
        return postcode;
    }

    public void setPostcode(long postcode) {
        this.postcode = postcode;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getSpecialcode() {
        return specialcode;
    }

    public void setSpecialcode(String specialcode) {
        this.specialcode = specialcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommadd() {
        return commadd;
    }

    public void setCommadd(String commadd) {
        this.commadd = commadd;
    }

    public String getCommtel() {
        return commtel;
    }

    public void setCommtel(String commtel) {
        this.commtel = commtel;
    }

    public String getHouseholda() {
        return householda;
    }

    public void setHouseholda(String householda) {
        this.householda = householda;
    }

    public String getHouseholdt() {
        return householdt;
    }

    public void setHouseholdt(String householdt) {
        this.householdt = householdt;
    }

    public Timestamp getIndate() {
        return indate;
    }

    public void setIndate(Timestamp indate) {
        this.indate = indate;
    }

    public Timestamp getOutdate() {
        return outdate;
    }

    public void setOutdate(Timestamp outdate) {
        this.outdate = outdate;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getContactel() {
        return contactel;
    }

    public void setContactel(String contactel) {
        this.contactel = contactel;
    }

    public Integer getPfine() {
        return pfine;
    }

    public void setPfine(Integer pfine) {
        this.pfine = pfine;
    }

    public double getPfineper() {
        return pfineper;
    }

    public void setPfineper(double pfineper) {
        this.pfineper = pfineper;
    }

    public String getD10000() {
        return d10000;
    }

    public void setD10000(String d10000) {
        this.d10000 = d10000;
    }

    public Integer getPfineday() {
        return pfineday;
    }

    public void setPfineday(Integer pfineday) {
        this.pfineday = pfineday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getGuarantorid() {
        return guarantorid;
    }

    public void setGuarantorid(String guarantorid) {
        this.guarantorid = guarantorid;
    }

    public String getGuarantortel() {
        return guarantortel;
    }

    public void setGuarantortel(String guarantortel) {
        this.guarantortel = guarantortel;
    }

    public String getGuarantoradd() {
        return guarantoradd;
    }

    public void setGuarantoradd(String guarantoradd) {
        this.guarantoradd = guarantoradd;
    }

    public Timestamp getLastPostFineDate() {
        return lastPostFineDate;
    }

    public void setLastPostFineDate(Timestamp lastPostFineDate) {
        this.lastPostFineDate = lastPostFineDate;
    }

    public Driver() {
    }

}
