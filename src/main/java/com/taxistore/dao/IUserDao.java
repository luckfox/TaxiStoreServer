package com.taxistore.dao;

import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;

import java.util.List;

public interface IUserDao
{
    public Result<Integer> validateUser(Password password);
    public Result<Password> getUserNameByUserNo(Password password);
}
