package me.oxlemonxo.shops;

import me.oxlemonxo.Bedwars;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ShopManager implements Listener
{
    private HashMap<Player, DyeColor> teamColors = new HashMap<>();
    private HashMap<NPC, ShopType> shopTypes = new HashMap<>();
    private HashMap<Player, ItemShopCategory> categoryOpened = new HashMap<>();
    private HashMap<Player, Inventory> inventoryOpened = new HashMap<>();
    private List<Shop> shops = new ArrayList<>();
    private Bedwars plugin;
    public ShopManager(Bedwars plugin)
    {
        this.plugin = plugin;
    }

    public void createShop(Location loc, ShopType type)
    {

        Shop shop = new Shop(plugin, type, loc);
        shopTypes.put(shop.getNPC(), type);
        shops.add(shop);

    }
    public void removeShop(Shop shop)
    {
        shopTypes.remove(shop.getNPC());
        shops.remove(shop);
        shop.getNPC().despawn();
        shop.getNPC().destroy();
    }

    public DyeColor getPlayerTeamColor(Player player)
    {
        return teamColors.getOrDefault(player, DyeColor.WHITE);
    }
    public ShopType getShopType(NPC npc)
    {
        return shopTypes.getOrDefault(npc, ShopType.ITEM);
    }
    public void setCategoryOpened(Player player, ItemShopCategory category)
    {
        categoryOpened.put(player, category);
    }
    public ItemShopCategory getCategory(Player player)
    {
        return categoryOpened.get(player);
    }
    public Inventory getInventoryOpened(Player player) { return inventoryOpened.get(player); }
    public void setInventoryOpened(Player player, Inventory inventory) { inventoryOpened.put(player, inventory); }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        categoryOpened.remove(event.getPlayer());
        inventoryOpened.remove(event.getPlayer());
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event)
    {
        categoryOpened.remove(event.getPlayer());
        inventoryOpened.remove(event.getPlayer());
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event)
    {
    }
}
