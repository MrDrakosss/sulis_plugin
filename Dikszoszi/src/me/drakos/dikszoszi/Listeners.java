package me.drakos.dikszoszi;

import me.drakos.dikszoszi.data.PlayerData;
import me.drakos.dikszoszi.data.PlayerDataManager;
import me.drakos.dikszoszi.data.PlayerTabName;
import me.drakos.dikszoszi.utils.ConfigManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Listeners implements Listener {

    private Main plugin;

    public Listeners(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        PlayerDataManager.setPlayerStats(e.getPlayer(), plugin);
        e.getPlayer().setPlayerListName(ConfigManager.get().getConfig().getConfig().get("PlayerData." + e.getPlayer().getName() + ".Class") + " §f");
        PlayerTabName.setTabName(e.getPlayer(), plugin.getPlayerClass.get(e.getPlayer()));
    }

    @EventHandler
    public void weapon(EntityDamageByEntityEvent e) {
        if(e.getDamager() == null || e.getEntity() == null){
            return;
        }
        /*
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (!getClassWeapon(plugin.getPlayerClass.get(p)).contains(p.getInventory().getItemInMainHand().getType())) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4§l! §7Ezt a fegyvert nem tudod a kasztoddal használni!"));
                e.setCancelled(true);
                return;
            }
            e.setDamage(plugin.getPlayerDamage.get(p));
        }*/

        ThreadLocalRandom random = ThreadLocalRandom.current();
        ArmorStand as = (ArmorStand) e.getEntity().getWorld().spawn(e.getEntity().getLocation(), ArmorStand.class);
        as.setVisible(false);
        as.teleport(e.getEntity().getLocation().add(random.nextDouble(0, 0.5), 0.5, random.nextDouble(0, 0.5)));
        as.setGravity(false);
        as.setCustomName("§c" + e.getDamage() + "§e\uD83D\uDDE1");
        as.setCustomNameVisible(true);
        as.setVisible(false);

        new BukkitRunnable() {
            @Override
            public void run() {
                as.remove();
            }
        }.runTaskLater(plugin, 20);
    }

    public List<Material> getClassWeapon(PlayerData.PlayerClass playerClass) {
        if (playerClass == PlayerData.PlayerClass.WARRIOR) {
            return Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD);
        }
        if (playerClass == PlayerData.PlayerClass.ARCHER) {
            return Arrays.asList(Material.BOW, Material.CROSSBOW);
        }
        if (playerClass == PlayerData.PlayerClass.MAGE) {
            return Arrays.asList(Material.STICK, Material.BLAZE_ROD);
        }
        return null;
    }

    @EventHandler
    public void block(BlockBreakEvent e) {
        //e.setCancelled(true);
    }

    @EventHandler
    public void block(BlockPlaceEvent e) {
        //e.setCancelled(true);
    }
}
