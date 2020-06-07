package me.fullpotato.badlandscaves.MobBuffs;

import me.fullpotato.badlandscaves.BadlandsCaves;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ZombieBuff implements Listener {
    private BadlandsCaves plugin;
    private World descension_world;
    private World reflection_world;
    public ZombieBuff(BadlandsCaves bcav) {
        plugin = bcav;
        descension_world = plugin.getServer().getWorld(plugin.descensionWorldName);
        reflection_world = plugin.getServer().getWorld(plugin.reflectionWorldName);
    }

    @EventHandler
    public void HM_zombie (CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Zombie)) return;
        if (event.getEntityType().equals(EntityType.PIG_ZOMBIE)) return;

        //make sure zombie is not a descension "Lost Soul"
        World world = event.getLocation().getWorld();
        if (world == null) return;
        if (world.equals(descension_world)) return;
        if (world.equals(reflection_world)) return;


        //giving armor
        Zombie zombie = (Zombie) event.getEntity();
        Random random = new Random();


        final boolean hardmode = plugin.getConfig().getBoolean("game_values.hardmode");
        final int chaos = plugin.getConfig().getInt("game_values.chaos_level");
        final double chance = Math.pow(1.045, chaos) - 1;
        if (hardmode) {

            final int augment = (chaos / 5) + plugin.getConfig().getInt("game_values.hardmode_values.augmented_spawn_chance");
            if (random.nextInt(100) < augment) {
                zombie.setBaby(false);
                zombie.getPersistentDataContainer().set(new NamespacedKey(plugin, "augmented"), PersistentDataType.BYTE, (byte) 1);
                zombie.getPersistentDataContainer().set(new NamespacedKey(plugin, "time_stop_cooldown"), PersistentDataType.BYTE, (byte) 0);
                zombie.getPersistentDataContainer().set(new NamespacedKey(plugin, "wryy_cooldown"), PersistentDataType.BYTE, (byte) 0);
                zombie.setCustomName(ChatColor.GOLD.toString() + ChatColor.BOLD + "DIO");

                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta boots_meta = (LeatherArmorMeta) boots.getItemMeta();
                boots_meta.setColor(Color.fromRGB(125, 81, 0));
                boots.setItemMeta(boots_meta);


                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta leggings_meta = (LeatherArmorMeta) leggings.getItemMeta();
                leggings_meta.setColor(Color.fromRGB(237, 177, 12));
                leggings.setItemMeta(leggings_meta);

                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chestplate_meta = (LeatherArmorMeta) chestplate.getItemMeta();
                chestplate_meta.setColor(Color.fromRGB(237, 177, 12));
                chestplate.setItemMeta(chestplate_meta);

                ItemStack[] armor = {boots, leggings, chestplate, null};
                zombie.getEquipment().setArmorContents(armor);

                zombie.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(25.0);
                zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue((chaos / 4.0) + 25);

                zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
                zombie.setHealth(40.0);
            }


            if (!zombie.getPersistentDataContainer().has(new NamespacedKey(plugin, "augmented"), PersistentDataType.BYTE) || zombie.getPersistentDataContainer().get(new NamespacedKey(plugin, "augmented"), PersistentDataType.BYTE) != (byte) 1) {
                boolean overpowered = random.nextInt(100) < chance;

                //SWORD-------------------------------------------------------------------
                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
                ItemMeta sword_meta = sword.getItemMeta();
                sword_meta.addEnchant(Enchantment.DAMAGE_ALL, random.nextInt(overpowered ? 8 : 5) + 2, true);
                final int fire = random.nextInt(2);
                if (fire > 0) {
                    sword_meta.addEnchant(Enchantment.FIRE_ASPECT, fire, false);
                }
                sword_meta.setDisplayName(ChatColor.DARK_GREEN + "Zombie's Sword");
                sword.setItemMeta(sword_meta);

                //ARMOR----------------------------------------------------------------
                int[] armor_protections = random.ints(4, 0, overpowered ? 10 : 5).toArray();

                //BOOTS-----------------------------
                boolean dia_upg = (overpowered && random.nextInt(100) < 75) || (!overpowered && random.nextBoolean());
                ItemStack boots = dia_upg ? new ItemStack(Material.DIAMOND_BOOTS) : new ItemStack(Material.IRON_BOOTS);
                if (armor_protections[0] > 0) {
                    ItemMeta boots_meta = boots.getItemMeta();
                    boots_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, armor_protections[0], true);
                    boots.setItemMeta(boots_meta);
                }

                //LEGGINGS--------------------------
                dia_upg = (overpowered && random.nextInt(100) < 75) || (!overpowered && random.nextBoolean());
                ItemStack leggings = dia_upg ? new ItemStack(Material.DIAMOND_LEGGINGS) : new ItemStack(Material.IRON_LEGGINGS);
                if (armor_protections[1] > 0){
                    ItemMeta leggings_meta = leggings.getItemMeta();
                    leggings_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, armor_protections[1], true);
                    leggings.setItemMeta(leggings_meta);
                }

                //CHESTPLATE------------------------
                dia_upg = (overpowered && random.nextInt(100) < 75) || (!overpowered && random.nextBoolean());
                ItemStack chestplate = dia_upg ? new ItemStack(Material.DIAMOND_CHESTPLATE) : new ItemStack(Material.IRON_CHESTPLATE);
                if (armor_protections[2] > 0){
                    ItemMeta chestplate_meta = chestplate.getItemMeta();
                    chestplate_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, armor_protections[2], true);
                    chestplate.setItemMeta(chestplate_meta);
                }

                //HELMET-----------------------------
                dia_upg = (overpowered && random.nextInt(100) < 75) || (!overpowered && random.nextBoolean());
                ItemStack helmet = dia_upg ? new ItemStack(Material.DIAMOND_HELMET) : new ItemStack(Material.IRON_HELMET);
                if (armor_protections[3] > 0){
                    ItemMeta helmet_meta = helmet.getItemMeta();
                    helmet_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, armor_protections[3], true);
                    helmet.setItemMeta(helmet_meta);
                }
                //------------------------------------------------------------------------

                ItemStack[] armor = {
                        boots,
                        leggings,
                        chestplate,
                        helmet,
                };

                zombie.getEquipment().setArmorContents(armor);
                zombie.getEquipment().setItemInMainHand(sword);
            }

            zombie.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(999);
        }
        else {
            if (chaos > 0) {
                HashMap<Material, Material> armor_materials = new HashMap<>();
                armor_materials.put(Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS);
                armor_materials.put(Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS);
                armor_materials.put(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE);
                armor_materials.put(Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET);

                ArrayList<ItemStack> armors = new ArrayList<>();
                for (Material armor_material : armor_materials.keySet()) {
                    if (random.nextInt(100) < chance) {
                        ItemStack armor = new ItemStack(random.nextInt(100) < chance ? armor_materials.get(armor_material) : armor_material);
                        if (random.nextInt(100) < chance) {
                            ItemMeta meta = armor.getItemMeta();

                            int protection = random.nextInt(5);
                            if (protection > 0) {
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protection, true);
                                armor.setItemMeta(meta);
                            }
                        }
                        armors.add(armor);
                    }
                }

                ItemStack[] armors_array = new ItemStack[4];
                for (ItemStack armor : armors) {
                    if (armor.getType().toString().contains("BOOTS")) {
                        armors_array[0] = armor;
                    }
                    else if (armor.getType().toString().contains("LEGGINGS")) {
                        armors_array[1] = armor;
                    }
                    else if (armor.getType().toString().contains("CHESTPLATE")) {
                        armors_array[2] = armor;
                    }
                    else if (armor.getType().toString().contains("HELMET")) {
                        armors_array[3] = armor;
                    }
                }

                zombie.getEquipment().setArmorContents(armors_array);

                if (random.nextInt(100) < chance) {
                    ItemStack sword = new ItemStack(random.nextInt(100) < chance ? Material.IRON_SWORD : Material.STONE_SWORD);
                    if (random.nextInt(100) < chance) {
                        ItemMeta meta = sword.getItemMeta();

                        int sharpness = random.nextInt(5);
                        if (sharpness > 0) {
                            meta.addEnchant(Enchantment.DAMAGE_ALL, sharpness, true);
                            sword.setItemMeta(meta);
                        }
                    }
                    zombie.getEquipment().setItemInMainHand(sword);
                }
            }
        }
    }
}