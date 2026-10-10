package com.taxistore.service.impl;
import com.taxistore.dao.impl.CarDao;
import com.taxistore.pojo.Car;
import com.taxistore.pojo.Result;
import com.taxistore.service.ICarService;

import java.util.List;

/**
 *
 * @author luckf
 * @date 2022/10/25
 * @function 提供Table car的相關功能。
 */
public class CarService  implements ICarService
{
    private final static CarDao carDao= new CarDao();

    private final static String TableName="car";
    private  static String KeyName="carseq";

    public CarService()
    {

    }

    @Override
    public Result<Long> insertCar(Car c)
    {
        return carDao.insertEntity(c);
    }

    @Override
    public Result<Integer> updateCar(Car c)
    {

        return carDao.updateEntity(c);

    }
    public Result<List<Car>> getCarsByCarnoLike(String carnoPart)
    {
        return carDao.getCarsByCarnoLike(carnoPart);
    }


}
