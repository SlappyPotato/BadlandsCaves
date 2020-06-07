package me.fullpotato.badlandscaves.CustomItems.Crafting;

import me.fullpotato.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.CustomItems.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class TitaniumBar extends MatchCrafting implements Listener {
    private BadlandsCaves plugin;

    public TitaniumBar(BadlandsCaves plugin) {
        this.plugin = plugin;
    }

    public void fragmentIntoBar() {
        ItemStack bar = CustomItem.TITANIUM_INGOT.getItem();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "titanium_ingot"), bar);

        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.COMMAND_BLOCK);

        plugin.getServer().addRecipe(recipe);
    }

    @EventHandler
    public void craftBar (PrepareItemCraftEvent event) {
        if (event.getRecipe() == null || event.getRecipe().getResult() == null) return;

        final ItemStack result = event.getRecipe().getResult();
        final ItemStack bar = CustomItem.TITANIUM_INGOT.getItem();
        if (!result.isSimilar(bar)) return;

        final ItemStack[] matrix = event.getInventory().getMatrix();
        final ItemStack fragment = CustomItem.TITANIUM_FRAGMENT.getItem();
        if (!isMatching(matrix, fragment)) {
            event.getInventory().setResult(null);
        }
    }
}