package me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.Using;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.CustomItem;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CustomBows implements Listener {
    private BadlandsCaves plugin;

    public CustomBows(BadlandsCaves plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void shoot (EntityShootBowEvent event) {
        if (event.getProjectile() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getProjectile();
            if (arrow instanceof SpectralArrow || !arrow.getBasePotionData().getType().equals(PotionType.UNCRAFTABLE))
                return;
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                PlayerInventory inventory = player.getInventory();
                ItemStack bow = event.getBow();

                int reg_slot = inventory.first(Material.ARROW);
                boolean inOffHand = inventory.getItemInOffHand().getType().equals(Material.ARROW);
                if (reg_slot != -1 || inOffHand) {
                    ItemStack arrowItem = inOffHand ? inventory.getItemInOffHand() : inventory.getItem(reg_slot);
                    final ItemStack corrosive_arrow = CustomItem.CORROSIVE_ARROW.getItem();
                    final ItemStack voltshock_arrow = CustomItem.VOLTSHOCK_ARROW.getItem();

                    if (arrowItem != null && (arrowItem.isSimilar(corrosive_arrow) || arrowItem.isSimilar(voltshock_arrow))) {
                        boolean supernatural = player.getMetadata("has_supernatural_powers").get(0).asBoolean();
                        if (!supernatural) {
                            if (arrowItem.isSimilar(corrosive_arrow)) {
                                arrow.setMetadata("corrosive_arrow", new FixedMetadataValue(plugin, true));
                                enforceAllowPickup(arrow);

                                if (event.getForce() >= 1) particleTrail(arrow, Color.fromRGB(0, 255, 0));
                            } else if (arrowItem.isSimilar(voltshock_arrow)) {
                                arrow.setMetadata("voltshock_arrow", new FixedMetadataValue(plugin, true));
                                enforceAllowPickup(arrow);

                                if (event.getForce() >= 1) {
                                    arrow.setVelocity(arrow.getVelocity().multiply(2));
                                    particleTrail(arrow, Color.AQUA);
                                }
                            }
                        }

                        if (bow != null && bow.getEnchantmentLevel(Enchantment.ARROW_INFINITE) > 0 && !player.getGameMode().equals(GameMode.CREATIVE)) {
                            arrowItem.setAmount(arrowItem.getAmount() - 1);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void pickupItem (PlayerPickupArrowEvent event) {
        AbstractArrow arrow = event.getArrow();
        if (arrow.hasMetadata("corrosive_arrow") && arrow.getMetadata("corrosive_arrow").get(0).asBoolean()) {
            event.getItem().setItemStack(CustomItem.CORROSIVE_ARROW.getItem());
        }
        else if (arrow.hasMetadata("voltshock_arrow") && arrow.getMetadata("voltshock_arrow").get(0).asBoolean()) {
            event.getItem().setItemStack(CustomItem.VOLTSHOCK_ARROW.getItem());
        }
    }

    public void particleTrail (Arrow arrow, Color color) {
        World world = arrow.getWorld();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead() || arrow.isInBlock()) {
                    this.cancel();
                }
                else {
                    world.spawnParticle(Particle.REDSTONE, arrow.getLocation(), 3, 0.1, 0.1, 0.1, 0, new Particle.DustOptions(color, 0.5F));
                }
            }
        }.runTaskTimer(plugin, 0, 0);
    }

    public void enforceAllowPickup (Arrow arrow) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead() || arrow.isInBlock()) {
                    this.cancel();
                }
                else {
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.ALLOWED);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 5);
    }
}