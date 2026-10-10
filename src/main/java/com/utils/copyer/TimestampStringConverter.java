package com.utils.copyer;

import com.utils.FormattedMatcher;
import com.utils.utils.DateUtils;
import org.apache.commons.beanutils.Converter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luckf on 2022/11/11.
 */
public class TimestampStringConverter  implements Converter
{
    private static final String[] patterns={"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy/MM/dd","yyyy/MM/dd HH:mm:ss"};
    @Override
    public <T> T convert(Class<T> type, Object value)
    {
        Date theDate=null;

        if(type.equals(Timestamp.class) && String.class.isInstance(value))
        {
            //dst型態為Date,來源端為string
            FormattedMatcher formattedMatcher=new FormattedMatcher();
            if(formattedMatcher.YYYY_MM_Matcher((String)value)) value=value+"-01";
            for(int i=0;i<patterns.length;i++)
            {
                if(null!=(theDate=isDate((String )value,patterns[i]))) break;
            }
            if(theDate==null) return null;
            return (T) new Timestamp(theDate.getTime());

        } else if(type.equals(String.class) && Date.class.isInstance(value))
        {
            return (T)DateUtils.format((Date)value);
        }return (T)value;
    }
    private static Date isDate(String param,String pattern)
    {
        DateFormat formate=new SimpleDateFormat(pattern);
        Date date=null;
        if(param==null) return null;

        try
        {
            date=formate.parse(param);
        } catch (ParseException e)
        {
            //e.printStackTrace();
            return null;
        }
        return date;
    }
}
