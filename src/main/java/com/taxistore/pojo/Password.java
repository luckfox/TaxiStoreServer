package com.taxistore.pojo;

import java.sql.Timestamp;

/**
 * @author luckf
 */
public class Password
{

    private long id;
    private Timestamp useDate;
    private String userNo;
    private String userName;
    private String password;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getUserNo()
    {
        return userNo;
    }

    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    public Timestamp getUseDate()
    {
        return useDate;
    }

    public void setUseDate(Timestamp useDate)
    {
        this.useDate = useDate;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", useDate=" + useDate +
                ", userNo='" + userNo + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
