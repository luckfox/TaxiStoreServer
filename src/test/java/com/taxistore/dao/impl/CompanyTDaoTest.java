package com.taxistore.dao.impl;

import com.taxistore.pojo.Company;
import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTDaoTest {

    CompanyTDao companyTDao=new CompanyTDao();
    @Test
    void updateCompany()
    {
        Company c=new Company();

    }

    @Test
    void addCompany()
    {
        Company c=new Company();
        c.setCompanyname("中央銀行");
        c.setSpecialcode("5uu");
        Result<Long> r=companyTDao.addCompany(c);
        if(r.isSuccess())
        {
            Log.normal("success written "+((Long)r.getData()).toString() +" record");
        }else Log.normal(r.getErrorMessage());
    }


    @Test
    void getPreviousCompany()
    {
        Result<CompanyDTO> r=companyTDao.getPreviousCompany(100);
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    void getCompaniesBySpecialCode()
    {
        Result<List<CompanyDTO>> r=companyTDao.getCompaniesBySpecialCode("25m");
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    void getNextCompany()
    {
        Result<CompanyDTO> r=companyTDao.getNextCompany(100);
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }


    @Test
    void getLastCompany()
    {
        Result<CompanyDTO> r=companyTDao.getLastCompany();
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    void getFirstCompany()
    {
        Result<CompanyDTO> r=companyTDao.getFirstCompany();
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }
}