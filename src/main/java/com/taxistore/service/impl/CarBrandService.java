package com.taxistore.service.impl;

import com.taxistore.dao.impl.CarBrandDao;
import com.taxistore.pojo.CarBrand;
import com.taxistore.pojo.Result;
import com.taxistore.service.ICarBrandService;

import java.util.List;

/**
 *
 * @author luckf
 * @date 2022/11/7
 */
public class CarBrandService implements ICarBrandService
{
    private final static CarBrandDao carBrandDao= new CarBrandDao();
    private final static String KeyName="carBrandId";
    @Override
    public Result<List<CarBrand>> getCarBrandBySpecialCode(String specialCode)
    {
        return carBrandDao.getEntitiesByFieldLike("code",specialCode);
    }
    @Override
    public Result<List<CarBrand>> getAllBrands()
    {
        return carBrandDao.getEntities();
    }
}
