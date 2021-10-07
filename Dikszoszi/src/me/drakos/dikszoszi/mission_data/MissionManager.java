package me.drakos.dikszoszi.mission_data;

import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {

    private Main plugin;

    public MissionManager(Main plugin) {
        this.plugin = plugin;
        MissionCheck();
        MissionSave();
    }

    private void MissionCheck() {
        new BukkitRunnable(){
            @Override
            public void run() {

            }
        }.runTaskTimer(plugin,0,20);
    }

    private void MissionSave() {
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()){
                    if(ConfigManager.get().getConfig().getConfig().get("PlayerMission."+player.getName()) == null){
                        ConfigManager.get().getConfig().getConfig().getStringList("PlayerMission").add(player.getName());
                        ConfigManager.get().getConfig().getConfig().set("PlayerMission."+player.getName()+".MissionID", new ArrayList<>());
                        ConfigManager.get().getConfig().saveConfig();
                        ConfigManager.get().getConfig().reloadConfig();
                    } else {
                        List<String> missionTypeList = new ArrayList<>();
                        if(plugin.getPlayerMissions.get(player) != null){
                            for (MissionData.MissionType missionType : plugin.getPlayerMissions.get(player)) {
                                missionTypeList.add(missionType.name());
                                ConfigManager.get().getConfig().getConfig().set("PlayerMission."+player.getName()+".MissionID",missionTypeList);
                            }
                        }

                    }

                    ConfigManager.get().getConfig().saveConfig();
                    ConfigManager.get().getConfig().reloadConfig();
                }
            }
        }.runTaskTimer(plugin,0,20);
    }
}
