package me.oxlemonxo.shops;

import me.oxlemonxo.Bedwars;
import me.oxlemonxo.playerdata.BPlayer;
import me.oxlemonxo.util.InventoryUtils;
import me.oxlemonxo.util.ReflectionUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ShopItemClickListener implements Listener
{
    private Bedwars plugin;

    public ShopItemClickListener(Bedwars plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void clickEvent(InventoryClickEvent event)
    {
        if (event.getSlotType() == InventoryType.SlotType.ARMOR)
        {
            event.setCancelled(true);
            return;
        }
        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getSlot();
        ItemShopCategory category = plugin.getSm().getCategory(player);
        if (event.getClickedInventory() != null && category != null)
        {
            event.setCancelled(true);
            if (clickedSlot <= 8)
            {
                handleCategoryClickEvent(clickedSlot, player, category);
                return;
            }
            if (category == ItemShopCategory.BLOCKS)
            {
                blockCategoryTransaction(clickedSlot, player);
            }
            if (category == ItemShopCategory.MELEE)
            {
                mmleeCategoryTransaction(clickedSlot, player);
            }
            if (category == ItemShopCategory.ARMOR)
            {
                armorCategoryTransaction(clickedSlot, player);
            }
            if(category == ItemShopCategory.TOOLS)
            {
                toolsCategoryTransaction(clickedSlot, player);
            }
        }
    }

    private void toolsCategoryTransaction(int slot, Player player)
    {
        BPlayer pl = plugin.getPm().getPlayer(player);
        switch (slot)
        {
            case 19:
                if (pl.isShears())
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You've already purchased this item!");
                    break;
                }
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 20))
                {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Shears");
                    pl.setShears(true);
                    plugin.getPm().updatePlayer(player, pl);
                    plugin.getPm().updateArmor(pl);
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;
            case 20:
                int tier = pl.getPickaxeUpgrade();
                if(tier == 3)
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You already have the highest tier of this item!");
                    break;
                }
                if (tier == 0 || tier == 1 ? InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 10) :
                        tier == 2 ? InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 3) :
                                InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 6))
                {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + (tier == 0 ? " Wooden Pickaxe" : tier == 1 ? " Iron Pickaxe" : tier == 2 ? " Gold Pickaxe" : " Diamond Pickaxe"));
                    pl.setPickaxeUpgrade(tier+1);
                    plugin.getPm().updatePlayer(player, pl);
                    plugin.getPm().updateArmor(pl);
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron/Gold!");
                break;
            case 21:
                tier = pl.getAxeUpgrade();
                if(tier == 3)
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You already have the highest tier of this item!");
                    break;
                }
                if (tier == 0 || tier == 1 ? InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 10) :
                        tier == 2 ? InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 3) :
                                InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 6))
                {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + (tier == 0 ? " Wooden Axe" : tier == 1 ? " Stone Axe" : tier == 2 ? " Iron Axe" : " Diamond Axe"));
                    pl.setAxeUpgrade(tier+1);
                    plugin.getPm().updatePlayer(player, pl);
                    plugin.getPm().updateArmor(pl);
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron/Gold!");
                break;
        }
    }

    private void armorCategoryTransaction(int slot, Player player)
    {
        BPlayer pl = plugin.getPm().getPlayer(player);
        switch (slot)
        {
            case 19:
                if (pl.isDiamondArmor() || pl.isIronArmor())
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You already have a higher tier item.");
                    break;
                }
                if (pl.isChainArmor())
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You've already purchased this item!");
                    break;
                }
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 40))
                {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Permanent Chainmail Armor");
                    pl.setChainArmor(true);
                    plugin.getPm().updatePlayer(player, pl);
                    plugin.getPm().updateArmor(pl);
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;

            case 20:
                if (pl.isDiamondArmor())
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You already have a higher tier item.");
                    break;
                }
                if (pl.isIronArmor())
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You've already purchased this item!");
                    break;
                }
                if (InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 7))
                {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Permanent Iron Armor");
                    pl.setIronArmor(true);
                    plugin.getPm().updatePlayer(player, pl);
                    plugin.getPm().updateArmor(pl);
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Gold!");
                break;

            case 21:
                if (pl.isDiamondArmor())
                {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                    player.sendMessage(ChatColor.RED + "You've already purchased this item!");
                    break;
                }
                if (InventoryUtils.buy(player.getInventory(), Material.EMERALD, 6))
                {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Permanent Diamond Armor");
                    pl.setDiamondArmor(true);
                    plugin.getPm().updatePlayer(player, pl);
                    plugin.getPm().updateArmor(pl);
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Emerald!");
                break;
        }
    }

    private void mmleeCategoryTransaction(int slot, Player player)
    {
        switch (slot)
        {
            case 19:
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 10))
                {
                    ItemStack item = new ItemStack(Material.STONE_SWORD, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.setUnbreakable(true);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Stone Sword");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;

            case 20:
                if (InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 7))
                {
                    ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.setUnbreakable(true);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Iron Sword");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Gold!");
                break;

            case 21:
                if (InventoryUtils.buy(player.getInventory(), Material.EMERALD, 3))
                {
                    ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta meta = item.getItemMeta();
                    meta.setUnbreakable(true);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Diamond Sword");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Emerald!");
                break;

            case 22:
                if (InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 10))
                {
                    ItemStack item = new ItemStack(Material.STICK, 1);
                    item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Knockback Stick");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Gold!");
                break;
        }
    }

    private void blockCategoryTransaction(int slot, Player player)
    {
        BPlayer pl = plugin.getPm().getPlayer(player);
        DyeColor team = pl.getTeam();
        switch (slot)
        {
            case 19:
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 4))
                {
                    ItemStack wool = new ItemStack(Material.WOOL, 16, team.getWoolData());
                    player.getInventory().addItem(wool);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Wool");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;
            case 20:
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 12))
                {
                    ItemStack clay = new ItemStack(Material.STAINED_CLAY, 16, team.getWoolData());
                    player.getInventory().addItem(clay);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Hardened Clay");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;

            case 21:
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 12))
                {
                    ItemStack glass = new ItemStack(Material.STAINED_GLASS, 4, team.getWoolData());
                    ItemMeta meta = glass.getItemMeta();
                    meta.setDisplayName("Blast-Proof Glass");
                    glass.setItemMeta(meta);
                    player.getInventory().addItem(glass);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Blast-Proof Glass");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;

            case 22:
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 24))
                {
                    ItemStack endstone = new ItemStack(Material.ENDER_STONE, 12);
                    player.getInventory().addItem(endstone);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " End Stone");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;

            case 23:
                if (InventoryUtils.buy(player.getInventory(), Material.IRON_INGOT, 4))
                {
                    ItemStack item = new ItemStack(Material.LADDER, 16);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Ladder");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Iron!");
                break;

            case 24:
                if (InventoryUtils.buy(player.getInventory(), Material.GOLD_INGOT, 4))
                {
                    ItemStack item = new ItemStack(Material.WOOD, 16);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Oak Wood Planks");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Gold!");
                break;

            case 25:
                if (InventoryUtils.buy(player.getInventory(), Material.EMERALD, 4))
                {
                    ItemStack item = new ItemStack(Material.OBSIDIAN, 4);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 2);
                    player.sendMessage(ChatColor.GREEN + "You have purchased" + ChatColor.GOLD + " Obsidian");
                    break;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 100, 1);
                player.sendMessage(ChatColor.RED + "You don't have enough Emerald!");
                break;
        }
    }


    @EventHandler
    public void click(net.citizensnpcs.api.event.NPCRightClickEvent event)
    {
        Player player = event.getClicker();
        ShopSetup setup = new ShopSetup();
        Inventory inventory = Bukkit.getServer().createInventory(null, 54, "Blocks");
        setup.blocksScreenItemShop(inventory, player);
        plugin.getSm().setCategoryOpened(player, ItemShopCategory.BLOCKS);
        plugin.getSm().setInventoryOpened(player, inventory);
        player.openInventory(inventory);
    }

    private void handleCategoryClickEvent(int slot, Player player, ItemShopCategory currentCategory)
    {
        try
        {

            ShopSetup setup = new ShopSetup();
            ReflectionUtils utils = new ReflectionUtils();
            switch (slot)
            {
                case 1:
                    if (currentCategory != ItemShopCategory.BLOCKS)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.blocksScreenItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Blocks");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.BLOCKS);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
                case 2:
                    if (currentCategory != ItemShopCategory.MELEE)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.meleeScreenItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Melee");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.MELEE);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
                case 3:
                    if (currentCategory != ItemShopCategory.ARMOR)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.armorItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Armor");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.ARMOR);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
                case 4:
                    if (currentCategory != ItemShopCategory.TOOLS)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.toolItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Tools");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.TOOLS);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
                case 5:
                    if (currentCategory != ItemShopCategory.RANGED)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.rangedItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Ranged");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.RANGED);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
                case 6:
                    if (currentCategory != ItemShopCategory.POTIONS)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.potionItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Potions");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.POTIONS);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
                case 7:
                    if (currentCategory != ItemShopCategory.UTILITY)
                    {
                        Inventory inventory = plugin.getSm().getInventoryOpened(player);
                        setup.utilityItemShop(inventory, player);
                        utils.updateInventoryTitle(inventory, player, "Utility");
                        plugin.getSm().setCategoryOpened(player, ItemShopCategory.UTILITY);
                        plugin.getSm().setInventoryOpened(player, inventory);
                    }
                    break;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
