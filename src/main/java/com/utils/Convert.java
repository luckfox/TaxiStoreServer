package com.utils;

import java.util.Map;

public class Convert
{
    /**
     * 將 Map 轉換為 JSON 字符串。
     *
     * @param messages 包含語言鍵值對的 Map
     * @return JSON 字符串
     */
    public static String convertToJson(Map<String, String> messages) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        for (Map.Entry<String, String> entry : messages.entrySet()) {
            jsonBuilder.append("\"")
                    .append(entry.getKey())
                    .append("\":\"")
                    .append(entry.getValue())
                    .append("\",");
        }
        if (!messages.isEmpty()) {
            jsonBuilder.setLength(jsonBuilder.length() - 1);
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
