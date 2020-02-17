package me.fullpotato.badlandscaves.badlandscaves;

import me.fullpotato.badlandscaves.badlandscaves.Commands.*;
import me.fullpotato.badlandscaves.badlandscaves.CustomItemRecipes.*;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.Crafting.combineTinyBlaze;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.Crafting.purgeEssence;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.Using.useFishingCrate;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.Using.useTaintPowder;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.stopCustomItemsRClick;
import me.fullpotato.badlandscaves.badlandscaves.Events.Deaths.deathHandler;
import me.fullpotato.badlandscaves.badlandscaves.Events.Deaths.gappleEat;
import me.fullpotato.badlandscaves.badlandscaves.Events.Loot.getFishingCrate;
import me.fullpotato.badlandscaves.badlandscaves.Events.Loot.zombieDeathLoot;
import me.fullpotato.badlandscaves.badlandscaves.Events.MobBuffs.*;
import me.fullpotato.badlandscaves.badlandscaves.Events.SupernaturalPowers.*;
import me.fullpotato.badlandscaves.badlandscaves.Events.Thirst.cauldronMenu;
import me.fullpotato.badlandscaves.badlandscaves.Events.Thirst.drinking;
import me.fullpotato.badlandscaves.badlandscaves.Events.Thirst.naturalThirstDecrease;
import me.fullpotato.badlandscaves.badlandscaves.Events.Thirst.toxicWaterBottling;
import me.fullpotato.badlandscaves.badlandscaves.Events.Toxicity.increaseToxInRain;
import me.fullpotato.badlandscaves.badlandscaves.Events.Toxicity.increaseToxInWater;
import me.fullpotato.badlandscaves.badlandscaves.Events.playerJoin;
import me.fullpotato.badlandscaves.badlandscaves.Events.playerLeave;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.Effects.deathEffectsRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.Effects.playerEffectsRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.Effects.thirstEffectsRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.Effects.toxEffectsRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.SupernaturalPowers.DescensionStage.*;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.SupernaturalPowers.agilitySpeedRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.SupernaturalPowers.manaBarRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.SupernaturalPowers.manaRegen;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.Toxicity.toxSlowDecreaseRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.actionbarRunnable;
import me.fullpotato.badlandscaves.badlandscaves.Runnables.playerSaveToConfig;
import me.fullpotato.badlandscaves.badlandscaves.WorldGeneration.descensionWorld;
import me.fullpotato.badlandscaves.badlandscaves.WorldGeneration.emptyWorld;
import me.fullpotato.badlandscaves.badlandscaves.WorldGeneration.preventNormalEnd;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class BadlandsCaves extends JavaPlugin {
    private final String[] player_values = {
            "Deaths",
            "*Thirst",
            "#Toxicity",
            "#thirst_sys_var",
            "#tox_nat_decr_var",
            "#tox_slow_incr_var",
            "deaths_buff_speed_lvl",
            "deaths_debuff_slowmine_lvl",
            "deaths_debuff_slow_lvl",
            "deaths_debuff_hunger_lvl",
            "deaths_debuff_poison_lvl",
            "tox_debuff_slowmine_lvl",
            "tox_debuff_slow_lvl",
            "tox_debuff_hunger_lvl",
            "tox_debuff_poison_lvl",
            "thirst_debuff_slowmine_lvl",
            "thirst_debuff_slow_lvl",
            "thirst_debuff_hunger_lvl",
            "thirst_debuff_poison_lvl",
            "opened_cauldron",
            "opened_cauldron_x",
            "opened_cauldron_y",
            "opened_cauldron_z",
            "has_supernatural_powers",
            "in_descension",
            "descension_detect",
            "descension_detect_cooldown",
            "descension_timer",
            "descension_shrines_capped",
            "is_cursed_soul",
            "*Mana",
            "*max_mana",
            "mana_needed_timer",
            "mana_regen_delay_timer",
            "mana_bar_active_timer",
            "swap_slot",
            "swap_cooldown",
            "swap_name_timer",
            "displace_level",
            "displace_particle_id",
            "has_displace_marker",
            "displace_x",
            "displace_y",
            "displace_z",
            "withdraw_level",
            "withdraw_x",
            "withdraw_y",
            "withdraw_z",
            "withdraw_chunk_x",
            "withdraw_chunk_z",
            "withdraw_timer",
            "eyes_level",
            "eyes_timer",
            "possess_level",
            "in_possession",
            "possessed_entity",
            "possess_orig_world",
            "possess_orig_x",
            "possess_orig_y",
            "possess_orig_z",
            "endurance_level",
            "agility_level",
            "agility_buff_speed_lvl",
            "agility_jump_id",
            "agility_jump_timer",
    };

    @Override
    public void onEnable() {
        //config
        loadConfig();

        //worlds
        {
            emptyWorld empty_world = new emptyWorld();
            empty_world.gen_void_world();

            descensionWorld desc_world = new descensionWorld(this);
            desc_world.gen_descension_world();
        }

        //event registering
        {
            this.getServer().getPluginManager().registerEvents(new playerJoin(this, player_values), this);
            this.getServer().getPluginManager().registerEvents(new naturalThirstDecrease(this), this);
            this.getServer().getPluginManager().registerEvents(new increaseToxInWater(this), this);
            this.getServer().getPluginManager().registerEvents(new deathHandler(this), this);
            this.getServer().getPluginManager().registerEvents(new gappleEat(this), this);
            this.getServer().getPluginManager().registerEvents(new cauldronMenu(this), this);
            this.getServer().getPluginManager().registerEvents(new toxicWaterBottling(this), this);
            this.getServer().getPluginManager().registerEvents(new drinking(this), this);
            this.getServer().getPluginManager().registerEvents(new toxicWaterBottling(this),this);
            this.getServer().getPluginManager().registerEvents(new playerLeave(this, player_values), this);
            this.getServer().getPluginManager().registerEvents(new combineTinyBlaze(this), this);
            this.getServer().getPluginManager().registerEvents(new purgeEssence(this), this);
            this.getServer().getPluginManager().registerEvents(new stopCustomItemsRClick(this), this);
            this.getServer().getPluginManager().registerEvents(new useTaintPowder(this), this);
            this.getServer().getPluginManager().registerEvents(new zombieDeathLoot(this), this);
            this.getServer().getPluginManager().registerEvents(new getFishingCrate(this), this);
            this.getServer().getPluginManager().registerEvents(new useFishingCrate(this), this);
            this.getServer().getPluginManager().registerEvents(new skeleBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new zombieBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new creeperBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new spiderBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new pigZombieAngerBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new silverfishBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new blazeBuff(this), this);
            this.getServer().getPluginManager().registerEvents(new swapPowers(this), this);
            this.getServer().getPluginManager().registerEvents(new Displace(this), this);
            this.getServer().getPluginManager().registerEvents(new noInteract(this), this);
            this.getServer().getPluginManager().registerEvents(new Withdraw(this), this);
            this.getServer().getPluginManager().registerEvents(new enhancedEyes(this), this);
            this.getServer().getPluginManager().registerEvents(new Possession(this), this);
            this.getServer().getPluginManager().registerEvents(new increaseToxInRain(this), this);
            this.getServer().getPluginManager().registerEvents(new enduranceCancelHunger(this), this);
            this.getServer().getPluginManager().registerEvents(new Agility(this), this);
            this.getServer().getPluginManager().registerEvents(new preventNormalEnd(this), this);
            this.getServer().getPluginManager().registerEvents(new descensionMobDetection(this), this);
        }

        //command reg
        {
            this.getCommand("thirst").setExecutor(new ThirstCommand(this));
            this.getCommand("thirst").setTabCompleter(new valueCommandsTabComplete());

            this.getCommand("toxicity").setExecutor(new ToxicityCommand(this));
            this.getCommand("toxicity").setTabCompleter(new valueCommandsTabComplete());

            this.getCommand("deaths").setExecutor(new DeathCommand(this));
            this.getCommand("deaths").setTabCompleter(new valueCommandsTabComplete());

            this.getCommand("hardmode").setExecutor(new HardmodeCommand(this));
            this.getCommand("hardmode").setTabCompleter(new HM_TabComplete());

            this.getCommand("mana").setExecutor(new ManaCommand(this));
            this.getCommand("mana").setTabCompleter(new valueCommandsTabComplete());

            this.getCommand("powers").setExecutor(new PowersCommand(this));
            this.getCommand("powers").setTabCompleter(new PowersTabComplete());
        }

        //runnables
        {
            new actionbarRunnable().runTaskTimerAsynchronously(this, 0 ,0);
            new toxEffectsRunnable(this).runTaskTimer(this, 0, 0);
            new thirstEffectsRunnable(this).runTaskTimer(this, 0, 0);
            new deathEffectsRunnable(this).runTaskTimer(this, 0, 0);
            new playerEffectsRunnable().runTaskTimer(this,0,0);
            new toxSlowDecreaseRunnable(this).runTaskTimerAsynchronously(this, 0, 600);
            new playerSaveToConfig(this, null, player_values, true).runTaskTimerAsynchronously(this, 5, 3600);
            new manaBarRunnable(this).runTaskTimerAsynchronously(this, 0, 5);
            new manaRegen(this).runTaskTimerAsynchronously(this, 0, 10);
            new agilitySpeedRunnable(this).runTaskTimerAsynchronously(this, 0, 15);
            new descensionReset(this).runTaskTimer(this, 0, 60);
            new lostSoulParticle().runTaskTimer(this, 0, 3);
            new detectedBar(this).runTaskTimerAsynchronously(this, 0, 3);
            new shrineCapture(this).runTaskTimer(this, 0 ,0);
            new descensionTimeLimit(this).runTaskTimer(this, 0, 20);
            new detectionDecrease(this).runTaskTimerAsynchronously(this, 0, 10);

        }

        //crafting recipes
        {
            tinyBlazePowder tiny_blz = new tinyBlazePowder(this);
            tiny_blz.tiny_blaze_powder_craft();
            tiny_blz.back_to_large();

            purgeEssenceCrafting prg_ess = new purgeEssenceCrafting(this);
            prg_ess.purge_essence_craft();

            notchAppleCrafting e_gap = new notchAppleCrafting(this);
            e_gap.crafting_notch_apple();

            reedsCrafting reeds = new reedsCrafting(this);
            reeds.craft_reeds();

            sandCrafting sand = new sandCrafting(this);
            sand.craft_sand();

            hellEssence hell_essence = new hellEssence(this);
            hell_essence.craft_hell_essence();

            magicEssence magic_essence = new magicEssence(this);
            magic_essence.magic_essence_craft();
        }
    }

    @Override
    public void onDisable() {
        //just a copy of the playersavetoconfig runnable (idk why, but if i just call the runnable, it spits out an error)
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (String meta : player_values) {
                String filtered;
                String dot_meta;
                if (meta.contains("!")) {
                    continue;
                }
                else if (meta.contains("#") || meta.contains("*")) {
                    filtered = meta.substring(1);
                }
                else {
                    filtered = meta;
                }
                dot_meta = "." + filtered;
                this.getConfig().set("Scores.users." + player.getUniqueId() + dot_meta, player.getMetadata(filtered).get(0).asDouble());
                this.saveConfig();
            }
        }
        this.saveConfig();
    }

    public void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

}
