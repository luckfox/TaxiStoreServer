package com.taxistore.service.impl;

import com.taxistore.pojo.Driver;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.testng.annotations.Test;

import java.util.List;


public class DriverServiceTest
{


    @Test
    void getDrivers()
    {
        DriverService driverService = new DriverService();
        Result<List<Driver>> d=driverService.getDrivers();

        Log.normal("getDriver");

    }
}