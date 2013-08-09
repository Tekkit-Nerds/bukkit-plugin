package me.tekkitnerds.controlcenter.scheduler;
// @author Niklas

import java.util.HashMap;
import me.tekkitnerds.controlcenter.controlcenter;
import org.bukkit.scheduler.BukkitScheduler;

public class Scheduler {

    private controlcenter plugin;
    private BukkitScheduler scheduler;
    private HashMap<String, Integer> taskids = new HashMap<String, Integer>();

    public Scheduler(controlcenter pPlugin) {
        this.plugin = pPlugin;
        this.scheduler = this.plugin.getServer().getScheduler();
    }

    public void setLockTime(boolean lock) {
        if (lock == true) {
            if (this.taskids.containsKey("lockTime")) {
                this.scheduler.cancelTask(this.taskids.get("lockTime"));
                this.taskids.remove("lockTime");
            }
            int id = this.scheduler.scheduleSyncRepeatingTask(this.plugin, new TaskLockTime(this.plugin), 20L, 600L);
            this.taskids.put("lockTime", id);
        } else {
            if (this.taskids.containsKey("lockTime")) {
                this.scheduler.cancelTask(this.taskids.get("lockTime"));
                this.taskids.remove("lockTime");
            }
        }
    }
}
