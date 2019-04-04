package me.oxlemonxo.shops;

import me.oxlemonxo.Bedwars;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class Shop
{
    private Bedwars plugin;
    private ShopType type;
    private Location loc;
    private NPC npc;

    Shop(Bedwars plugin, ShopType type, Location loc)
    {
        this.plugin = plugin;
        this.type = type;
        this.loc = loc;
        if (type == ShopType.TEAM_UPGRADES)
        {
            npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Team Upgrades");

        }
        else
        {
            npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Item Shop");
        }
        npc.spawn(loc);
    }

    public NPC getNPC()
    {
        return npc;
    }
}
