package com.ruserious99.simplediscordbridge.config;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;

public class ConfigCommand {

    private SimpleDiscordBridge simpleDiscordBridge;

    public ConfigCommand(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    public String getToken() {
        return simpleDiscordBridge.getConfig().getString("token");
    }


}



