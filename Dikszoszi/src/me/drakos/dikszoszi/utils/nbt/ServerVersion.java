package me.drakos.dikszoszi.utils.nbt;

import org.bukkit.Bukkit;

public enum ServerVersion {
    VERSION_1_13,
    VERSION_1_14,
    VERSION_1_15,
    VERSION_1_16,
    ;

    public static ServerVersion getVersion() {
        String a = Bukkit.getServer().getClass().getPackage().getName();
        String version = a.substring(a.lastIndexOf('.') + 1);

        for (ServerVersion serverVersion : values()) {
            if (version.contains(serverVersion.name().replace("VERSION_", ""))) {
                return serverVersion;
            }
        }
        return ServerVersion.VERSION_1_16;
    }
}
