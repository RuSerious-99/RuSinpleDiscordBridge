package com.ruserious99.simplediscordbridge;

import com.ruserious99.simplediscordbridge.config.ConfigFile;
import com.ruserious99.simplediscordbridge.config.ConfigValues;
import com.ruserious99.simplediscordbridge.listeners.DiscordListener;
import com.ruserious99.simplediscordbridge.commands.GiveRole;
import com.ruserious99.simplediscordbridge.commands.RemoveRole;
import com.ruserious99.simplediscordbridge.discord_only_commands.CommandManager;
import com.ruserious99.simplediscordbridge.listeners.Welcome;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class SimpleDiscordBridge extends JavaPlugin {

    private JDA jda;

    @Override
    public void onEnable() {

        ConfigFile.loadConfig();
        //ConfigValues.loadKeyValues();

        JDABuilder builder = JDABuilder.create(ConfigValues.TOKEN, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES);
        builder.setActivity(Activity.watching("You"));
        builder.setStatus(OnlineStatus.IDLE);
        builder.addEventListeners(new DiscordListener());
        builder.addEventListeners(new Welcome());
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        try {
            jda = builder.build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Success");

        registerCommands();

    }

    private void registerCommands() {
        //discord
        CommandManager commandManager = new CommandManager();
        jda.addEventListener(commandManager);


        //Minecraft
        getCommand("removerole").setExecutor(new RemoveRole(this));
        getCommand("giverole").setExecutor(new GiveRole(this));
    }

    public JDA getJda() {
        return jda;
    }
}
