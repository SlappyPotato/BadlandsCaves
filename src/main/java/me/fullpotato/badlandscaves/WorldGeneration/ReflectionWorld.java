package me.fullpotato.badlandscaves.WorldGeneration;

import me.fullpotato.badlandscaves.BadlandsCaves;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class ReflectionWorld {
    private final BadlandsCaves plugin;

    public ReflectionWorld(BadlandsCaves plugin) {
        this.plugin = plugin;
    }


    public void gen_refl_world() {
        WorldCreator reflectionworld = new WorldCreator(plugin.getReflectionWorldName());
        reflectionworld.environment(World.Environment.NORMAL)
                .generator(new ReflectionGen());
        World world_reflection = plugin.getServer().createWorld(reflectionworld);
        world_reflection.setDifficulty(Difficulty.HARD);
        //gamerules
        world_reflection.setGameRule(GameRule.DO_INSOMNIA, false);
        world_reflection.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world_reflection.setGameRule(GameRule.FALL_DAMAGE, false);
        world_reflection.setGameRule(GameRule.DISABLE_RAIDS, true);
        world_reflection.setGameRule(GameRule.KEEP_INVENTORY, true);
        world_reflection.setGameRule(GameRule.DO_TILE_DROPS, false);
        world_reflection.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world_reflection.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world_reflection.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world_reflection.setGameRule(GameRule.MOB_GRIEFING, false);
        world_reflection.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world_reflection.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        world_reflection.setGameRule(GameRule.FIRE_DAMAGE, true);
        world_reflection.setGameRule(GameRule.DO_MOB_LOOT, false);
        world_reflection.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world_reflection.setGameRule(GameRule.DROWNING_DAMAGE, true);
        world_reflection.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);

        //spawn and border
        world_reflection.setSpawnLocation(0, 255, 0);
        world_reflection.getWorldBorder().setCenter(0, 0);
        world_reflection.getWorldBorder().setSize(1000);

    }
}
