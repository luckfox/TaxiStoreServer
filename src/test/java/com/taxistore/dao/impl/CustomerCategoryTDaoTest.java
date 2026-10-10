package com.taxistore.dao.impl;

import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.Test;


import java.util.List;


public class CustomerCategoryTDaoTest
{
    CustomerCategoryTDao customerCategoryTDao=new CustomerCategoryTDao();
    @Test
    public void updateCustomerCategory()
    {
        CustomerCategory cc=new CustomerCategory();
        cc.setCustCateName("銀行");
        cc.setCustCateCode("uc");
        cc.setCustCateID(6);
        Result<Integer> r=customerCategoryTDao.updateEntity(cc);
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
        Result<Long> r=customerCategoryTDao.insertEntity(cc);

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