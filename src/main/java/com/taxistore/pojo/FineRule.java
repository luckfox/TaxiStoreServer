package com.taxistore.pojo;

/*
Drop table `fine_rule`
CREATE TABLE `fine_rule` (
  `fineRuleSeq` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule1` varchar(255) DEFAULT NULL,
  `rule2` varchar(255) DEFAULT NULL,
  `rule3` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`fineRuleSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8

 */

import java.io.Serializable;

/**
 * FineRule 是用來對應資料表 fine_rule 的 Java Bean。
 */
public class FineRule implements Serializable {
    private Long fineRuleSeq;
    private String rule1;
    private String rule2;
    private String rule3;
    private Integer amount;
    private String description;

    public FineRule() {}

    public FineRule(Long fineRuleSeq, String rule1, String rule2, String rule3, Integer amount, String description) {
        this.fineRuleSeq = fineRuleSeq;
        this.rule1 = rule1;
        this.rule2 = rule2;
        this.rule3 = rule3;
        this.amount = amount;
        this.description = description;
    }

    public Long getFineRuleSeq() {
        return fineRuleSeq;
    }

    public void setFineRuleSeq(Long fineRuleSeq) {
        this.fineRuleSeq = fineRuleSeq;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FineRule{" +
                "fineRuleSeq=" + fineRuleSeq +
                ", rule1='" + rule1 + '\'' +
                ", rule2='" + rule2 + '\'' +
                ", rule3='" + rule3 + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
