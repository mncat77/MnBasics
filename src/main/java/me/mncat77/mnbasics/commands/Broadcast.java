package me.mncat77.mnbasics.commands;

import me.mncat77.mnbasics.MnBasics;
import me.mncat77.mnbasics.configuration.MnConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broadcast implements CommandExecutor{
     
    	private MnBasics plugin;
     
    	public Broadcast(MnBasics plugin) {
    		this.plugin = plugin;
    	}
     
    	@Override
    	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            
            if(sender.hasPermission("MnBasics.broadcast")){
                String broadcastFormat = MnConfig.mnConfigGetValue(plugin, "broadcast-format");
                Bukkit.broadcastMessage(broadcastFormat);       
                
            }
            else{sender.sendMessage(ChatColor.RED + "You don't have permission to broadcast a message.");}
            return true;
        }
    
    
    
}
