package me.fullpotato.badlandscaves.badlandscaves.Events.CustomItems.Crafting;

import me.fullpotato.badlandscaves.badlandscaves.BadlandsCaves;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Voltshock extends MatchCrafting implements Listener {
    private BadlandsCaves plugin;
    private String shock_lore = "§3Voltshock";
    private Material[] swords = {
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
    };

    public Voltshock(BadlandsCaves plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void batteryAndShock (PrepareItemCraftEvent event) {
        if (event.getRecipe() != null && event.getRecipe().getResult() != null) {
            final ItemStack result = event.getRecipe().getResult();
            final ItemStack battery = ItemStack.deserialize(plugin.getConfig().getConfigurationSection("items.voltshock_battery").getValues(true));
            final ItemStack shocker = ItemStack.deserialize(plugin.getConfig().getConfigurationSection("items.voltshock_shocker").getValues(true));
            if (result.isSimilar(battery) || result.isSimilar(shocker)) {
                if (event.getViewers().get(0).getMetadata("has_supernatural_powers").get(0).asBoolean()) {
                    event.getInventory().setResult(null);
                }
            }
        }
    }


    @EventHandler
    public void applyToSword(PrepareItemCraftEvent event) {
        if (event.getRecipe() != null && event.getRecipe().getResult() != null) {
            final ItemStack result = event.getRecipe().getResult();
            final ItemStack placeholder = ItemStack.deserialize(plugin.getConfig().getConfigurationSection("items.voltshock_placeholder").getValues(true));
            if (result.isSimilar(placeholder)) {
                final ItemStack[] matrix = event.getInventory().getMatrix();
                final ItemStack battery = ItemStack.deserialize(plugin.getConfig().getConfigurationSection("items.voltshock_battery").getValues(true));
                final ItemStack shocker = ItemStack.deserialize(plugin.getConfig().getConfigurationSection("items.voltshock_shocker").getValues(true));
                if (isMatching(matrix, battery, 6) && isMatching(matrix, shocker, 2) && isMatching(matrix, shocker, 5)) {
                    if (!event.getViewers().get(0).getMetadata("has_supernatural_powers").get(0).asBoolean()) {
                        SerratedSwords serrated = new SerratedSwords(plugin);
                        for (ItemStack item : matrix) {
                            if (item != null && Arrays.asList(swords).contains(item.getType())) {
                                boolean sword_ready = !serrated.isSerrated(item) && !isVoltshock(item);
                                if (sword_ready) {
                                    ItemStack modified_sword = item.clone();
                                    ItemMeta meta = modified_sword.getItemMeta();
                                    meta.setCustomModelData(127);
                                    PersistentDataContainer data = meta.getPersistentDataContainer();
                                    NamespacedKey charge_key = new NamespacedKey(plugin, "charge");
                                    data.set(charge_key, PersistentDataType.INTEGER, 0);

                                    ArrayList<String> lore = new ArrayList<>();
                                    lore.add(shock_lore);
                                    lore.add("§70 / 50 Charge");
                                    meta.setLore(lore);
                                    modified_sword.setItemMeta(meta);

                                    event.getInventory().setResult(modified_sword);
                                    return;
                                }
                            }
                        }
                    }
                }
                event.getInventory().setResult(null);
            }
        }
    }

    @EventHandler
    public void chargeSword(PrepareItemCraftEvent event) {
        if (event.getRecipe() != null && event.getRecipe().getResult() != null) {
            final ItemStack result = event.getRecipe().getResult();
            final ItemStack placeholder = ItemStack.deserialize(plugin.getConfig().getConfigurationSection("items.voltshock_sword_charge_placeholder").getValues(true));
            if (result.isSimilar(placeholder)) {
                final ItemStack[] matrix = event.getInventory().getMatrix();
                ItemStack sword = null;
                ItemStack exp_bottle = null;

                for (ItemStack ingredient : matrix) {
                    if (ingredient != null) {
                        if (Arrays.asList(swords).contains(ingredient.getType())) {
                            if (isVoltshock(ingredient)) {
                                sword = ingredient;
                            }
                        } else if (ingredient.getType().equals(Material.EXPERIENCE_BOTTLE))
                            exp_bottle = ingredient;
                    }
                }

                if (sword != null && exp_bottle != null) {
                    final int current_charge = getCharge(sword);
                    if (current_charge < 50) {
                        int exp_stored = 0;
                        final ItemMeta xp_meta = exp_bottle.getItemMeta();
                        if (xp_meta.hasLore()) {
                            List<String> lore = xp_meta.getLore();
                            try {
                                exp_stored = Integer.parseInt(lore.get(0).split(" ")[0].substring(2));
                            } catch (NumberFormatException ignored) {
                            }

                            int added_charge = exp_stored / 10;
                            if (added_charge > 0) {
                                final int new_charge = Math.min(50, current_charge + added_charge);

                                ItemStack new_result = sword.clone();
                                setCharge(new_result, new_charge);

                                event.getInventory().setResult(new_result);
                                return;
                            }
                        }
                    }
                }
                event.getInventory().setResult(null);
            }
        }
    }

    public boolean isVoltshock(ItemStack item) {
        if (Arrays.asList(swords).contains(item.getType())) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    if (lore != null && lore.size() >= 1) {
                        return lore.get(0).equalsIgnoreCase(shock_lore);
                    }
                }
            }
        }
        return false;
    }

    public int getCharge(ItemStack item) {
        return isVoltshock(item) ? item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "charge"), PersistentDataType.INTEGER) : -1;
    }

    public void setCharge(ItemStack item, int charge) {
        if (isVoltshock(item)) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "charge"), PersistentDataType.INTEGER, charge);
            List<String> lore = meta.getLore();
            lore.set(1, "§7" + charge + " / 50 Charge");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public void setOnCooldown(ItemStack item, boolean onCooldown) {
        if (isVoltshock(item)) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "isOnCooldown"), PersistentDataType.STRING, Boolean.toString(onCooldown));
            meta.setCustomModelData(128); // TODO: 4/20/2020
            item.setItemMeta(meta);
        }
    }

    public boolean getOnCooldown(ItemStack item) {
        return !isVoltshock(item) || Boolean.parseBoolean(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "isOnCooldown"), PersistentDataType.STRING));
    }
}
