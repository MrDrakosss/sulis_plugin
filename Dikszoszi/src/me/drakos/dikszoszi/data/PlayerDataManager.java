package me.drakos.dikszoszi.data;

import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.utils.Config;
import me.drakos.dikszoszi.utils.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDataManager {

    public static void setPlayerStats(Player player, Main plugin) {
        if(ConfigManager.get().getConfig().getConfig().getStringList("PlayerData").contains(player.getName())) {
            plugin.getPlayerClass.put(player, PlayerData.PlayerClass.valueOf((String) ConfigManager.get().getConfig().getConfig().get("PlayerData." + player.getName() + ".Class")));
            plugin.getPlayerHealth.put(player, (Integer) ConfigManager.get().getConfig().getConfig().get("PlayerData." + player.getName() + ".Health"));
            plugin.getPlayerDamage.put(player, (Integer) ConfigManager.get().getConfig().getConfig().get("PlayerData." + player.getName() + ".Damage"));
            plugin.getPlayerLevel.put(player, (Integer) ConfigManager.get().getConfig().getConfig().get("PlayerData." + player.getName() + ".Level"));

            player.setMaxHealth(Double.valueOf(plugin.getPlayerHealth.get(player)));
            player.setHealth(player.getMaxHealth());


            PlayerTabName.setTabName(player, plugin.getPlayerClass.get(player));
        } else {
            PlayerTabName.setTabName(player, PlayerData.PlayerClass.UNKNOWN);
            player.setMaxHealth(2);
            player.setHealth(player.getMaxHealth());
        }
    }
}
