package com.ruserious99.simplediscordbridge;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class SimpleDiscordBridge extends JavaPlugin {

    private JDA jda;

    //OTY3OTczNzkyNzY2ODkwMDk0.YmYF2A.06kjTbpw-7NJSQnxB36bLTAVG18
    @Override
    public void onEnable() {

        JDABuilder builder =  JDABuilder.createDefault("OTY3OTczNzkyNzY2ODkwMDk0.YmYF2A.06kjTbpw-7NJSQnxB36bLTAVG18");
        builder.setActivity(Activity.watching("Mystic server"));
        try {
            jda = builder.build();
            System.out.println("Success");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
