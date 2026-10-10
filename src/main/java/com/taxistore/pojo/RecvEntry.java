package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE `recventry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `FORMID` bigint(20) DEFAULT NULL,
  `acc` bigint(20) DEFAULT NULL,
  `summary` varchar(5000) DEFAULT NULL,
  `debitcredit` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `noteno` varchar(255) DEFAULT NULL,
  `notebank` varchar(255) DEFAULT NULL,
  `notebranch` varchar(255) DEFAULT NULL,
  `notepaydate` datetime DEFAULT NULL,
  `RecvFormId` bigint(20) DEFAULT NULL,
  `entryorder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `noteno_UNIQUE` (`noteno`),
  KEY `FKEC8FFE2C4C79536B` (`RecvFormId`),
  KEY `FKEC8FFE2CC3490D2B` (`acc`),
  KEY `FKEC8FFE2C76917E05` (`FORMID`),
  CONSTRAINT `FKEC8FFE2C4C79536B` FOREIGN KEY (`RecvFormId`) REFERENCES `recvform` (`id`),
  CONSTRAINT `FKEC8FFE2C76917E05` FOREIGN KEY (`FORMID`) REFERENCES `recvform` (`id`),
  CONSTRAINT `FKEC8FFE2CC3490D2B` FOREIGN KEY (`acc`) REFERENCES `fee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168736 DEFAULT CHARSET=utf8

 */
public class RecvEntry
{
    private Long id;
    private Long formId;
    private Long acc;
    private String summary;
    private String debitCredit;
    private Integer amount;
    private String noteNo;
    private String noteBank;
    private String noteBranch;
    private Timestamp notePayDate;
    private Long recvFormId;
    private Integer entryOrder;

    public RecvEntry(Long id, Long formId, Long acc, String summary, String debitCredit, Integer amount, String noteNo, String noteBank, String noteBranch, Timestamp notePayDate, Long recvFormId, Integer entryOrder) {
        this.id = id;
        this.formId = formId;
        this.acc = acc;
        this.summary = summary;
        this.debitCredit = debitCredit;
        this.amount = amount;
        this.noteNo = noteNo;
        this.noteBank = noteBank;
        this.noteBranch = noteBranch;
        this.notePayDate = notePayDate;
        this.recvFormId = recvFormId;
        this.entryOrder = entryOrder;
    }

    public RecvEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getAcc() {
        return acc;
    }

    public void setAcc(Long acc) {
        this.acc = acc;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(String noteNo) {
        this.noteNo = noteNo;
    }

    public String getNoteBank() {
        return noteBank;
    }

    public void setNoteBank(String noteBank) {
        this.noteBank = noteBank;
    }

    public String getNoteBranch() {
        return noteBranch;
    }

    public void setNoteBranch(String noteBranch) {
        this.noteBranch = noteBranch;
    }

    public Timestamp getNotePayDate() {
        return notePayDate;
    }

    public void setNotePayDate(Timestamp notePayDate) {
        this.notePayDate = notePayDate;
    }

    public Long getRecvFormId() {
        return recvFormId;
    }

    public void setRecvFormId(Long recvFormId) {
        this.recvFormId = recvFormId;
    }

    public Integer getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }



    @Override
    public String toString() {
        return "RecvEntry{" +
                "id=" + id +
                ", formId=" + formId +
                ", acc=" + acc +
                ", summary='" + summary + '\'' +
                ", debitCredit='" + debitCredit + '\'' +
                ", amount=" + amount +
                ", noteNo='" + noteNo + '\'' +
                ", noteBank='" + noteBank + '\'' +
                ", noteBranch='" + noteBranch + '\'' +
                ", notePayDate=" + notePayDate +
                ", recvFormId=" + recvFormId +
                ", entryOrder=" + entryOrder +
                '}';
    }
}
