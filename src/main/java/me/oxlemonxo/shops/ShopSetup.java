package me.oxlemonxo.shops;


import me.oxlemonxo.Bedwars;
import me.oxlemonxo.playerdata.BPlayer;
import me.oxlemonxo.util.IntegerUtils;
import me.oxlemonxo.util.InventoryUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ShopSetup
{

    private void setupFirstRowItemShop(Inventory inventory)
    {
        ItemStack air = new ItemStack(Material.AIR, 1);
        inventory.setItem(0, air);
        insertInventoryFirst(Material.HARD_CLAY, "Blocks", 1, inventory);
        insertInventoryFirst(Material.GOLD_SWORD, "Melee", 2, inventory);
        insertInventoryFirst(Material.CHAINMAIL_BOOTS, "Armor", 3, inventory);
        insertInventoryFirst(Material.STONE_PICKAXE, "Tools", 4, inventory);
        insertInventoryFirst(Material.BOW, "Ranged", 5, inventory);
        insertInventoryFirst(Material.BREWING_STAND_ITEM, "Potions", 6, inventory);
        insertInventoryFirst(Material.TNT, "Utilities", 7, inventory);
        inventory.setItem(8, air);
    }
    private void setupSecondRowItemShop(Inventory inventory, int selected)
    {
        ItemStack selectionPaneBlack = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getWoolData());
        ItemMeta meta = selectionPaneBlack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "↑" + ChatColor.GRAY + " Categories");
        meta.setLore(Collections.singletonList(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "↓" + ChatColor.GRAY + " Items"));
        selectionPaneBlack.setItemMeta(meta);
        ItemStack selectionPaneGreen = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getWoolData());
        meta = selectionPaneGreen.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "↑" + ChatColor.GRAY + " Categories");
        meta.setLore(Collections.singletonList(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "↓" + ChatColor.GRAY + " Items"));
        selectionPaneGreen.setItemMeta(meta);
        IntStream.range(0, 9).forEach(i ->
        {
            if (i == selected)
            {
                inventory.setItem(i + 9, selectionPaneGreen);
                return;
            }
            inventory.setItem(i + 9, selectionPaneBlack);
        });
    }

    private void insertInventoryFirst(Material material, String name, int slot, Inventory inventory)
    {
        ItemStack h = new ItemStack(material, 1);
        ItemMeta meta = h.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to view!"));
        h.setItemMeta(meta);
        inventory.setItem(slot, h);
    }


    public void blocksScreenItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 1);

        ItemStack wool = new ItemStack(Material.WOOL, 16);
        ItemMeta meta = wool.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 4) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Wool");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 4 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Great for bridging across");
        lore.add(ChatColor.GRAY + "islands. Turns into your team's");
        lore.add(ChatColor.GRAY + "color.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 4) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        wool.setItemMeta(meta);
        inventory.setItem(19, wool);

        ItemStack clay = new ItemStack(Material.HARD_CLAY, 16);
        meta = clay.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 12) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Hardened Clay");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 12 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Basic block to defend your bed.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 12) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        clay.setItemMeta(meta);
        inventory.setItem(20, clay);

        ItemStack glass = new ItemStack(Material.GLASS, 4);
        meta = glass.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 12) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Blast-Proof Glass");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 12 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Immune to explosions.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 12) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        glass.setItemMeta(meta);
        inventory.setItem(21, glass);

        ItemStack endstone = new ItemStack(Material.ENDER_STONE, 12);
        meta = endstone.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 24) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "End Stone");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 24 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Solid block to defend your bed.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 24) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        endstone.setItemMeta(meta);
        inventory.setItem(22, endstone);

        ItemStack ladder = new ItemStack(Material.LADDER, 16);
        meta = ladder.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 4) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Ladder");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 4 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Useful to save cats stuck in");
        lore.add(ChatColor.GRAY + "trees.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 4) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        ladder.setItemMeta(meta);
        inventory.setItem(23, ladder);

        ItemStack wood = new ItemStack(Material.WOOD, 16);
        meta = wood.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 4) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Oak Wood Planks");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 4 Gold");
        lore.add("");
        lore.add(ChatColor.GRAY + "Good block to defend your bed.");
        lore.add(ChatColor.GRAY + "Strong against pickaxes.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 4) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        wood.setItemMeta(meta);
        inventory.setItem(24, wood);

        ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 4);
        meta = obsidian.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 4) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Obsidian");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 4 Emerald");
        lore.add("");
        lore.add(ChatColor.GRAY + "Extreme protection for your bed.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 4) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        obsidian.setItemMeta(meta);
        inventory.setItem(25, obsidian);
    }

    public void meleeScreenItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 2);

        ItemStack stone_sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta meta = stone_sword.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Stone Sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 10 Iron");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stone_sword.setItemMeta(meta);
        inventory.setItem(19, stone_sword);

        ItemStack iron_sword = new ItemStack(Material.IRON_SWORD, 1);
        meta = iron_sword.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 7) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Iron Sword");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 7 Gold");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 7) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        iron_sword.setItemMeta(meta);
        inventory.setItem(20, iron_sword);

        ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        meta = diamond_sword.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 3) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Diamond Sword");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 3 Emerald");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 3) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        diamond_sword.setItemMeta(meta);
        inventory.setItem(21, diamond_sword);

        ItemStack kbstick = new ItemStack(Material.STICK, 1);
        meta = kbstick.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 10) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Stick (Knockback I)");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 10 Gold");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 10) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        kbstick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        kbstick.setItemMeta(meta);
        inventory.setItem(22, kbstick);
        
    }

    public void armorItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 3);

        BPlayer bPlayer = Bedwars.plugin().getPm().getPlayer(player);
        boolean hasChainmailArmor = bPlayer.isChainArmor();
        boolean hasDiaArmor = bPlayer.isDiamondArmor();
        boolean hasIronArmor = bPlayer.isIronArmor();


        ItemStack chainmail_armor = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemMeta meta = chainmail_armor.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 40) && !hasChainmailArmor ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Permanent Chainmail Armor");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 40 Iron");
        lore.add("");
        lore.add(hasChainmailArmor ? ChatColor.GREEN + "UNLOCKED" :
                (InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 40) ?
                        ChatColor.YELLOW + "Click to purchase!" :
                        ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        chainmail_armor.setItemMeta(meta);
        inventory.setItem(19, chainmail_armor);

        ItemStack iron_armor = new ItemStack(Material.IRON_BOOTS, 1);
        meta = iron_armor.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 12) && !hasIronArmor ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Permanent Iron Armor");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 12 Gold");
        lore.add("");
        lore.add(hasIronArmor ? ChatColor.GREEN + "UNLOCKED" :
                (InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 12) ?
                        ChatColor.YELLOW + "Click to purchase!" :
                        ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        iron_armor.setItemMeta(meta);
        inventory.setItem(20, iron_armor);

        ItemStack diamond_armor = new ItemStack(Material.DIAMOND_BOOTS, 1);
        meta = diamond_armor.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 6) && !hasDiaArmor ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Permanent Diamond Armor");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 6 Emerald");
        lore.add("");
        lore.add(hasDiaArmor ? ChatColor.GREEN + "UNLOCKED" :
                (InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 6) ?
                        ChatColor.YELLOW + "Click to purchase!" :
                        ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        diamond_armor.setItemMeta(meta);
        inventory.setItem(21, diamond_armor);
        
    }

    public void toolItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 4);

        BPlayer bPlayer = Bedwars.plugin().getPm().getPlayer(player);
        int axeUpgrade = bPlayer.getAxeUpgrade();
        int pickUpgrade = bPlayer.getPickaxeUpgrade();
        boolean hasShears = bPlayer.isShears();
        IntegerUtils utils = new IntegerUtils();

        ItemStack shears = new ItemStack(Material.SHEARS, 1);
        ItemMeta meta = shears.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 20) && !hasShears ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Permanent Shears");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 20 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Great way to get rid of wool. You");
        lore.add(ChatColor.GRAY + "will always spawn with these");
        lore.add(ChatColor.GRAY + "shears.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 20) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        shears.setItemMeta(meta);
        inventory.setItem(19, shears);

        ItemStack pickaxe = new ItemStack(pickUpgrade == 0 ?
                Material.WOOD_PICKAXE :
                pickUpgrade == 1 ?
                        Material.IRON_PICKAXE :
                        pickUpgrade == 2 ?
                                Material.GOLD_PICKAXE :
                                Material.DIAMOND_PICKAXE, 1);
        meta = pickaxe.getItemMeta();
        meta.setDisplayName(pickUpgrade == 0 ?
                InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                        ChatColor.GREEN + "Wooden Pickaxe (Efficiency I)" :
                        ChatColor.RED + "Wooden Pickaxe (Efficiency I)" :
                pickUpgrade == 1 ?
                        InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                                ChatColor.GREEN + "Iron Pickaxe (Efficiency II)" :
                                ChatColor.RED + "Iron Pickaxe (Efficiency II)" :
                        pickUpgrade == 2 ?
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 3) ?
                                        ChatColor.GREEN + "Gold Pickaxe (Efficiency III, Sharpness II)" :
                                        ChatColor.RED + "Gold Pickaxe (Efficiency III, Sharpness II)" :
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 6) ?
                                        ChatColor.GREEN + "Diamond Pickaxe (Efficiency III)" :
                                        ChatColor.RED + "Diamond Pickaxe (Efficiency III");

        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost: " +
                (pickUpgrade == 0 ? ChatColor.WHITE + "10 Iron" :
                        pickUpgrade == 1 ? ChatColor.WHITE + "10 Iron" :
                                pickUpgrade == 2 ? ChatColor.GOLD + "3 Gold" :
                                        ChatColor.GOLD + "6 Gold"));
        lore.add(ChatColor.GRAY + "Tier: " + ChatColor.YELLOW + utils.intToRoman(pickUpgrade + 1));
        lore.add("");
        lore.add(ChatColor.GRAY + "This is an upgradable item.");
        lore.add(ChatColor.GRAY + "It will lose 1 tier upon");
        lore.add(ChatColor.GRAY + "death!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You will permanently");
        lore.add(ChatColor.GRAY + "respawn with at least the");
        lore.add(ChatColor.GRAY + "lowest tier.");
        lore.add("");
        lore.add(pickUpgrade == 0 ?
                InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                        ChatColor.YELLOW + "Click to purchase!" :
                        ChatColor.RED + "You do not have enough Iron!" :
                pickUpgrade == 1 ?
                        InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                                ChatColor.YELLOW + "Click to purchase!" :
                                ChatColor.RED + "You do not have enough Iron!" :
                        pickUpgrade == 2 ?
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 3) ?
                                        ChatColor.YELLOW + "Click to purchase!" :
                                        ChatColor.RED + "You do not have enough Gold!" :
                                pickUpgrade == 3?
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 6) ?
                                        ChatColor.YELLOW + "Click to purchase!" :
                                        ChatColor.RED + "You do not have enough Gold!":
                                        ChatColor.GREEN + "MAXED!");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(meta);
        inventory.setItem(20, pickaxe);

        ItemStack axe = new ItemStack(pickUpgrade == 0 ?
                Material.WOOD_AXE :
                pickUpgrade == 1 ?
                        Material.STONE_AXE :
                        pickUpgrade == 2 ?
                                Material.IRON_AXE :
                                Material.DIAMOND_AXE, 1);
        meta = axe.getItemMeta();
        meta.setDisplayName(axeUpgrade == 0 ?
                InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                        ChatColor.GREEN + "Wooden Axe (Efficiency I)" :
                        ChatColor.RED + "Wooden Axe (Efficiency I)" :
                axeUpgrade == 1 ?
                        InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                                ChatColor.GREEN + "Stone Axe (Efficiency II)" :
                                ChatColor.RED + "Stone Axe (Efficiency II)" :
                        axeUpgrade == 2 ?
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 3) ?
                                        ChatColor.GREEN + "Iron Axe (Efficiency III)" :
                                        ChatColor.RED + "Iron Axe (Efficiency III)" :
                                InventoryUtils.canAfford(inventory, Material.GOLD_INGOT, 6) ?
                                        ChatColor.GREEN + "Diamond Axe (Efficiency III)" :
                                        ChatColor.RED + "Diamond Axe (Efficiency III");

        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost: " +
                (axeUpgrade == 0 ? ChatColor.WHITE + "10 Iron" :
                        axeUpgrade == 1 ? ChatColor.WHITE + "10 Iron" :
                                axeUpgrade == 2 ? ChatColor.GOLD + "3 Gold" :
                                        ChatColor.GOLD + "6 Gold"));
        lore.add(ChatColor.GRAY + "Tier: " + ChatColor.YELLOW + utils.intToRoman(axeUpgrade + 1));
        lore.add("");
        lore.add(ChatColor.GRAY + "This is an upgradable item.");
        lore.add(ChatColor.GRAY + "It will lose 1 tier upon");
        lore.add(ChatColor.GRAY + "death!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You will permanently");
        lore.add(ChatColor.GRAY + "respawn with at least the");
        lore.add(ChatColor.GRAY + "lowest tier.");
        lore.add("");
        lore.add(axeUpgrade == 0 ?
                InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                        ChatColor.YELLOW + "Click to purchase!" :
                        ChatColor.RED + "You do not have enough Iron!" :
                axeUpgrade == 1 ?
                        InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 10) ?
                                ChatColor.YELLOW + "Click to purchase!" :
                                ChatColor.RED + "You do not have enough Iron!" :
                        axeUpgrade == 2 ?
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 3) ?
                                        ChatColor.YELLOW + "Click to purchase!" :
                                        ChatColor.RED + "You do not have enough Gold!" :
                                axeUpgrade == 3 ?
                                InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 6) ?
                                        ChatColor.YELLOW + "Click to purchase!" :
                                        ChatColor.RED + "You do not have enough Gold!":
        ChatColor.GREEN + "MAXED");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        axe.setItemMeta(meta);
        inventory.setItem(21, axe);
        
    }

    public void rangedItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 5);

        ItemStack arrow = new ItemStack(Material.ARROW, 8);
        ItemMeta meta = arrow.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 2) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Arrow");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 2 Gold");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 2) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        arrow.setItemMeta(meta);
        inventory.setItem(19,  arrow);

        ItemStack bow = new ItemStack(Material.BOW, 1);
        meta = bow.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 12) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Bow");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 12 Gold");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 12) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        bow.setItemMeta(meta);
        inventory.setItem(20, bow);

        ItemStack powerbow = new ItemStack(Material.BOW, 1);
        meta = powerbow.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 24) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Bow (Power I)");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 24 Gold");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 24) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        powerbow.setItemMeta(meta);
        powerbow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        inventory.setItem(21, powerbow);

        ItemStack punchbow = new ItemStack(Material.BOW, 1);
        meta = punchbow.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 6) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Bow (Power I, Punch I)");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 6 Emerald");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 6) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        punchbow.setItemMeta(meta);
        punchbow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        punchbow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
        inventory.setItem(22, punchbow);
        
    }

    public void potionItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 6);

        //speed
        ItemStack speed = new ItemStack(Material.POTION, 1);
        ItemMeta meta = speed.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 1) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Speed II Potion (45 seconds)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 1 Emerald");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 1) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        ((PotionMeta)meta).addCustomEffect(new PotionEffect(PotionEffectType.SPEED,45*20, 1), true);
        ((PotionMeta)meta).setColor(Color.AQUA);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        speed.setItemMeta(meta);
        inventory.setItem(19,  speed);

        //NORMAL BOW
        ItemStack jump = new ItemStack(Material.POTION, 1);
        meta = jump.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 1) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Jump V Potion (45 seconds)");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 1 Emerald");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 1) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        ((PotionMeta)meta).addCustomEffect(new PotionEffect(PotionEffectType.JUMP,45*20, 4), true);
        ((PotionMeta)meta).setColor(Color.LIME);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        jump.setItemMeta(meta);
        inventory.setItem(20, jump);

        //DIA SWORD
        ItemStack invis = new ItemStack(Material.POTION, 1);
        meta = invis.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 2) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Invisibility Potion (30 seconds)");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 2 Emerald");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 1) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        ((PotionMeta)meta).addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30*20, 0), true);
        ((PotionMeta)meta).setColor(Color.GRAY);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        invis.setItemMeta(meta);
        inventory.setItem(21, invis);
        
    }

    public void utilityItemShop(Inventory inventory, Player player)
    {
        inventory.clear();
        setupFirstRowItemShop(inventory);
        setupSecondRowItemShop(inventory, 7);

        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta = gapple.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 3) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Golden Apple");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 3 Gold");
        lore.add("");
        lore.add(ChatColor.GRAY + "Well rounded healing.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 3) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        gapple.setItemMeta(meta);
        inventory.setItem(19, gapple);

        ItemStack bedbug = new ItemStack(Material.SNOW_BALL, 1);
        meta = bedbug.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 40) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Bedbug");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 40 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Spawns silverfish where the");
        lore.add(ChatColor.GRAY + "snowball lands to distract your");
        lore.add(ChatColor.GRAY + "enemies.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 40) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        bedbug.setItemMeta(meta);
        inventory.setItem(20, bedbug);

        ItemStack dreamdefender = new ItemStack(Material.MONSTER_EGG, 1);
        meta = dreamdefender.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 120) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Dream Defender");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 120 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Iron Golem to help defend your");
        lore.add(ChatColor.GRAY + "base.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 120) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        dreamdefender.setItemMeta(meta);
        inventory.setItem(21, dreamdefender);

        ItemStack fireball = new ItemStack(Material.FIREBALL, 1);
        meta = fireball.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 40) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Fireball");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.WHITE + " 40 Iron");
        lore.add("");
        lore.add(ChatColor.GRAY + "Right click to launch! Great to");
        lore.add(ChatColor.GRAY + "knock back enemies walking on");
        lore.add(ChatColor.GRAY + "thin bridges.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.IRON_INGOT, 40) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Iron!"));
        meta.setLore(lore);
        fireball.setItemMeta(meta);
        inventory.setItem(22, fireball);

        ItemStack tnt = new ItemStack(Material.TNT, 1);
        meta = tnt.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 8) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "TNT");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 8 Gold");
        lore.add("");
        lore.add(ChatColor.GRAY + "Instantly ignites, appropriate");
        lore.add(ChatColor.GRAY + "to explode things.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 8) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        tnt.setItemMeta(meta);
        inventory.setItem(23, tnt);

        ItemStack epearl = new ItemStack(Material.ENDER_PEARL, 1);
        meta = epearl.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 4) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Ender Pearl");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 4 Emerald");
        lore.add("");
        lore.add(ChatColor.GRAY + "The quickest way to invade enemy");
        lore.add(ChatColor.GRAY + "bases.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 4) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        epearl.setItemMeta(meta);
        inventory.setItem(24, epearl);

        ItemStack water_bucket = new ItemStack(Material.WATER_BUCKET, 1);
        meta = water_bucket.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 8) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Water Bucket");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + "8 Gold");
        lore.add("");
        lore.add(ChatColor.GRAY + "Great to slow down approaching");
        lore.add(ChatColor.GRAY + "enemies. Can also protect");
        lore.add(ChatColor.GRAY + "against TNT.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 8) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold!"));
        meta.setLore(lore);
        water_bucket.setItemMeta(meta);
        inventory.setItem(25, water_bucket);

        ItemStack bridge_egg = new ItemStack(Material.EGG, 1);
        meta = bridge_egg.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 2) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Bridge Egg");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.DARK_GREEN + " 2 Emerald");
        lore.add("");
        lore.add(ChatColor.GRAY + "This egg creates a bridge");
        lore.add(ChatColor.GRAY + "in its trail after being thrown.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.EMERALD, 2) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Emerald!"));
        meta.setLore(lore);
        bridge_egg.setItemMeta(meta);
        inventory.setItem(28, bridge_egg);

        ItemStack magic_milk = new ItemStack(Material.MILK_BUCKET, 1);
        meta = magic_milk.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 4) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Magic Milk");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 4 Gold");
        lore.add("");
        lore.add(ChatColor.GRAY + "Avoid triggering traps for 60");
        lore.add(ChatColor.GRAY + "seconds after consuming.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 4) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold"));
        meta.setLore(lore);
        magic_milk.setItemMeta(meta);
        inventory.setItem(29, magic_milk);

        ItemStack sponge = new ItemStack(Material.SPONGE, 1);
        meta = sponge.getItemMeta();
        meta.setDisplayName((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 6) ? ChatColor.GREEN + "" : ChatColor.RED + "") + "Sponge");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost:" + ChatColor.GOLD + " 6 Gold");
        lore.add("");
        lore.add(ChatColor.GRAY + "Great for soaking up water.");
        lore.add("");
        lore.add((InventoryUtils.canAfford(player.getInventory(), Material.GOLD_INGOT, 6) ?
                ChatColor.YELLOW + "Click to purchase!" :
                ChatColor.RED + "You don't have enough Gold"));
        meta.setLore(lore);
        sponge.setItemMeta(meta);
        inventory.setItem(30, sponge);
        
    }
}
