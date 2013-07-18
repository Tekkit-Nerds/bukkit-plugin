package me.tekkitnerds.controlcenter;

public class database {
    private boolean isConnected = false;

    public database(String host, String user, String passwort, String database) {
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
        this.isConnected = true;
    }

    private void disconnect() {
        this.isConnected = false;
    }
}
