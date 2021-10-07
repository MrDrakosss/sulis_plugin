package me.drakos.dikszoszi;

import me.drakos.dikszoszi.command.Commands;
import me.drakos.dikszoszi.command.Mission;
import me.drakos.dikszoszi.command.Pickaxe;
import me.drakos.dikszoszi.data.PlayerData;
import me.drakos.dikszoszi.data.PlayerDataManager;
import me.drakos.dikszoszi.data.PlayerTabName;
import me.drakos.dikszoszi.mission_data.MissionData;
import me.drakos.dikszoszi.mission_data.MissionListener;
import me.drakos.dikszoszi.mission_data.MissionManager;
import me.drakos.dikszoszi.tntpickaxe.TnTListener;
import me.drakos.dikszoszi.utils.ConfigManager;
import me.drakos.dikszoszi.utils.menus.IMenuHandler;
import me.drakos.dikszoszi.zipline.ZipListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    public HashMap<Player, PlayerData.PlayerClass> getPlayerClass = new HashMap();
    public HashMap<Player, Integer> getPlayerHealth = new HashMap();
    public HashMap<Player, Integer> getPlayerDamage = new HashMap();
    public HashMap<Player, Integer> getPlayerLevel = new HashMap();

    public HashMap<Player, List<MissionData.MissionType>> getPlayerMissions = new HashMap<>();
    public HashMap<Player, HashMap<MissionData.MissionType, String>> getPlayerActiveMissions = new HashMap<>();

    public HashMap<Player, Integer> getZipLineTimer = new HashMap<>();

    @Override
    public void onEnable() {
        new ConfigManager(this);

        this.getCommand("class").setExecutor(new Commands(this));
        this.getCommand("mission").setExecutor(new Mission(this));
        this.getCommand("pickaxe").setExecutor(new Pickaxe(this));

        new IMenuHandler(this);
        new Listeners(this);


        new MissionManager(this);
        new MissionListener(this);

        new ZipListener(this);
        new TnTListener(this);

        Bukkit.getConsoleSender().sendMessage(Info.getPrefix() + "§aFinally enable plugin");

        //Load playerdata
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerDataManager.setPlayerStats(player, this);

            HashMap<MissionData.MissionType, String> mission = new HashMap<>();
            mission.put(MissionData.MissionType.MINER,"20;0");
            mission.put(MissionData.MissionType.LUMBERJACK,"10;0");
            getPlayerActiveMissions.put(player, mission);
        }

    }

}
