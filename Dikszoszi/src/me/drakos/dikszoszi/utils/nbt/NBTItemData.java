package me.drakos.dikszoszi.utils.nbt;

import me.drakos.dikszoszi.utils.nbt.item_1_16.NBTItemData_1_16;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class NBTItemData {

    private static final ServerVersion version = ServerVersion.getVersion();

    public static ItemStack set(String base, String path, String i, ItemStack itemStack) {
        switch (version) {
            case VERSION_1_16:
                return NBTItemData_1_16.set(base, path, i, itemStack);
        }
        return itemStack;
    }

    public static String get(String base, String path, ItemStack itemStack) {
        switch (version) {
            case VERSION_1_16:
                return NBTItemData_1_16.get(base, path, itemStack);
        }
        return null;
    }

    public static Set<String> getList(String base, ItemStack itemStack) {
        switch (version) {
            case VERSION_1_16:
                return NBTItemData_1_16.getList(base, itemStack);
        }
        return new HashSet<>();
    }

    public static boolean isDefault(String base, ItemStack itemStack) {
        switch (version) {
            case VERSION_1_16:
                return NBTItemData_1_16.isDefault(base, itemStack);
        }
        return false;
    }

}
