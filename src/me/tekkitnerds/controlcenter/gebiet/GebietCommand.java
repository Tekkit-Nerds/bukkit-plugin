package me.tekkitnerds.controlcenter.gebiet;
//@author niklas
import java.util.Map;
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
        String name = cs.getName().toLowerCase();
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("info")) {
                StringBuilder sb = new StringBuilder("Es gibt folgende Gebiete: ");
                for (Map.Entry<String, Gebiet> e : this.plugin.alleGebiete.entrySet()) {
                    sb.append(e.getValue().getName());
                    sb.append(' ');
                }
                cs.sendMessage(sb.toString());
                return true;
            }
        } else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("create")) {
                if (this.plugin.alleGebiete.containsKey(args[0].toLowerCase())) {
                    cs.sendMessage("[TN-CC] Dieses Gebiet gibt es bereits!");
                    return true;
                }
                if (!this.plugin.playerLoc1.containsKey(name) || !this.plugin.playerLoc2.containsKey(name)) {
                    cs.sendMessage("[TN-CC] Bitte setze zu erst beide Positionen!");
                    return true;
                }
                if (!this.plugin.playerLoc1.get(name).getWorld().equals(this.plugin.playerLoc2.get(name).getWorld())) {
                    cs.sendMessage("[TN-CC] Die beiden Punkte müssen in der selben Welt sein!");
                    return true;
                }
                Gebiet ng = new Gebiet(this.plugin, args[0].toLowerCase(), this.plugin.playerLoc1.get(name), this.plugin.playerLoc2.get(name));
                ng.save();
                this.plugin.alleGebiete.put(args[0].toLowerCase(), ng);
                cs.sendMessage("[TN-CC] Das Gebiet \"" + args[0].toLowerCase() + "\" wurde erstellt.");
                return true;
            }
        } else if (args[1].equalsIgnoreCase("info")) {
            if (!this.plugin.alleGebiete.containsKey(args[0].toLowerCase())) {
                cs.sendMessage("[TN-CC] Dieses Gebiet gibt es nicht!");
                return true;
            }
            cs.sendMessage(this.plugin.alleGebiete.get(args[0].toLowerCase()).getInfo());
            return true;
        }
        return true;
    }
}
