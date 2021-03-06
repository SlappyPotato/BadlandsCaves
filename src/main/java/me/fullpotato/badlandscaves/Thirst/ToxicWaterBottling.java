package me.fullpotato.badlandscaves.Thirst;

import me.fullpotato.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.Toxicity.ToxBottlingRunnable;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ToxicWaterBottling implements Listener {
    private final BadlandsCaves plugin;
    public ToxicWaterBottling(BadlandsCaves bcav) {
        plugin = bcav;
    }

    @EventHandler
    public void bottleWater (PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (event.getHand() != null) {
                if (event.getHand().equals(EquipmentSlot.HAND)) {
                    if (event.getItem() != null) {
                        if (event.getItem().getType().equals(Material.GLASS_BOTTLE)) {
                            new ToxBottlingRunnable(plugin, event.getPlayer()).runTaskLaterAsynchronously(plugin, 1);
                        }
                    }
                }
            }
        }
    }
}