package me.drakos.dikszoszi.command;

import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.menu.ClassMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pickaxe implements CommandExecutor {

    private final Main plugin;

    public Pickaxe(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;

        new ClassMenu(p, plugin);
        return false;
    }
}


