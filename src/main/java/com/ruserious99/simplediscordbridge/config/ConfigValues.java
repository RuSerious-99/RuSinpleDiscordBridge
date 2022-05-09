package com.ruserious99.simplediscordbridge.config;

public class ConfigValues {

    public static String TOKEN;


    public static void loadKeyValues(){
        TOKEN = ConfigManager.GetKeyValue("token");
    }
}
