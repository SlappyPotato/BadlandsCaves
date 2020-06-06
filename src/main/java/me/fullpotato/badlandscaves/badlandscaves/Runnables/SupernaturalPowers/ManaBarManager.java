package me.fullpotato.badlandscaves.badlandscaves.Runnables.SupernaturalPowers;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ManaBarManager extends BukkitRunnable {
    private BadlandsCaves plugin;
    private final String title = ChatColor.DARK_AQUA + "Mana";
    public ManaBarManager(BadlandsCaves bcav) {
        plugin = bcav;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            final boolean has_powers = player.getMetadata("has_supernatural_powers").get(0).asBoolean();
            KeyedBossBar manaBar = getManaBar(player);
            if (has_powers) {
                double max_mana = player.getMetadata("max_mana").get(0).asDouble();
                double mana = player.getMetadata("Mana").get(0).asDouble();
                double percentage;
                try {
                    percentage = Math.min(Math.max(mana / max_mana, 0.0), 1.0);
                }
                catch (ArithmeticException e) {
                    percentage = 0.0;
                }
                manaBar.setProgress(percentage);

                int swap_slot = player.getMetadata("swap_slot").get(0).asInt();
                int swap_name_timer = player.getMetadata("swap_name_timer").get(0).asInt();
                int mana_bar_message_timer = player.getMetadata("mana_bar_message_timer").get(0).asInt();

                if (manaBar.isVisible()) {
                    if (mana_bar_message_timer > 0) {
                        player.setMetadata("mana_bar_message_timer", new FixedMetadataValue(plugin, mana_bar_message_timer - 1));
                    }
                    else {
                        manaBar.setTitle(title);
                    }
                }

                int active_timer = player.getMetadata("mana_bar_active_timer").get(0).asInt();
                if (active_timer > 0) {
                    manaBar.setVisible(true);
                    active_timer--;
                    player.setMetadata("mana_bar_active_timer", new FixedMetadataValue(plugin, active_timer));
                }
                else {
                    manaBar.setVisible(false);
                }
            }
            else {
                if (manaBar != null) {
                    manaBar.setVisible(false);
                    plugin.getServer().removeBossBar(manaBar.getKey());
                }
            }
        }
    }

    public KeyedBossBar getManaBar(Player player) {
        final boolean has_powers = player.getMetadata("has_supernatural_powers").get(0).asBoolean();
        if (has_powers) {
            NamespacedKey key = new NamespacedKey(plugin, "mana_bar_" + player.getUniqueId());
            KeyedBossBar manaBar = plugin.getServer().getBossBar(key);
            if (manaBar == null) {
                manaBar = plugin.getServer().createBossBar(key, title, BarColor.BLUE, BarStyle.SEGMENTED_10);
                manaBar.addPlayer(player);
            }
            return manaBar;
        }
        return null;
    }

    public void displayMessage (Player player, String message, int time, boolean force) {
        if (!player.getMetadata("has_supernatural_powers").get(0).asBoolean()) return;
        if (!force && player.getMetadata("mana_bar_message_timer").get(0).asInt() > 0) return;

        KeyedBossBar bar = getManaBar(player);
        player.setMetadata("mana_bar_active_timer", new FixedMetadataValue(plugin, 60));
        player.setMetadata("mana_bar_message_timer", new FixedMetadataValue(plugin, time * 4));
        bar.setTitle(message);
    }

    public void clearMessage (Player player) {
        if (!player.getMetadata("has_supernatural_powers").get(0).asBoolean()) return;
        if (player.getMetadata("mana_bar_message_timer").get(0).asInt() <= 0) return;

        player.setMetadata("mana_bar_message_timer", new FixedMetadataValue(plugin, 0));
    }
}