package com.taxistore.dao.impl;

import com.taxistore.pojo.Company;
import com.taxistore.dto.CompanyDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.Test;

import java.util.List;



public class CompanyTDaoTest {

    CompanyDao companyDao=new CompanyDao();
    @Test
    public void updateCompany()
    {
        Company c=new Company();

    }

    @Test
    public void addCompany()
    {
        Company c=new Company();
        c.setCompanyname("中央銀行2");
        c.setSpecialcode("5uu");
        Result<Long> r=companyDao.insertEntity(c);
        if(r.isSuccess())
        {
            Log.normal("success written "+((Long)r.getData()).toString() +" record");
        }else Log.normal(r.getErrorMessage());
    }


    @Test
    public void getPreviousCompany()
    {
        Result<CompanyDTO> r=companyDao.getPreviousCompanyDTO(100L);
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    public void getCompaniesBySpecialCode()
    {
        Result<List<CompanyDTO>> r=companyDao.getCompaniesDTOBySpecialCode("25m");
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    public void getNextCompany()
    {
        Result<CompanyDTO> r=companyDao.getNextCompanyDTO(100L);
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }


    @Test
    public void getLastCompany()
    {
        Result<CompanyDTO> r=companyDao.getLastCompanyDTO();
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }

    @Test
    public void getFirstCompany()
    {
        Result<CompanyDTO> r=companyDao.getFirstCompanyDTO();
        if(r.isSuccess())
        {
            Log.normal("success !"+r.getData().toString());
        }else
        {
            Log.normal(r.getErrorMessage());
        }
    }
}