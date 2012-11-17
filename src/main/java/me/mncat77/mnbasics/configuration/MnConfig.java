package me.mncat77.mnbasics.configuration;

import me.mncat77.mnbasics.MnBasics;

public class MnConfig {
    
    private MnBasics plugin;
    
    public static String mnConfigGetValue(MnBasics plugin,String path){
        //this.plugin = plugin;
        String value = plugin.getConfig().getString(path);
        return value;
    }
    
}