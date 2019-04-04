package me.oxlemonxo;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.Packets;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.*;
import lombok.Getter;
import me.oxlemonxo.listeners.DeathListener;
import me.oxlemonxo.playerdata.PlayerManager;
import me.oxlemonxo.shops.ShopItemClickListener;
import me.oxlemonxo.shops.ShopManager;
import me.oxlemonxo.shops.ShopType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Bedwars extends JavaPlugin
{
    @Getter
    ShopManager sm;
    @Getter
    PlayerManager pm;
    @Override
    public void onEnable()
    {
        sm = new ShopManager(this);
        pm = new PlayerManager(this);
        DeathListener deathlistener = new DeathListener(this);
        ShopItemClickListener shoplistener = new ShopItemClickListener(this);
        getServer().getPluginManager().registerEvents(sm, this);
        getServer().getPluginManager().registerEvents(deathlistener, this);
        getServer().getPluginManager().registerEvents(shoplistener, this);
        // let citizens load
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
           /*     World world = getServer().getWorld("world");
                Location loc = new Location(world, 304, 64, 514);
                sm.createShop(loc, ShopType.ITEM,  EntityType.PLAYER, "OxLemonxO");
                Location lo1c = new Location(world, 301, 64, 514);
                sm.createShop(lo1c, ShopType.ITEM,  EntityType.PLAYER, "Arcaknight"); */
            }
        }.runTaskLater(this, 80L);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Server.ENTITY_EFFECT)
        {
            @Override
            public void onPacketSending(PacketEvent event)
            {
                PacketContainer container = event.getPacket();
                Player player = event.getPlayer();
                int id = container.getBytes().read(0);
                if(player.getGameMode() == GameMode.CREATIVE && id == PotionEffectType.HEAL.getId())
                {
                    player.removePotionEffect(PotionEffectType.HEAL);
                    event.setCancelled(true);
                }
            }
        }
      );
        //304 64 514
    }
    @Override
    public void onDisable()
    {

    }

    public static Bedwars plugin()
    {
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins())
        {
            if(plugin instanceof Bedwars)
            {
                return (Bedwars) plugin;
            }
        }
        return null;
    }

}
