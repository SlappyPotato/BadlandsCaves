package me.fullpotato.badlandscaves.Thirst;

import me.fullpotato.badlandscaves.BadlandsCaves;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Random;

public class NaturalThirstDecrease implements Listener {
    private BadlandsCaves plugin;
    public NaturalThirstDecrease(BadlandsCaves bcav) {
        plugin = bcav;
    }


    @EventHandler
    public void decrease_thirst (PlayerMoveEvent event) {
        if (event.getTo() == null) return;
        Player player = event.getPlayer();

        if (!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) return;
        if (player.isDead()) return;

        if (player.getWorld().equals(plugin.getServer().getWorld(plugin.descensionWorldName))) return;
        if (player.getWorld().equals(plugin.getServer().getWorld(plugin.reflectionWorldName))) return;
        if (player.getWorld().equals(plugin.getServer().getWorld(plugin.backroomsWorldName))) return;

        boolean moved_x = (Math.abs(event.getTo().getX() - event.getFrom().getX()) > 0);
        boolean moved_y = (Math.abs(event.getTo().getY() - event.getFrom().getY()) > 0);
        boolean moved_z = (Math.abs(event.getTo().getZ() - event.getFrom().getZ()) > 0);
        boolean moved = moved_x || moved_y || moved_z;
        boolean sprint = player.isSprinting();
        boolean sneak = player.isSneaking();
        boolean climb = moved_y && (player.getLocation().getBlock().getType().equals(Material.LADDER) || player.getLocation().getBlock().getType().equals(Material.VINE));
        double current_thirst_sys = player.getMetadata("thirst_sys_var").get(0).asDouble();

        if (moved) {
            //endurance cancel
            final boolean has_powers = player.getMetadata("has_supernatural_powers").get(0).asBoolean();
            if (has_powers) {
                int endurance_level = player.getMetadata("endurance_level").get(0).asInt();
                if (endurance_level > 0) {
                    Random random = new Random();
                    int endurance_rand_cancel = random.nextInt(100);
                    if ((endurance_level == 1 && endurance_rand_cancel < 25) || (endurance_level == 2 && endurance_rand_cancel < 50)) {
                        return;
                    }
                }
            }


            double new_thirst_sys;
            if (climb) {
                new_thirst_sys = current_thirst_sys + 3;
            }
            else if (sprint) {
                new_thirst_sys = current_thirst_sys + 1.5;
            }
            else if (sneak) {
                new_thirst_sys = current_thirst_sys + 0.5;
            }
            else {
                new_thirst_sys = current_thirst_sys + 1;
            }
            player.setMetadata("thirst_sys_var", new FixedMetadataValue(plugin, new_thirst_sys));

        }

        boolean isHardmode = plugin.getConfig().getBoolean("game_values.hardmode");
        int threshold;

        if (isHardmode) {
            threshold = plugin.getConfig().getInt("game_values.hardmode_values.threshold_thirst_sys");
        }
        else {
            threshold = plugin.getConfig().getInt("game_values.pre_hardmode_values.threshold_thirst_sys");
        }

        if (player.getMetadata("thirst_sys_var").get(0).asDouble() >= threshold) {
            player.setMetadata("thirst_sys_var", new FixedMetadataValue(plugin, 0));

            double current_thirst = player.getMetadata("Thirst").get(0).asDouble();
            double new_thirst = current_thirst - 0.1;
            player.setMetadata("Thirst" , new FixedMetadataValue(plugin, new_thirst));
        }
    }

}