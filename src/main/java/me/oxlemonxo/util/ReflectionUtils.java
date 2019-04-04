package me.oxlemonxo.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils
{
    public void updateInventoryTitle(Inventory inventory, Player player, String title) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException
    {
        Object handle = player.getClass().getMethod("getHandle").invoke(player);
        Field field = handle.getClass().getDeclaredField("containerCounter");
        field.setAccessible(true);
        int containerCounter = (int) field.get(handle);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        final PacketContainer packet = manager.createPacket(PacketType.Play.Server.OPEN_WINDOW);
        packet.getIntegers().write(0, containerCounter);
        packet.getStrings().write(0, "minecraft:container");
        packet.getChatComponents().write(0, WrappedChatComponent.fromJson("{\"text\": \"" + title + "\"}"));
        packet.getIntegers().write(1, inventory.getSize());
        manager.sendServerPacket(player, packet);
        // One downside: the clientside Inventory will be empty. Luckily,
        // this allows up to force Spigot to update this in our stead :P
        player.updateInventory();

    }

    public void send(Player player, String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime)
    {
        try
        {
            Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + title + "\"}");
            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    fadeInTime, showTime, fadeOutTime);

            Object chatsTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + subtitle + "\"}");
            Constructor<?> stitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object spacket = stitleConstructor.newInstance(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    fadeInTime, showTime, fadeOutTime);

            sendPacket(player, packet);
            sendPacket(player, spacket);
        }
        catch (Exception ignored)
        {
            ignored.printStackTrace();
        }
    }

    public void sendPacket(Player player, Object packet) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
        Object handle = player.getClass().getMethod("getHandle").invoke(player);
        Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
        playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
    }

    public Class<?> getNMSClass(String name)
    {
        try
        {
            String packageName = Bukkit.getServer().getClass().getPackage().getName();
            return Class.forName("net.minecraft.server."
                    + packageName.substring(packageName.lastIndexOf('.') + 1) + "." + name);
        }
        catch (ClassNotFoundException ignored)
        {
            ignored.printStackTrace();
        }
        return null;
    }
}
