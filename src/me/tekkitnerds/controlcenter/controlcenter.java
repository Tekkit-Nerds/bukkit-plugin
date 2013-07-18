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
        //Module laden
        this.modBeruf = new me.tekkitnerds.controlcenter.beruf.main(this.db);
        this.modGebiet = new me.tekkitnerds.controlcenter.gebiet.main(this.db);
    }

    @EventHandler
    public void onPlayerJoin() {
        ((me.tekkitnerds.controlcenter.beruf.main) this.modBeruf).onPlayerJoin();
        ((me.tekkitnerds.controlcenter.gebiet.main) this.modGebiet).onPlayerJoin();
        //weitere Module für diesen Eventhandler
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        boolean erfolg = false;
        if (cmd.getName().equalsIgnoreCase("cc")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("beruf")) {
                    return this.modBeruf.onCommand(sender, cmd, cmdLabel, args);
                } else if (args[0].equalsIgnoreCase("gebiet")) {
                    return this.modGebiet.onCommand(sender, cmd, cmdLabel, args);
                } else {
                    return false;
                }
            }
        }
        return erfolg;
    }
}
