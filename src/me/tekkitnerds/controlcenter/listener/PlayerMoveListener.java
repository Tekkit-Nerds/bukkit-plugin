package me.tekkitnerds.controlcenter.listener;
//@author Niklas

import java.util.Map;
import me.tekkitnerds.controlcenter.benutzer.Benutzer;
import me.tekkitnerds.controlcenter.controlcenter;
import me.tekkitnerds.controlcenter.gebiet.Gebiet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private controlcenter plugin;

    public PlayerMoveListener(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location locFrom = e.getFrom();
        Location locTo = e.getTo();
        Player p = e.getPlayer();
        Benutzer b = this.plugin.alleBenutzer.get(p.getName());
        for (Map.Entry<String, Gebiet> mapGebiet : this.plugin.alleGebiete.entrySet()) {
            Gebiet g = mapGebiet.getValue();
            if (g.isInside(locTo) && !g.isInside(locFrom)){
                // Gebiet Enter
                if (g.checkEnterAllowed(b, p)){
                    g.onEnter(b, p);
                } else {
                    p.teleport(locFrom);
                    p.sendMessage("Du darfst dieses Gebiet nicht betreten");
                }
                
            } else if (!g.isInside(locTo) && g.isInside(locFrom)) {
                // Gebiet Leave
                if (g.checkLeaveAllowed(b, p)){
                    g.onLeave(b, p);
                } else {
                    p.teleport(locFrom);
                    p.sendMessage("Du darfst dieses Gebiet nicht verlassen");
                }
                
            } else if (g.isInside(locTo) && g.isInside(locFrom)) {
                // Im Gebiet
            } else {
                // Nicht im Gebiet
            }
        }


    }
}
