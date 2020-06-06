package me.fullpotato.badlandscaves.badlandscaves.CustomItemRecipes;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class VoltshockCrafting {
    private BadlandsCaves plugin;

    public VoltshockCrafting(BadlandsCaves plugin) {
        this.plugin = plugin;
    }

    public void craft_battery() {
        final ItemStack battery = CustomItem.VOLTSHOCK_BATTERY.getItem();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "battery"), battery);
        /*
        *  %
        * #*#
        * #*#
        * # is iron ingot, * is redstone dust, % is comparator
        */

        recipe.shape(" % ", "#*#", "#*#");
        recipe.setIngredient('%', Material.COMPARATOR);
        recipe.setIngredient('*', Material.REDSTONE);
        recipe.setIngredient('#', Material.IRON_INGOT);

        plugin.getServer().addRecipe(recipe);
    }

    public void craft_shocker() {
        final ItemStack shocker = CustomItem.VOLTSHOCK_SHOCKER.getItem();
        shocker.setAmount(3);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "shocker"), shocker);
        /*
        * *#*
        * *#*
        * *#*
        * * is redstone, # is iron bar
        */
        recipe.shape("*#*", "*#*", "*#*");
        recipe.setIngredient('*', Material.REDSTONE);
        recipe.setIngredient('#', Material.IRON_BARS);

        plugin.getServer().addRecipe(recipe);
    }

    public void modify_sword() {
        final ItemStack placeholder = CustomItem.VOLTSHOCK_PLACEHOLDER.getItem();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "voltshock_module"), placeholder);

        /*
        *  *|
        *  *|
        * #&
        * # = battery, * = redstone, | = shocker
        * */
        recipe.shape(" *|", " *|", "#& ");
        recipe.setIngredient('#', Material.COMMAND_BLOCK);
        recipe.setIngredient('*', Material.REDSTONE);
        recipe.setIngredient('|', Material.COMMAND_BLOCK);
        recipe.setIngredient('&', new RecipeChoice.MaterialChoice(Material.IRON_SWORD, Material.GOLDEN_SWORD));

        plugin.getServer().addRecipe(recipe);
    }

    public void charge_sword() {
        final ItemStack voltshock_sword_charge_placeholder = CustomItem.VOLTSHOCK_SWORD_CHARGE_PLACEHOLDER.getItem();
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, "charge_voltshock_sword"), voltshock_sword_charge_placeholder);
        recipe.addIngredient(Material.EXPERIENCE_BOTTLE);
        recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.IRON_SWORD, Material.GOLDEN_SWORD));

        plugin.getServer().addRecipe(recipe);

    }

    public void craft_arrow() {
        final ItemStack voltshock_arrow = CustomItem.VOLTSHOCK_ARROW.getItem();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "voltshock_arrow"), voltshock_arrow);

        /*
        *   #
        *  *
        * &%
        * where & is battery, % is arrow, * is redstone, and # is shocker
        * */

        recipe.shape("  #", " * ", "&% ");
        recipe.setIngredient('#', Material.COMMAND_BLOCK);
        recipe.setIngredient('*', Material.REDSTONE);
        recipe.setIngredient('&', Material.COMMAND_BLOCK);
        recipe.setIngredient('%', Material.ARROW);

        plugin.getServer().addRecipe(recipe);
    }
}