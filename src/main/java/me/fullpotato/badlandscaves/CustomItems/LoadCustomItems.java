package me.fullpotato.badlandscaves.CustomItems;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.UUID;

public class LoadCustomItems {
    public ItemStack getItem(CustomItem item) {
        if (item.equals(CustomItem.STARTER_SAPLING)){
            ItemStack starter_sapling = new ItemStack(Material.OAK_SAPLING);
            ItemMeta starter_sapling_meta = starter_sapling.getItemMeta();
            starter_sapling_meta.setDisplayName("§8[§aStarter Sapling§8]");
            starter_sapling_meta.addEnchant(Enchantment.DURABILITY, 1, false);
            starter_sapling_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            starter_sapling.setItemMeta(starter_sapling_meta);

            return starter_sapling;
        }

        else if (item.equals(CustomItem.STARTER_BONE_MEAL)){
            ItemStack starter_bone_meal = new ItemStack(Material.BONE_MEAL, 3);
            ItemMeta starter_bone_meal_meta = starter_bone_meal.getItemMeta();
            starter_bone_meal_meta.setDisplayName("§8[§fStarter Bone Meal§8]");
            starter_bone_meal_meta.addEnchant(Enchantment.DURABILITY, 1, false);
            starter_bone_meal_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            starter_bone_meal.setItemMeta(starter_bone_meal_meta);

            return starter_bone_meal;
        }

        else if (item.equals(CustomItem.TOXIC_WATER)){
            ItemStack toxic_water = new ItemStack(Material.POTION);
            PotionMeta toxic_water_meta = (PotionMeta) toxic_water.getItemMeta();
            ArrayList<String> toxic_water_lore = new ArrayList<>();

            toxic_water_meta.setDisplayName("§2Toxic Water Bottle");

            toxic_water_lore.add("§8Leaves a disgusting taste in your mouth");
            toxic_water_meta.setLore(toxic_water_lore);

            toxic_water_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            toxic_water_meta.setColor(Color.fromRGB(55, 117, 89));
            toxic_water_meta.setBasePotionData(new PotionData(PotionType.WATER));
            toxic_water.setItemMeta(toxic_water_meta);

            return toxic_water;
        }

        else if (item.equals(CustomItem.PURIFIED_WATER)){
            ItemStack purified_water = new ItemStack(Material.POTION);
            PotionMeta purified_water_meta = (PotionMeta) purified_water.getItemMeta();
            ArrayList<String> purified_water_lore = new ArrayList<>();

            purified_water_meta.setDisplayName("§3Purified Water Bottle");

            purified_water_lore.add("§7Light and refreshing.");
            purified_water_meta.setLore(purified_water_lore);

            purified_water_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            purified_water_meta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
            purified_water_meta.setColor(Color.fromRGB(76, 162, 255));
            purified_water.setItemMeta(purified_water_meta);

            return purified_water;
        }

        else if (item.equals(CustomItem.FISHING_CRATE)){
            ItemStack fishing_crate = new ItemStack(Material.BARREL);
            ItemMeta fishing_crate_meta = fishing_crate.getItemMeta();
            ArrayList<String> fishing_crate_lore = new ArrayList<>();
            fishing_crate_meta.setDisplayName("§2Fishing Crate");

            fishing_crate_lore.add("§7Right click to open.");
            fishing_crate_lore.add("§2Contains Prehardmode Loot.");
            fishing_crate_meta.setLore(fishing_crate_lore);

            fishing_crate_meta.addEnchant(Enchantment.DURABILITY, 1, false);
            fishing_crate_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            fishing_crate.setItemMeta(fishing_crate_meta);

            return fishing_crate;
        }

        else if (item.equals(CustomItem.FISHING_CRATE_HARDMODE)){
            ItemStack fishing_crate_hardmode = new ItemStack(Material.BARREL);
            ItemMeta fishing_crate_hardmode_meta = fishing_crate_hardmode.getItemMeta();
            ArrayList<String> fishing_crate_hardmode_lore = new ArrayList<>();
            fishing_crate_hardmode_meta.setDisplayName("§6Fishing Crate");

            fishing_crate_hardmode_lore.add("§7Right click to open.");
            fishing_crate_hardmode_lore.add("§6Contains Hardmode Loot.");
            fishing_crate_hardmode_meta.setLore(fishing_crate_hardmode_lore);

            fishing_crate_hardmode_meta.addEnchant(Enchantment.DURABILITY, 1, false);
            fishing_crate_hardmode_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            fishing_crate_hardmode.setItemMeta(fishing_crate_hardmode_meta);

            return fishing_crate_hardmode;
        }

        else if (item.equals(CustomItem.ANTIDOTE)){
            ItemStack antidote = new ItemStack(Material.POTION);
            PotionMeta antidote_meta = (PotionMeta) antidote.getItemMeta();
            ArrayList<String> antidote_lore = new ArrayList<>();

            antidote_meta.setDisplayName("§dAntidote Bottle");

            antidote_lore.add("§7Purges the toxins from your body.");
            antidote_meta.setLore(antidote_lore);

            antidote_meta.addEnchant(Enchantment.DURABILITY, 50, true);
            antidote_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
            antidote_meta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
            antidote_meta.setColor(Color.fromRGB(224, 74, 188));

            antidote.setItemMeta(antidote_meta);

            return antidote;
        }

        else if (item.equals(CustomItem.MANA_POTION)){
            ItemStack mana_potion = new ItemStack(Material.POTION);
            PotionMeta mana_potion_meta = (PotionMeta) mana_potion.getItemMeta();
            ArrayList<String> mana_potion_lore = new ArrayList<>();

            mana_potion_meta.setDisplayName("§9Mana Potion");

            mana_potion_lore.add("§7Recovers §9100 §7Mana points.");
            mana_potion_meta.setLore(mana_potion_lore);

            mana_potion_meta.addEnchant(Enchantment.DURABILITY, 100, true);
            mana_potion_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
            mana_potion_meta.setColor(Color.fromRGB(54, 137, 255));
            mana_potion_meta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));

