package me.drakos.dikszoszi.zipline;

import com.sun.org.apache.xml.internal.utils.NameSpace;
import me.drakos.dikszoszi.Info;
import me.drakos.dikszoszi.Main;
import me.drakos.dikszoszi.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Arrays;

public class ZipListener implements Listener {

    private Main plugin;

    private NamespacedKey nameSpace;

    public ZipListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

        nameSpace = new NamespacedKey(plugin, "zipline_armorstand");

        //run();
    }

    @EventHandler
    public void spawn(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CHAIN) {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getType() == Material.GHAST_TEAR) {
                e.setCancelled(true);
                if (plugin.getZipLineTimer.containsKey(p)) {

                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                    plugin.getZipLineTimer.remove(p);

                    ArmorStand armorStand = e.getPlayer().getWorld().spawn(e.getClickedBlock().getLocation().add(+0.5, -2, +0.5), ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.setVisible(false);

                    armorStand.setCustomNameVisible(false);
                    armorStand.setCustomName(null);

                    armorStand.setBasePlate(false);

                    armorStand.setHeadPose(new EulerAngle(0f, 0f, 0f));
                    armorStand.setLeftArmPose(new EulerAngle(0f, 0f, 0f));
                    armorStand.setRightArmPose(new EulerAngle(0f, 0f, 0f));
                    armorStand.setBodyPose(new EulerAngle(0f, 0f, 0f));
                    armorStand.setLeftLegPose(new EulerAngle(0f, 0f, 0f));
                    armorStand.setRightLegPose(new EulerAngle(0f, 0f, 0f));
                    armorStand.getEquipment().setHelmet(ItemUtils.constructItem(Material.GHAST_TEAR, "", new ArrayList<>()));


                    armorStand.getPersistentDataContainer().set(nameSpace, PersistentDataType.STRING, "zipline_armorstand");

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            armorStand.setRotation(setRotation(armorStand, p), 0);
                        }
                    }.runTaskLater(plugin, 2);

                    p.sendMessage(Info.getInfoPrefix() + "§aZipline elkészítve!");


                } else {
                    plugin.getZipLineTimer.put(p, 10);
                    p.sendMessage(Info.getInfoPrefix() + "§aMegerősítéshez kattints még egyszer!");
                }
            }
        }
    }

    @EventHandler
    public void inte(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() != null && e.getPlayer() != null && e.getRightClicked() instanceof ArmorStand) {
            if (e.getRightClicked().getPersistentDataContainer().has(nameSpace, PersistentDataType.STRING)) {


                Player p = e.getPlayer();
                ArmorStand armorStand = (ArmorStand) e.getRightClicked();

                if (p.isSneaking()) {
                    armorStand.remove();
                    p.getInventory().addItem(ItemUtils.constructItem(Material.GHAST_TEAR, "§aZipline", Arrays.asList("", "§8Lerakáshoz jobb klikk", "§8a lánc végére!")));
                    return;
                }

                armorStand.setPassenger(p);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (armorStand == null) {
                            this.cancel();
                        }
                        if (armorStand.getFacing() == BlockFace.EAST) {
                            if (armorStand.getLocation().add(+0.6, +2.5, 0).getBlock().getType() == Material.CHAIN) {
                                //armorStand.teleport(armorStand.getLocation().add(+0.2, 0, 0));

                                armorStand.setGravity(true);
                                armorStand.setVelocity(armorStand.getLocation().add(+0.2, 0, 0).toVector().subtract(armorStand.getLocation().toVector()));
                            } else {
                                this.cancel();

                                armorStand.setGravity(false);
                                armorStand.removePassenger(p);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        armorStand.setRotation(setRotation(armorStand, p), 0);
                                    }
                                }.runTaskLater(plugin, 2);
                            }
                        }
                        if (armorStand.getFacing() == BlockFace.SOUTH) {
                            if (armorStand.getLocation().add(0, +2.5, +0.6).getBlock().getType() == Material.CHAIN) {
                                //armorStand.teleport(armorStand.getLocation().add(0, 0, +0.2));

                                armorStand.setGravity(true);
                                armorStand.setVelocity(armorStand.getLocation().add(0, 0, +0.2).toVector().subtract(armorStand.getLocation().toVector()));
                            } else {
                                this.cancel();
                                armorStand.setGravity(false);
                                armorStand.removePassenger(p);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        armorStand.setRotation(setRotation(armorStand, p), 0);
                                    }
                                }.runTaskLater(plugin, 2);
                            }
                        }
                        if (armorStand.getFacing() == BlockFace.WEST) {
                            if (armorStand.getLocation().add(-0.6, +2.5, 0).getBlock().getType() == Material.CHAIN) {
                                //armorStand.teleport(armorStand.getLocation().add(-0.2, 0, 0));

                                armorStand.setGravity(true);
                                armorStand.setVelocity(armorStand.getLocation().add(-0.2, 0, 0).toVector().subtract(armorStand.getLocation().toVector()));
                            } else {
                                this.cancel();
                                armorStand.setGravity(false);
                                armorStand.removePassenger(p);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        armorStand.setRotation(setRotation(armorStand, p), 0);
                                    }
                                }.runTaskLater(plugin, 2);
                            }
                        }
                        if (armorStand.getFacing() == BlockFace.NORTH) {
                            if (armorStand.getLocation().add(0, +2.5, -0.6).getBlock().getType() == Material.CHAIN) {
                                //armorStand.teleport(armorStand.getLocation().add(0, 0, -0.2));

                                armorStand.setGravity(true);
                                armorStand.setVelocity(armorStand.getLocation().add(0, 0, -0.2).toVector().subtract(armorStand.getLocation().toVector()));
                            } else {
                                this.cancel();
                                armorStand.setGravity(false);
                                armorStand.removePassenger(p);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        armorStand.setRotation(setRotation(armorStand, p), 0);
                                    }
                                }.runTaskLater(plugin, 2);
                            }
                        }
                    }
                }.runTaskTimer(plugin, 0, 0);
            }
        }
    }


    private int setRotation(ArmorStand stand, Player player) {
        if (stand.getLocation().add(0, +2, 0).getBlock().getType() == Material.CHAIN) {
            if (stand.getLocation().add(+1, +2.5, 0).getBlock().getType() == Material.CHAIN && // Happy
                    stand.getLocation().add(0, +2.5, +1).getBlock().getType() != Material.CHAIN && // Endrod
                    stand.getLocation().add(-1, +2.5, 0).getBlock().getType() != Material.CHAIN && // Crit
                    stand.getLocation().add(0, +2.5, -1).getBlock().getType() != Material.CHAIN // Angry
            ) {
                return -90;
            }
            if (stand.getLocation().add(+1, +2.5, 0).getBlock().getType() != Material.CHAIN && // Happy
                    stand.getLocation().add(0, +2.5, +1).getBlock().getType() != Material.CHAIN && // Endrod
                    stand.getLocation().add(-1, +2.5, 0).getBlock().getType() == Material.CHAIN && // Crit
                    stand.getLocation().add(0, +2.5, -1).getBlock().getType() != Material.CHAIN // Angry
            ) {
                return 90;
            }
            if (stand.getLocation().add(+1, +2.5, 0).getBlock().getType() != Material.CHAIN && // Happy
                    stand.getLocation().add(0, +2.5, +1).getBlock().getType() != Material.CHAIN && // Endrod
                    stand.getLocation().add(-1, +2.5, 0).getBlock().getType() != Material.CHAIN && // Crit
                    stand.getLocation().add(0, +2.5, -1).getBlock().getType() == Material.CHAIN // Angry
            ) {
                return -180;
            }
            if (stand.getLocation().add(+1, +2.5, 0).getBlock().getType() != Material.CHAIN && // Happy
                    stand.getLocation().add(0, +2.5, +1).getBlock().getType() == Material.CHAIN && // Endrod
                    stand.getLocation().add(-1, +2.5, 0).getBlock().getType() != Material.CHAIN && // Crit
                    stand.getLocation().add(0, +2.5, -1).getBlock().getType() != Material.CHAIN // Angry
            ) {
                return 0;
            }
        }
        return 0;
    }

    private void moveZip(ArmorStand armorStand, BlockFace blockFace) {
        if (blockFace == BlockFace.EAST) {
            if (armorStand.getLocation().add(+1, +2.5, 0).getBlock().getType() == Material.CHAIN) {
                armorStand.teleport(armorStand.getLocation().add(+0.1, 0, 0));
            }
        }
        if (blockFace == BlockFace.SOUTH && armorStand.getLocation().add(0, +2.5, +1).getBlock().getType() == Material.CHAIN) {
            armorStand.teleport(armorStand.getLocation().add(0, 0, +0.1));
        }
        if (blockFace == BlockFace.WEST && armorStand.getLocation().add(-1, +2.5, 0).getBlock().getType() == Material.CHAIN) {
            armorStand.teleport(armorStand.getLocation().add(-0.1, 0, 0));
        }
        if (blockFace == BlockFace.NORTH && armorStand.getLocation().add(0, +2.5, -1).getBlock().getType() == Material.CHAIN) {
            armorStand.teleport(armorStand.getLocation().add(0, 0, -0.1));
        }
    }

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
                    if (entity instanceof ArmorStand) {
                        if (entity.getPersistentDataContainer().has(nameSpace, PersistentDataType.STRING)) {
                            Location location = entity.getLocation().add(0, +2.5, 0);
                            location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation().add(+1, +2.5, 0), 1, 0, 0, 0, 0);
                            location.getWorld().spawnParticle(Particle.END_ROD, entity.getLocation().add(0, +2.5, +1), 1, 0, 0, 0, 0);
                            location.getWorld().spawnParticle(Particle.CRIT, entity.getLocation().add(-1, +2.5, 0), 1, 0, 0, 0, 0);
                            location.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, entity.getLocation().add(0, +2.5, -1), 1, 0, 0, 0, 0);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }


    @EventHandler
    public void man(PlayerArmorStandManipulateEvent e) {
        if (e.getRightClicked().getPersistentDataContainer().has(nameSpace, PersistentDataType.STRING)) {
            e.setCancelled(true);
        }
    }

}
