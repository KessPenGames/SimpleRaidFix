package simple.raid.fixer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;

import java.util.HashMap;

public class Raids implements Listener {
    private Main plugin;
    public Raids(Main plugin) {
        this.plugin = plugin;
    }
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    @EventHandler
    public void onTriggerRaid(RaidTriggerEvent event) {
        int cooldownTime = 900;
        if(cooldowns.containsKey(event.getPlayer().getName())) {
            long secondsLeft = ((cooldowns.get(event.getPlayer().getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(secondsLeft>0) {
                event.setCancelled(true);
                return;
            }
        }
        cooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
    }
}
