package com.taxistore.dao.impl;

import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCategoryTDaoTest
{
    CustomerCategoryTDao customerCategoryTDao=new CustomerCategoryTDao();
    @Test
    void updateCustomerCategory()
    {
        CustomerCategory cc=new CustomerCategory();
        cc.setCustCateName("銀行");
        cc.setCustCateCode("uc");
        cc.setCustCateID(6);
        Result<Integer> r=customerCategoryTDao.updateCustomerCategory(cc);
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    void addCustomerCategory()
    {
        CustomerCategory cc=new CustomerCategory();
        cc.setCustCateName("銀行");
        cc.setCustCateCode("uc");
        Result<Long> r=customerCategoryTDao.addCustomerCategory(cc);

        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    void getCustomerCategory()
    {
        Result<CustomerCategory> r=customerCategoryTDao.getCustomerCategory(3);
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    void getCustomerCategoryBySpecialCode()
    {
        Result<List<CustomerCategory>> r=customerCategoryTDao.getCustomerCategoryBySpecialCode("TC");
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }
}