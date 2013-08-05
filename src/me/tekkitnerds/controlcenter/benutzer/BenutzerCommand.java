package me.tekkitnerds.controlcenter.benutzer;
//@author niklas

import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BenutzerCommand implements CommandExecutor {

    private controlcenter plugin;

    public BenutzerCommand(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
        String name = cs.getName().toLowerCase();
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("info")) {
                this.plugin.alleBenutzer.get(name).sendInfo(cs);
                return true;
            } else if (args[0].equalsIgnoreCase("passwd")) {
                if (args.length == 2) {
                    if (args[1].length() >= 6) {
                        this.plugin.alleBenutzer.get(name).changePW(cs, args[1]);
                        return true;
                    }
                }
                cs.sendMessage("Syntax: /benutzer passwd <neuesPasswort> (min 6. Zeichen)");
            } else {
                cs.sendMessage("passwd, info,");
            }
        }
        return true;
    }
}
