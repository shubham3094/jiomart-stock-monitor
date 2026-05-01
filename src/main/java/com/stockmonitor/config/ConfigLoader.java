package com.stockmonitor.config;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}