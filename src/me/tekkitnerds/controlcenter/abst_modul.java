package me.tekkitnerds.controlcenter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class abst_modul {
    
        public abstract boolean onCommand (CommandSender sender, Command cmd, String cmdLabel, String[] args);
    
}
