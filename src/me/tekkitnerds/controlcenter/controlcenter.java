package me.tekkitnerds.controlcenter;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class controlcenter extends JavaPlugin {
    protected abst_modul modBeruf, modGebiet, modMinecart, modQuest, modShop, modMessenger;
    private database db;
    
    @Override
    public void onDisable() {
        System.out.println("[TN-CC] deaktiviert");
    }
    
    @Override
    public void onEnable() {
        System.out.println("[TN-CC] aktiviert");
        this.db = new database("localhost", "user1", "geheim", "dbbezeichnung");
        this.modBeruf = new me.tekkitnerds.controlcenter.beruf.main(db);  
        //weitere Module laden
    }
    
    @EventHandler
    public void onPlayerJoin(){
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
        boolean erfolg = false;
        if (cmd.getName().equalsIgnoreCase("cc")){
            if (args.length == 0){

            }
        }
        
        
        
        return erfolg;
    }
    
}
