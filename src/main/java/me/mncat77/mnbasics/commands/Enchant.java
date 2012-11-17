package me.mncat77.mnbasics.commands;

import me.mncat77.mnbasics.MnBasics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Enchant implements CommandExecutor{
     
    	private MnBasics plugin;

    	public Enchant(MnBasics plugin) {
    		this.plugin = plugin;
        }
        
        @Override
    	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            
            if(sender instanceof Player){
                Player player = (Player)sender;
                ItemStack item = player.getInventory().getItemInHand();
                if(item == null){
                    sender.sendMessage(ChatColor.RED + "Not an enchantable item.");
                    return true;
                }
                //Enchantment enchantment = args[0];
                int level;
                try{level = Integer.parseInt(args[1]);}
                catch(NumberFormatException e){
                    boolean levelValid = false;
                    sender.sendMessage(ChatColor.RED + "Invalid level.");
                    return true;
                }
                catch(ArrayIndexOutOfBoundsException e){
                    boolean levelValid = false;
                    sender.sendMessage(ChatColor.RED + "Invalid level.");
                    return true;
                }
               
                if(level > 32767){
                    level = 32767;
                }
                else if(level < 1){
                    level = 1;
                }

                Enchantment enchantment = getEnchantment(args[0]);
                if(enchantment == null){
                    sender.sendMessage(ChatColor.RED + "Invalid enchantment.");
                    return true;
                }
                else{
                    item.addUnsafeEnchantment(enchantment, level);
                    return true;
                }
                
            }
            else{
                sender.sendMessage("You have to be a player 'n stuff");
                return true;
            }
            
        }
        
        private Enchantment getEnchantment(String enchStr){
            
            int enchID = -1;
            boolean enchStrIsID = true;
            
            try{enchID = Integer.parseInt(enchStr);}
            catch(NumberFormatException e){
                enchStrIsID = false;
            }
            
            if(enchStrIsID){
                return Enchantment.getById(enchID);
            }
            else{
                try{
                    Enchantment enchantment = Enchantment.getByName(enchStr.toUpperCase());
                    return enchantment;
                }
                catch(NullPointerException e){
                }
                if(enchStr.equalsIgnoreCase("Protection")){
                    return Enchantment.PROTECTION_ENVIRONMENTAL;
                }
                else if(enchStr.equalsIgnoreCase("Fireprotection") || enchStr.equalsIgnoreCase("Fire_Protection")){
                    return Enchantment.PROTECTION_FIRE;
                }
                else if(enchStr.equalsIgnoreCase("Featherfalling") || enchStr.equalsIgnoreCase("Fall_Protection")){
                    return Enchantment.PROTECTION_FALL;
                }
                else if(enchStr.equalsIgnoreCase("Blastprotection") || enchStr.equalsIgnoreCase("Blast_Protection") || enchStr.equalsIgnoreCase("Explosionprotection")){
                    return Enchantment.PROTECTION_EXPLOSIONS;
                }
                else if(enchStr.equalsIgnoreCase("Projectileprotection") || enchStr.equalsIgnoreCase("Projectile_Protection")){
                    return Enchantment.PROTECTION_PROJECTILE;
                }
                else if(enchStr.equalsIgnoreCase("Respiration") || enchStr.equalsIgnoreCase("Air")){
                    return Enchantment.OXYGEN;
                }
                else if(enchStr.equalsIgnoreCase("Aquaaffinity") || enchStr.equalsIgnoreCase("Aqua_Affinity")){
                    return Enchantment.WATER_WORKER;
                }
                else if(enchStr.equalsIgnoreCase("Sharpness") || enchStr.equalsIgnoreCase("Damage")){
                    return Enchantment.DAMAGE_ALL;
                }
                else if(enchStr.equalsIgnoreCase("Smite")){
                    return Enchantment.DAMAGE_UNDEAD;
                }
                else if(enchStr.equalsIgnoreCase("BaneofArtrthropods") || enchStr.equalsIgnoreCase("Bane") || enchStr.equalsIgnoreCase("Bane_of_Artrthropods")){
                    return Enchantment.DAMAGE_ARTHROPODS;
                }
                else if(enchStr.equalsIgnoreCase("Knock")){
                    return Enchantment.KNOCKBACK;
                }
                else if(enchStr.equalsIgnoreCase("Fireaspect") || enchStr.equalsIgnoreCase("Fire")){
                    return Enchantment.FIRE_ASPECT;
                }
                else if(enchStr.equalsIgnoreCase("Fireaspect") || enchStr.equalsIgnoreCase("Fire")){
                    return Enchantment.FIRE_ASPECT;
                }
                
                return Enchantment.ARROW_DAMAGE;
            }
            
        }
}
