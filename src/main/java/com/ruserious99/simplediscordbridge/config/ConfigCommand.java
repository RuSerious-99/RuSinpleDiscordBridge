package com.ruserious99.simplediscordbridge.config;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;

public class ConfigCommand {

    private SimpleDiscordBridge simpleDiscordBridge;

    public ConfigCommand(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    public String getToken() {return simpleDiscordBridge.getConfig().getString("token");}
    public String getGeneralTextChannel() {
        return simpleDiscordBridge.getConfig().getString("general_text_channel");
    }
    public String getGuildId() {
        return simpleDiscordBridge.getConfig().getString("guild_id");
    }
    public String getAdmin() {
        return simpleDiscordBridge.getConfig().getString("admin");
    }
    public String getAdmin_Id() {
        return simpleDiscordBridge.getConfig().getString("admin_id");
    }
    public String getMember() {
        return simpleDiscordBridge.getConfig().getString("member");
    }
    public String getMemberId() {
        return simpleDiscordBridge.getConfig().getString("member_id");
    }
    public String getHelper() {
        return simpleDiscordBridge.getConfig().getString("helper");
    }
    public String getHelperId() {
        return simpleDiscordBridge.getConfig().getString("helper_id");
    }

}



