package me.tekkitnerds.controlcenter.gebiet;
//@author niklas
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GebietCommand implements CommandExecutor {

    private controlcenter plugin;

    public GebietCommand(controlcenter pPlugin) {
        this.plugin = pPlugin;

    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String label, String[] args) {
        return true;
    }
}
