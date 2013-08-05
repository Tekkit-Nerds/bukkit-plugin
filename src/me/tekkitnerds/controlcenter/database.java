package me.tekkitnerds.controlcenter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.Bukkit;

public class database {

    private Connection conn;
    private boolean isConnected = false;
    private String DRIVER = "com.mysql.jdbc.Driver";
    private String JAVA_IO_TMPDIR = "java.io.tmpdir";
    private String host, user, passwort, database;
    private int port;

    public database(String host, int port, String user, String passwort, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.passwort = passwort;
        this.database = database;
        this.connect();
    }

    // Prüft ob die verbindung vorhanden ist, sonnst wird eine neue Verbindung herstellt. Wenn erfolgreich dann true
    public boolean isConnected() {
        if (!this.isConnected) {
            this.connect();
            if (this.isConnected) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void connect() {
        this.isConnected = false;
        File ourAppDir = new File(System.getProperty(JAVA_IO_TMPDIR));
        File databaseDir = new File(ourAppDir, "mysql-mxj");
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
            this.conn = DriverManager.getConnection(url, this.user, this.passwort);
            this.isConnected = true;
        } catch (Exception e) {
            this.isConnected = false;
        }
    }

    private void disconnect() {
        try {
            this.isConnected = false;
            this.conn.close();
        } catch (SQLException ex) {
        }
    }

    public ResultSet Query(String SQLquery) {
        return Query (SQLquery, false);
    }
    
    public ResultSet Query(String SQLquery, boolean isInsert) {
        try {
            if (isInsert){
                Statement stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(SQLquery);
                return null;
            } else {
                Statement stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                return stmt.executeQuery(SQLquery);
            }
        } catch (SQLException ex) {
            Bukkit.getServer().broadcastMessage(ex.toString());
            return null;
        }
    }
}
