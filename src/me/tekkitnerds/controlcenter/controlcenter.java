package me.tekkitnerds.controlcenter;
//@author niklas
import me.tekkitnerds.controlcenter.benutzer.Benutzer;
import me.tekkitnerds.controlcenter.listener.JoinListener;
import java.util.HashMap;
import java.util.Map;
import me.tekkitnerds.controlcenter.benutzer.BenutzerCommand;
import me.tekkitnerds.controlcenter.gebiet.Gebiet;
import me.tekkitnerds.controlcenter.regelbuch.RegelbuchCommand;
import me.tekkitnerds.controlcenter.gebiet.GebietCommand;
import me.tekkitnerds.controlcenter.listener.PlayerMoveListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class controlcenter extends JavaPlugin {

    public database db;
    public HashMap<String, Benutzer> alleBenutzer = new HashMap<String, Benutzer>();
    public HashMap<String, Gebiet> alleGebiete = new HashMap<String, Gebiet>();

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

        //Commands registrieren
        this.getCommand("regelbuch").setExecutor(new RegelbuchCommand(this));
        this.getCommand("gebiet").setExecutor(new GebietCommand(this));
        this.getCommand("benutzer").setExecutor(new BenutzerCommand(this));

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new PlayerMoveListener(this), this);

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
