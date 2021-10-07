package me.drakos.dikszoszi.data;

import org.bukkit.entity.Player;

public class PlayerTabName {

    public static void setTabName(Player player, PlayerData.PlayerClass playerClass) {
        if (playerClass == PlayerData.PlayerClass.WARRIOR) {
            player.setPlayerListName("§b§lWARRIOR §f" + player.getName());
        }
        if (playerClass == PlayerData.PlayerClass.ARCHER) {
            player.setPlayerListName("§2§lARCHER §f" + player.getName());
        }
        if (playerClass == PlayerData.PlayerClass.MAGE) {
            player.setPlayerListName("§d§lMAGE §f" + player.getName());
        }
        if (playerClass == PlayerData.PlayerClass.UNKNOWN) {
            player.setPlayerListName("§a§l? §f" + player.getName());
        }
    }
}
