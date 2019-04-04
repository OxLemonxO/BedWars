package me.oxlemonxo.playerdata;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

public class BPlayer
{
    @Getter
    Player player;
    BPlayer(Player player)
    {
        this.player = player;
    }
    @Getter
    @Setter
    DyeColor team = DyeColor.WHITE;
    @Getter
    @Setter
    int pickaxeUpgrade = 0;
    @Getter
    @Setter
    int axeUpgrade = 0;
    @Getter
    @Setter
    boolean chainArmor = false;
    @Getter
    @Setter
    boolean ironArmor = false;
    @Getter
    @Setter
    boolean diamondArmor = false;
    @Getter
    @Setter
    boolean shears = false;
}
