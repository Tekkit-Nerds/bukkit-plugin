package me.tekkitnerds.controlcenter.beruf;
// @author Niklas
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class BerufCommand implements CommandExecutor {
    private controlcenter plugin;
    public BerufCommand(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        return true;
    }
    
}
