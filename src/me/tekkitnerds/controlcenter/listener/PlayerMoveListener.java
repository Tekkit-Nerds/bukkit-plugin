package me.tekkitnerds.controlcenter.listener;
//@author Niklas

import me.tekkitnerds.controlcenter.controlcenter;
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
        
    }

}
