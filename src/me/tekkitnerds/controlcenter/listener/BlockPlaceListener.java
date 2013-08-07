package me.tekkitnerds.controlcenter.listener;
// @author Niklas
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class BlockPlaceListener implements Listener {
    private controlcenter plugin;

    public BlockPlaceListener(controlcenter pPlugin) {
        this.plugin =  pPlugin;
    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent e){
         //darf man hier place?
        //was gibt es fürs place an erfahrung?       
        //Diverse Blöcke verbieten (TNT)
        e.setCancelled(true);
    }
    
}
