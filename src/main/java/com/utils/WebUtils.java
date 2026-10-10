package com.utils;

import com.utils.copyer.DateStringConverter;
import com.utils.copyer.TimestampStringConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class WebUtils
{
    private final static Boolean Debug=false;
    private static boolean custom_covert_registed=false;
    /**
     * 把Map中的值注入到对应的JavaBean属性中。
     * @param value 來源資料
     * @param bean 目標bean
     */
    public static <T> T copyParamToBean( Map value , T bean )
    {
        if(!custom_covert_registed)
        {
            ConvertUtils.register(new DateStringConverter(), Date.class);
            ConvertUtils.register(new TimestampStringConverter(), Timestamp.class);
            custom_covert_registed=true;
        }
        try
        {
            if(Debug) System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到bean对象中
             */
            BeanUtils.populate(bean, value);//將value inject to bean
            if(Debug) System.out.println("注入之后：" + bean);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue)
    {
        try
        {
            return Integer.parseInt(strInt);
        } catch (Exception e)
        {
//            e.printStackTrace();
        }
        return defaultValue;
    }

    public static String getParamter(String s)
    {
        if(null==s) return "";
        return s;
    }
    public static int stringToInt(String param)
    {
        try
        {
            return Integer.valueOf(param);
        } catch(NumberFormatException e)
        {
            return -1;
        }
    }
    public static Long stringToLong(String param)
    {
        try
        {
            return Long.valueOf(param);
        } catch(NumberFormatException e)
        {
            return -1L;
        }
    }

}
