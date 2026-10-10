package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE `recvform` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `situation` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `remark` varchar(5000) DEFAULT NULL,
  `userNo` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FKFF5FF3AA367C5220` (`situation`),
CONSTRAINT `FKFF5FF3AA367C5220` FOREIGN KEY (`situation`) REFERENCES `situation` (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=145036 DEFAULT CHARSET=utf8

 */
public class RecvForm {
    private Long id;
    private Timestamp createdate;
    private Long situation;
    private String type;
    private String remark;
    private String userNo;
    private String userName;

    // Default constructor
    public RecvForm() {
    }

    // Parameterized constructor
    public RecvForm(Long id, Timestamp createdate, Long situation, String type, String remark, String userNo, String userName) {
        this.id = id;
        this.createdate = createdate;
        this.situation = situation;
        this.type = type;
        this.remark = remark;
        this.userNo = userNo;
        this.userName = userName;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Long getSituation() {
        return situation;
    }

    public void setSituation(Long situation) {
        this.situation = situation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // toString method
    @Override
    public String toString() {
        return "RecvForm{" +
                "id=" + id +
                ", createdate=" + createdate +
                ", situation=" + situation +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", userNo='" + userNo + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}