package com.taxistore.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DriverDTO {
    private long driverseq;          //BigInteger,流水號
    private Timestamp createdate;   //datetime,建檔日期
    private String str_createdate;
    private String serialno;        //String,序號
    private long postcodeId;           //BigInteger,區域號碼ID
    private String postcodeCode;           //區域號碼
    private String regionName;       //縣市名稱
    private String districtName;    //鄉鎮名稱
    private String str_postcode;    //String ，區域名稱
    private String drivername;      //varchar(255),司機姓名
    private String specialcode;     //varchar(255),特別碼
    private String id;              //varchar(255),身分證號
    private Timestamp birthday;     //datetime,出生日期
    private String str_birthday;
    private String portable;        //varchar(255),行動電話
    private String email;           //varchar(255),email
    private String commadd;         //varchar(255),通訊地址
    private String commtel;         //varchar(255),通訊電話
    private String householda;      //varchar(255),戶籍地址
    private String householdt;      //varchar(255),戶籍電話
    private Timestamp indate;       //datetime,受僱日期
    private String str_indate;
    private Timestamp outdate;      //datetime,離行日期
    private String str_outdate;
    private String carno;           //受僱車號
    private String ownerName;       //受僱車行
    private String ownerSC;         //受僱車行特別碼
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

    public DriverDTO() {
    }

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
        if(createdate !=null) this.str_createdate = new SimpleDateFormat("yyyy-MM-dd").format(createdate);
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
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
        if(birthday !=null) this.str_birthday = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
        this.str_postcode= this.regionName+this.districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
        this.str_postcode= this.regionName+this.districtName;
    }

    public long getPostcodeId() {
        return postcodeId;
    }

    public void setPostcodeId(long postcodeId) {
        this.postcodeId = postcodeId;
    }

    public void setPostcodeCode(String postcodeCode) {
        this.postcodeCode = postcodeCode;
    }
    public String getPostcodeCode() {
        return postcodeCode;
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
        if(indate !=null) this.str_indate = new SimpleDateFormat("yyyy-MM-dd").format(indate);
    }

    public Timestamp getOutdate() {
        return outdate;
    }

    public void setOutdate(Timestamp outdate) {
        this.outdate = outdate;
        if(outdate !=null) this.str_outdate = new SimpleDateFormat("yyyy-MM-dd").format(outdate);
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSC() {
        return ownerSC;
    }

    public void setOwnerSC(String ownerSC) {
        this.ownerSC = ownerSC;
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
    public String getStr_createdate() {
        return str_createdate;
    }

    public void setStr_createdate(String str_createdate) {
        this.str_createdate = str_createdate;
    }

    public String getStr_outdate() {
        return str_outdate;
    }

    public void setStr_outdate(String str_outdate) {
        this.str_outdate = str_outdate;
    }

    public String getStr_indate() {
        return str_indate;
    }

    public void setStr_indate(String str_indate) {
        this.str_indate = str_indate;
    }

    public String getStr_birthday() {
        return str_birthday;
    }

    public void setStr_birthday(String str_birthday) {
        this.str_birthday = str_birthday;
    }

    public String getStr_postcode() {
        return  this.regionName+this.districtName;
    }

    public void setStr_postcode(String str_postcode) {
        this.str_postcode = str_postcode;
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
                "driverseq=" + driverseq +
                ", createdate=" + createdate +
                ", str_createdate='" + str_createdate + '\'' +
                ", serialno='" + serialno + '\'' +
                ", postcodeCode=" + postcodeCode +
                ", regionName='" + regionName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", str_postcode='" + str_postcode + '\'' +
                ", drivername='" + drivername + '\'' +
                ", specialcode='" + specialcode + '\'' +
                ", id='" + id + '\'' +
                ", birthday=" + birthday +
                ", str_birthday='" + str_birthday + '\'' +
                ", portable='" + portable + '\'' +
                ", email='" + email + '\'' +
                ", commadd='" + commadd + '\'' +
                ", commtel='" + commtel + '\'' +
                ", householda='" + householda + '\'' +
                ", householdt='" + householdt + '\'' +
                ", indate=" + indate +
                ", str_indate='" + str_indate + '\'' +
                ", outdate=" + outdate +
                ", str_outdate='" + str_outdate + '\'' +
                ", carno='" + carno + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerSC='" + ownerSC + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", contactel='" + contactel + '\'' +
                ", pfine=" + pfine +
                ", pfineper=" + pfineper +
                ", d10000='" + d10000 + '\'' +
                ", pfineday=" + pfineday +
                ", remark='" + remark + '\'' +
                ", guarantor='" + guarantor + '\'' +
                ", relation='" + relation + '\'' +
                ", guarantorid='" + guarantorid + '\'' +
                ", guarantortel='" + guarantortel + '\'' +
                ", guarantoradd='" + guarantoradd + '\'' +
                ", lastPostFineDate=" + lastPostFineDate +
                '}';
    }
}

