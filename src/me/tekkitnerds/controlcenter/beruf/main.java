package me.tekkitnerds.controlcenter.beruf;

import me.tekkitnerds.controlcenter.database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class main extends me.tekkitnerds.controlcenter.abst_modul {

    private database db;

    public main(database db) {
        this.db = db;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        Player p = (Player) sender;


        return true;
    }
}
