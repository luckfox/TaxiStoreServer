package com.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogTest
{

    @Test
    void normal()
    {
        Log.normal("中文輸出123");
    }
}