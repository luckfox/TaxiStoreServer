package com.taxistore.dao.impl;

import com.taxistore.dao.ICarBrandDao;
import com.taxistore.pojo.CarBrand;
import com.taxistore.pojo.Company;
import com.taxistore.pojo.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luckf
 * @date 2022/11/7
 */
public class CarBrandDao extends AbstractFormDao<CarBrand> implements ICarBrandDao
{
    private static final String TABLE_NAME = "carBrand";
    private static final String KEY_NAME = "CARBRANDID";
    public CarBrandDao()
    {
        super(CarBrand.class,TABLE_NAME,KEY_NAME );
    }


    public static void main(String[] args) {
        CarBrandDao dao=new CarBrandDao();
        Result<List<CarBrand>> result=dao.getItems();
        Result<List<CarBrand>> result1=dao.getEntitiesByFieldLike("code","eb");

    }
}
