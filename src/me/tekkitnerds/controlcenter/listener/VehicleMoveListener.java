package me.tekkitnerds.controlcenter.listener;
// @author Niklas

import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;


public class VehicleMoveListener implements Listener {

    private controlcenter plugin;

    public VehicleMoveListener(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }
    
    @EventHandler
    public void onVehicleMove (VehicleMoveEvent e){
        
    }
    
}
