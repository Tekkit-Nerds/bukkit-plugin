package me.tekkitnerds.controlcenter.scheduler;
//@author Niklas

import me.tekkitnerds.controlcenter.controlcenter;

public class TaskLockTime implements Runnable {
    
    private long locktime = 0;
    private controlcenter plugin;
    
    TaskLockTime(controlcenter pPlugin) {
        this.plugin = pPlugin;
        this.locktime = this.plugin.getServer().getWorlds().get(0).getTime();
    }
    
    @Override
    public void run() {
        if (this.plugin.getServer().getWorlds().get(0).getTime() - 60 > this.locktime) {
            this.plugin.getServer().getWorlds().get(0).setTime(this.locktime);
        }
        
    }
}
