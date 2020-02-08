package me.fullpotato.badlandscaves.badlandscaves.Runnables.Effects;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class deathEffectsRunnable extends BukkitRunnable {

    private BadlandsCaves plugin;
    public deathEffectsRunnable(BadlandsCaves bcav ) {
        plugin = bcav;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) continue;

            int deaths = player.getMetadata("Deaths").get(0).asInt();
            int poison_lvl = 0;
            int hunger_lvl = 0;
            int slow_lvl = 0;
            int slowmine_lvl = 0;
            int speed_lvl = 0;

            if (deaths >= 50) {
                int hasPowers = player.getMetadata("has_supernatural_powers").get(0).asInt();
                int in_descension = player.getMetadata("in_descension").get(0).asInt();
                if (in_descension != 3 && hasPowers < 0.5) {
                    if (in_descension == 0) {
                        player.setMetadata("in_descension", new FixedMetadataValue(plugin, 1));
                    }
                    World descension_world = Bukkit.getWorld("world_descension");
                    if (descension_world != null && descension_world.isChunkLoaded(0, 0)) {
                        if (!player.getWorld().equals(descension_world)) {
                            Location descension_spawn = new Location(descension_world, 0, 197, 0);
                            try {
                                if (descension_spawn != null && descension_spawn.isWorldLoaded()) player.teleport(descension_spawn);
                            }
                            catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if (in_descension == 1 || in_descension == 2) {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
                }
                else {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1.0);
                    poison_lvl = 5;
                    hunger_lvl = 3;
                    slow_lvl = 5;
                    slowmine_lvl = 4;
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90, 4, true, false), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 90, 19, true, false), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 90, 0, true, false));
                }
            }
            else if (deaths >= 30) {
                poison_lvl = 2;
                hunger_lvl = 3;
                slow_lvl = 3;
                slowmine_lvl = 2;

                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90, 2, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 90, 9, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 90, 0, true, false));

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1.0);
            }
            else if (deaths >= 15) {
                hunger_lvl = 2;
                slow_lvl = 2;
                slowmine_lvl = 1;

                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 90, 3, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 90, 0, true, false));

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);
            }
            else if (deaths >= 10) {
                hunger_lvl = 1;
                slow_lvl = 2;
                slowmine_lvl = 1;

                player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 90, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90, 0, true, false), true);
            }
            else if (deaths >= 6) {
                slow_lvl = 1;

                player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 90, 1, true, false), true);

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
            }
            else if (deaths == 5) {
            }
            else if (deaths >= 1) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 90, 0, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90, 0, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90, 0, true, false), true);

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
            }
            else if (deaths == 0) {

                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 90, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90, 0, true, false), true);
                speed_lvl = 2;
                //player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 90, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 90, 4, true, false), true);

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            }

            player.setMetadata("deaths_debuff_slowmine_lvl", new FixedMetadataValue(plugin, slowmine_lvl));
            player.setMetadata("deaths_debuff_slow_lvl", new FixedMetadataValue(plugin, slow_lvl));
            player.setMetadata("deaths_debuff_hunger_lvl", new FixedMetadataValue(plugin, hunger_lvl));
            player.setMetadata("deaths_debuff_poison_lvl", new FixedMetadataValue(plugin, poison_lvl));
            player.setMetadata("deaths_buff_speed_lvl", new FixedMetadataValue(plugin, speed_lvl));
        }
    }
}
