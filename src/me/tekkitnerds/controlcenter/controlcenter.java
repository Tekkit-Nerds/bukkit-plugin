package me.tekkitnerds.controlcenter;
//@author niklas
import me.tekkitnerds.controlcenter.benutzer.Benutzer;
import me.tekkitnerds.controlcenter.listener.PlayerJoinListener;
import java.util.HashMap;
import java.util.Map;
import me.tekkitnerds.controlcenter.benutzer.BenutzerCommand;
import me.tekkitnerds.controlcenter.beruf.BerufCommand;
import me.tekkitnerds.controlcenter.gebiet.Gebiet;
import me.tekkitnerds.controlcenter.regelbuch.RegelbuchCommand;
import me.tekkitnerds.controlcenter.gebiet.GebietCommand;
import me.tekkitnerds.controlcenter.listener.BlockBreakListener;
import me.tekkitnerds.controlcenter.listener.BlockPlaceListener;
import me.tekkitnerds.controlcenter.listener.PlayerInteractListener;
import me.tekkitnerds.controlcenter.listener.PlayerMoveListener;
import me.tekkitnerds.controlcenter.listener.VehicleMoveListener;
import me.tekkitnerds.controlcenter.scheduler.Scheduler;
import me.tekkitnerds.controlcenter.set.SetCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class controlcenter extends JavaPlugin {

    public database db;
    public Scheduler scheduler;
    public HashMap<String, Benutzer> alleBenutzer = new HashMap<String, Benutzer>();
    public HashMap<String, Gebiet> alleGebiete = new HashMap<String, Gebiet>();
    public HashMap<String, Location> playerLoc1 = new HashMap<String, Location>();
    public HashMap<String, Location> playerLoc2 = new HashMap<String, Location>();
    public HashMap<String, String> configStrings = new HashMap<String, String>();

    @Override
    public void onDisable() {
        for (Map.Entry<String, Benutzer> e : this.alleBenutzer.entrySet()) {
            e.getValue().save();
        }
        System.out.println("[TN-CC] deaktiviert");
    }

    @Override
    public void onEnable() {
        this.db = new database("localhost", 3306, "bukkit-plugin", "KJmatKABG9feCbDX", "bukkit-plugin");

        this.scheduler = new Scheduler(this);

        //Commands registrieren
        this.getCommand("regelbuch").setExecutor(new RegelbuchCommand(this));
        this.getCommand("gebiet").setExecutor(new GebietCommand(this));
        this.getCommand("benutzer").setExecutor(new BenutzerCommand(this));
        this.getCommand("beruf").setExecutor(new BerufCommand(this));
        this.getCommand("set").setExecutor(new SetCommand(this));

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerMoveListener(this), this);
        pm.registerEvents(new BlockBreakListener(this), this);
        pm.registerEvents(new BlockPlaceListener(this), this);
        pm.registerEvents(new PlayerInteractListener(this), this);
        pm.registerEvents(new VehicleMoveListener(this), this);

        Player[] pl = this.getServer().getOnlinePlayers();
        for (int i = 0; i < pl.length; i++) {
            String name = pl[i].getName().toLowerCase();
            Benutzer nb = new Benutzer(this, name);
            nb.load();
            this.alleBenutzer.put(name, nb);
        }

        System.out.println("[TN-CC] aktiviert");
    }

    protected database getDB() {
        return this.db;
    }
}
