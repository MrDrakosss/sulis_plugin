package me.drakos.dikszoszi.utils.nbt.item_1_16;

import net.minecraft.server.v1_16_R3.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;

import java.util.Set;

public class NBTItemData_1_16 {

    public static org.bukkit.inventory.ItemStack set(String base, String path, String i, org.bukkit.inventory.ItemStack itemStack) {
        ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        if (!item.hasTag()) {
            item.setTag(new NBTTagCompound());
        }
        NBTTagCompound tag = item.getTag();
        if (!tag.hasKey(base)) {
            tag.set(base, new NBTTagCompound());
        }
        NBTTagCompound info = tag.getCompound(base);
        info.setString(path, i);
        item.setTag(tag);
        return CraftItemStack.asCraftMirror(item);
    }

    public static String get(String base, String path, org.bukkit.inventory.ItemStack itemStack) {
        ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        if (!item.hasTag()) {
            item.setTag(new NBTTagCompound());
        }
        NBTTagCompound tag = item.getTag();
        if (!tag.hasKey(base)) {
            tag.set(base, new NBTTagCompound());
        }
        NBTTagCompound info = tag.getCompound(base);
        return info.getString(path);
    }

    public static Set<String> getList(String base, org.bukkit.inventory.ItemStack itemStack) {
        ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        if (!item.hasTag()) {
            item.setTag(new NBTTagCompound());
        }
        NBTTagCompound tag = item.getTag();
        if (!tag.hasKey(base)) {
            tag.set(base, new NBTTagCompound());
        }
        NBTTagCompound info = tag.getCompound(base);
        return info.getKeys();
    }

    public static boolean isDefault(String base, org.bukkit.inventory.ItemStack itemStack) {
        ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        if (!item.hasTag()) {
            return true;
        }
        NBTTagCompound tag = item.getTag();
        if (!tag.hasKey(base)) {
            return true;
        }
        return false;
    }

}
