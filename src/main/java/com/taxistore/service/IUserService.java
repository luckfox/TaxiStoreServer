package com.taxistore.service;

import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;

import java.util.List;

public interface IUserService
{
    public Result<Integer> login(Password password);
    public Result<Password> getUserNameByUserNo(Password user);

}
