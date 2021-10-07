package me.drakos.dikszoszi.utils.menus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

public class IMenuHandler implements Listener {

    private Plugin plugin;

    public IMenuHandler(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (!(event.getView().getTopInventory().getHolder() instanceof IMenu)) {
            return;
        }
        event.setCancelled(true);
        if (!event.getClickedInventory().equals(event.getView().getTopInventory())) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }
        ((IMenu) event.getClickedInventory().getHolder()).OnGUI((Player) event.getWhoClicked(), event.getSlot(), event.getCurrentItem(), event.getClick());
    }
}
