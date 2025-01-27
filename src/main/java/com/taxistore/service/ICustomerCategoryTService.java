package com.taxistore.service;

import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;

import java.util.List;

/**
 * @author luckf
 */
public interface ICustomerCategoryTService
{
    public Result<CustomerCategory> getCustomerCategory(long serialNo);
    public Result<Long> addCustomerCategory(CustomerCategory c);
    public Result<Integer> updateCustomerCategory(CustomerCategory c);
    public Result<List<CustomerCategory>> getCustomerCategoryBySpecialCode(String specialCode);
}
