package me.fullpotato.badlandscaves.badlandscaves.Runnables.Toxicity;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class ToxSlowDecreaseRunnable extends BukkitRunnable {

    private BadlandsCaves plugin;
    private Player player;
    public ToxSlowDecreaseRunnable(BadlandsCaves bcav) {
        plugin = bcav;
    }

    @Override
    public void run() {
        boolean isHardmode = plugin.getConfig().getBoolean("game_values.hardmode");
        if (isHardmode) {
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            double thirst = player.getMetadata("Thirst").get(0).asDouble();
            double tox = player.getMetadata("Toxicity").get(0).asDouble();
            if (thirst >= 80) {
                if (tox > 0.1) {
                    player.setMetadata("Toxicity", new FixedMetadataValue(plugin, tox - 0.1));
                }
            }
        }
    }
}