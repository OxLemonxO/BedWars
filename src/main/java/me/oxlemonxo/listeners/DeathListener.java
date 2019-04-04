package me.oxlemonxo.listeners;

import me.oxlemonxo.Bedwars;
import me.oxlemonxo.playerdata.BPlayer;
import me.oxlemonxo.util.ReflectionUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathListener implements Listener
{
    final int respawnSeconds = 5;
    private Bedwars plugin;

    public DeathListener(Bedwars plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(EntityDeathEvent event)
    {
        if (!(event.getEntity() instanceof Player))
        {
            return;
        }
        final Player player = (Player) event.getEntity();
        if (plugin.getPm().getPlayer(player) == null) //check if player is playing
        {
            return;
        }
        player.spigot().respawn();
        player.setGameMode(GameMode.SPECTATOR);
        final ReflectionUtils utils = new ReflectionUtils();
        final Location spawnLocation = player.getLocation();

        new BukkitRunnable()
        {
            int i = 6;

            public void run()
            {
                utils.send(player, ChatColor.RED + "YOU DIED!", ChatColor.YELLOW + "You will respawn in "
                        + ChatColor.RED + (i-1) + ChatColor.YELLOW +
                        " seconds!", 10, 10, 10);
                i--;
                if (i == 0)
                {
                    this.cancel();
                    //looks terrible but whatever
                    new BukkitRunnable()
                    {
                        @Override
                        public void run()
                        {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.teleport(player.getWorld().getSpawnLocation());
                            utils.send(player, "", "", 10, 5, 10);
                            plugin.getPm().updateArmor(plugin.getPm().getPlayer(player));
                        }
                    }.runTask(plugin);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 20, 20);
    }


    @EventHandler
    public void PlayerCommand(PlayerCommandPreprocessEvent event)
    {
        String message = event.getMessage();
        String[] array = message.split(" ");
        if(array.length == 2)
        {
            if(array[0].equalsIgnoreCase("/bwteamcolor"))
            {
                try
                {
                    DyeColor color = DyeColor.valueOf(array[1]);
                    BPlayer player = plugin.getPm().getPlayer(event.getPlayer());
                    player.setTeam(color);
                    plugin.getPm().updatePlayer(event.getPlayer(), player);
                }
                catch(Exception ex)
                {
                    event.getPlayer().sendMessage(ChatColor.GRAY + "Color not found.");
                    return;
            }
                event.getPlayer().sendMessage("Set color to " + plugin.getPm().getPlayer(event.getPlayer()).getTeam().name());
        }
        }
    }




}
