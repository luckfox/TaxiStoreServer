package com.taxistore.config;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest
{

    @Test
    void testConnection()
    {
        DatabaseManager.setProviderType(DatabaseManager.ProviderType.DBCONFIG);
        try
        {
            DatabaseManager.getConnection();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}