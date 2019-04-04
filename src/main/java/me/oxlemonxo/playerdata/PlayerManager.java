package me.oxlemonxo.playerdata;


import lombok.Getter;
import me.oxlemonxo.Bedwars;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashMap;

public class PlayerManager
{
    private  HashMap<Player, BPlayer> playerMap = new HashMap<>();
    private Bedwars plugin;

    public PlayerManager(Bedwars plugin)
    {
        this.plugin = plugin;
    }

    public BPlayer getPlayer(Player player)
    {
        if(playerMap.containsKey(player))
        {
            return playerMap.get(player);
        }
        playerMap.put(player, new BPlayer(player));
        return playerMap.get(player);
    }
    public void updatePlayer(Player player, BPlayer pl)
    {
        playerMap.remove(player);
        playerMap.put(player, pl);
    }
    public void updateArmor(BPlayer player)
    {
        Player pl = player.getPlayer();
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(player.getTeam().getColor());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        helmet.setItemMeta(meta);
        helmet.addEnchantment(Enchantment.OXYGEN, 1);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
         meta = (LeatherArmorMeta) chestplate.getItemMeta();
        meta.setColor(player.getTeam().getColor());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        chestplate.setItemMeta(meta);
        pl.getInventory().setHelmet(helmet);
        pl.getInventory().setChestplate(chestplate);
        if(player.isDiamondArmor())
        {
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
            ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            ItemMeta m = boots.getItemMeta();
            m.setUnbreakable(true);
            m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            boots.setItemMeta(m);
            m = leggings.getItemMeta();
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            leggings.setItemMeta(m);
            pl.getInventory().setBoots(boots);
            pl.getInventory().setLeggings(leggings);
            return;
        }
        if(player.isIronArmor())
        {
            ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemMeta m = boots.getItemMeta();
            m.setUnbreakable(true);
            m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            boots.setItemMeta(m);
            m = leggings.getItemMeta();
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            leggings.setItemMeta(m);
            pl.getInventory().setBoots(boots);
            pl.getInventory().setLeggings(leggings);
            return;
        }
        if(player.isChainArmor())
        {
            ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
            ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            ItemMeta m = boots.getItemMeta();
            m.setUnbreakable(true);
            m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            boots.setItemMeta(m);
            m = leggings.getItemMeta();
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            leggings.setItemMeta(m);
            pl.getInventory().setBoots(boots);
            pl.getInventory().setLeggings(leggings);
            return;
        }
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta m = (LeatherArmorMeta) boots.getItemMeta();
        m.setUnbreakable(true);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.setColor(player.getTeam().getColor());
        boots.setItemMeta(m);
        m = (LeatherArmorMeta) leggings.getItemMeta();
        m.setUnbreakable(true);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.setColor(player.getTeam().getColor());
        leggings.setItemMeta(m);
        pl.getInventory().setBoots(boots);
        pl.getInventory().setLeggings(leggings);
    }
}
