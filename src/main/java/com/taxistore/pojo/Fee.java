package com.taxistore.pojo;


import java.io.Serializable;
/*
CREATE TABLE `fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `accountCode` varchar(255) DEFAULT NULL,
  `payForm` varchar(255) DEFAULT NULL,
  `genForm` varchar(255) DEFAULT NULL,
  `recvForm` varchar(255) DEFAULT NULL,
  `billByDays` varchar(255) DEFAULT NULL,
  `notToIncome` varchar(255) DEFAULT NULL,
  `notToAccount` varchar(255) DEFAULT NULL,
  `special` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8
 */
/**
 * `Fee` 實體類，對應 `fee` 資料表
 */

public class Fee implements Serializable {

    /** 主鍵，自增 ID */
    private Long id;

    /** 費用代碼，唯一索引 */
    private String code;

    /** 帳戶代碼 */
    private String accountCode;

    /** 付款方式 */
    private String payForm;

    /** 產生方式 */
    private String genForm;

    /** 收款方式 */
    private String recvForm;

    /** 計費天數 */
    private String billByDays;

    /** 是否不計入收入 */
    private String notToIncome;

    /** 是否不記入帳務 */
    private String notToAccount;

    /** 特殊標記 */
    private String special;

    public Fee() {
    }

    public String getSpecial() {
        return special;
    }
    public void setSpecial(String special) {
        this.special = special;
    }
    public String getNotToAccount() {
        return notToAccount;
    }
    public void setNotToAccount(String notToAccount) {
        this.notToAccount = notToAccount;
    }

    public String getNotToIncome() {
        return notToIncome;
    }

    public void setNotToIncome(String notToIncome) {
        this.notToIncome = notToIncome;
    }

    public String getBillByDays() {
        return billByDays;
    }

    public void setBillByDays(String billByDays) {
        this.billByDays = billByDays;
    }

    public String getRecvForm() {
        return recvForm;
    }

    public void setRecvForm(String recvForm) {
        this.recvForm = recvForm;
    }

    public String getGenForm() {
        return genForm;
    }

    public void setGenForm(String genForm) {
        this.genForm = genForm;
    }

    public String getPayForm() {
        return payForm;
    }

    public void setPayForm(String payForm) {
        this.payForm = payForm;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", accountCode='" + accountCode + '\'' +
                ", payForm='" + payForm + '\'' +
                ", genForm='" + genForm + '\'' +
                ", recvForm='" + recvForm + '\'' +
                ", billByDays='" + billByDays + '\'' +
                ", notToIncome='" + notToIncome + '\'' +
                ", notToAccount='" + notToAccount + '\'' +
                ", special='" + special + '\'' +
                '}';
    }

}
