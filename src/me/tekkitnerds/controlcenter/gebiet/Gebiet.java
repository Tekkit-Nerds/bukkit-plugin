package me.tekkitnerds.controlcenter.gebiet;
// @author Niklas
import java.sql.ResultSet;
import java.sql.SQLException;
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.Location;

public class Gebiet {

    private controlcenter plugin;
    private int minX, minY, minZ;
    private int maxX, maxY, maxZ;
    private String world;
    private String name;

    public Gebiet(controlcenter pPlugin, String pName, Location pos1, Location pos2) {
        //die Minwerte ermitteln
        this.minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        this.minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        this.minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

        //die Maxwerte ermitteln
        this.maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        this.maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        this.maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        //Andere Werte
        this.world = pos1.getWorld().getName();
        this.name = pName;
        this.plugin = pPlugin;

        // Speichern
        this.save();
    } // Ende Gebiet (4)

    public Gebiet(controlcenter pPlugin, ResultSet gebiet) {
        try {
            //die Minwerte ermitteln
            this.minX = gebiet.getInt("gebiet_min_x");
            this.minY = gebiet.getInt("gebiet_min_y");
            this.minZ = gebiet.getInt("gebiet_min_z");

            //die Maxwerte ermitteln
            this.maxX = gebiet.getInt("gebiet_min_x");
            this.maxY = gebiet.getInt("gebiet_min_y");
            this.maxZ = gebiet.getInt("gebiet_min_z");

            //Andere Werte
            this.world = gebiet.getString("gebiet_world");
            this.name = gebiet.getString("gebiet_name");
            this.plugin = pPlugin;
        } catch (SQLException ex) {
        }
    } // Ende Gebiet (2)

    public boolean isInside(Location loc) {
        if (loc.getWorld().getName().equalsIgnoreCase(this.world)) {
            return false;
        }
        if (loc.getBlockX() < this.minX || this.maxX < loc.getBlockX()) {
            return false;
        }
        if (loc.getBlockY() < this.minY || this.maxY < loc.getBlockY()) {
            return false;
        }
        if (loc.getBlockZ() < this.minZ || this.maxZ < loc.getBlockZ()) {
            return false;
        }
        return true;
    } // Ende isInside

    public void save() {
        try {
            ResultSet erg = this.plugin.db.Query("SELECT * FROM `tncc_gebiete` WHERE `gebiet_name` = '" + this.name + "'");
            if (erg.absolute(0)) {
                this.plugin.db.Query("UPDATE `tncc_gebiete`"
                        + "             SET `gebiet_min_x` = '" + this.minX + "',"
                        + "                 `gebiet_min_y` = '" + this.minY + "',"
                        + "                 `gebiet_min_z` = '" + this.minZ + "',"
                        + "                 `gebiet_max_x` = '" + this.maxX + "',"
                        + "                 `gebiet_max_y` = '" + this.maxY + "',"
                        + "                 `gebiet_max_z` = '" + this.maxZ + "',"
                        + "                 `gebiet_world` = '" + this.world + "',"
                        + "             WHERE `gebiet_name` = '" + this.name + "'");
            } else {
                this.plugin.db.Query("INSERT INTO `tncc_gebiete`"
                        + "             SET `gebiet_min_x` = '" + this.minX + "',"
                        + "                 `gebiet_min_y` = '" + this.minY + "',"
                        + "                 `gebiet_min_z` = '" + this.minZ + "',"
                        + "                 `gebiet_max_x` = '" + this.maxX + "',"
                        + "                 `gebiet_max_y` = '" + this.maxY + "',"
                        + "                 `gebiet_max_z` = '" + this.maxZ + "',"
                        + "                 `gebiet_world` = '" + this.world + "',"
                        + "                 `gebiet_name` = '" + this.name + "'");
            }
        } catch (Exception e) {
        }
    } // Ende Save
} // Ende Class
