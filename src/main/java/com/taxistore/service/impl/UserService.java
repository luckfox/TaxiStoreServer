package com.taxistore.service.impl;

import com.taxistore.dao.impl.UserDao;
import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.taxistore.service.IUserService;
import com.utils.Log;

import java.util.List;

/**
 * @author luckf
 */
public class UserService implements IUserService
{
    private UserDao userDao=new UserDao();

    public UserService() {

    }

    @Override
    public Result<Integer> login(Password user)
    {
        return userDao.validateUser(user);
    }
    /*
        根據userNo取得相關的password資料表的內容
     */
    @Override
    public Result<Password> getUserNameByUserNo(Password user)
    {
        return userDao.getUserNameByUserNo(user);
    }
    public Result<Password> getUserName(Password user)
    {
        return userDao.getUserNameById(user);
    }
    public static void main(String[] args)
    {
        Password password=new Password();
        UserService service=new UserService();
        password.setUserNo("0000");

        Result<Password> result=service.getUserNameByUserNo(password);
        password.setId(15L);
        Result<Password> result3=service.getUserName(password);
        password.setPassword("3795");
        Result<Integer> result2=service.login(password);

        if(result.isSuccess())
        {
            Log.normal(result.toJSON());
        }

    }
}
