package com.taxistore.service;

import com.taxistore.dao.impl.CompanyTDao;
import com.taxistore.pojo.Company;
import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public interface ICompanyTService
{
    public Boolean existCompany();
    public Result<CompanyDTO> getCompany(long serialNo);
    public Result<Long> addCompany(Company c);
    public Result<Integer> updateCompany(Company c);
    public Result<CompanyDTO> getLastCompany();
    public Result<CompanyDTO> getFirstCompany();
    public Result<CompanyDTO> getNextCompany(long serialNo);
    public Result<CompanyDTO> getPreviousCompany(long serialNo);
    public Result<CompanyDTO> deleteCompany(long serialNo);
    public Result<List<CompanyDTO>> getCompaniesBySpecialCode(String specialCode);
}
