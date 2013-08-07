package me.tekkitnerds.controlcenter.beruf;
// @author Niklas
import me.tekkitnerds.controlcenter.benutzer.Benutzer;
import me.tekkitnerds.controlcenter.controlcenter;

public class Beruf {
    private controlcenter plugin;
    private String name;
    private int xp;
    private Benutzer benutzer;

    public Beruf(controlcenter plugin, String name, int xp, Benutzer benutzer) {
        this.plugin = plugin;
        this.name = name;
        this.xp = xp;
        this.benutzer = benutzer;
    }

    public String getName() {
        return this.name;
    }

    
    
    
}
