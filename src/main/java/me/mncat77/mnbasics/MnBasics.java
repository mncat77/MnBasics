package me.mncat77.mnbasics;

import me.mncat77.mnbasics.commands.Enchant;
import me.mncat77.mnbasics.commands.Root;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        getCommand("MnBasics").setExecutor(new Root(this));
        getCommand("enchant").setExecutor(new Enchant(this));
        
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        return false;
        
    }
    
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //event.getPlayer().sendMessage("Welcome, " + event.getPlayer().getDisplayName() + "!");
        String motd = "MOTD - Work in Progress";
        event.getPlayer().sendMessage(motd);
    }
}

