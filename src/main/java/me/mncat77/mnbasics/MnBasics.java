package me.mncat77.mnbasics;

import me.mncat77.mnbasics.commands.Enchant;
import me.mncat77.mnbasics.commands.Gear;
import me.mncat77.mnbasics.commands.Root;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MnBasics extends JavaPlugin implements Listener {
    
    @Override
    public void onDisable() {
        System.out.println("MnBasics has been disabled");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Gear(this), this);
        getCommand("MnBasics").setExecutor(new Root(this));
        getCommand("enchant").setExecutor(new Enchant(this));
        getCommand("gear").setExecutor(new Gear(this));
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String motd = "MOTD - Work in Progress";
        event.getPlayer().sendMessage(motd);
    }
}

