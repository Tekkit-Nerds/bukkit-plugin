package me.tekkitnerds.controlcenter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

public class controlcenter extends abst_constolcenter {

    protected abst_modul modBeruf, modGebiet, modMinecart, modQuest, modShop, modMessenger;
    

    @Override
    public void onDisable() {
        System.out.println("[TN-CC] deaktiviert");
    }

    @Override
    public void onEnable() {
        System.out.println("[TN-CC] aktiviert");
        super.onEnable();
        //Module laden
        this.modBeruf = new me.tekkitnerds.controlcenter.beruf.main(this.getDB());
        this.modGebiet = new me.tekkitnerds.controlcenter.gebiet.main(this.getDB());
    }

    @EventHandler
    public void onPlayerJoin() {
        super.onPlayerJoin();
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
        } else if (cmd.getName().equalsIgnoreCase("regelbuch")) {
            new regelbuch(sender);
        }
        return erfolg;
    }
}
