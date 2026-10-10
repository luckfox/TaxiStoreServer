package com.taxistore.pojo;

import com.taxistore.dao.impl.LineUserTDao;

/**
DROP TABLE `line_users`;

 Create Table

 CREATE TABLE `line_users` (
 `serialID` bigint(20) NOT NULL AUTO_INCREMENT,
 `userId` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
 `displayName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
 `tag` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
 `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `driverseq` bigint(20) unsigned zerofill NOT NULL,
 PRIMARY KEY (`serialID`),
 UNIQUE KEY `userId` (`userId`)
 ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

 INSERT INTO `line_users` (userId, displayName, tag, driverseq)
 VALUES
 ('lineuser001', 'Alice Johnson', 'tag for Alice', 1001),
 ('lineuser002', 'Bob Smith', 'tag for Bob', 1002),
 ('lineuser003', 'Carol Williams', 'tag for Carol', 1003),
 ('lineuser004', 'David Brown', 'tag for David', 1004),
 ('lineuser005', 'Eva Davis', 'tag for Eva', 1005),
 ('lineuser006', 'Frank Miller', 'tag for Frank', 1006),
 ('lineuser007', 'Grace Wilson', 'tag for Grace', 1007),
 ('lineuser008', 'Hannah Moore', 'tag for Hannah', 1008),
 ('lineuser009', 'Ian Taylor', 'tag for Ian', 1009),
 ('lineuser010', 'Jane Anderson', 'tag for Jane', 1010);
*/

//@Entity
//@Table(name = "LineUser")
public class LineUser {

    private long serialID;
    private String userId;
    private String displayName;
    private String tag;
    private String createDate;
    private Long driverseq; // 可為 NULL，因此使用 Long 而非 long

    // 無參數建構子
    public LineUser() {}
    // 全參數建構子
    public LineUser(long serialID, String userId, String displayName, String tag, String createDate, Long driverseq) {
        this.serialID = serialID;
        this.userId = userId;
        this.tag=tag;
        this.displayName = displayName;
        this.createDate = createDate;
        this.driverseq = driverseq;
    }

    public long getSerialID() {
        return serialID;
    }

    public void setSerialID(long serialID)
    {
        this.serialID = serialID;

    }

    public String getUserId() {
        return userId;
    }

    public void  setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getDriverseq() {
        return driverseq;
    }

    public void setDriverseq(Long driverseq) {
        this.driverseq = driverseq;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    @Override
    public String toString() {
        return "LineUser{" +
                "serialID=" + serialID +
                ", userId='" + userId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", tag='" + tag + '\'' +
                ", createDate='" + createDate + '\'' +
                ", driverseq=" + driverseq +
                '}';
    }

}
