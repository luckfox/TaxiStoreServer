package com.taxistore.service;

import com.taxistore.pojo.Car;
import com.taxistore.pojo.Result;

/**
 * @author luckf
 */
public interface ICarService
{

    public Result<Long> insertCar(Car c);
    public Result<Integer> updateCar(Car c);


}
