package com.ruserious99.simplediscordbridge;

import commands.GiveRole;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class SimpleDiscordBridge extends JavaPlugin {

    private JDA jda;

    @Override
    public void onEnable() {

        registerCommands();

        JDABuilder builder = JDABuilder.createDefault("OTY3OTczNzkyNzY2ODkwMDk0.YmYF2A.kcmUkt446DlqbBMpEM1pxAC1onM");
        builder.setActivity(Activity.watching("You"));
        builder.setStatus(OnlineStatus.IDLE);
        builder.addEventListeners(new DiscordListener());
        try {
            jda = builder.build();
            System.out.println("Success");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        getCommand("give_role").setExecutor(new GiveRole(this));
    }

    public JDA getJda() {
        return jda;
    }
}
