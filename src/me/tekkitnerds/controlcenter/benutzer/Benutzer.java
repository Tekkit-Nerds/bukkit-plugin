package me.tekkitnerds.controlcenter.benutzer;
//@author Niklas

import java.security.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import me.tekkitnerds.controlcenter.beruf.Beruf;
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Benutzer {

    private boolean isOp;
    private String name, pw;
    private int BXP, coins;
    private controlcenter plugin;
    private HashMap<String, Beruf> berufe = new HashMap<String, Beruf>();

    public HashMap getBerufe() {
        return this.berufe;
    }

    public Beruf getBeruf(String berufName) {
        return this.berufe.get(berufName);
    }

    public void setBeruf(Beruf newBeruf) {
        if (!this.berufe.containsKey(newBeruf.getName())){
            this.berufe.put(newBeruf.getName(), newBeruf);
        }
    }
    
    public void loadBerufe (){
        
    }

    public Benutzer(controlcenter pPlugin, String pName) {
        this.plugin = pPlugin;
        this.name = pName.toLowerCase();
        this.BXP = 0;
        this.coins = 100;
        this.pw = "";
        this.isOp = false;
    }

    public Benutzer(controlcenter pPlugin, String pName, int pBXP, int pCoins, String pPw, boolean pIsOp) {
        this.plugin = pPlugin;
        this.name = pName.toLowerCase();
        this.BXP = pBXP;
        this.coins = pCoins;
        this.pw = pPw;
        this.isOp = pIsOp;
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

    public boolean isOp() {
        return isOp;
    }

    public void load() {
        try {
            ResultSet erg = this.plugin.db.Query("SELECT * FROM `tncc_benutzer` WHERE `benutzer_name` = '" + this.name + "'");
            if (erg.next()) {
                this.BXP = erg.getInt("benutzer_xp");
                this.coins = erg.getInt("benutzer_coins");
                this.pw = "";
                if (erg.getString("benutzer_pw") != null) {
                    this.pw = erg.getString("benutzer_pw").toString();
                }
            }
            erg.close();
        } catch (SQLException ex) {
        }
    }

    public void save() {
        try {
            ResultSet erg = this.plugin.db.Query("SELECT * FROM `tncc_benutzer` WHERE `benutzer_name` = '" + this.name + "'");
            if (erg.next()) {
                this.plugin.getServer().broadcastMessage("UPDATE");
                this.plugin.db.Query("UPDATE `tncc_benutzer`"
                        + "             SET `benutzer_xp` = '" + this.BXP + "',"
                        + "                 `benutzer_coins` = '" + this.coins + "',"
                        + "                 `benutzer_update` = NOW()"
                        + "             WHERE `benutzer_name` = '" + this.name + "'", true);
            } else {
                this.plugin.getServer().broadcastMessage("INSERT");
                this.plugin.db.Query("INSERT INTO `tncc_benutzer`"
                        + "             SET `benutzer_xp` = '" + this.BXP + "',"
                        + "                 `benutzer_coins` = '" + this.coins + "',"
                        + "                 `benutzer_create` = NOW(),"
                        + "                 `benutzer_update` = NOW(),"
                        + "                 `benutzer_name` = '" + this.name + "'", true);
            }
            erg.close();
        } catch (Exception e) {
            this.plugin.getServer().broadcastMessage(e.toString());
        }
    }

    void sendInfo(CommandSender cs) {
        ((Player) cs).sendMessage("Name: " + this.getName());
        ((Player) cs).sendMessage("BXP:  " + this.getBxp());
        ((Player) cs).sendMessage("Coins: " + this.getCoins());
    }

    private String crypt(String pw) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(pw.getBytes());
            byte[] result = md5.digest();
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                hexString.append(Integer.toHexString(0xFF & result[i]));
            }
            return hexString.toString();
        } catch (Exception e) {
            return pw;
        }
    }

    void changePW(CommandSender cs, String newPW) {
        String cryptNewPW = crypt(newPW);
        this.pw = cryptNewPW;
        this.plugin.db.Query("UPDATE `tncc_benutzer` SET `benutzer_pw` = '" + cryptNewPW + "' WHERE `benutzer_name` = '" + this.name + "'", true);
        cs.sendMessage("Das Passwort wurde geändert!");
    }
}
