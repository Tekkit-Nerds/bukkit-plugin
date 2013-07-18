package me.tekkitnerds.controlcenter.gebiet;

import me.tekkitnerds.controlcenter.abst_modul;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class main extends abst_modul {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        Player p = (Player) sender;

        return true;
    }
}
