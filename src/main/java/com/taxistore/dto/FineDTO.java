package com.taxistore.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FineDTO
{
    private Long fineSeq;           //罰單序號
    private Timestamp createdate;   //建檔日期
    private String str_createdate;
    private String fineNo;          //罰單號碼
    private String tactName;        //被通知人
    private Timestamp fineDate;     //罰單開立日期
    private String str_fineDate;
    private Integer  fineHH;        //罰單開立時間
    private Integer fineNN;         //罰單開立分鐘
    private Long situationId;       //situation ID
    private Long fineRuleId;        //罰單法規條目
    private String rule1;           //rule1
    private String rule2;           //rule2
    private String rule3;           //rule3
    private String factDescription; //違規事實
    private String fineContact;     //違規事實
    private String fineCreate;      //違規地點
    private Timestamp fineClDate;   //到案日期
    private String str_fineClDate;
    private Integer amount;         //罰款金額
    private String  fineAdd;        //到案地點
    private String fineText1;
    private String fineText2;
    private Timestamp passDate;     //移轉日期
    private String str_passDate;
    private String fineStatus;      //過帳狀態(不管過帳司機或是入帳。都是P，只不過會計科目不同
    private Timestamp payDate;      //繳納日期
    private String str_payDate;
    private String payStatus;       //是否已經繳納(E：繳納 /P：
    private String remark;          //備註
    private Long driverseq;
    private String driverName;
    private Long carseq;
    private String carno;

    public FineDTO() {

    }
    public String getStr_createdate() {
        return str_createdate;
    }

    public void setStr_createdate(String str_createdate) {
        this.str_createdate = str_createdate;
    }

    public String getStr_fineDate() {
        return str_fineDate;
    }

    public void setStr_fineDate(String str_fineDate) {
        this.str_fineDate = str_fineDate;
    }

    public String getStr_fineClDate() {
        return str_fineClDate;
    }

    public void setStr_fineClDate(String str_fineClDate) {
        this.str_fineClDate = str_fineClDate;
    }

    public Timestamp getPassDate() {
        return passDate;
    }

    public void setPassDate(Timestamp passDate) {
        this.passDate = passDate;
        if(passDate !=null) this.str_passDate = new SimpleDateFormat("yyyy-MM-dd").format(passDate);
    }

    public String getStr_passDate() {
        return str_passDate;
    }

    public void setStr_passDate(String str_passDate) {
        this.str_passDate = str_passDate;
    }

    public String getStr_payDate() {
        return str_payDate;
    }

    public void setStr_payDate(String str_payDate) {
        this.str_payDate = str_payDate;
    }

    public Long getDriverseq() {
        return driverseq;
    }

    public void setDriverseq(Long driverseq) {
        this.driverseq = driverseq;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getCarseq() {
        return carseq;
    }

    public void setCarseq(Long carseq) {
        this.carseq = carseq;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }


    public Long getFineSeq() {
        return fineSeq;
    }

    public void setFineSeq(Long fineSeq) {
        this.fineSeq = fineSeq;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
        if(createdate !=null) this.str_createdate = new SimpleDateFormat("yyyy-MM-dd").format(createdate);
    }

    public String getFineNo() {
        return fineNo;
    }

    public void setFineNo(String fineNo) {
        this.fineNo = fineNo;
    }

    public String getTactName() {
        return tactName;
    }

    public void setTactName(String tactName) {
        this.tactName = tactName;
    }

    public Timestamp getFineDate() {
        return fineDate;
    }

    public void setFineDate(Timestamp fineDate) {
        this.fineDate = fineDate;
        if(fineDate !=null) this.str_fineDate = new SimpleDateFormat("yyyy-MM-dd").format(fineDate);
    }

    public Integer getFineHH() {
        return fineHH;
    }

    public void setFineHH(Integer fineHH) {
        this.fineHH = fineHH;
    }

    public Integer getFineNN() {
        return fineNN;
    }

    public void setFineNN(Integer fineNN) {
        this.fineNN = fineNN;
    }

    public Long getSituationId() {
        return situationId;
    }

    public void setSituationId(Long situationId) {
        this.situationId = situationId;
    }

    public Long getFineRuleId() {
        return fineRuleId;
    }

    public void setFineRuleId(Long fineRuleId) {
        this.fineRuleId = fineRuleId;
    }

    public String getFineContact() {
        return fineContact;
    }

    public void setFineContact(String fineContact) {
        this.fineContact = fineContact;
    }

    public String getFineCreate() {
        return fineCreate;
    }

    public void setFineCreate(String fineCreate) {
        this.fineCreate = fineCreate;
    }

    public Timestamp getFineClDate() {
        return fineClDate;
    }

    public void setFineClDate(Timestamp fineClDate) {
        this.fineClDate = fineClDate;
        if(fineClDate !=null) this.str_fineClDate = new SimpleDateFormat("yyyy-MM-dd").format(fineClDate);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getFineAdd() {
        return fineAdd;
    }

    public void setFineAdd(String fineAdd) {
        this.fineAdd = fineAdd;
    }

    public String getFineText1() {
        return fineText1;
    }

    public void setFineText1(String fineText1) {
        this.fineText1 = fineText1;
    }

    public String getFineText2() {
        return fineText2;
    }

    public void setFineText2(String fineText2) {
        this.fineText2 = fineText2;
    }

    public String getFineStatus() {
        return fineStatus;
    }

    public void setFineStatus(String fineStatus) {
        this.fineStatus = fineStatus;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRule1() {
        return rule1;
    }

    public void setRule1(String rule1) {
        this.rule1 = rule1;
    }

    public String getRule2() {
        return rule2;
    }

    public void setRule2(String rule2) {
        this.rule2 = rule2;
    }

    public String getRule3() {
        return rule3;
    }

    public void setRule3(String rule3) {
        this.rule3 = rule3;
    }

    public String getFactDescription() {
        return factDescription;
    }

    public void setFactDescription(String factDescription) {
        this.factDescription = factDescription;
    }

    @Override
    public String toString() {
        return "FineDTO{" +
                "fineSeq=" + fineSeq +
                ", createdate=" + createdate +
                ", str_createdate='" + str_createdate + '\'' +
                ", fineNo='" + fineNo + '\'' +
                ", tactName='" + tactName + '\'' +
                ", fineDate=" + fineDate +
                ", str_fineDate='" + str_fineDate + '\'' +
                ", fineHH=" + fineHH +
                ", fineNN=" + fineNN +
                ", situationId=" + situationId +
                ", fineRuleId=" + fineRuleId +
                ", rule1='" + rule1 + '\'' +
                ", rule2='" + rule2 + '\'' +
                ", rule3='" + rule3 + '\'' +
                ", factDescription='" + factDescription + '\'' +
                ", fineContact='" + fineContact + '\'' +
                ", fineCreate='" + fineCreate + '\'' +
                ", fineClDate=" + fineClDate +
                ", str_fineClDate='" + str_fineClDate + '\'' +
                ", amount=" + amount +
                ", fineAdd='" + fineAdd + '\'' +
                ", fineText1='" + fineText1 + '\'' +
                ", fineText2='" + fineText2 + '\'' +
                ", passDate=" + passDate +
                ", str_passDate='" + str_passDate + '\'' +
                ", fineStatus='" + fineStatus + '\'' +
                ", payDate=" + payDate +
                ", str_payDate='" + str_payDate + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", driverseq=" + driverseq +
                ", driverName='" + driverName + '\'' +
                ", carseq=" + carseq +
                ", carno='" + carno + '\'' +
                '}';
    }
}



