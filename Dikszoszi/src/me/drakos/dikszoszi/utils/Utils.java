package me.drakos.dikszoszi.utils;

import me.drakos.dikszoszi.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private final static int CENTER_PX = 154;
    public static void sendCenteredMessage(CommandSender sender, String message) {
        if(sender instanceof Player) {
            sender.sendMessage(getCenteredMessage(message));
        } else {
            sender.sendMessage(message);
        }
    }
    public static String getCenteredMessage(String message){
        //if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            } else if(previousCode){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                } else isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + message;
    }

    public static int[] getSideSlots(int size) {
        if(size%9 != 0
                || size < 9
                || size > 54) {
            Main.getPlugin(Main.class).getLogger().info("Error: You have to provide an int divisible by 9, more than 18 (or equal), and less than 54.");
            return new int[] {0};
        }
        List<Integer> slots = new ArrayList<>();
        // Top
        for(int i = 0; i < 9; i++) {
            slots.add(i);
        }
        // Left side
        for(int i = 9; i <= size - 18; i += 9) {
            slots.add(i);
        }
        // Right side
        for(int i = 17; i <= size - 10; i += 9) {
            slots.add(i);
        }
        // Bottom
        for(int i = size - 9; i < size; i++) {
            slots.add(i);
        }
        int[] array = new int[slots.size()];
        for(int i = 0; i < slots.size(); i++) {
            array[i] = slots.get(i);
        }
        return array;
    }
}
