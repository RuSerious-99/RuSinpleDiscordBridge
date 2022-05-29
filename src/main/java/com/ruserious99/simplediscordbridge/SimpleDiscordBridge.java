package com.ruserious99.simplediscordbridge;

import com.ruserious99.simplediscordbridge.commands.GiveRole;
import com.ruserious99.simplediscordbridge.commands.RemoveRole;
import com.ruserious99.simplediscordbridge.config.ConfigCommand;
import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import com.ruserious99.simplediscordbridge.discord_only_commands.CommandManager;
import com.ruserious99.simplediscordbridge.events.DeleteMessageEvent;
import com.ruserious99.simplediscordbridge.events.ReactionAddEvent;
import com.ruserious99.simplediscordbridge.listeners.ButtonClick;
import com.ruserious99.simplediscordbridge.listeners.DiscordListener;
import com.ruserious99.simplediscordbridge.listeners.Welcome;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public final class SimpleDiscordBridge extends JavaPlugin {

    private JDA jda;
    private ConfigCommand configCommand;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        configCommand = new ConfigCommand(this);
        JDABuilder builder = JDABuilder.create(configCommand.getToken(), GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES);
        builder.setActivity(Activity.watching("You"));
        builder.setStatus(OnlineStatus.IDLE);
        builder.addEventListeners(new DiscordListener(this));
        builder.addEventListeners(new Welcome(this));
        builder.addEventListeners(new ButtonClick());
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        try {
            jda = builder.build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Success");
        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.disconnect();
    }

    private void registerEvents() {
        ReactionAddEvent reactionAddEvent = new ReactionAddEvent();
        jda.addEventListener(reactionAddEvent);
        DeleteMessageEvent deleteMessageEvent = new DeleteMessageEvent();
        jda.addEventListener(deleteMessageEvent);

    }

    private void registerCommands() {
        //discord
        CommandManager commandManager = new CommandManager();
        jda.addEventListener(commandManager);

        //Minecraft
        Objects.requireNonNull(getCommand("removerole")).setExecutor(new RemoveRole(this));
        Objects.requireNonNull(getCommand("giverole")).setExecutor(new GiveRole(this));
    }

    public ConfigCommand getConfigCommand() {
        return configCommand;
    }

    public JDA getJda() {
        return jda;
    }
}
