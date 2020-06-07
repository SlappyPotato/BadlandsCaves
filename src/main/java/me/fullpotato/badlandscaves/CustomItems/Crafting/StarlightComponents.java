package me.fullpotato.badlandscaves.CustomItems.Crafting;

import me.fullpotato.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.CustomItems.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class StarlightComponents extends MatchCrafting implements Listener {
    private BadlandsCaves plugin;

    public StarlightComponents(BadlandsCaves plugin) {
        this.plugin = plugin;
    }

    public void craftBinding () {
        ItemStack binding = CustomItem.BINDING.getItem();
        binding.setAmount(8);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "raw_binding"), binding);
        recipe.shape("#@", "@#");

        recipe.setIngredient('@', Material.GRAVEL);
        recipe.setIngredient('#', new RecipeChoice.MaterialChoice(Material.SAND, Material.RED_SAND));

        plugin.getServer().addRecipe(recipe);
    }

    public void craftGoldCable () {
        ItemStack cable = CustomItem.GOLDEN_CABLE.getItem();
        cable.setAmount(6);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "golden_cable"), cable);
        recipe.shape("###", "@@@", "###");
        recipe.setIngredient('#', Material.COMMAND_BLOCK);
        recipe.setIngredient('@', Material.GOLD_INGOT);

        plugin.getServer().addRecipe(recipe);
    }

    public void craftNetherStarFragment() {
        ItemStack fragment = CustomItem.NETHER_STAR_FRAGMENT.getItem();
        fragment.setAmount(4);

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, "nether_star_fragment"), fragment);
        recipe.addIngredient(Material.NETHER_STAR);

        plugin.getServer().addRecipe(recipe);
    }

    public void craftStarlightCircuit () {
        ItemStack circuit = CustomItem.STARLIGHT_CIRCUIT.getItem();

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "starlight_circuit"), circuit);
        recipe.shape("---", "#@#", "---");

        recipe.setIngredient('-', Material.COMMAND_BLOCK);
        recipe.setIngredient('#', Material.REDSTONE);
        recipe.setIngredient('@', Material.COMMAND_BLOCK);

        plugin.getServer().addRecipe(recipe);
    }

    public void craftStarlightBattery() {
        ItemStack battery = CustomItem.STARLIGHT_BATTERY.getItem();

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "starlight_battery"), battery);
        recipe.shape(" % ", "#@#", "#@#");

        recipe.setIngredient('%', Material.COMPARATOR);
        recipe.setIngredient('#', Material.COMMAND_BLOCK);
        recipe.setIngredient('@', Material.COMMAND_BLOCK);

        plugin.getServer().addRecipe(recipe);
    }

    public void craftStarlightModule() {
        ItemStack module = CustomItem.STARLIGHT_MODULE.getItem();

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "starlight_module"), module);
        recipe.shape("###", "#@#", "#%#");
        recipe.setIngredient('#', Material.COMMAND_BLOCK);
        recipe.setIngredient('@', Material.STRUCTURE_BLOCK);
        recipe.setIngredient('%', Material.COMMAND_BLOCK);

        plugin.getServer().addRecipe(recipe);
    }

    @EventHandler
    public void craftCable (PrepareItemCraftEvent event) {
        if (event.getRecipe() == null || event.getRecipe().getResult() == null) return;

        final ItemStack result = event.getRecipe().getResult();
        final ItemStack golden_cable = CustomItem.GOLDEN_CABLE.getItem();
        if (!result.isSimilar(golden_cable)) return;

        final ItemStack[] matrix = event.getInventory().getMatrix();
        final ItemStack binding = CustomItem.BINDING.getItem();
        if (!isMatching(matrix, binding)) event.getInventory().setResult(null);
    }

    @EventHandler
    public void craftCircuit (PrepareItemCraftEvent event) {
        if (event.getRecipe() == null || event.getRecipe().getResult() == null) return;

        final ItemStack result = event.getRecipe().getResult();
        final ItemStack starlight_circuit = CustomItem.STARLIGHT_CIRCUIT.getItem();
        if (!result.isSimilar(starlight_circuit)) return;

        final ItemStack[] matrix = event.getInventory().getMatrix();
        final ItemStack golden_cable = CustomItem.GOLDEN_CABLE.getItem();
        final ItemStack nether_star_fragment = CustomItem.NETHER_STAR_FRAGMENT.getItem();

        boolean matches = true;
        final int[] slots = {0, 1, 2, 6, 7, 8};
        for (int slot : slots) {
            if (!matrix[slot].isSimilar(golden_cable)) {
                matches = false;
            }
        }

        if (matches) {
            if (!isMatching(matrix, nether_star_fragment, 4)) {
                matches = false;
            }
        }

        if (!matches) {
            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void craftBattery (PrepareItemCraftEvent event) {
        if (event.getRecipe() == null || event.getRecipe().getResult() == null) return;

        final ItemStack result = event.getRecipe().getResult();
        final ItemStack starlight_battery = CustomItem.STARLIGHT_BATTERY.getItem();
        if (!result.isSimilar(starlight_battery)) return;

        final ItemStack[] matrix = event.getInventory().getMatrix();
        final ItemStack titanium_ingot = CustomItem.TITANIUM_INGOT.getItem();
        final ItemStack nether_star_fragment = CustomItem.NETHER_STAR_FRAGMENT.getItem();

        boolean matches = true;
        final int[] titanium_slots = {3, 5, 6, 8};
        for (int slot : titanium_slots) {
            if (!matrix[slot].isSimilar(titanium_ingot)) {
                matches = false;
            }
        }

        if (matches) {
            final int[] fragment_slots = {4, 7};
            for (int slot : fragment_slots) {
                if (!matrix[slot].isSimilar(nether_star_fragment)) {
                    matches = false;
                }
            }
        }

        if (!matches) {
            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void craftModule (PrepareItemCraftEvent event) {
        if (event.getRecipe() == null || event.getRecipe().getResult() == null) return;

        final ItemStack result = event.getRecipe().getResult();
        final ItemStack starlight_module = CustomItem.STARLIGHT_MODULE.getItem();
        if (!result.isSimilar(starlight_module)) return;

        final ItemStack[] matrix = event.getInventory().getMatrix();
        final ItemStack titanium_ingot = CustomItem.TITANIUM_INGOT.getItem();
        final ItemStack starlight_battery = CustomItem.STARLIGHT_BATTERY.getItem();
        final ItemStack starlight_circuit = CustomItem.STARLIGHT_CIRCUIT.getItem();

        boolean matches = true;
        final int[] titanium_slots = {0, 1, 2, 3, 5, 6, 8};
        for (int slot : titanium_slots) {
            if (!matrix[slot].isSimilar(titanium_ingot)) {
                matches = false;
            }
        }

        if (matches) {
            if (!isMatching(matrix, starlight_circuit)) {
                matches = false;
            }
            if (matches) {
                if (!isMatching(matrix, starlight_battery, 7)) {
                    matches = false;
                }
            }
        }

        if (!matches) {
            event.getInventory().setResult(null);
        }
    }
}