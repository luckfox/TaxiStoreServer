package com.taxistore.service.impl;

import com.taxistore.dao.impl.CompanyTDao;
import com.taxistore.pojo.Company;
import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.ICompanyTService;

import java.util.List;

/**
 * @author luckf
 */
public class CompanyTService implements ICompanyTService
{
    private final CompanyTDao companyTDao=new CompanyTDao();
    private final String KeyName="companyseq";
    @Override

    public Boolean existCompany() {
        return null;
    }

    @Override
    public Result<Long> addCompany(Company c)
    {
        long time = System.currentTimeMillis();
        java.sql.Timestamp createdate = new java.sql.Timestamp(time);
        c.setCreatedate(createdate);
        return companyTDao.addCompany(c);

    }

    @Override
    public Result<Integer> updateCompany(Company c)
    {
        return companyTDao.updateCompany(c);
    }

    @Override
    public Result<CompanyDTO> getLastCompany()
    {
        return companyTDao.getLastCompany();
    }
    public Result<CompanyDTO> getFirstCompany()
    {
        return companyTDao.getFirstCompany();
    }
    @Override
    public Result<CompanyDTO> getNextCompany(long serialNo)
    {
        Result <CompanyDTO> result=companyTDao.getNextCompany(serialNo);
        if(result.isSuccess() && result.getData()==null)
        {
            //雖然SQL執行成功，但是卻沒有得到值，所以已經是最後一筆資料。
            result.setSuccess(false).setErrorMessage("database.query.lastrecord","");
        }
        return result;
    }

    @Override
    public Result<CompanyDTO> getPreviousCompany(long serialNo)
    {
        Result <CompanyDTO> result=companyTDao.getPreviousCompany(serialNo) ;
        if(result.isSuccess() && result.getData()==null)
        {
            //雖然SQL執行成功，但是卻沒有得到值，所以已經是最後一筆資料。
            result.setSuccess(false).setErrorMessage("database.query.firstrecord","");
        }
        return result;
    }

    @Override
    public Result<CompanyDTO> deleteCompany(long serialNo)
    {
        Result<CompanyDTO> c_result=new Result<CompanyDTO>();
        Result<Integer> result=companyTDao.deleteCompanyBySerialNo(serialNo);

        if(result.isSuccess() && (Integer)result.getData()!=0)
        {
            //表示，成功刪除。
            c_result = companyTDao.getPreviousCompany(serialNo);
            if (c_result.isSuccess() && c_result.getData() != null) return c_result;

            c_result = companyTDao.getNextCompany(serialNo);
            if (c_result.isSuccess() && c_result.getData() != null) return c_result;

        } else
        {
            c_result.setErrorMessage("database.delete.fail","");
        }
        return c_result;
    }
    @Override
    public Result<CompanyDTO> getCompany(long serialNo)
    {
        Result<CompanyDTO> result= companyTDao.getCompany(serialNo);
        if(result.isSuccess() && result.getData()==null)
            result.setSuccess(false).setErrorMessage("database.query.norecord","");
        return result;

    }

    @Override
    public Result<List<CompanyDTO>> getCompaniesBySpecialCode(String specialCode)
    {
        Result<List<CompanyDTO>> result=companyTDao.getCompaniesBySpecialCode(specialCode);
        if(result.isSuccess() && result.getData()==null)
            result.setSuccess(false).setErrorMessage("database.query.norecord","");
        return result;

    }
}
