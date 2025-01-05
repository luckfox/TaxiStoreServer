package com.taxistore.service.impl;

import com.taxistore.pojo.Driver;
import com.utils.Log;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest
{


    @Test
    void getDrivers()
    {
        DriverService driverService = new DriverService();
        List<Driver> d=driverService.getDrivers();

        Log.normal("getDriver");

    }
}