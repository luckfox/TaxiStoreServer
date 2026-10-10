package com.utils;

import java.util.regex.Pattern;

/**
 * Created by luckf on 2022/11/10.
 */
public class FormattedMatcher implements Matcher
{
    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static Pattern DATE_PATTERN2 = Pattern.compile("^(?:(?:1[6-9]|[2-9][0-9])[0-9]{2}([-/.]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:(?:1[6-9]|[2-9][0-9])(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)([-/.]?)0?2\2(?:29))$");

    private static Pattern yyyy_MM_PATTERN = Pattern.compile("^(201[1-9]|202[0-9])-(0[1-9]|1[0-2])$");


    @Override
    public boolean DateMatcher(String date)
    {
        return DATE_PATTERN2.matcher(date).matches();
    }

    @Override
    public boolean YYYY_MM_Matcher(String date)
    {
        return yyyy_MM_PATTERN.matcher(date).matches();
    }
}
