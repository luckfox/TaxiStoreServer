package com.taxistore.dao;

import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;

import java.util.List;

public interface ICustomerCategoryTDao
{
    public Result<Integer> updateCustomerCategory(CustomerCategory c);
    public Result<Long> addCustomerCategory(CustomerCategory c);
    public Result<CustomerCategory> getCustomerCategory(long serialNo);
    public Result<List<CustomerCategory>> getCustomerCategoryBySpecialCode(String specialCode);
}

