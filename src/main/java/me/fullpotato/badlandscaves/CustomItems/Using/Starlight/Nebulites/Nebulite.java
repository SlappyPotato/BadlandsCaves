package me.fullpotato.badlandscaves.CustomItems.Using.Starlight.Nebulites;

import me.fullpotato.badlandscaves.CustomItems.CustomItem;

import javax.annotation.Nullable;

public enum Nebulite {
    ENERGY_STORAGE(CustomItem.NEBULITE_ENERGY_STORAGE, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS, CustomItem.STARLIGHT_SABER, CustomItem.STARLIGHT_BLASTER, CustomItem.STARLIGHT_SHIELD, CustomItem.STARLIGHT_PAXEL, CustomItem.STARLIGHT_SENTRY),
    CORRODING_LIGHTS(CustomItem.NEBULITE_CORRODING_LIGHTS, new CustomItem[]{CustomItem.NEBULITE_JAGGED_LIGHTS}, CustomItem.STARLIGHT_SABER),
    JAGGED_LIGHTS(CustomItem.NEBULITE_JAGGED_LIGHTS, new CustomItem[]{CustomItem.NEBULITE_CORRODING_LIGHTS}, CustomItem.STARLIGHT_SABER),
    FLURRYING_SWINGS(CustomItem.NEBULITE_FLURRYING_SWINGS, new CustomItem[]{CustomItem.NEBULITE_DECISIVE_SLICE}, CustomItem.STARLIGHT_SABER),
    DECISIVE_SLICE(CustomItem.NEBULITE_DECISIVE_SLICE, new CustomItem[]{CustomItem.NEBULITE_FLURRYING_SWINGS}, CustomItem.STARLIGHT_SABER),
    WIDE_SWING(CustomItem.NEBULITE_WIDE_SWING, null, CustomItem.STARLIGHT_SABER),
    REINFORCED_PLATING(CustomItem.NEBULITE_REINFORCED_PLATING, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    STRONG_STANCE(CustomItem.NEBULITE_STRONG_STANCE, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    THRUSTER(CustomItem.NEBULITE_THRUSTER, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    OXYGENATOR(CustomItem.NEBULITE_OXYGENATOR, null, CustomItem.STARLIGHT_HELMET),
    SMOLDERING_FLAMES(CustomItem.NEBULITE_SMOLDERING_FLAMES, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    TOXIN_EXPELLER(CustomItem.NEBULITE_TOXIN_EXPELLER, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    SHOCK_ABSORBER(CustomItem.NEBULITE_SHOCK_ABSORBER, null, CustomItem.STARLIGHT_BOOTS),
    SONIC_SPEED(CustomItem.NEBULITE_SONIC_SPEED, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    FORCEFIELD(CustomItem.NEBULITE_FORCEFIELD, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    GUARDIAN_ANGEL(CustomItem.NEBULITE_GUARDIAN_ANGEL, null, CustomItem.STARLIGHT_HELMET, CustomItem.STARLIGHT_CHESTPLATE, CustomItem.STARLIGHT_LEGGINGS, CustomItem.STARLIGHT_BOOTS),
    RAPID_FIRE(CustomItem.NEBULITE_RAPID_FIRE, null, CustomItem.STARLIGHT_BLASTER),
    SCATTERING_LIGHTS(CustomItem.NEBULITE_SCATTERING_LIGHTS, new CustomItem[]{CustomItem.NEBULITE_PENETRATING_BEAM}, CustomItem.STARLIGHT_BLASTER),
    PENETRATING_BEAM(CustomItem.NEBULITE_PENETRATING_BEAM, new CustomItem[]{CustomItem.NEBULITE_SCATTERING_LIGHTS}, CustomItem.STARLIGHT_BLASTER),
    SUPERHEATING_LASER(CustomItem.NEBULITE_SUPERHEATING_LASER, null, CustomItem.STARLIGHT_BLASTER),
    LIGHTSPEED_PROPULSORS(CustomItem.NEBULITE_LIGHTSPEED_PROPULSORS, null, CustomItem.STARLIGHT_PAXEL),
    MOLECULAR_PRESERVATION(CustomItem.NEBULITE_MOLECULAR_PRESERVATION, null, CustomItem.STARLIGHT_PAXEL),
    BIG_SMASH(CustomItem.NEBULITE_BIG_SMASH, new CustomItem[]{CustomItem.NEBULITE_DECISIVE_DISINTEGRATION}, CustomItem.STARLIGHT_PAXEL),
    DECISIVE_DISINTEGRATION(CustomItem.NEBULITE_DECISIVE_DISINTEGRATION, new CustomItem[]{CustomItem.NEBULITE_BIG_SMASH}, CustomItem.STARLIGHT_PAXEL),
    HARDENED_DEFENSE(CustomItem.NEBULITE_HARDENED_DEFENSE, new CustomItem[]{CustomItem.NEBULITE_SHIELD_GENERATOR}, CustomItem.STARLIGHT_SHIELD),
    SHIELD_GENERATOR(CustomItem.NEBULITE_SHIELD_GENERATOR, new CustomItem[]{CustomItem.NEBULITE_HARDENED_DEFENSE, CustomItem.NEBULITE_PROPULSION_BASH}, CustomItem.STARLIGHT_SHIELD),
    PROPULSION_BASH(CustomItem.NEBULITE_PROPULSION_BASH, new CustomItem[]{CustomItem.NEBULITE_SHIELD_GENERATOR}, CustomItem.STARLIGHT_SHIELD);

    private final CustomItem nebuliteItem;
    private final CustomItem[] mutuallyExplicit;
    private final CustomItem[] baseItems;
    Nebulite (CustomItem nebuliteItem, @Nullable CustomItem[] mutuallyExplicit, CustomItem... baseItems) {
        this.nebuliteItem = nebuliteItem;
        this.mutuallyExplicit = mutuallyExplicit;
        this.baseItems = baseItems;
    }

    public CustomItem getNebuliteItem() {
        return nebuliteItem;
    }

    public CustomItem[] getBaseItems() {
        return baseItems;
    }

    public CustomItem[] getMutuallyExplicit() {
        return mutuallyExplicit;
    }
}