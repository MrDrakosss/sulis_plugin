package me.drakos.dikszoszi.tntpickaxe;

import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.utils.nbt.NBTItemData;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class TnTListener implements Listener {

    private Main plugin;

    private static HashMap<Player, Integer> blockFace;

    public TnTListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);

        blockFace = new HashMap<>();
    }

    @EventHandler
    public void man(BlockBreakEvent e) {
        if(e.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("_PICKAXE")){
            if(NBTItemData.getList("SURVIVAL", e.getPlayer().getInventory().getItemInMainHand()).contains("TNT_PICKAXE")){
            }
        }
    }

    @EventHandler
    public void getBlockFace(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final BlockFace bFace = event.getBlockFace();
        if (NBTItemData.getList("SURVIVAL", event.getItem()).contains("TNT_PICKAXE")) {
            if (bFace == BlockFace.UP || bFace == BlockFace.DOWN) {
                blockFace.put(player, 1);
            }
            if (bFace == BlockFace.NORTH || bFace == BlockFace.SOUTH) {
                blockFace.put(player, 2);
            }
            if (bFace == BlockFace.WEST || bFace == BlockFace.EAST) {
                blockFace.put(player, 3);
            }
        }
        Bukkit.broadcastMessage(blockFace.get(player)+"");
    }

    @EventHandler
    public void heavyPickAxeEffect(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemInHand = player.getItemInHand();
        if (NBTItemData.getList("SURVIVAL", event.getPlayer().getInventory().getItemInMainHand()).contains("TNT_PICKAXE")) {
            ArrayList<Material> materialList = new ArrayList<>();
            materialList.add(Material.STONE);
            this.threeByThree(materialList, event);
        }
    }

    public void threeByThree(final ArrayList<Material> list, final BlockBreakEvent event) {
        if (!event.isCancelled()) {
            final Player player = event.getPlayer();
            final ItemStack itemInHand = player.getItemInHand();
            final Block mainBlock = event.getBlock();
            int total = 0;
            int enchant = 0;
            int unbreaking = 0;
            if (itemInHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                enchant = 1;
            }
            else {
                enchant = 0;
            }
            if (itemInHand.containsEnchantment(Enchantment.DURABILITY)) {
                if (itemInHand.getEnchantmentLevel(Enchantment.DURABILITY) == 1) {
                    unbreaking = this.plugin.getConfig().getInt("unbreaking1");
                    if (unbreaking <= 0) {
                        unbreaking = 0;
                    }
                    if (unbreaking >= 8) {
                        unbreaking = 8;
                    }
                }
                if (itemInHand.getEnchantmentLevel(Enchantment.DURABILITY) == 2) {
                    unbreaking = this.plugin.getConfig().getInt("unbreaking2");
                    if (unbreaking <= 0) {
                        unbreaking = 0;
                    }
                    if (unbreaking >= 8) {
                        unbreaking = 8;
                    }
                }
                if (itemInHand.getEnchantmentLevel(Enchantment.DURABILITY) == 3) {
                    unbreaking = this.plugin.getConfig().getInt("unbreaking3");
                    if (unbreaking <= 0) {
                        unbreaking = 0;
                    }
                    else if (unbreaking >= 8) {
                        unbreaking = 8;
                    }
                }
            }
            final ArrayList<Block> blocks = new ArrayList<Block>();
            if (blockFace.get(player) == 1) {
                blocks.add(mainBlock.getRelative(BlockFace.NORTH_WEST));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH_EAST));
                blocks.add(mainBlock.getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.EAST));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH_WEST));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH_EAST));
            }
            if (blockFace.get(player) == 2) {
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.UP));
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
                blocks.add(mainBlock.getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.EAST));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
            }
            if (blockFace.get(player) == 3) {
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.UP));
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
            }
            for (final Block block : blocks) {
                if (list.contains(block.getType())) {
                    final BlockBreakEvent b = new BlockBreakEvent(block, player);
                    Bukkit.getServer().getPluginManager().callEvent((Event)b);
                    if (b.isCancelled()) {
                        continue;
                    }
                    if (enchant == 0) {
                        block.getLocation().getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, (Object)block.getType());
                        block.breakNaturally();
                        ++total;
                    }
                    if (enchant != 1) {
                        continue;
                    }
                    final byte data = block.getData();
                    final Material drop = block.getType();
                    block.getLocation().getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, (Object)block.getType());
                    block.setType(Material.AIR);
                    mainBlock.getLocation().getWorld().dropItemNaturally(mainBlock.getLocation(), new ItemStack(drop, 1, (short)data));
                    ++total;
                }
            }
            if (this.plugin.getConfig().contains("extraDurabilityLoss") && this.plugin.getConfig().getString("extraDurabilityLoss").equalsIgnoreCase("true")) {
                total -= unbreaking;
                if (total <= 0) {
                    total = 0;
                }
            }
            else {
                total = 0;
            }
            player.getItemInHand().setDurability((short)(player.getItemInHand().getDurability() + total));
        }
    }
}
