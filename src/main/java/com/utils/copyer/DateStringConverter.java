package com.utils.copyer;


import com.utils.FormattedMatcher;
import com.utils.utils.DateUtils;
import org.apache.commons.beanutils.Converter;

import java.util.Date;

public class DateStringConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value)
	{
		if(type.equals(Date.class) && String.class.isInstance(value))
		{
			//來源端為
			FormattedMatcher formattedMatcher=new FormattedMatcher();
			if(formattedMatcher.YYYY_MM_Matcher((String)value))
			{
				value=value+"-01";
			}
			return (T) DateUtils.convert((String)value);

		} else if(type.equals(String.class) && Date.class.isInstance(value))
		{
			return (T)DateUtils.format((Date)value);

		}
//
//		else if(type.equals(Integer.class) &&  ))
//		{
//			return (T)
//		}
		return (T)value;
	}


}