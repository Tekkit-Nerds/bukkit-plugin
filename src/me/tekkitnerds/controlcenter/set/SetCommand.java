package me.tekkitnerds.controlcenter.set;
//@author Niklas

import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {

    private controlcenter plugin;

    public SetCommand(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        Player p = (Player) cs;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("tag") || args[0].equalsIgnoreCase("day")) {
                this.plugin.getServer().getWorld(p.getWorld().getName()).setTime(0);
            } else if (args[0].equalsIgnoreCase("nacht") || args[0].equalsIgnoreCase("night")) {
                this.plugin.getServer().getWorld(p.getWorld().getName()).setTime(20000);
            } else if (args[0].equalsIgnoreCase("timelock")) {
                this.plugin.scheduler.setLockTime(true);
                this.plugin.getServer().broadcastMessage("Die Serveruhr wurde angehalten!");
            } else if (args[0].equalsIgnoreCase("timeunlock")) {
                this.plugin.scheduler.setLockTime(false);
                this.plugin.getServer().broadcastMessage("Die Serveruhr läuft wieder!");
            }


        } else if (args.length > 1) {
            if (args[0].equalsIgnoreCase("wellcomeMessage")) {
                if (this.plugin.configStrings.containsKey("wellcomeMessage")) {
                    this.plugin.configStrings.remove("wellcomeMessage");
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]).append(' ');
                }
                this.plugin.configStrings.put("wellcomeMessage", sb.toString().trim());
                p.sendMessage("[TN-CC] WellcomeMessage: wurde gesetzt.");
            }
        }



        return true;
    }
}
