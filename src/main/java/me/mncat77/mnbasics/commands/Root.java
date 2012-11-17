package me.mncat77.mnbasics.commands;

import me.mncat77.mnbasics.MnBasics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

    public class Root implements CommandExecutor {
     
    	private MnBasics plugin;
     
    	public Root(MnBasics plugin) {
    		this.plugin = plugin;
    	}
     
    	@Override
    	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            
            switch(args.length){
                
                default:
                    return false;
                case 1:
                    if(args[0].equalsIgnoreCase("help")){
                        sender.sendMessage("[" + ChatColor.DARK_AQUA + "Mn" + ChatColor.GOLD + "Basics" + ChatColor.RESET + "]  " + ChatColor.GRAY + "version 0.0  " + ChatColor.RESET + "by " + ChatColor.DARK_AQUA + "mncat77");
                        sender.sendMessage(ChatColor.GRAY + "Help Page 1/1");
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("reload")){
                        if(sender.hasPermission("MnBasics.reload")){
                            sender.sendMessage("[" + ChatColor.DARK_AQUA + "Mn" + ChatColor.GOLD + "Basics" + ChatColor.RESET + "]  " + ChatColor.RESET + "Plugin was reloaded succesfully");
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "You don't have permission to reload the plugin.");
                        }
                        return true;
                    }
                    else{
                        return false;
                    }
                case 2:
                    if(args[0].equalsIgnoreCase("help")){
                        switch(Integer.parseInt(args[1])){
                            
                            case 1:
                                sender.sendMessage("[" + ChatColor.DARK_AQUA + "Mn" + ChatColor.GOLD + "Basics" + ChatColor.RESET + "]  " + ChatColor.GRAY + "version 0.0  " + ChatColor.RESET + "by " + ChatColor.DARK_AQUA + "mncat77");
                                sender.sendMessage(ChatColor.GRAY + "Help Page 1/1");
                                return true;
                            default:
                                sender.sendMessage(ChatColor.RED + "Invalid Page Number");
                                return true;
                                
                        }
                            
                    }
                        
                        if(Integer.parseInt(args[1]) > 1){
                            
                            sender.sendMessage(ChatColor.RED + "Invalid Page Number");
                            return true;
                            
                        }                        
                    
                    }
            return false;
        }
        
    }
