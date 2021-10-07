package me.drakos.dikszoszi.menu;

import me.drakos.dikszoszi.Info;
import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.data.PlayerData;
import me.drakos.dikszoszi.data.PlayerDataManager;
import me.drakos.dikszoszi.data.PlayerTabName;
import me.drakos.dikszoszi.utils.ConfigManager;
import me.drakos.dikszoszi.utils.ItemUtils;
import me.drakos.dikszoszi.utils.menus.IMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class ClassMenu implements IMenu {

    private Inventory inv;
    private Main plugin;

    public ClassMenu(Player p, Main plugin) {
        this.plugin = plugin;
        inv = Bukkit.createInventory(this, 9, "§8");
        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.25f, 0);
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemUtils.constructItem(Material.BLACK_STAINED_GLASS_PANE, "§f", new ArrayList<>()));
        }

        String playerClass = plugin.getPlayerClass.get(p) == null ? Info.getText.UNKNOWN.text : plugin.getPlayerClass.get(p).toString();
        String playerHealth = plugin.getPlayerHealth.get(p) == null ? Info.getText.UNKNOWN.text : plugin.getPlayerHealth.get(p).toString();
        String playerDamage = plugin.getPlayerDamage.get(p) == null ? Info.getText.UNKNOWN.text : plugin.getPlayerDamage.get(p).toString();
        String PlayerLevel = plugin.getPlayerLevel.get(p) == null ? Info.getText.UNKNOWN.text : plugin.getPlayerLevel.get(p).toString();

        inv.setItem(2,
                ItemUtils.constructHead(
                        p.getName(),
                        "§e§l"+p.getName(),
                        Arrays.asList(
                                "",
                                "§aKaszt: §f"+ playerClass ,
                                "§aÉlet: §f"+playerHealth+"§c❤",
                                "§aSebzés: §f"+playerDamage+"§e\uD83D\uDDE1",
                                "§aSzint: §f"+PlayerLevel+"§d✪"
                        )));

        inv.setItem(4,
                ItemUtils.constructItem(
                        Material.IRON_SWORD,
                        "§b§lWarrior",
                        Arrays.asList(
                                "",
                                "§aÉlet: §f12§c❤",
                                "",
                                "§aSebzés: §f+6§e\uD83D\uDDE1",
                                "",
                                "§aFegyver: §fKard",
                                "",
                                "§6Kattints a kiválasztáshoz"
                        )));
        inv.setItem(5,
                ItemUtils.constructItem(
                        Material.CROSSBOW,
                        "§2§lArcher",
                        Arrays.asList(
                                "",
                                "§aÉlet: §f7§c❤",
                                "",
                                "§aSebzés: §f+4§e\uD83D\uDDE1",
                                "",
                                "§aFegyver: §fÍj / Számszeríj",
                                "",
                                "§6Kattints a kiválasztáshoz"
                        )));
        inv.setItem(6,
                ItemUtils.constructItem(
                        Material.BLAZE_POWDER,
                        "§d§lMage",
                        Arrays.asList(
                                "",
                                "§aÉlet: §f4§c❤",
                                "",
                                "§aSebzés: §f+8§e\uD83D\uDDE1",
                                "",
                                "§aFegyver: §fVarázs pálca",
                                "",
                                "§6Kattints a kiválasztáshoz"
                        )));
        p.openInventory(inv);
    }

    @Override
    public void OnGUI(Player p, int slot, ItemStack item, ClickType clickType) {
        if (slot == 4) {
            addConfig(p, PlayerData.PlayerClass.WARRIOR);
            p.closeInventory();
            return;
        }
        if (slot == 5) {
            addConfig(p, PlayerData.PlayerClass.ARCHER);
            p.closeInventory();
            return;
        }
        if (slot == 6) {
            addConfig(p, PlayerData.PlayerClass.MAGE);
            p.closeInventory();
            return;
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }


    private void addConfig(Player p, PlayerData.PlayerClass playerClass){
        if(!ConfigManager.get().getConfig().getConfig().getStringList("PlayerData").contains(p.getName())){
            ConfigManager.get().getConfig().getConfig().getStringList("PlayerData").add(p.getName());
        }
        if(playerClass == PlayerData.PlayerClass.WARRIOR){
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Class", PlayerData.PlayerClass.WARRIOR.name());
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Level", 1);
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Health", 12);
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Damage", 6);
            plugin.getPlayerClass.put(p, PlayerData.PlayerClass.WARRIOR);
            plugin.getPlayerLevel.put(p,1);
            plugin.getPlayerHealth.put(p,12);
            plugin.getPlayerDamage.put(p,6);
        }
        if(playerClass == PlayerData.PlayerClass.ARCHER){
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Class", PlayerData.PlayerClass.ARCHER.name());
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Level", 1);
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Health", 7);
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Damage", 4);
            plugin.getPlayerClass.put(p, PlayerData.PlayerClass.ARCHER);
            plugin.getPlayerLevel.put(p,1);
            plugin.getPlayerHealth.put(p,7);
            plugin.getPlayerDamage.put(p,4);
        }
        if(playerClass == PlayerData.PlayerClass.MAGE){
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Class", PlayerData.PlayerClass.MAGE.name());
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Level", 1);
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Health", 4);
            ConfigManager.get().getConfig().getConfig().set("PlayerData."+p.getName()+".Damage", 8);
            plugin.getPlayerClass.put(p, PlayerData.PlayerClass.MAGE);
            plugin.getPlayerLevel.put(p,1);
            plugin.getPlayerHealth.put(p,4);
            plugin.getPlayerDamage.put(p,8);
        }
        ConfigManager.get().getConfig().saveConfig();
        ConfigManager.get().getConfig().reloadConfig();
        PlayerTabName.setTabName(p,plugin.getPlayerClass.get(p));
        PlayerDataManager.setPlayerStats(p,plugin);
    }
}
