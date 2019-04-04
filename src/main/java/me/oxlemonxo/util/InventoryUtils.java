package me.oxlemonxo.util;


import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils
{
    public static boolean canAfford(Inventory inventory, Material priceItem, int price)
    {
        int count = 0;
        ItemStack[] items = inventory.getContents();
        for (ItemStack is : items)
        {
            if (is != null && is.getType() == priceItem)
            {
                count += is.getAmount();
            }
            if (count >= price)
            {
                return true;
            }
        }
        return count >= price;
    }
    public static boolean buy(Inventory inventory, Material priceItem, int price)
    {
        if(canAfford(inventory, priceItem, price))
        {
            removeFromInventory(inventory, priceItem, price);
            return true;
        }
        return false;
    }

    public static void removeFromInventory(Inventory inventory, Material material, int amt)
    {
        ItemStack[] items = inventory.getContents();
        for (int i = 0; i < items.length; i++)
        {
            if (items[i] != null && items[i].getType() == material)
            {
                if (items[i].getAmount() > amt)
                {
                    items[i].setAmount(items[i].getAmount() - amt);
                    break;
                }
                else if (items[i].getAmount() == amt)
                {
                    items[i] = null;
                    break;
                }
                else
                {
                    amt -= items[i].getAmount();
                    items[i] = null;
                }
            }
        }
        inventory.setContents(items);
    }

}
