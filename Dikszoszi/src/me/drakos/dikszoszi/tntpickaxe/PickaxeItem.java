package me.drakos.dikszoszi.tntpickaxe;

import me.drakos.dikszoszi.utils.ItemUtils;
import me.drakos.dikszoszi.utils.nbt.NBTItemData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PickaxeItem {

    public static ItemStack getTnTPickaxe() {
        ItemStack itemStack = ItemUtils.constructItem(Material.DIAMOND_PICKAXE,"§aKöcsög vagy", new ArrayList<>());
        itemStack = NBTItemData.set("SURVIVAL", "TNT_PICKAXE", "true", itemStack);
        return itemStack;
    }
}
