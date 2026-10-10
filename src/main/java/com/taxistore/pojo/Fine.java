package com.taxistore.pojo;
/*
CREATE TABLE `fine` (
        `fineSeq` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` datetime DEFAULT NULL,
        `fineNo` varchar(255) DEFAULT NULL,
  `tactName` varchar(255) DEFAULT NULL,
  `fineDate` datetime DEFAULT NULL,
        `fineHH` int(11) DEFAULT NULL,
  `fineNN` int(11) DEFAULT NULL,
  `situationId` bigint(20) DEFAULT NULL,
  `fineRuleId` bigint(20) DEFAULT NULL,
  `fineContact` varchar(255) DEFAULT NULL,
  `fineCreate` varchar(255) DEFAULT NULL,
  `fineClDate` datetime DEFAULT NULL,
        `amount` int(11) DEFAULT NULL,
  `fineAdd` varchar(255) DEFAULT NULL,
  `fineText1` varchar(255) DEFAULT NULL,
  `fineText2` varchar(255) DEFAULT NULL,
  `passDate` datetime DEFAULT NULL,
        `fineStatus` varchar(255) DEFAULT NULL,
  `payDate` datetime DEFAULT NULL,
        `payStatus` varchar(255) DEFAULT NULL,
  `remark` varchar(5000) DEFAULT NULL,
PRIMARY KEY (`fineSeq`),
KEY `FK20EDBA392EB883` (`fineRuleId`),
KEY `FK20EDBACFCDD7BB` (`situationId`),
KEY `fine_fineno` (`fineNo`),
CONSTRAINT `FK20EDBA392EB883` FOREIGN KEY (`fineRuleId`) REFERENCES `fine_rule` (`fineRuleSeq`),
CONSTRAINT `FK20EDBACFCDD7BB` FOREIGN KEY (`situationId`) REFERENCES `situation` (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=16886 DEFAULT CHARSET=utf8
*/
import java.util.Date;

/**
 * POJO for the 'fine' table.
 */
public class Fine {

    /** 主鍵，自動遞增的罰單序號 */
    private Long fineSeq;

    /** 建立日期 */
    private Date createdate;

    /** 罰單編號 */
    private String fineNo;

    /** 開立單位名稱（如警方、監理所） */
    private String tactName;

    /** 罰單發生日期 */
    private Date fineDate;

    /** 發生時間的「時」欄位（hour） */
    private Integer fineHH;

    /** 發生時間的「分」欄位（minute） */
    private Integer fineNN;

    /** 關聯的交通情境 ID（對應 situation.id） */
    private Long situationId;

    /** 違規法條 ID（對應 fine_rule.fineRuleSeq） */
    private Long fineRuleId;

    /** 聯絡人（開單人員或聯絡方式） */
    private String fineContact;

    /** 建單人（可能是系統使用者） */
    private String fineCreate;

    /** 案件結案時間 */
    private Date fineClDate;

    /** 罰鍰金額 */
    private Integer amount;

    /** 地點（可能是違規發生地） */
    private String fineAdd;

    /** 備註欄位1 */
    private String fineText1;

    /** 備註欄位2 */
    private String fineText2;

    /** 通過時間（可能為簽核或審核通過時間） */
    private Date passDate;

    /** 狀態（如「待處理」、「已繳費」等） */
    private String fineStatus;

    /** 繳費日期 */
    private Date payDate;

    /** 繳費狀態（如「已繳」、「未繳」） */
    private String payStatus;

    /** 備註（可存放大段文字） */
    private String remark;

    // === Getters and Setters ===

    public Long getFineSeq() {
        return fineSeq;
    }

    public void setFineSeq(Long fineSeq) {
        this.fineSeq = fineSeq;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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

    public Date getFineDate() {
        return fineDate;
    }

    public void setFineDate(Date fineDate) {
        this.fineDate = fineDate;
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

    public Date getFineClDate() {
        return fineClDate;
    }

    public void setFineClDate(Date fineClDate) {
        this.fineClDate = fineClDate;
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

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getFineStatus() {
        return fineStatus;
    }

    public void setFineStatus(String fineStatus) {
        this.fineStatus = fineStatus;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
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

    @Override
    public String toString() {
        return "Fine{" +
                "fineSeq=" + fineSeq +
                ", createdate=" + createdate +
                ", fineNo='" + fineNo + '\'' +
                ", tactName='" + tactName + '\'' +
                ", fineDate=" + fineDate +
                ", fineHH=" + fineHH +
                ", fineNN=" + fineNN +
                ", situationId=" + situationId +
                ", fineRuleId=" + fineRuleId +
                ", fineContact='" + fineContact + '\'' +
                ", fineCreate='" + fineCreate + '\'' +
                ", fineClDate=" + fineClDate +
                ", amount=" + amount +
                ", fineAdd='" + fineAdd + '\'' +
                ", fineText1='" + fineText1 + '\'' +
                ", fineText2='" + fineText2 + '\'' +
                ", passDate=" + passDate +
                ", fineStatus='" + fineStatus + '\'' +
                ", payDate=" + payDate +
                ", payStatus='" + payStatus + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}