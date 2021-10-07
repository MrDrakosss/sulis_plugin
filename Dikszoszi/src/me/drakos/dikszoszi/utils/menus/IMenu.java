package me.drakos.dikszoszi.utils.menus;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface IMenu extends InventoryHolder {
    void OnGUI(Player player, int slot, ItemStack item, ClickType clickType);
}
