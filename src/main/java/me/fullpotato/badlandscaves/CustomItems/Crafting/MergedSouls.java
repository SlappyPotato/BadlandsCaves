package me.fullpotato.badlandscaves.CustomItems.Crafting;

import me.fullpotato.badlandscaves.CustomItems.CustomItem;
import me.fullpotato.badlandscaves.BadlandsCaves;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.SoundCategory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashMap;

public class MergedSouls extends MatchCrafting implements Listener {
    private final BadlandsCaves plugin;
    public MergedSouls(BadlandsCaves bcav) {
        plugin = bcav;
    }

    public void merge_souls() {
        final ItemStack merged_souls = CustomItem.MERGED_SOULS.getItem();

        ShapelessRecipe merged_souls_recipe = new ShapelessRecipe(new NamespacedKey(plugin, "merged_souls"), merged_souls);
        for (int a = 0; a < 9; a++) {
            merged_souls_recipe.addIngredient(Material.COMMAND_BLOCK);
        }

        plugin.getServer().addRecipe(merged_souls_recipe);
    }

    @EventHandler
    public void merged_souls (PrepareItemCraftEvent event) {
        if (event.getRecipe() == null || event.getRecipe().getResult() == null) return;

        final ItemStack result = event.getRecipe().getResult();
        final ItemStack merged_souls = CustomItem.MERGED_SOULS.getItem();

        if (!result.isSimilar(merged_souls)) return;

        HashMap<ItemStack, Integer> souls = new HashMap<>();
        souls.put(CustomItem.ZOMBIE_SOUL.getItem(), 1);
        souls.put(CustomItem.CREEPER_SOUL.getItem(), 1);
        souls.put(CustomItem.SPIDER_SOUL.getItem(), 1);
        souls.put(CustomItem.SKELETON_SOUL.getItem(), 1);
        souls.put(CustomItem.GHAST_SOUL.getItem(), 1);
        souls.put(CustomItem.SILVERFISH_SOUL.getItem(), 1);
        souls.put(CustomItem.PHANTOM_SOUL.getItem(), 1);
        souls.put(CustomItem.PIGZOMBIE_SOUL.getItem(), 1);
        souls.put(CustomItem.WITCH_SOUL.getItem(), 1);


        if (!isMatching(event.getInventory().getMatrix(), souls)) {
            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void craftSound (CraftItemEvent event) {
        final ItemStack merged_souls = CustomItem.MERGED_SOULS.getItem();
        if (event.getRecipe().getResult().isSimilar(merged_souls)) {
            for (HumanEntity human : event.getViewers()) {
                if (human instanceof Player) {
                    Player player = (Player) human;
                    player.playSound(player.getLocation(), "custom.darkrooms_whispers", SoundCategory.BLOCKS, 0.05F, 1);
                }
            }
        }
    }

}
