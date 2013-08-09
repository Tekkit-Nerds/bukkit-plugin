package me.tekkitnerds.controlcenter.listener;
// @author Niklas
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class BlockBreakListener implements Listener {
    private controlcenter plugin;
    public BlockBreakListener(controlcenter pPlugin) {
        this.plugin = pPlugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {//todo
        //darf man hier abbauen?
        //was gibt es fürs abbauen an erfahrung?        
        //e.setCancelled(true);
    }
    
}
