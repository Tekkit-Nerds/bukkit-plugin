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

public class PlayerJoinListener implements Listener {

    private controlcenter plugin;

    public PlayerJoinListener(controlcenter pPlugin) {
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
                if (erg.next()) {
                    String pw = "";
                    if (erg.getString("benutzer_pw") != null){
                        pw = erg.getString("benutzer_pw").toString();
                    }
                    sp = new Benutzer(this.plugin, erg.getString("benutzer_name").toString(), erg.getInt("benutzer_xp"), erg.getInt("benutzer_coins"), pw, false);
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
            if (this.plugin.configStrings.containsKey("wellcomeMessage")){
                if (this.plugin.configStrings.get("wellcomeMessage").length() > 0){
                    String msg = this.plugin.configStrings.get("wellcomeMessage");
                    msg = msg.replace("%P%", event.getPlayer().getName());
                    event.getPlayer().sendMessage(msg);
                }
            }
            

        } catch (SQLException ex) {
        }
    }
}