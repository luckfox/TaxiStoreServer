package com.taxistore.dao;

import com.taxistore.dto.CompanyDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public interface ICompanyDao
{

    public Result<CompanyDTO> getCompanyDTO(long serialNo);
    public Result<CompanyDTO> getNextCompanyDTO(long serialNo);
    public Result<CompanyDTO> getPreviousCompanyDTO(long serialNo);
    public Result<CompanyDTO> getLastCompanyDTO();
    public Result<CompanyDTO> getFirstCompanyDTO();
    public Result<List<CompanyDTO>> getCompaniesDTOBySpecialCode(String specialCode);
}
