package com.ruserious99.simplediscordbridge;

import com.ruserious99.simplediscordbridge.commands.GiveRole;
import com.ruserious99.simplediscordbridge.commands.RemoveRole;
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

        registerCommands();

        JDABuilder builder = JDABuilder.create("OTY3OTczNzkyNzY2ODkwMDk0.YmYF2A.wfrKhqCZd-c1723mKlokm_k0fZU",
                GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES);
        builder.setActivity(Activity.watching("You"));
        builder.setStatus(OnlineStatus.IDLE);
        builder.addEventListeners(new DiscordListener());
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        System.out.println("Success");

    }

    private void registerCommands() {
        getCommand("removerole").setExecutor(new RemoveRole(this));
        getCommand("giverole").setExecutor(new GiveRole(this));
    }

    public JDA getJda() {
        return jda;
    }
}
