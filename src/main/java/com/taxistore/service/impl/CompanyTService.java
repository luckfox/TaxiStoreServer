package com.taxistore.service.impl;


import com.taxistore.dao.impl.CompanyDao;
import com.taxistore.pojo.Company;
import com.taxistore.dto.CompanyDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.ICompanyTService;

import java.util.List;

/**
 * @author luckf
 */
public class CompanyTService implements ICompanyTService
{
    private final CompanyDao companyDao=new CompanyDao();
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

        return null;

    }

    @Override
    public Result<Integer> updateCompany(Company c)
    {
        return companyDao.updateEntity(c);
    }

    @Override
    public Result<CompanyDTO> getLastCompany()
    {
        return companyDao.getLastCompanyDTO();
    }
    public Result<CompanyDTO> getFirstCompany()
    {
        return companyDao.getFirstCompanyDTO();
    }
    @Override
    public Result<CompanyDTO> getNextCompany(long serialNo)
    {
        Result <CompanyDTO> result=companyDao.getNextCompanyDTO(serialNo);
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
        Result <CompanyDTO> result=companyDao.getPreviousCompanyDTO(serialNo) ;
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

        Result<Integer> result=companyDao.deleteEntity(serialNo);
        if(result.isSuccess() && (Integer)result.getData()!=0)
        {
            //表示，成功刪除。
            Result<CompanyDTO> prev = companyDao.getPreviousCompanyDTO(serialNo);
            if (prev.isSuccess() && prev.getData() != null) return prev;  //取得上一筆

            Result<CompanyDTO> next = companyDao.getNextCompanyDTO(serialNo);
            if (next.isSuccess() && next.getData() != null) return next;  //取得下一筆
            // 沒有前後公司，回傳空成功物件
            return new Result<>();
        }
        return new Result<>("database.delete.fail","");
    }
    @Override
    public Result<CompanyDTO> getCompany(long serialNo)
    {
        Result<CompanyDTO> result= companyDao.getCompanyDTO(serialNo);
        if(result.isSuccess() && result.getData()==null)
            result.setSuccess(false).setErrorMessage("database.query.norecord","");
        return result;

    }

    @Override
    public Result<List<CompanyDTO>> getCompaniesBySpecialCode(String specialCode)
    {
        Result<List<CompanyDTO>> result=companyDao.getCompaniesDTOBySpecialCode(specialCode);
        if(result.isSuccess() && result.getData()==null)
            result.setSuccess(false).setErrorMessage("database.query.norecord","");
        return result;

    }
}
