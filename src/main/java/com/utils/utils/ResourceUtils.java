package com.utils.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ResourceUtils {
    public static void listResources(String directory) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources(directory);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                System.out.println("Resource found: " + resource.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
