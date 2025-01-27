package com.taxistore.dao;

import com.taxistore.pojo.Company;
import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public interface ICompanyTDao
{
    public Result<Integer> updateCompany(Company c);
    public Result<Long> addCompany(Company c);

    public Result<CompanyDTO> getCompany(long serialNo);

    public Result<CompanyDTO> getNextCompany(long serialNo);
    public Result<CompanyDTO> getPreviousCompany(long serialNo);
    public Result<CompanyDTO> getLastCompany();
    public Result<CompanyDTO> getFirstCompany();
    public Result<Integer> deleteCompanyBySerialNo(long serialNo);
    public Result<List<CompanyDTO>> getCompaniesBySpecialCode(String specialCode);
}
