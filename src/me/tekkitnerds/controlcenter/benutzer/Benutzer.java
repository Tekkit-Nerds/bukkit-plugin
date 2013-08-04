package me.tekkitnerds.controlcenter.benutzer;
//@author Niklas

import java.sql.ResultSet;
import java.sql.SQLException;
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Benutzer {

    private String name, pw;
    private int BXP, coins;
    private controlcenter plugin;

    public Benutzer(controlcenter pPlugin, String pName) {
        this.plugin = pPlugin;
        this.name = pName.toLowerCase();
        this.BXP = 0;
        this.coins = 100;
        this.pw = "";
    }

    public Benutzer(controlcenter pPlugin, String pName, int pBXP, int pCoins, String pPw) {
        this.plugin = pPlugin;
        this.name = pName.toLowerCase();
        this.BXP = pBXP;
        this.coins = pCoins;
        this.pw = pPw;
    }

    public String getName() {
        return name;
    }

    public int getBxp() {
        return BXP;
    }

    public int getCoins() {
        return coins;
    }

    public void load() {
        try {
            ResultSet erg = this.plugin.db.Query("SELECT * FROM `tncc_benutzer` WHERE `benutzer_name` = '" + this.name + "'");
            if (erg.absolute(0)) {
                this.BXP = erg.getInt("benutzer_xp");
                this.coins = erg.getInt("benutzer_coins");
                this.pw = erg.getString("benutzer_pw").toString();
            }
        } catch (SQLException ex) {
        }
    }

    public void save() {
        try {
            ResultSet erg = this.plugin.db.Query("SELECT * FROM `tncc_benutzer` WHERE `benutzer_name` = '" + this.name + "'");
            if (erg.absolute(0)) {
                this.plugin.db.Query("UPDATE `tncc_benutzer`"
                        + "             SET `benutzer_xp` = '" + this.BXP + "',"
                        + "                 `benutzer_coins` = '" + this.coins + "',"
                        + "                 `benutzer_update` = NOW()"
                        + "             WHERE `benutzer_name` = '" + this.name + "'");
            } else {
                this.plugin.db.Query("INSERT INTO `tncc_benutzer`"
                        + "             SET `benutzer_xp` = '" + this.BXP + "',"
                        + "                 `benutzer_coins` = '" + this.coins + "',"
                        + "                 `benutzer_create` = NOW(),"
                        + "                 `benutzer_update` = NOW(),"
                        + "                 `benutzer_name` = '" + this.name + "'");
            }
        } catch (Exception e) {
        }
    }

    void sendInfo(CommandSender cs) {
        ((Player) cs).sendMessage("Name: " + this.getName());
        ((Player) cs).sendMessage("BXP:  " + this.getBxp());
        ((Player) cs).sendMessage("Coins: " + this.getCoins());
    }

    private String crypt(String pw) {
        return pw;
    }

    void changePW(CommandSender cs, String oldPW, String newPW) {
        if (!oldPW.equals(newPW)) {
            String cryptOldPW = crypt(oldPW);
            if (this.pw.equals(cryptOldPW)) {
                String cryptNewPW = crypt(newPW);
                this.pw = cryptNewPW;
                this.plugin.db.Query("UPDATE `tncc_benutzer` SET `benutzer_pw` = '" + cryptNewPW + "' WHERE `benutzer_name` = '" + this.name + "'");
                cs.sendMessage("Das Passwort wurde geändert!");
            }
        } else {
            cs.sendMessage("Die Passwörter sind identisch.");
        }
    }
}
