package me.fullpotato.badlandscaves.badlandscaves.Runnables.Effects;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.badlandscaves.Util.AddPotionEffect;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class DeathEffects {
    public HashMap<String, Integer> getDeathEffects(Player player) {
        final int deaths = player.getMetadata("Deaths").get(0).asInt();
        final boolean supernatural = (player.hasMetadata("has_supernatural_powers") && player.getMetadata("has_supernatural_powers").get(0).asBoolean());
        int poison_lvl = 0;
        int hunger_lvl = 0;
        int walk_speed = 0;
        int mine_speed = 0;
        int weakness_lvl = 0;
        int unluck_lvl = 0;
        int blindness = 0;
        int health = 20;

        //100+ DEATHS---------------------------------------------
        if (deaths >= 100) {
            poison_lvl = 10;
            hunger_lvl = 10;
            walk_speed = -10;
            mine_speed = -10;
            weakness_lvl = 10;
            unluck_lvl = supernatural ? 99 : 10;
            blindness = 1;

            health = 1;
        }

        //80+ DEATHS----------------------------------------------
        else if (deaths >= 80) {
            poison_lvl = supernatural ? 8 : 10;
            hunger_lvl = supernatural ? 8 : 10;
            walk_speed = supernatural ? -8 : -10;
            mine_speed = supernatural ? -8 : -10;
            weakness_lvl = supernatural ? 8 : 10;
            unluck_lvl = supernatural ? 50 : 10;
            blindness = 1;

            health = 1;
        }

        //60+ DEATHS----------------------------------------------
        else if (deaths >= 60) {
            poison_lvl = supernatural ? 0 : 5;
            hunger_lvl = supernatural ? 5 : 6;
            walk_speed = supernatural ? -5 : -6;
            mine_speed = supernatural ? -4 : -5;
            weakness_lvl = supernatural ? 4 : 5;
            unluck_lvl = supernatural ? 25 : 6;
            blindness = 1;

            health = supernatural ? 4 : 1;
        }

        //50+ DEATHS----------------------------------------------
        else if (deaths >= 50) {
            poison_lvl = supernatural ? 0 : 2;
            hunger_lvl = supernatural ? 2 : 4;
            walk_speed = supernatural ? -3 : -4;
            mine_speed = supernatural ? -2 : -3;
            weakness_lvl = supernatural ? 2 : 3;
            unluck_lvl = supernatural ? 10 : 4;
            blindness = 1;

            health = supernatural ? 10 : 6;
        }

        //30+ DEATHS----------------------------------------------
        else if (deaths >= 30) {
            hunger_lvl = supernatural ? 1 : 2;
            walk_speed = supernatural ? -2 : -3;
            mine_speed = supernatural ? -1 : -2;
            weakness_lvl = supernatural ? 1 : 2;
            unluck_lvl = supernatural ? 10 : 3;
            blindness = supernatural ? 0 : 1;

            health = supernatural ? 13 : 10;
        }

        //20+ DEATHS----------------------------------------------
        else if (deaths >= 20) {
            walk_speed = supernatural ? -1 : -2;
            mine_speed = supernatural ? -0 : -1;
            weakness_lvl = supernatural ? 1 : 1;
            unluck_lvl = supernatural ? 10 : 2;

            health = 16;
        }

        //10+ DEATHS----------------------------------------------
        else if (deaths >= 10) {
            walk_speed = supernatural ? -1 : -2;
            unluck_lvl = supernatural ? 10 : 2;
            weakness_lvl = supernatural ? 0 : 1;
        }

        //6+ DEATHS-----------------------------------------------
        else if (deaths >= 6) {
            if (supernatural) {
                unluck_lvl = 10;
            }
            else {
                walk_speed = -1;
                unluck_lvl = 1;
            }
        }

        //5+ DEATHS-----------------------------------------------
        else if (deaths == 5) {
            if (supernatural) {
                unluck_lvl = 10;
                walk_speed = 1;
            }
        }

        //1+ DEATHS-----------------------------------------------
        else if (deaths >= 1) {
            if (supernatural) {
                unluck_lvl = 10;
                walk_speed = 1;
                mine_speed = 1;
            }
            else {
                walk_speed = 1;
                mine_speed = 1;
                AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90, 0, true, false));
            }
        }

        //NO DEATHS-----------------------------------------------
        else if (deaths == 0) {
            if (supernatural) {
                walk_speed = 1;
                unluck_lvl = 10;
                mine_speed = 1;
                AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90, 0, true, false));
            }
            else {
                walk_speed = 2;
                mine_speed = 2;
                AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90, 0, true, false));
                AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.JUMP, 90, 1, true, false));
                AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90, 1, true, false));
                AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.LUCK, 90, 4, true, false));
            }

            health = 40;
        }

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null && player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() != health) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        }

        if (unluck_lvl > 0) {
            AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.UNLUCK, 90, unluck_lvl - 1, true, false));
        }

        if (weakness_lvl > 0) {
            AddPotionEffect.addPotionEffect(player, new PotionEffect(PotionEffectType.WEAKNESS, 90, weakness_lvl - 1, true, false));
        }

        HashMap<String, Integer> output = new HashMap<>();
        output.put("poison_lvl", poison_lvl);
        output.put("hunger_lvl", hunger_lvl);
        output.put("walk_speed", walk_speed);
        output.put("mine_speed", mine_speed);
        output.put("blindness", blindness);

        return output;
    }
}