package me.drakos.dikszoszi.mission_data;

import me.drakos.dikszoszi.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class MissionListener implements Listener {

    private Main plugin;

    public MissionListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void blockbreak(BlockBreakEvent e) {
        if (e.getBlock() == null) {
            return;
        }
        if (e.getBlock().getType() == Material.STONE) {
            String s = plugin.getPlayerActiveMissions.get(e.getPlayer()).get(MissionData.MissionType.MINER);
            plugin.getPlayerActiveMissions.get(e.getPlayer()).put(MissionData.MissionType.MINER, s.split(";")[0] + ";" + (Integer.parseInt(s.split(";")[1]) + 1));
            Bukkit.broadcastMessage(plugin.getPlayerActiveMissions.get(e.getPlayer()).get(MissionData.MissionType.MINER));

            String s2 = plugin.getPlayerActiveMissions.get(e.getPlayer()).get(MissionData.MissionType.MINER);
            if (Integer.parseInt(s2.split(";")[0]) == Integer.parseInt(s2.split(";")[1])) {
                e.getPlayer().sendMessage("§aMiner mission complette");
                List<MissionData.MissionType> mission = new ArrayList<>();
                if (plugin.getPlayerMissions.get(e.getPlayer()) != null) {
                    for (MissionData.MissionType missionType : plugin.getPlayerMissions.get(e.getPlayer())) {
                        mission.add(missionType);
                    }
                }
                mission.add(MissionData.MissionType.MINER);
                plugin.getPlayerMissions.put(e.getPlayer(), mission);
            }
        }
        if (e.getBlock().getType() == Material.OAK_LOG) {
            String s = plugin.getPlayerActiveMissions.get(e.getPlayer()).get(MissionData.MissionType.LUMBERJACK);
            plugin.getPlayerActiveMissions.get(e.getPlayer()).put(MissionData.MissionType.LUMBERJACK, s.split(";")[0] + ";" + (Integer.parseInt(s.split(";")[1]) + 1));
            Bukkit.broadcastMessage(plugin.getPlayerActiveMissions.get(e.getPlayer()).get(MissionData.MissionType.LUMBERJACK));

            String s2 = plugin.getPlayerActiveMissions.get(e.getPlayer()).get(MissionData.MissionType.LUMBERJACK);
            if (Integer.parseInt(s2.split(";")[0]) == Integer.parseInt(s2.split(";")[1])) {
                e.getPlayer().sendMessage("§aLUMBERJACK mission complette");
                List<MissionData.MissionType> mission = new ArrayList<>();
                if (plugin.getPlayerMissions.get(e.getPlayer()) != null) {
                    for (MissionData.MissionType missionType : plugin.getPlayerMissions.get(e.getPlayer())) {
                        mission.add(missionType);
                    }
                }
                mission.add(MissionData.MissionType.LUMBERJACK);
                plugin.getPlayerMissions.put(e.getPlayer(), mission);
            }
        }
    }
}
