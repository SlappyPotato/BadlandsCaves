package me.fullpotato.badlandscaves.CustomItems.Using;

import me.fullpotato.badlandscaves.BadlandsCaves;
import me.fullpotato.badlandscaves.CustomItems.CustomItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class UseChargedRune implements Listener {
    private BadlandsCaves plugin;
    private String title = "§8Upgrade Supernatural Abilities";
    private String confirm_title;
    private ItemStack empty;
    private final int displace_max_level = 2;
    private final int eyes_max_level = 2;
    private final int withdraw_max_level = 2;
    private final int possess_max_level = 2;
    private final int agility_max_level = 2;
    private final int endurance_max_level = 2;
    private final int max_mana_max = 200;

    public UseChargedRune(BadlandsCaves plugin) {
        this.plugin = plugin;
        makeEmpty();
    }

    private void makeEmpty() {
        empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta empty_meta = empty.getItemMeta();
        empty_meta.setDisplayName("§r");
        empty.setItemMeta(empty_meta);
    }
    
    @EventHandler
    public void useChargedRune(PlayerInteractEvent event) {
        final Action action = event.getAction();
        if (!action.equals(Action.RIGHT_CLICK_BLOCK) && !action.equals(Action.RIGHT_CLICK_AIR)) return;
        if (event.getItem() == null) return;

        final ItemStack current = event.getItem();
        final ItemStack charged_rune = CustomItem.CHARGED_RUNE.getItem();
        if (!current.isSimilar(charged_rune)) return;

        final Player player = event.getPlayer();
        event.setCancelled(true);

        if (event.getHand() == null || event.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        if (!player.getMetadata("has_supernatural_powers").get(0).asBoolean()) {
            player.sendMessage(ChatColor.RED + "This item can only be used by Heretics.");
            return;
        }

        openGUI(player);
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.PLAYERS, 0.5F, 1);
        player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.PLAYERS, 0.5F, 1);
    }

    @EventHandler
    public void closeInventory (InventoryCloseEvent event) {
        final Inventory inventory = event.getInventory();
        final InventoryView view = event.getView();
        final Player player = (Player) event.getPlayer();

        if ((view.getTitle().equals(title) || view.getTitle().equals(confirm_title))) {
            // FIXME: 4/28/2020 uh sound no work pls fix
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!player.getOpenInventory().getTitle().equals(confirm_title)) {
                        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.PLAYERS, 0.5F, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.PLAYERS, 0.5F, 1);
                    }
                }
            }.runTaskLaterAsynchronously(plugin, 1);
        }
    }

    public void openGUI(final Player player) {
        Inventory inventory = plugin.getServer().createInventory(player, 27, title);
        
        for (int i = 0; i < 10; i++) {
            inventory.setItem(i, empty);
        }
        for (int i = 17; i < 27; i++) {
            inventory.setItem(i, empty);
        }

        inventory.setItem(10, generateDisplaceIcon(player));
        inventory.setItem(11, generateWithdrawIcon(player));
        inventory.setItem(12, generateAgilityIcon(player));
        inventory.setItem(13, generateManaIcon(player));
        inventory.setItem(14, generateEnduranceIcon(player));
        inventory.setItem(15, generateEyesIcon(player));
        inventory.setItem(16, generatePossessIcon(player));

        player.openInventory(inventory);
    }

    @EventHandler
    public void menuClick (InventoryClickEvent event) {
        final Inventory clicked_inv = event.getClickedInventory();
        if (clicked_inv != null) {
            final boolean confirmGUI = (confirm_title != null && !confirm_title.isEmpty() && event.getView().getTitle().equals(confirm_title));
            if (event.getView().getTitle().equals(title) || confirmGUI) {
                final Inventory target_inv = event.getView().getTopInventory();
                final Inventory player_inv = event.getView().getBottomInventory();
                final Player player = (Player) event.getWhoClicked();
                final ItemStack current = event.getCurrentItem();
                final ItemStack charged_rune = CustomItem.CHARGED_RUNE.getItem();
                final int slot = event.getSlot();

                if (clicked_inv.equals(target_inv)) {
                    if (current == null || current.getType().equals(Material.BARRIER) || current.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
                        event.setCancelled(true);
                    }
                    else {
                        HashMap<ItemStack, String> icons = new HashMap<>();
                        icons.put(generateDisplaceIcon(player), "displace_level");
                        icons.put(generateWithdrawIcon(player), "withdraw_level");
                        icons.put(generateAgilityIcon(player), "agility_level");
                        icons.put(generateManaIcon(player), "max_mana");
                        icons.put(generateEnduranceIcon(player), "endurance_level");
                        icons.put(generateEyesIcon(player), "eyes_level");
                        icons.put(generatePossessIcon(player), "possess_level");


                        if (icons.containsKey(current)) {
                            event.setCancelled(true);
                            if (!confirmGUI) {
                                openConfirmationGUI(player, current);
                            }
                        }
                        else if (confirmGUI) {
                            if (current.getType().equals(Material.LIME_CONCRETE)) {
                                event.setCancelled(true);
                                if (player.getInventory().getItemInMainHand().isSimilar(charged_rune)) {
                                    player.getInventory().setItemInMainHand(null);

                                    String power = icons.get(clicked_inv.getItem(4));

                                    if (power.equals("max_mana")) {
                                        player.setMetadata(power, new FixedMetadataValue(plugin, player.getMetadata(power).get(0).asInt() + 10));
                                    }
                                    else {
                                        player.setMetadata(power, new FixedMetadataValue(plugin, player.getMetadata(power).get(0).asInt() + 1));
                                    }

                                    player.closeInventory();
                                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 0.5F, 1);
                                    player.sendMessage("§7The power of the Rune flows into your body.");
                                }
                            }
                            else if (current.getType().equals(Material.RED_CONCRETE)) {
                                event.setCancelled(true);
                                openGUI(player);
                            }
                        }
                    }
                }
                else if (clicked_inv.equals(player_inv)) {
                    if (current != null && current.isSimilar(charged_rune)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    public void openConfirmationGUI (final Player player, final ItemStack item) {
        String item_name = item.getItemMeta().getDisplayName().split("\\|")[0];
        confirm_title = "§8Upgrade " + item_name.substring(0, item_name.length() - 3) + ChatColor.DARK_GRAY + "?";
        Inventory inventory = plugin.getServer().createInventory(player, 9, confirm_title);

        ItemStack confirm = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta confirm_meta = confirm.getItemMeta();
        confirm_meta.setDisplayName("§aConfirm");
        confirm.setItemMeta(confirm_meta);

        ItemStack decline = new ItemStack(Material.RED_CONCRETE);
        ItemMeta decline_meta = confirm.getItemMeta();
        decline_meta.setDisplayName("§cDecline");
        decline.setItemMeta(decline_meta);

        inventory.setItem(0, empty);
        inventory.setItem(1, empty);
        inventory.setItem(2, confirm);
        inventory.setItem(3, empty);
        inventory.setItem(4, item);
        inventory.setItem(5, empty);
        inventory.setItem(6, decline);
        inventory.setItem(7, empty);
        inventory.setItem(8, empty);

        player.openInventory(inventory);
    }


    private ItemStack generateDisplaceIcon(final Player player) {
        final int displace_level = player.getMetadata("displace_level").get(0).asInt();
        if (displace_level >= displace_max_level) {
            ItemStack displace_cancel = new ItemStack(Material.BARRIER);
            ItemMeta displace_cancel_meta = displace_cancel.getItemMeta();
            displace_cancel_meta.setDisplayName("§dDisplace §8| §aMAX LEVEL");

            ArrayList<String> displace_cancel_lore = new ArrayList<>();
            displace_cancel_lore.add("§cYou cannot upgrade this power anymore.");

            displace_cancel_meta.setLore(displace_cancel_lore);
            displace_cancel.setItemMeta(displace_cancel_meta);

            return displace_cancel;
        }
        else {
            ItemStack displace = CustomItem.DISPLACE.getItem();
            ItemMeta displace_meta = displace.getItemMeta();
            displace_meta.setDisplayName("§dDisplace §8| §7Upgrade to Level §a" + (displace_level + 1));

            ArrayList<String> displace_lore = new ArrayList<>();
            if (displace_level == 0) {
                final int cost = plugin.getConfig().getInt("game_values.displace_mana_cost");
                //displace_lore.add("§dDisplace §7Level §a1");
                displace_lore.add("§7--------------------");
                displace_lore.add("§7Place a marker, and teleport to it.");
                displace_lore.add("§7--------------------");
                displace_lore.add("§7Range: §a10 §7Blocks.");
                displace_lore.add("§3Mana §7Cost: §a" + cost + " §3Mana§7.");
                displace_lore.add("§7Requires Line of Sight.");
            }
            else if (displace_level == 1) {
                //displace_lore.add("§dDisplace §7Level §a2");
                displace_lore.add("§7--------------------");
                displace_lore.add("§7Range upgraded to §a20 §7Blocks.");
                displace_lore.add("§7Resets fall damage upon using §dDisplace§7.");
            }

            displace_meta.setLore(displace_lore);
            displace.setItemMeta(displace_meta);

            return displace;
        }
    }

    private ItemStack generateWithdrawIcon(final Player player) {
        final int withdraw_level = player.getMetadata("withdraw_level").get(0).asInt();
        if (withdraw_level >= withdraw_max_level) {
            ItemStack withdraw_cancel = new ItemStack(Material.BARRIER);
            ItemMeta withdraw_cancel_meta = withdraw_cancel.getItemMeta();
            withdraw_cancel_meta.setDisplayName("§8Withdraw §8| §aMAX LEVEL");

            ArrayList<String> withdraw_cancel_lore = new ArrayList<>();
            withdraw_cancel_lore.add("§cYou cannot upgrade this power anymore.");

            withdraw_cancel_meta.setLore(withdraw_cancel_lore);
            withdraw_cancel.setItemMeta(withdraw_cancel_meta);

            return withdraw_cancel;
        }
        else {
            ItemStack withdraw = CustomItem.WITHDRAW.getItem();
            ItemMeta withdraw_meta = withdraw.getItemMeta();
            withdraw_meta.setDisplayName("§8Withdraw §8| §7Upgrade to Level §a" + (withdraw_level + 1));

            ArrayList<String> withdraw_lore = new ArrayList<>();
            if (withdraw_level == 0) {
                final int cost = plugin.getConfig().getInt("game_values.withdraw_mana_cost");
                //withdraw_lore.add("§7Withdraw §8| §7Level §a1");
                withdraw_lore.add("§7--------------------");
                withdraw_lore.add("§7Retreat to an alternate copy of your surroundings.");
                withdraw_lore.add("§7--------------------");
                withdraw_lore.add("§3Mana §7Cost: §a" + cost + " §3Mana§7.");
            }
            else if (withdraw_level == 1) {
                //withdraw_lore.add("§7Withdraw §8| §7Level §a2");
                withdraw_lore.add("§7--------------------");
                withdraw_lore.add("§7Any §dDisplace §7markers placed within the §8Withdraw§7 world");
                withdraw_lore.add("§7are transferred back to the real world upon returning.");
                withdraw_lore.add("§7--------------------");
                withdraw_lore.add("§7You regenerate health, hunger, thirst, and toxicity");
                withdraw_lore.add("§7passively in the §8Withdraw§7 world.");
            }

            withdraw_meta.setLore(withdraw_lore);
            withdraw.setItemMeta(withdraw_meta);

            return withdraw;
        }
    }

    private ItemStack generateEyesIcon(final Player player) {
        final int eyes_level = player.getMetadata("eyes_level").get(0).asInt();
        if (eyes_level >= eyes_max_level) {
            ItemStack eyes_cancel = new ItemStack(Material.BARRIER);
            ItemMeta eyes_cancel_meta = eyes_cancel.getItemMeta();
            eyes_cancel_meta.setDisplayName("§9Enhanced Eyes §8| §aMAX LEVEL");

            ArrayList<String> eyes_cancel_lore = new ArrayList<>();
            eyes_cancel_lore.add("§cYou cannot upgrade this power anymore.");

            eyes_cancel_meta.setLore(eyes_cancel_lore);
            eyes_cancel.setItemMeta(eyes_cancel_meta);

            return eyes_cancel;
        }
        else {
            ItemStack eyes = CustomItem.ENHANCED_EYES.getItem();
            ItemMeta eyes_meta = eyes.getItemMeta();
            eyes_meta.setDisplayName("§9Enhanced Eyes §8| §7Upgrade to Level §a" + (eyes_level + 1));

            ArrayList<String> eyes_lore = new ArrayList<>();
            if (eyes_level == 0) {
                final int cost = plugin.getConfig().getInt("game_values.eyes_mana_cost");
                final int drain = plugin.getConfig().getInt("game_values.eyes_mana_drain");
                eyes_lore.add("§7--------------------");
                eyes_lore.add("§7See important blocks and entities around you through walls.");
                eyes_lore.add("§7--------------------");
                eyes_lore.add("§7Range: §a7 §7Blocks.");
                eyes_lore.add("§3Mana §7Cost: §a" + cost + " §7initial, §a" + drain + " §7drained per second after.");
            }
            else if (eyes_level == 1) {
                eyes_lore.add("§7--------------------");
                eyes_lore.add("§7Range increased to §a15 §7Blocks.");
                eyes_lore.add("§7--------------------");
                eyes_lore.add("§7Blocks are now color coded:");
                eyes_lore.add("§7Gray - Tier 1 Ores");
                eyes_lore.add("§9Blue - Tier 2 Ores");
                eyes_lore.add("§aGreen - Storage Containers and Spawners");
                eyes_lore.add("§7--------------------");
                eyes_lore.add("§7Entities are now color coded:");
                eyes_lore.add("§aGreen - Passive Mobs");
                eyes_lore.add("§cRed - Hostile Mobs");
                eyes_lore.add("§7Gray - Other");
            }


            eyes_meta.setLore(eyes_lore);
            eyes.setItemMeta(eyes_meta);

            return eyes;
        }
    }

    private ItemStack generatePossessIcon(final Player player) {
        final int possess_level = player.getMetadata("possess_level").get(0).asInt();
        if (possess_level >= possess_max_level) {
            ItemStack possess_cancel = new ItemStack(Material.BARRIER);
            ItemMeta possess_cancel_meta = possess_cancel.getItemMeta();
            possess_cancel_meta.setDisplayName("§2Possession §8| §aMAX LEVEL");

            ArrayList<String> possess_cancel_lore = new ArrayList<>();
            possess_cancel_lore.add("§cYou cannot upgrade this power anymore.");

            possess_cancel_meta.setLore(possess_cancel_lore);
            possess_cancel.setItemMeta(possess_cancel_meta);

            return possess_cancel;
        }
        else {
            ItemStack possess = CustomItem.POSSESS.getItem();
            ItemMeta possess_meta = possess.getItemMeta();
            possess_meta.setDisplayName("§2Possession §8| §7Upgrade to Level §a" + (possess_level + 1));

            ArrayList<String> possess_lore = new ArrayList<>();
            if (possess_level == 0) {
                final int drain = plugin.getConfig().getInt("game_values.possess_mana_drain");
                final int cost = plugin.getConfig().getInt("game_values.possess_mana_cost");
                possess_lore.add("§7--------------------");
                possess_lore.add("§7Take control of monsters.");
                possess_lore.add("§7Upon ending, your body materializes");
                possess_lore.add("§7where you were standing before.");
                possess_lore.add("§7--------------------");
                possess_lore.add("§3Mana §7Cost: §a" + cost + " §7initial, §a" + drain + " §7drained per second after.");
            }
            else if (possess_level == 1) {
                possess_lore.add("§7--------------------");
                possess_lore.add("§7Put down a §dDisplace §7marker before Possession ends to reappear there,");
                possess_lore.add("§7instead of at your original body's location. This costs §ano §3Mana§7.");
            }

            possess_meta.setLore(possess_lore);
            possess.setItemMeta(possess_meta);

            return possess;
        }

    }

    private ItemStack generateAgilityIcon(final Player player) {
        final int agility_level = player.getMetadata("agility_level").get(0).asInt();
        if (agility_level >= agility_max_level) {
            ItemStack agility_cancel = new ItemStack(Material.BARRIER);
            ItemMeta agility_cancel_meta = agility_cancel.getItemMeta();
            agility_cancel_meta.setDisplayName("§bAgility §8| §aMAX LEVEL");

            ArrayList<String> agility_cancel_lore = new ArrayList<>();
            agility_cancel_lore.add("§cYou cannot upgrade this power anymore.");

            agility_cancel_meta.setLore(agility_cancel_lore);
            agility_cancel.setItemMeta(agility_cancel_meta);

            return agility_cancel;
        }
        else {
            ItemStack agility_icon = new ItemStack(Material.FEATHER);
            ItemMeta meta = agility_icon.getItemMeta();

            meta.setDisplayName("§bAgility §8| §7Upgrade to Level §a" + (agility_level + 1));

            ArrayList<String> lore = new ArrayList<>();
            if (agility_level == 0) {
                lore.add("§7--------------------");
                lore.add("§7Passive Ability: §bSpeed §a+1");
                lore.add("§7Adds Double Jump ability. Costs §a5 §3Mana§7 to use.");
            }
            else if (agility_level == 1) {
                lore.add("§7--------------------");
                lore.add("§7Increases §bSpeed §7another level.");
                lore.add("§7Double Jump ability travels a lot further.");
            }

            meta.setLore(lore);
            agility_icon.setItemMeta(meta);
            return agility_icon;
        }

    }

    private ItemStack generateEnduranceIcon(final Player player) {
        final int endurance_level = player.getMetadata("endurance_level").get(0).asInt();
        if (endurance_level >= endurance_max_level) {
            ItemStack endurance_cancel = new ItemStack(Material.BARRIER);
            ItemMeta endurance_cancel_meta = endurance_cancel.getItemMeta();
            endurance_cancel_meta.setDisplayName("§6Endurance §8| §aMAX LEVEL");

            ArrayList<String> endurance_cancel_lore = new ArrayList<>();
            endurance_cancel_lore.add("§cYou cannot upgrade this power anymore.");

            endurance_cancel_meta.setLore(endurance_cancel_lore);
            endurance_cancel.setItemMeta(endurance_cancel_meta);

            return endurance_cancel;
        }
        else {
            ItemStack endurance_icon = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta meta = endurance_icon.getItemMeta();

            meta.setDisplayName("§6Endurance §8| §7Upgrade to Level §a" + (endurance_level + 1));
            meta.setCustomModelData(123);
            ArrayList<String> lore = new ArrayList<>();
            if (endurance_level == 0) {
                lore.add("§7--------------------");
                lore.add("§7You don't become hungry or thirsty as often.");
                lore.add("§7Every time Hunger or Thirst decreases,");
                lore.add("§7there's a §a25% §7chance it doesn't happen.");
            } else if (endurance_level == 1) {
                lore.add("§7--------------------");
                lore.add("§7You're now even more likely to not become hungry or thirsty.");
                lore.add("§7Increases chance to §a50%");
            }

            meta.setLore(lore);
            endurance_icon.setItemMeta(meta);
            return endurance_icon;
        }
    }

    private ItemStack generateManaIcon(final Player player) {
        final int max_mana = player.getMetadata("max_mana").get(0).asInt();
        if (max_mana >= max_mana_max) {
            ItemStack max_mana_cancel = new ItemStack(Material.BARRIER);
            ItemMeta cancel_meta = max_mana_cancel.getItemMeta();
            cancel_meta.setDisplayName("§3Max Mana §8| §aMAX LEVEL");

            ArrayList<String> cancel_lore = new ArrayList<>();
            cancel_lore.add("§cYou cannot upgrade this anymore.");

            cancel_meta.setLore(cancel_lore);
            max_mana_cancel.setItemMeta(cancel_meta);

            return max_mana_cancel;
        }
        else {
            ItemStack icon = CustomItem.MAGIC_ESSENCE.getItem();
            ItemMeta meta = icon.getItemMeta();
            meta.setDisplayName("§3Max Mana §8| §7Increase to §a" + (max_mana + 10));

            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7--------------------");
            lore.add("§7Increase your §3Max Mana §7by §a10 §7points.");

            meta.setLore(lore);
            icon.setItemMeta(meta);

            return icon;
        }
    }
}