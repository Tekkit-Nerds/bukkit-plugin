package me.tekkitnerds.controlcenter.listener;
//@author niklas
import java.sql.ResultSet;
import java.sql.SQLException;
import me.tekkitnerds.controlcenter.benutzer.Benutzer;
import me.tekkitnerds.controlcenter.controlcenter;
import me.tekkitnerds.controlcenter.regelbuch.RegelbuchCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private controlcenter plugin;

    public JoinListener(controlcenter pPlugin) {
        this.plugin = pPlugin;
        this.plugin.getServer().broadcastMessage("test");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            String playerName = event.getPlayer().getName().toLowerCase();
            Benutzer sp;
            if (!this.plugin.alleBenutzer.keySet().contains(playerName)){
                // User aus der DB holen
                ResultSet erg = this.plugin.db.Query("SELECT * FROM `tncc_benutzer` WHERE `benutzer_name` = '" + playerName + "'");
                erg.first();
                if (erg.absolute(0)) {
                    sp = new Benutzer(this.plugin, erg.getString("benutzer_name").toString(), erg.getInt("benutzer_xp"), erg.getInt("benutzer_coins"), erg.getString("benutzer_pw").toString());
                } else {
                    sp = new Benutzer(this.plugin, playerName);
                    sp.save();
                    RegelbuchCommand rc = new RegelbuchCommand(event.getPlayer());
                    event.getPlayer().getServer().broadcastMessage("[TN-CC] Ein neuer Spieler hat den Weg auf den Server gefunden.");
                }
                this.plugin.alleBenutzer.put(playerName, sp);
            }
            sp = this.plugin.alleBenutzer.get(playerName);
            event.getPlayer().sendMessage("Hallo " + playerName);
            event.getPlayer().sendMessage("Du hast " + sp.getBxp() + " Erfahrungspunkte");
            event.getPlayer().sendMessage("und " + sp.getCoins() + " Coins");

        } catch (SQLException ex) {
        }
    }
}