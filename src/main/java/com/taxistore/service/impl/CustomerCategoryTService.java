package com.taxistore.service.impl;

import com.taxistore.dao.impl.CustomerCategoryTDao;
import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;
import com.taxistore.service.ICustomerCategoryTService;

import java.util.List;

/**
 * @author luckf
 */
public class CustomerCategoryTService implements ICustomerCategoryTService
{
    private final CustomerCategoryTDao customerCategoryTDao=new CustomerCategoryTDao();
    private final String KeyName="CustCateID";
    @Override
    public Result<CustomerCategory> getCustomerCategory(long serialNo)
    {
        Result<CustomerCategory> result= customerCategoryTDao.getCustomerCategory(serialNo);
        if(result.isSuccess() && result.getData()==null)
            result.setSuccess(false).setErrorMessage("database.query.norecord","");
        return result;
    }

    @Override
    public Result<Long> addCustomerCategory(CustomerCategory c)
    {
        return customerCategoryTDao.addCustomerCategory(c);
    }

    @Override
    public Result<Integer> updateCustomerCategory(CustomerCategory c)
    {
        return customerCategoryTDao.updateCustomerCategory(c);
    }

    @Override
    public Result<List<CustomerCategory>> getCustomerCategoryBySpecialCode(String specialCode)
    {
        Result<List<CustomerCategory>> result= customerCategoryTDao.getCustomerCategoryBySpecialCode(specialCode);
        if(result.isSuccess() && result.getData()==null)
            result.setSuccess(false).setErrorMessage("database.query.norecord","");
        return result;
    }
}
