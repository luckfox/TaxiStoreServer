package com.taxistore.dao;

import com.taxistore.dto.DriverDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public interface IDriverDao
{
    public Result<DriverDTO> getNextDriverDTO(Long driverseq);
    public Result<DriverDTO> getPreviousDriverDTO(Long driverseq);
    public Result<DriverDTO> getFirstDriverDTO();
    public Result<DriverDTO> getLastDriverDTO();
    public Result<DriverDTO> getDriverDTO(Long driverseq);
    public Result<List<DriverDTO>> getDriversBySpecialCode(String specialCode);
    public Result<List<DriverDTO>> getDriversBySpecialCode(String specialCode, long start, long length);
    public Result<List<DriverDTO>> getDriversByDriverId(String driverId);
    public Result<List<DriverDTO>> getDriversByDriverId(String driverId, long start, long length);
}