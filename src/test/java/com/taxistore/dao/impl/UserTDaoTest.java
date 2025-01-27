package com.taxistore.dao.impl;

import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTDaoTest {

    UserTDao userDao=new UserTDao();
    @Test
    void getUserNameByUserNo() {
        Result<Password> r=userDao.getUserNameByUserNo("1000");
        if(r.isSuccess()) {
            Log.normal("success");
        }
        else
        {
            Log.normal(r.getErrorMessage());
        }
    }
}