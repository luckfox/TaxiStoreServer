package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Log {
    // 日誌分級 Enum
    public enum Level { ERROR, WARNING, NORMAL, DEBUG };

    // 配置
    private static final Properties config = new Properties();

    static {
        try (InputStream input = Log.class.getClassLoader().getResourceAsStream("log.properties")) {
            if (input != null) {
                config.load(input);
            } else {
                System.err.println("Could not find log.properties. Using default settings.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 確保 System.out 使用 UTF-8
    private static final PrintStream utf8Out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    // 日誌方法
    public static void log(Level level, String message) {
        StringBuilder logMessage = new StringBuilder();

        // 時間戳
        if (Boolean.parseBoolean(config.getProperty("timestamp", "true"))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            logMessage.append("[").append(timestamp).append("]");
        }

        // 等級
        if (Boolean.parseBoolean(config.getProperty("level", "true"))) {
            String sLevel = switch (level) {
                case ERROR -> "E";
                case NORMAL -> "N";
                case DEBUG -> "D";
                case WARNING -> "W";
            };
            logMessage.append("[").append(sLevel).append("]");
        }

        // 類別和方法名稱
        StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
        if (Boolean.parseBoolean(config.getProperty("classname", "true"))) {
            String simpleClassName = caller.getClassName().substring(caller.getClassName().lastIndexOf(".") + 1);
            logMessage.append("[").append(simpleClassName).append("]");
        }

        if (Boolean.parseBoolean(config.getProperty("methodname", "true"))) {
            logMessage.append("[").append(caller.getMethodName()).append("]");
        }

        // 訊息
        if (Boolean.parseBoolean(config.getProperty("message", "true"))) {
            logMessage.append(message);
        }

        // UTF-8 輸出
        try {
            utf8Out.write((logMessage.toString().trim() + "\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 快捷方法：ERROR
    public static void error(String message) {
        log(Level.ERROR, message);
    }

    public static void debug(String message) {
        log(Level.DEBUG, message);
    }

    // 快捷方法：WARNING
    public static void warning(String message) {
        log(Level.WARNING, message);
    }

    // 快捷方法：NORMAL
    public static void normal(String message) {
        log(Level.NORMAL, message);
    }
}
