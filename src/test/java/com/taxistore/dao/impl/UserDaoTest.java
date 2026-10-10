package com.taxistore.dao.impl;
import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.Test;

import java.util.List;


public class UserDaoTest
{
    UserDao userDao=new UserDao();
    @Test
    public void validateUser()
    {
        Password password=new Password();
        password.setPassword("3795");
        password.setUserNo("0000");
        Result<Integer> result =userDao.validateUser(password);
        Log.normal("userNo=0000;password=3795:return="+result.isSuccess());
    }
    @Test
    public void getUserNameByUserNo()
    {
        Password password=new Password();
        password.setUserNo("0000");
        Result<Password> result=userDao.getUserNameByUserNo(password);
        Log.normal("userNo=0000;userName=");
    }
}