            mana_potion.setItemMeta(mana_potion_meta);

            return mana_potion;
        }

        else if (item.equals(CustomItem.PURGE_ESSENCE)){
            ItemStack purge_essence = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta purge_essence_meta = purge_essence.getItemMeta();
            purge_essence_meta.setDisplayName("§dEssence of Purging");
            purge_essence_meta.setCustomModelData(101);
            purge_essence.setItemMeta(purge_essence_meta);

            return purge_essence;
        }

        else if (item.equals(CustomItem.HELL_ESSENCE)){
            ItemStack hell_essence = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta hell_essence_meta = hell_essence.getItemMeta();
            hell_essence_meta.setDisplayName("§cEssence of Hell");
            hell_essence_meta.setCustomModelData(105);
            hell_essence.setItemMeta(hell_essence_meta);

            return hell_essence;
        }

        else if (item.equals(CustomItem.MAGIC_ESSENCE)){
            ItemStack magic_essence = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta magic_essence_meta = magic_essence.getItemMeta();
            magic_essence_meta.setDisplayName("§9Essence of Magic");
            magic_essence_meta.setCustomModelData(108);
            magic_essence.setItemMeta(magic_essence_meta);

            return magic_essence;
        }

        else if (item.equals(CustomItem.DISPLACE)){
            ItemStack displace = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta displace_meta = displace.getItemMeta();
            displace_meta.setDisplayName("§dDisplace");
            displace_meta.setCustomModelData(103);
            displace.setItemMeta(displace_meta);

            return displace;
        }

        else if (item.equals(CustomItem.WITHDRAW)){
            ItemStack withdraw = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta withdraw_meta = withdraw.getItemMeta();
            withdraw_meta.setDisplayName("§7Withdraw");
            withdraw_meta.setCustomModelData(104);
            withdraw.setItemMeta(withdraw_meta);

            return withdraw;
        }

        else if (item.equals(CustomItem.ENHANCED_EYES)){
            ItemStack enhanced_eyes = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta enhanced_eyes_meta = enhanced_eyes.getItemMeta();
            enhanced_eyes_meta.setDisplayName("§9Enhanced Eyes");
            enhanced_eyes_meta.setCustomModelData(106);
            enhanced_eyes.setItemMeta(enhanced_eyes_meta);

            return enhanced_eyes;
        }

        else if (item.equals(CustomItem.POSSESS)){
            ItemStack possess = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta possess_meta = possess.getItemMeta();
            possess_meta.setDisplayName("§2Possession");
            possess_meta.setCustomModelData(107);
            possess.setItemMeta(possess_meta);

            return possess;
        }

        else if (item.equals(CustomItem.TINY_BLAZE_POWDER)){
            ItemStack tiny_blaze_powder = new ItemStack(Material.STRUCTURE_BLOCK, 9);
            ItemMeta tiny_blaze_powder_meta = tiny_blaze_powder.getItemMeta();
            tiny_blaze_powder_meta.setDisplayName("§rTiny Pile of Blaze Powder");
            tiny_blaze_powder_meta.setCustomModelData(100);
            tiny_blaze_powder.setItemMeta(tiny_blaze_powder_meta);

            return tiny_blaze_powder;
        }

        else if (item.equals(CustomItem.TAINTED_POWDER)){
            ItemStack tainted_powder = new ItemStack(Material.COMMAND_BLOCK, 2);
            ItemMeta tainted_powder_meta = tainted_powder.getItemMeta();
            ArrayList<String> tainted_powder_lore = new ArrayList<>();
            tainted_powder_meta.setDisplayName("§2Tainted Powder");

            tainted_powder_lore.add("§7Right click to throw.");
            tainted_powder_meta.setLore(tainted_powder_lore);

            tainted_powder_meta.setCustomModelData(102);
            tainted_powder.setItemMeta(tainted_powder_meta);

            return tainted_powder;
        }

        else if (item.equals(CustomItem.ZOMBIE_SOUL)){
            ItemStack zombie_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta zombie_soul_meta = zombie_soul.getItemMeta();
            zombie_soul_meta.setDisplayName("§rSoul of Decay");
            zombie_soul_meta.setCustomModelData(109);
            zombie_soul.setItemMeta(zombie_soul_meta);

            return zombie_soul;
        }

        else if (item.equals(CustomItem.CREEPER_SOUL)){
            ItemStack creeper_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta creeper_soul_meta = creeper_soul.getItemMeta();
            creeper_soul_meta.setDisplayName("§rSoul of Destruction");
            creeper_soul_meta.setCustomModelData(110);
            creeper_soul.setItemMeta(creeper_soul_meta);

            return creeper_soul;
        }

        else if (item.equals(CustomItem.SKELETON_SOUL)){
            ItemStack skeleton_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta skeleton_soul_meta = skeleton_soul.getItemMeta();
            skeleton_soul_meta.setDisplayName("§rSoul of War");
            skeleton_soul_meta.setCustomModelData(111);
            skeleton_soul.setItemMeta(skeleton_soul_meta);

            return skeleton_soul;
        }

        else if (item.equals(CustomItem.SPIDER_SOUL)){
            ItemStack spider_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta spider_soul_meta = spider_soul.getItemMeta();
            spider_soul_meta.setDisplayName("§rSoul of Arachnid");
            spider_soul_meta.setCustomModelData(112);
            spider_soul.setItemMeta(spider_soul_meta);

            return spider_soul;
        }

        else if (item.equals(CustomItem.PIGZOMBIE_SOUL)){
            ItemStack pigzombie_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta pigzombie_soul_meta = pigzombie_soul.getItemMeta();
            pigzombie_soul_meta.setDisplayName("§rSoul of Hellish Decay");
            pigzombie_soul_meta.setCustomModelData(113);
            pigzombie_soul.setItemMeta(pigzombie_soul_meta);

            return pigzombie_soul;
        }

        else if (item.equals(CustomItem.GHAST_SOUL)){
            ItemStack ghast_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta ghast_soul_meta = ghast_soul.getItemMeta();
            ghast_soul_meta.setDisplayName("§rSoul of Suffering");
            ghast_soul_meta.setCustomModelData(114);
            ghast_soul.setItemMeta(ghast_soul_meta);

            return ghast_soul;
        }

        else if (item.equals(CustomItem.SILVERFISH_SOUL)){
            ItemStack silverfish_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta silverfish_soul_meta = silverfish_soul.getItemMeta();
            silverfish_soul_meta.setDisplayName("§rSoul of Parasite");
            silverfish_soul_meta.setCustomModelData(115);
            silverfish_soul.setItemMeta(silverfish_soul_meta);

            return silverfish_soul;
        }

        else if (item.equals(CustomItem.WITCH_SOUL)){
            ItemStack witch_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta witch_soul_meta = witch_soul.getItemMeta();
            witch_soul_meta.setDisplayName("§rSoul of Supernatural");
            witch_soul_meta.setCustomModelData(116);
            witch_soul.setItemMeta(witch_soul_meta);

            return witch_soul;
        }

        else if (item.equals(CustomItem.PHANTOM_SOUL)){
            ItemStack phantom_soul = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta phantom_soul_meta = phantom_soul.getItemMeta();
            phantom_soul_meta.setDisplayName("§rSoul of Insomnia");
            phantom_soul_meta.setCustomModelData(117);
            phantom_soul.setItemMeta(phantom_soul_meta);

            return phantom_soul;
        }

        else if (item.equals(CustomItem.MERGED_SOULS)){
            ItemStack merged_souls = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta merged_souls_meta = merged_souls.getItemMeta();
            ArrayList<String> merged_souls_lore = new ArrayList<>();

            merged_souls_meta.setDisplayName("§9§k%§r§d§lMerged Souls§r§9§k%");
            merged_souls_lore.add(ChatColor.GRAY + "If you listen closely, you can hear the screams.");
            merged_souls_meta.setLore(merged_souls_lore);
            merged_souls_meta.setCustomModelData(118);
            merged_souls.setItemMeta(merged_souls_meta);

            return merged_souls;
        }

        else if (item.equals(CustomItem.SOUL_CRYSTAL_INCOMPLETE)){
            ItemStack soul_crystal_incomplete = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta soul_crystal_incomplete_meta = soul_crystal_incomplete.getItemMeta();
            ArrayList<String> soul_crystal_incomplete_lore = new ArrayList<>();

            soul_crystal_incomplete_meta.setDisplayName("§3§k%§r§5§lIncomplete Soul Crystal§r§3§k%");

            soul_crystal_incomplete_lore.add("§9§lRight Click§r§7 to collect your own soul. Be prepared to fight.");
            soul_crystal_incomplete_lore.add("§7Requires a human soul to complete.");
            soul_crystal_incomplete_lore.add("§7Uses Left: 9");
            soul_crystal_incomplete_meta.setLore(soul_crystal_incomplete_lore);

            soul_crystal_incomplete_meta.setCustomModelData(119);
            soul_crystal_incomplete.setItemMeta(soul_crystal_incomplete_meta);

            return soul_crystal_incomplete;
        }

        else if (item.equals(CustomItem.SOUL_CRYSTAL)){
            ItemStack soul_crystal = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta soul_crystal_meta = soul_crystal.getItemMeta();
            ArrayList<String> soul_crystal_lore = new ArrayList<>();

            soul_crystal_meta.setDisplayName("§b§k%§r§d§lSoul Crystal§r§b§k%");

            soul_crystal_lore.add("§9§lRight Click§r§7 to use.");
            soul_crystal_lore.add("§7Can be used as a sacrifice to §kenter Descension§r§7.");
            soul_crystal_lore.add("§7Uses Left: 10");
            soul_crystal_meta.setLore(soul_crystal_lore);

            soul_crystal_meta.setCustomModelData(120);
            soul_crystal.setItemMeta(soul_crystal_meta);

            return soul_crystal;
        }

        else if (item.equals(CustomItem.RUNE)){
            ItemStack rune = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta rune_meta = rune.getItemMeta();
            ArrayList<String> rune_lore = new ArrayList<>();

            rune_meta.setDisplayName("§8§lRune");

            rune_lore.add("§9§lRight Click§r§7 to use.");
            rune_lore.add("§7Used to upgrade Supernatural Abilities.");
            rune_lore.add("§70 / 8 §dMerged Souls");
            rune_lore.add("§70 / 8 §9Essences of Magic");

            rune_meta.setLore(rune_lore);

            rune_meta.setCustomModelData(121);
            rune.setItemMeta(rune_meta);

            return rune;
        }

        else if (item.equals(CustomItem.CHARGED_RUNE)){
            ItemStack charged_rune = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta charged_rune_meta = charged_rune.getItemMeta();
            ArrayList<String> charged_rune_lore = new ArrayList<>();

            charged_rune_meta.setDisplayName("§d§k%§r§8§lRune§r§9§k%");

            charged_rune_lore.add("§9§lRight Click§r§7 to upgrade Supernatural Abilities.");

            charged_rune_meta.setLore(charged_rune_lore);

            charged_rune_meta.setCustomModelData(122);
            charged_rune.setItemMeta(charged_rune_meta);

            return charged_rune;
        }

        else if (item.equals(CustomItem.VOLTSHOCK_BATTERY)){
            ItemStack voltshock_battery = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta voltshock_batter_meta = voltshock_battery.getItemMeta();
            voltshock_batter_meta.setDisplayName("§rBattery");
            voltshock_batter_meta.setCustomModelData(125);
            voltshock_battery.setItemMeta(voltshock_batter_meta);

            return voltshock_battery;
        }

        else if (item.equals(CustomItem.VOLTSHOCK_SHOCKER)){
            ItemStack voltshock_shocker = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta voltshock_shocker_meta = voltshock_shocker.getItemMeta();
            voltshock_shocker_meta.setDisplayName("§rShocker");
            voltshock_shocker_meta.setCustomModelData(126);
            voltshock_shocker.setItemMeta(voltshock_shocker_meta);

            return voltshock_shocker;
        }

        else if (item.equals(CustomItem.VOLTSHOCK_PLACEHOLDER)) {
            ItemStack voltshock_placeholder = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta voltshock_placeholder_meta = voltshock_placeholder.getItemMeta();
            voltshock_placeholder_meta.setDisplayName("§rVoltshock Sword");
            voltshock_placeholder_meta.setCustomModelData(136);
            voltshock_placeholder.setItemMeta(voltshock_placeholder_meta);

            return voltshock_placeholder;
        }

        else if (item.equals(CustomItem.VOLTSHOCK_SWORD_CHARGE_PLACEHOLDER)) {
            ItemStack voltshock_placeholder = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta voltshock_placeholder_meta = voltshock_placeholder.getItemMeta();
            voltshock_placeholder_meta.setDisplayName("§rVoltshock Sword");
            voltshock_placeholder_meta.setCustomModelData(136);
            voltshock_placeholder.setItemMeta(voltshock_placeholder_meta);

            return voltshock_placeholder;
        }

        else if (item.equals(CustomItem.VOLTSHOCK_ARROW)){
            ItemStack voltshock_sword_charge_placeholder = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta voltshock_sword_charge_placeholder_meta = voltshock_sword_charge_placeholder.getItemMeta();
            voltshock_sword_charge_placeholder_meta.setDisplayName("§rCharge Voltshock Sword");
            voltshock_sword_charge_placeholder_meta.setCustomModelData(137);
            voltshock_sword_charge_placeholder.setItemMeta(voltshock_sword_charge_placeholder_meta);

            return voltshock_sword_charge_placeholder;
        }

        else if (item.equals(CustomItem.CORROSIVE_SUBSTANCE)){
            ItemStack corrosive_substance = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta corrosive_substance_meta = corrosive_substance.getItemMeta();
            corrosive_substance_meta.setDisplayName("§2Corrosive Substance");
            corrosive_substance_meta.setCustomModelData(129);
            corrosive_substance.setItemMeta(corrosive_substance_meta);

            return corrosive_substance;
        }

        else if (item.equals(CustomItem.CORROSIVE_PLACEHOLDER)) {
            ItemStack corrosive_placeholder = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta corrosive_placeholder_meta = corrosive_placeholder.getItemMeta();
            corrosive_placeholder_meta.setDisplayName("§rCorrosive Sword");
            corrosive_placeholder_meta.setCustomModelData(138);
            corrosive_placeholder.setItemMeta(corrosive_placeholder_meta);

            return corrosive_placeholder;
        }

        else if (item.equals(CustomItem.CORROSIVE_ARROW)){
            ItemStack corrosive_arrow = new ItemStack(Material.ARROW);
            ItemMeta corrosive_arrow_meta = corrosive_arrow.getItemMeta();
            corrosive_arrow_meta.setDisplayName("§2Corrosive Arrow");
            corrosive_arrow_meta.setCustomModelData(132);
            corrosive_arrow.setItemMeta(corrosive_arrow_meta);

            return corrosive_arrow;
        }

        else if (item.equals(CustomItem.CHAMBER_MAGMA_KEY)){
            ItemStack chamber_magma_key = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta chamber_magma_key_meta = chamber_magma_key.getItemMeta();
            chamber_magma_key_meta.setDisplayName("§cMagma Key");
            chamber_magma_key_meta.setCustomModelData(133);
            chamber_magma_key.setItemMeta(chamber_magma_key_meta);

            return chamber_magma_key;
        }

        else if (item.equals(CustomItem.CHAMBER_GLOWSTONE_KEY)){
            ItemStack chamber_glowstone_key = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta chamber_glowstone_key_meta = chamber_glowstone_key.getItemMeta();
            chamber_glowstone_key_meta.setDisplayName("§6Glowstone Key");
            chamber_glowstone_key_meta.setCustomModelData(134);
            chamber_glowstone_key.setItemMeta(chamber_glowstone_key_meta);

            return chamber_glowstone_key;
        }

        else if (item.equals(CustomItem.CHAMBER_SOULSAND_KEY)){
            ItemStack chamber_soulsand_key = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta chamber_soulsand_key_meta = chamber_soulsand_key.getItemMeta();
            chamber_soulsand_key_meta.setDisplayName("§8Soul Sand Key");
            chamber_soulsand_key_meta.setCustomModelData(135);
            chamber_soulsand_key.setItemMeta(chamber_soulsand_key_meta);

            return chamber_soulsand_key;
        }

        else if (item.equals(CustomItem.BLESSED_APPLE)){
            ItemStack blessed_apple = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta blessed_apple_meta = blessed_apple.getItemMeta();
            blessed_apple_meta.setDisplayName("§bBlessed Apple");
            blessed_apple_meta.setCustomModelData(139);
            blessed_apple.setItemMeta(blessed_apple_meta);

            return blessed_apple;
        }

        else if (item.equals(CustomItem.ENCHANTED_BLESSED_APPLE)){
            ItemStack enchanted_blessed_apple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
            ItemMeta enchanted_blessed_apple_meta = enchanted_blessed_apple.getItemMeta();
            enchanted_blessed_apple_meta.setDisplayName("§dEnchanted Blessed Apple");
            enchanted_blessed_apple_meta.setCustomModelData(140);
            enchanted_blessed_apple.setItemMeta(enchanted_blessed_apple_meta);

            return enchanted_blessed_apple;
        }

        else if (item.equals(CustomItem.STONE_SHIELD)){
            ItemStack stone_shield = new ItemStack(Material.SHIELD);
            ItemMeta stone_shield_meta = stone_shield.getItemMeta();
            stone_shield_meta.setDisplayName("§rStone Shield");
            stone_shield_meta.setCustomModelData(141);
            stone_shield_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Shield Speed Modifier", -0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
            stone_shield_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Shield Speed Modifier", -0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
            stone_shield_meta.addEnchant(Enchantment.DURABILITY, 4, true);
            stone_shield.setItemMeta(stone_shield_meta);

            return stone_shield;
        }

        else if (item.equals(CustomItem.IRON_SHIELD)){
            ItemStack iron_shield = new ItemStack(Material.SHIELD);
            ItemMeta iron_shield_meta = iron_shield.getItemMeta();
            iron_shield_meta.setDisplayName("§rIron Shield");
            iron_shield_meta.setCustomModelData(142);
            iron_shield_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Shield Speed Modifier", -0.3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
            iron_shield_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Shield Speed Modifier", -0.3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
            iron_shield_meta.addEnchant(Enchantment.DURABILITY, 5, true);
            iron_shield.setItemMeta(iron_shield_meta);

            return iron_shield;
        }

        else if (item.equals(CustomItem.DIAMOND_SHIELD)){
            ItemStack diamond_shield = new ItemStack(Material.SHIELD);
            ItemMeta diamond_shield_meta = diamond_shield.getItemMeta();
            diamond_shield_meta.setDisplayName("§rDiamond Shield");
            diamond_shield_meta.setCustomModelData(144);
            diamond_shield_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Shield Speed Modifier", -0.4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
            diamond_shield_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Shield Speed Modifier", -0.4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
            diamond_shield_meta.addEnchant(Enchantment.DURABILITY, 6, true);
            diamond_shield.setItemMeta(diamond_shield_meta);

            return diamond_shield;
        }

        else if (item.equals(CustomItem.RECALL_POTION)){
            ItemStack recall_potion = new ItemStack(Material.POTION);
            PotionMeta recall_potion_meta = (PotionMeta) recall_potion.getItemMeta();
            ArrayList<String> recall_potion_lore = new ArrayList<>();

            recall_potion_meta.setDisplayName("§eRecall Potion");

            recall_potion_lore.add("§7Brings you home.");
            recall_potion_lore.add("§cDo not move when using!");
            recall_potion_meta.setLore(recall_potion_lore);

            recall_potion_meta.addEnchant(Enchantment.DURABILITY, 50, true);
            recall_potion_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
            recall_potion_meta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
            recall_potion_meta.setColor(Color.fromRGB(255, 255, 0));

            recall_potion.setItemMeta(recall_potion_meta);

            return recall_potion;
        }

        else if (item.equals(CustomItem.TITANIUM_FRAGMENT)){
            ItemStack titanium_fragment = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta titanium_fragment_meta = titanium_fragment.getItemMeta();
            titanium_fragment_meta.setDisplayName("§bTitanium Fragment");
            titanium_fragment_meta.setCustomModelData(143);
            titanium_fragment.setItemMeta(titanium_fragment_meta);

            return titanium_fragment;
        }

        else if (item.equals(CustomItem.TITANIUM_INGOT)){
            ItemStack titanium_ingot = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta titanium_ingot_meta = titanium_ingot.getItemMeta();
            titanium_ingot_meta.setDisplayName("§bTitanium Ingot");
            titanium_ingot_meta.setCustomModelData(144);
            titanium_ingot.setItemMeta(titanium_ingot_meta);

            return titanium_ingot;
        }

        else if (item.equals(CustomItem.BINDING)){
            ItemStack binding = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta binding_meta = binding.getItemMeta();
            binding_meta.setDisplayName("§rBinding");
            binding_meta.setCustomModelData(145);
            binding.setItemMeta(binding_meta);

            return binding;
        }

        else if (item.equals(CustomItem.GOLDEN_CABLE)){
            ItemStack golden_cable = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta golden_cable_meta = golden_cable.getItemMeta();
            golden_cable_meta.setDisplayName("§rGolden Cable");
            golden_cable_meta.setCustomModelData(146);
            golden_cable.setItemMeta(golden_cable_meta);

            return golden_cable;
        }

        else if (item.equals(CustomItem.NETHER_STAR_FRAGMENT)){
            ItemStack nether_star_fragment = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta nether_star_fragment_meta = nether_star_fragment.getItemMeta();
            nether_star_fragment_meta.setDisplayName("§eNether Star Fragment");
            nether_star_fragment_meta.setCustomModelData(147);
            nether_star_fragment.setItemMeta(nether_star_fragment_meta);

            return nether_star_fragment;
        }

        else if (item.equals(CustomItem.STARLIGHT_CIRCUIT)){
            ItemStack starlight_circuit = new ItemStack(Material.STRUCTURE_BLOCK);
            ItemMeta starlight_circuit_meta = starlight_circuit.getItemMeta();
            starlight_circuit_meta.setDisplayName("§eStarlight Circuit");
            starlight_circuit_meta.setCustomModelData(148);
            starlight_circuit.setItemMeta(starlight_circuit_meta);

            return starlight_circuit;
        }

        else if (item.equals(CustomItem.STARLIGHT_BATTERY)){
            ItemStack starlight_battery = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta starlight_battery_meta = starlight_battery.getItemMeta();
            starlight_battery_meta.setDisplayName("§eStarlight Battery");
            starlight_battery_meta.setCustomModelData(149);
            starlight_battery.setItemMeta(starlight_battery_meta);

            return starlight_battery;
        }

        else if (item.equals(CustomItem.STARLIGHT_MODULE)){
            ItemStack starlight_module = new ItemStack(Material.STRUCTURE_BLOCK);
            ItemMeta starlight_module_meta = starlight_module.getItemMeta();
            starlight_module_meta.setDisplayName("§eStarlight Module");
            starlight_module_meta.setCustomModelData(150);
            starlight_module.setItemMeta(starlight_module_meta);

            return starlight_module;
        }

        return null;
    }
}