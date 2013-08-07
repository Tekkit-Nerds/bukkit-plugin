package me.tekkitnerds.controlcenter.listener;
// @author Niklas

import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private controlcenter plugin;
    public PlayerInteractListener(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Action aktion = e.getAction();
        Player p = e.getPlayer();
        String name = p.getName().toLowerCase();
        Material itemInHand = p.getItemInHand().getType();
        if (aktion.equals(Action.RIGHT_CLICK_BLOCK)){
            Location pos = e.getClickedBlock().getLocation();
            if (itemInHand.equals(Material.DIAMOND_AXE)){  // Positionen Setzen
                if (this.plugin.playerLoc2.containsKey(name)){
                    this.plugin.playerLoc2.remove(name);
                } 
                this.plugin.playerLoc2.put(name, pos);
                if (this.plugin.playerLoc1.containsKey(name) && this.plugin.playerLoc1.get(name).getWorld().equals(pos.getWorld())){
                    Location otherPos = this.plugin.playerLoc1.get(name);
                    double distance = otherPos.distanceSquared(pos);
                    p.sendMessage("[TN-CC] Position 2 gesetzt (" + pos.getBlockX() + ", " + pos.getBlockY() + ", " + pos.getBlockZ() + ") (" + distance + ")");
                } else {
                    p.sendMessage("[TN-CC] Position 2 gesetzt (" + pos.getBlockX() + ", " + pos.getBlockY() + ", " + pos.getBlockZ() + ") (Kein Gebiet)");
                }
            }  // Positionen Setzen ende
            
            
        } else if (aktion.equals(Action.LEFT_CLICK_BLOCK)){
            Location pos = e.getClickedBlock().getLocation();
            if (itemInHand.equals(Material.DIAMOND_AXE)){ // Positionen Setzen
                if (this.plugin.playerLoc1.containsKey(name)){
                    this.plugin.playerLoc1.remove(name);
                } 
                this.plugin.playerLoc1.put(name, pos);
                if (this.plugin.playerLoc2.containsKey(name) && this.plugin.playerLoc2.get(name).getWorld().equals(pos.getWorld())){
                    Location otherPos = this.plugin.playerLoc2.get(name);
                    double distance = otherPos.distanceSquared(pos);
                    p.sendMessage("[TN-CC] Position 1 gesetzt (" + pos.getBlockX() + ", " + pos.getBlockY() + ", " + pos.getBlockZ() + ") (" + distance + ")");
                } else {
                    p.sendMessage("[TN-CC] Position 1 gesetzt (" + pos.getBlockX() + ", " + pos.getBlockY() + ", " + pos.getBlockZ() + ") (Kein Gebiet)");
                }
            } // Positionen Setzen ende
        }
        
        
    }
}
