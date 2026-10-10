package com.taxistore.service;

import com.taxistore.pojo.Driver;
import com.taxistore.dto.DriverDTO;
import com.taxistore.pojo.Result;

import java.util.List;

/**
 * Created by luckf on 2022/10/25.
 */
public interface IDriverService
{
    public Result<Long> insertDriver(DriverDTO c);
    public Result<Integer> updateDriver(DriverDTO c);
    public Result<Driver> getFirstDriver();
    public Result<Driver> getLastDriver();
    public Result<Driver> getNextDriver(long serialNo);
    public Result<Driver> getDriver(long serialNo);
    public Result<Driver> getPreviousDriver(long serialNo);
    public Result<DriverDTO> getFirstDriverDTO();
    public Result<DriverDTO> getLastDriverDTO();
    public Result<DriverDTO> getNextDriverDTO(long serialNo);
    public Result<DriverDTO> getDriverDTO(long serialNo);
    public Result<DriverDTO> getPreviousDriverDTO(long serialNo);
    public Result<Driver> deleteDriver(long serialNo);
    public Result<List<Driver>> getDrivers();
    public Result<List<DriverDTO>> getDriversBySpecialCode(String specialCode, long start, long length);
    public Result<List<DriverDTO>> getDriversByDriverId(String driverId, Integer start, Integer length);
}
