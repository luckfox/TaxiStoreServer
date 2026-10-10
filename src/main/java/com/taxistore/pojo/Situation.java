package com.taxistore.pojo;

import java.sql.Timestamp;

/*
  CREATE TABLE `situation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `seqNo` varchar(255) DEFAULT NULL,
  `giveCarDate` datetime DEFAULT NULL,
  `terminateDate` datetime DEFAULT NULL,
  `carseq` bigint(20) DEFAULT NULL,
  `driverseq` bigint(20) DEFAULT NULL,
  `recvDeposit` int(11) DEFAULT NULL,
  `retDeposit` int(11) DEFAULT NULL,
  `keepMoney` int(11) DEFAULT NULL,
  `accRecAmount` int(11) DEFAULT NULL,
  `accPayAmount` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `memo` varchar(5000) DEFAULT NULL,
  `lastPostFineDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA1B869DE92CE9FBB` (`driverseq`),
  KEY `FKA1B869DE71C3C503` (`carseq`),
  KEY `situation_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1901 DEFAULT CHARSET=utf8


 */
public class Situation
{
    private long id;                // bigint(20) NOT NULL AUTO_INCREMENT,
    private Timestamp createDate;   // datetime,建檔日期,
    private String seqNo;           // varchar(255),
    private Timestamp giveCarDate;  // datetime,交車日期,
    private Timestamp terminateDate;// datetime,解約日期,
    private long carseq;            // bigint(20),車輛序號,==>car(carseq)
    private long driverseq;         // bigint(20),司機序號==>driver(driverseq)
    private Integer recvDeposit;    // int(11),預收保證金,
    private Integer retDeposit;     // int(11),預退保證金,
    private Integer keepMoney;      // int(11) ,????,
    private Integer accRecAmount;   // int(11),當前結餘款,
    private Integer accPayAmount;   // int(11),????,
    private String status;          // varchar(255),狀態(0 日,1 夜,2 靠行,3 合作社,4 租送 5,包月),
    private String memo;            // varchar(5000),註解,
    private Timestamp lastPostFineDate;// datetime,最後修改日期

    public Situation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Timestamp getGiveCarDate() {
        return giveCarDate;
    }

    public void setGiveCarDate(Timestamp giveCarDate) {
        this.giveCarDate = giveCarDate;
    }

    public Timestamp getTerminateDate() {
        return terminateDate;
    }

    public void setTerminateDate(Timestamp terminateDate) {
        this.terminateDate = terminateDate;
    }

    public long getCarseq() {
        return carseq;
    }

    public void setCarseq(long carseq) {
        this.carseq = carseq;
    }

    public long getDriverseq() {
        return driverseq;
    }

    public void setDriverseq(long driverseq) {
        this.driverseq = driverseq;
    }

    public Integer getRecvDeposit() {
        return recvDeposit;
    }

    public void setRecvDeposit(Integer recvDeposit) {
        this.recvDeposit = recvDeposit;
    }

    public Integer getRetDeposit() {
        return retDeposit;
    }

    public void setRetDeposit(Integer retDeposit) {
        this.retDeposit = retDeposit;
    }

    public Integer getKeepMoney() {
        return keepMoney;
    }

    public void setKeepMoney(Integer keepMoney) {
        this.keepMoney = keepMoney;
    }

    public Integer getAccRecAmount() {
        return accRecAmount;
    }

    public void setAccRecAmount(Integer accRecAmount) {
        this.accRecAmount = accRecAmount;
    }

    public Integer getAccPayAmount() {
        return accPayAmount;
    }

    public void setAccPayAmount(Integer accPayAmount) {
        this.accPayAmount = accPayAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Timestamp getLastPostFineDate() {
        return lastPostFineDate;
    }

    public void setLastPostFineDate(Timestamp lastPostFineDate) {
        this.lastPostFineDate = lastPostFineDate;
    }
    @Override
    public String toString() {
        return "Situation{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", seqNo='" + seqNo + '\'' +
                ", giveCarDate=" + giveCarDate +
                ", terminateDate=" + terminateDate +
                ", carseq=" + carseq +
                ", driverseq=" + driverseq +
                ", recvDeposit=" + recvDeposit +
                ", retDeposit=" + retDeposit +
                ", keepMoney=" + keepMoney +
                ", accRecAmount=" + accRecAmount +
                ", accPayAmount=" + accPayAmount +
                ", status='" + status + '\'' +
                ", memo='" + memo + '\'' +
                ", lastPostFineDate=" + lastPostFineDate +
                '}';
    }

}
