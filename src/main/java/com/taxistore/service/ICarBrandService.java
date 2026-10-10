package com.taxistore.service;

import com.taxistore.pojo.CarBrand;
import com.taxistore.pojo.Result;

import java.util.List;

public interface ICarBrandService
{
    public Result<List<CarBrand>> getCarBrandBySpecialCode(String specialCode);
    public Result<List<CarBrand>> getAllBrands();
}
