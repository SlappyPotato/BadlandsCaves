package me.fullpotato.badlandscaves.badlandscaves.events.Deaths;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class death_handler implements Listener {

    private BadlandsCaves plugin;
    public death_handler (BadlandsCaves bcav) {
        plugin = bcav;
    }

    @EventHandler
    public void death_handler(PlayerDeathEvent event) {
        Player player = event.getEntity();
        int death_count = player.getMetadata("Deaths").get(0).asInt();
        double toxicity = player.getMetadata("Toxicity").get(0).asDouble();
        double thirst = player.getMetadata("Thirst").get(0).asDouble();

        if (toxicity >= 100 && thirst <= 0) {
            event.setDeathMessage(player.getDisplayName()+ " died of multiple causes.");
        }
        else if (toxicity >= 100) {
            event.setDeathMessage(player.getDisplayName() + " died of toxicity.");
        }
        else if (thirst <= 0) {
            event.setDeathMessage(player.getDisplayName() + " died of dehydration.");
        }

        //resetting thirst/tox values on death
        player.setMetadata("Thirst", new FixedMetadataValue(plugin, 100.0));
        player.setMetadata("Toxicity", new FixedMetadataValue(plugin, 0.0));
        player.setMetadata("thirst_sys_var", new FixedMetadataValue(plugin, 0.0));
        player.setMetadata("tox_nat_decr_var", new FixedMetadataValue(plugin, 0.0));
        player.setMetadata("tox_slow_incr_var", new FixedMetadataValue(plugin, 0.0));

        player.setMetadata("Deaths", new FixedMetadataValue(plugin, death_count + 1));
    }
}