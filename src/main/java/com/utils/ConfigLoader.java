package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
//    private static final String LINE_USER_PROFILE = "line_user_profile.properties";
//    private static final String APPLICATION_PROPERTIES_FILE = "application.properties";
//
//    private static final Properties userProfiles = new Properties();
//    private static final Properties applicationProperties = new Properties();
//    private static String CHANNEL_ACCESS_TOKEN;

//    public static void loadUserProfiles() {
//        loadProperties(LINE_USER_PROFILE, userProfiles);
//        loadProperties(APPLICATION_PROPERTIES_FILE, applicationProperties);
//
//        CHANNEL_ACCESS_TOKEN = applicationProperties.getProperty("channel.access.token");
//        Log.debug("channel_access_token=" + CHANNEL_ACCESS_TOKEN);
//    }

    public static void load(String fileName, Properties properties) {
        try (InputStream propertiesStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (propertiesStream != null)
            {
                properties.load(propertiesStream);
            } else
            {
               // Log.warn("Properties file not found: " + fileName);
            }
        } catch (IOException e)
        {
            //Log.error("Failed to load properties file: " + fileName, e);
        }
    }

}
