package me.tekkitnerds.controlcenter;

import org.bukkit.plugin.java.JavaPlugin;

public class abst_constolcenter extends JavaPlugin {

    private database db;

    @Override
    public void onEnable() {
        this.db = new database("localhost", 1234, "user1", "geheim", "dbbezeichnung");
    }

    protected database getDB() {
        return this.db;
    }
    
    protected void onPlayerJoin(){
        //prüfen ob der user bereits im cache ist, sonnst neu holen und ggf. anlegen in der DB
    }
    
}
