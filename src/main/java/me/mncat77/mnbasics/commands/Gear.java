package me.mncat77.mnbasics.commands;

import java.util.ArrayList;
import me.mncat77.mnbasics.MnBasics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Gear implements CommandExecutor, Listener{
    
    private ArrayList<String> GearModeEnabled = new ArrayList<String>();
    private MnBasics plugin;
    
    	public Gear(MnBasics plugin) {
            this.plugin = plugin;
        }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length != 0){return false;}
            Player player = (Player)sender;
            if(player.hasPermission("MnBasics.Gear")){
                if(!GearModeEnabled.contains(player.getName())) {
                    GearModeEnabled.add(player.getName());
                    player.sendMessage(ChatColor.YELLOW + "Gear Mode is now activated");
                }
                else{
                    GearModeEnabled.remove(player.getName());
                    player.sendMessage(ChatColor.YELLOW + "Gear Mode is now deactivated");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "You don't have permission to toggle your Gear Mode.");
            }
        }
        else{sender.sendMessage(ChatColor.RED + "You have to be ingame to toggle the Gear Mode.");}
        return true;
    }
    
    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        if(!GearModeEnabled.contains(player.getName())){}
        else{
            Entity entity = event.getRightClicked();
            if(entity instanceof CraftLivingEntity){
                CraftLivingEntity lEntity = (CraftLivingEntity)entity;
                CraftItemStack pItem = new CraftItemStack(player.getItemInHand());
                net.minecraft.server.ItemStack[] equipment = lEntity.getHandle().getEquipment();
                int pItemID = pItem.getTypeId();
                if(((pItemID > 297) && (pItemID < 318)) || ((pItemID) == 397) || ((pItemID) == 86)){
                    if((((pItemID - 298) % 4) == 0)  || ((pItemID) == 397) || ((pItemID) == 86)){
                        player.setItemInHand(new CraftItemStack(equipment[4]));
                        lEntity.getHandle().setEquipment(4, pItem.getHandle());
                    }
                    else if(((pItemID - 299) % 4) == 0){
                        player.setItemInHand(new CraftItemStack(equipment[3]));
                        lEntity.getHandle().setEquipment(3, pItem.getHandle());
                    }
                    else if(((pItemID - 300) % 4) == 0){
                        player.setItemInHand(new CraftItemStack(equipment[2]));
                        lEntity.getHandle().setEquipment(2, pItem.getHandle());
                    }
                    else if(((pItemID - 301) % 4) == 0){
                        player.setItemInHand(new CraftItemStack(equipment[1]));
                        lEntity.getHandle().setEquipment(1, pItem.getHandle());
                    }
                }
                else if(pItem.getTypeId() == 0){
                    if(lEntity instanceof Skeleton){
                        Skeleton newSkeleton = (Skeleton)lEntity.getWorld().spawnEntity(lEntity.getLocation(), EntityType.SKELETON);
                        newSkeleton.setHealth(lEntity.getHealth());
                        newSkeleton.setFireTicks(lEntity.getFireTicks());
                        for(int i=0; i<5; i++){
                            if(new CraftItemStack(lEntity.getHandle().getEquipment(i)).getTypeId()!=0) {
                                if(!((i == 0) && (new CraftItemStack(lEntity.getHandle().getEquipment(0)).equals(new CraftItemStack(Material.BOW))))){lEntity.getWorld().dropItemNaturally(lEntity.getLocation(), new CraftItemStack(lEntity.getHandle().getEquipment(i)));}
                            }
                        }
                        lEntity.remove();
                        return;
                    }
                    for(int i=0; i<5; i++){
                        if(new CraftItemStack(lEntity.getHandle().getEquipment(i)).getTypeId()!=0) {
                            if(!((lEntity instanceof Skeleton) && (i == 0) && (new CraftItemStack(lEntity.getHandle().getEquipment(0)).equals(new CraftItemStack(Material.BOW))))){lEntity.getWorld().dropItemNaturally(lEntity.getLocation(), new CraftItemStack(lEntity.getHandle().getEquipment(i)));}
                        }
                        lEntity.getHandle().setEquipment(i, null);
                    }
                    
                    
                }
                else{
                    if(!((lEntity instanceof Skeleton) && (new CraftItemStack(lEntity.getHandle().getEquipment(0)).equals(new CraftItemStack(Material.BOW))))){player.setItemInHand(new CraftItemStack(equipment[0]));}
                    lEntity.getHandle().setEquipment(0, pItem.getHandle());
                }
            }
            
        }
        
    }   
        
}
