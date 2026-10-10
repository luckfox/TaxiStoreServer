package com.taxistore.config;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConfigTest
{

    //DBConfig dbConfig=new DBConfig();
    @Test
    void getConnection()
    {
        try
        {
            DBConfig.getInstance().getConnection();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}