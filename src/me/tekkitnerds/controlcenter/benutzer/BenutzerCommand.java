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
            if (args[0] == "info") {
                this.plugin.alleBenutzer.get(name).sendInfo(cs);
            } else if (args[0] == "passwd") {
                if (args.length == 3){
                    if (args[1].length() >= 6 && args[2].length() >= 6){
                        this.plugin.alleBenutzer.get(name).changePW(cs, args[1], args[2]);
                        return true;
                    } 
                }
                cs.sendMessage("Syntax: /benutzer passwd <altesPasswort> <neuesPasswort> (min 6. Zeichen)");
                cs.sendMessage("Hier kann man dann das Passwort ändern!");
            } else {
                cs.sendMessage("passwd, info,");
            }
        }
        return true;
    }
}
