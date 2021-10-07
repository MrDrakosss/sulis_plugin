package me.drakos.dikszoszi.command;

import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.menu.ClassMenu;
import me.drakos.dikszoszi.menu.MissionMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mission implements CommandExecutor {

    private final Main plugin;

    public Mission(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;

        new MissionMenu(p, plugin);
        return false;
    }
}


