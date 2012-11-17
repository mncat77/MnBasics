package me.mncat77.mnbasics.commands;

import me.mncat77.mnbasics.MnBasics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
                if(!(player.hasPermission("MnBasics.Enchant") || player.hasPermission("MnBasics.EnchantAll"))){
                    player.sendMessage(ChatColor.RED + "You don't have permission to enchant items.");
                }
                ItemStack item = player.getInventory().getItemInHand();
                if(item.getType() == Material.AIR){
                    player.sendMessage(ChatColor.RED + "Not an item.");
                    return true;
                }
                
                if(args.length == 0){return false;}
                
                if(args[0].equalsIgnoreCase("clear")){
                    int j=0;
                    for(int i=0;i < Enchantment.values().length;i++){
                        if(item.containsEnchantment(Enchantment.values()[i])) {
                            item.removeEnchantment(Enchantment.values()[i]);
                            j++;
                        }
                        
                    }
                    
                    if(j!=1){player.sendMessage(ChatColor.YELLOW + "Removed " + j + " enchantments.");}
                    else{player.sendMessage(ChatColor.YELLOW + "Removed 1 enchantment.");}
                    return true;
                    
                }
                
                if(args.length != 2){return false;}
                
                Enchantment enchantment = getEnchantment(args[0]);
                if(enchantment == null){
                    player.sendMessage(ChatColor.RED + "Invalid enchantment.");
                    return true;
                }
                
                int level;
                try{level = Integer.parseInt(args[1]);}
                catch(NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "Invalid level.");
                    return true;
                }
                catch(ArrayIndexOutOfBoundsException e){
                    player.sendMessage(ChatColor.RED + "Invalid level.");
                    return true;
                }
                
                if(level > 32767){
                    level = 32767;
                }
                else if(level < 1){
                    level = 1;
                }
                if(!player.hasPermission("MnBasics.EnchantAll")){
                    if(level > enchantment.getMaxLevel()){level = enchantment.getMaxLevel();}
                }
                
                boolean isEnchanted = false;
                for(int i=0;i < Enchantment.values().length;i++){
                    if(item.containsEnchantment(Enchantment.values()[i])) {
                        isEnchanted = true;
                        break;
                    }
                }
                
                item.addUnsafeEnchantment(enchantment, level);
                if(isEnchanted){player.sendMessage(ChatColor.YELLOW + "Enchantment added.");}
                else{player.sendMessage(ChatColor.YELLOW + "Item enchanted.");}
                return true;
                
            }
            else{
                sender.sendMessage(ChatColor.RED + "This Command can not be executed from the Console");
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
                    if(enchantment != null){return enchantment;}
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
                else if(enchStr.equalsIgnoreCase("Looting") || enchStr.equalsIgnoreCase("Loot")){
                    return Enchantment.LOOT_BONUS_MOBS;
                }
                else if(enchStr.equalsIgnoreCase("Efficiency") || enchStr.equalsIgnoreCase("DigSpeed")){
                    return Enchantment.DIG_SPEED;
                }
                else if(enchStr.equalsIgnoreCase("Silktouch") || enchStr.equalsIgnoreCase("Silk")){
                    return Enchantment.SILK_TOUCH;
                }
                else if(enchStr.equalsIgnoreCase("Unbreaking")){
                    return Enchantment.DURABILITY;
                }
                else if(enchStr.equalsIgnoreCase("Fortune")){
                    return Enchantment.LOOT_BONUS_BLOCKS;
                }
                else if(enchStr.equalsIgnoreCase("Power")){
                    return Enchantment.ARROW_DAMAGE;
                }
                else if(enchStr.equalsIgnoreCase("Punch")){
                    return Enchantment.ARROW_KNOCKBACK;
                }
                else if(enchStr.equalsIgnoreCase("Flame")){
                    return Enchantment.ARROW_FIRE;
                }
                else if(enchStr.equalsIgnoreCase("Infinity")){
                    return Enchantment.ARROW_INFINITE;
                }
            }
            
        return null;
            
        }
}
