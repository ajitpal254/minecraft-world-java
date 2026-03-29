package com.example;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Register a basic Custom Item
    public static final Item CUSTOM_ITEM = registerItem("custom_item", new Item(new FabricItemSettings()));

    // Early Game Weaponry
    public static final Item MUSKET = registerItem("musket", new com.example.item.custom.MusketItem(new FabricItemSettings().maxDamage(250)));
    public static final Item IRON_BULLET = registerItem("iron_bullet", new Item(new FabricItemSettings()));

    // Spawn Eggs
    public static final Item GHOST_SPAWN_EGG = registerItem("ghost_spawn_egg", 
            new net.minecraft.item.SpawnEggItem(com.example.entity.ModEntities.GHOST, 0xdcdcdc, 0xc8c8c8, new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Main.LOGGER.info("Registering ModItems for " + Main.MOD_ID);

        // Add the item to the Ingredients Creative Tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CUSTOM_ITEM);
            entries.add(IRON_BULLET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(MUSKET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(GHOST_SPAWN_EGG);
        });
    }
}
