package com.taxistore.dao.impl;

import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.utils.Log;

import org.testng.annotations.Test;

import java.util.List;

public class UserTDaoTest {

    UserDao userDao=new UserDao();

    @Test
    public void getUserNameByUserNo() {

        Password password=new Password();
        password.setUserNo("1000");
        Result<Password> r=userDao.getUserNameByUserNo(password);
        if(r.isSuccess()) {
            Log.normal("success");
        }
        else
        {
            Log.normal(r.getErrorMessage());
        }
    }
}