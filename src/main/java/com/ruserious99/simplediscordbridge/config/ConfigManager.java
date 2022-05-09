package com.ruserious99.simplediscordbridge.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    public static String GetKeyValue(String keyValue){

        Properties properties = new Properties();
        try{
            InputStream inputStream = new FileInputStream("config.yml");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(keyValue);
    }
}
