package com.taxistore.service.impl;
import com.taxistore.dao.impl.DriverDao;
import com.taxistore.mapper.DriverMapper;
import com.taxistore.pojo.Driver;
import com.taxistore.dto.DriverDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.IDriverService;
import com.utils.Log;

import java.util.List;

/**
 *
 * @author luckf
 * @date 2022/10/25
 */
public class DriverService implements IDriverService
{
    private final static DriverDao driverDao=new DriverDao();
    private final static String KeyName="driverseq";
    private final static String TableName="driver";

    public DriverService()
    {

    }

    @Override
    public Result<Long> insertDriver(DriverDTO dto)
    {
        Driver driver = DriverMapper.INSTANCE.toDriver(dto);
        return driverDao.insertEntity(driver);
    }

    @Override
    public Result<Integer> updateDriver(DriverDTO dto)
    {
        Driver driver = DriverMapper.INSTANCE.toDriver(dto);
        return driverDao.updateEntity(driver);
    }

    @Override
    public Result<Driver> getDriver(long serialNo)
    {
        return driverDao.getEntity(serialNo);
    }
    @Override
    public Result<Driver> getLastDriver()
    {
        return driverDao.getLastEntity();
    }
    @Override
    public Result<Driver> getFirstDriver()
    {
        return driverDao.getFirstEntity();
    }
    @Override
    public Result<Driver> getNextDriver(long serialNo)
    {
        return driverDao.getNextEntity(serialNo);
    }
    @Override
    public Result<Driver> getPreviousDriver(long serialNo)
    {
        return driverDao.getPreviousEntity(serialNo);
    }
    @Override
    public Result<DriverDTO> getDriverDTO(long serialNo)
    {
        return driverDao.getDriverDTO(serialNo);
    }
    @Override
    public Result<DriverDTO> getLastDriverDTO()
    {
        return driverDao.getLastDriverDTO();
    }
    @Override
    public Result<DriverDTO> getFirstDriverDTO()
    {
        return driverDao.getFirstDriverDTO();
    }
    @Override
    public Result<DriverDTO> getNextDriverDTO(long serialNo)
    {
        return driverDao.getNextDriverDTO(serialNo);
    }
    @Override
    public Result<DriverDTO> getPreviousDriverDTO(long serialNo)
    {
        return driverDao.getPreviousDriverDTO(serialNo);
    }
    @Override
    public Result<List<DriverDTO>> getDriversBySpecialCode(String specialCode, long start, long length)
    {
        return driverDao.getDriversBySpecialCode(specialCode, start, length);
    }
    @Override
    public Result<List<DriverDTO>> getDriversByDriverId(String driverId, Integer start, Integer length) {
        return driverDao.getDriversByDriverId(driverId, start, length);
    }
    @Override
    public Result<Driver> deleteDriver(long serialNo)
    {

        Result<Integer> result=driverDao.deleteEntity(serialNo);
        if(result.isSuccess() && (Integer)result.getData()!=0)
        {
            //表示，成功刪除。
            Result<Driver> prev = driverDao.getPreviousEntity(serialNo);
            if (prev.isSuccess() && prev.getData() != null) return prev;  //取得上一筆

            Result<Driver> next = driverDao.getNextEntity(serialNo);
            if (next.isSuccess() && next.getData() != null) return next;  //取得下一筆
            // 沒有前後公司，回傳空成功物件
            return new Result<>();
        }
        return new Result<>("database.delete.fail","");

    }

    @Override
    public Result<List<Driver>> getDrivers()
    {
        return driverDao.getItems();
    }

    public static void main(String[] args)
    {
        DriverService service=new DriverService();
        Result<List<DriverDTO>>result=service.getDriversBySpecialCode("xg",0,10);
        Log.debug(result.toJSON());
    }

}

