package commands;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import net.dv8tion.jda.api.entities.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveRole implements CommandExecutor {

    private SimpleDiscordBridge simpleDiscordBridge;

    public GiveRole(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Guild guild = simpleDiscordBridge.getJda().getGuildById("969822792520454204");
            guild.addRoleToMember("642987928129241094", guild.getRoleById("635532547287285762"));

        }




        return false;
    }
}
