package com.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {
    private final Properties props;

    public Config(Properties props) {
        this.props = props;
    }

    public String get(String key) {
        return props.getProperty(key);
    }

    public String getOrDefault(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public Map<String, String> getAll() {
        Map<String, String> map = new HashMap<>();
        for (String name : props.stringPropertyNames()) {
            map.put(name, props.getProperty(name));
        }
        return map;
    }

    public String toJSON() {
        return Convert.convertToJson(getAll());
    }

    @Override
    public String toString() {
        return props.toString();
    }
}