package ldahleen54.minecartimproved;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class MinecartImproved extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().log(Level.INFO, "MinecartImproved plugin has started");
        getServer().getPluginManager().registerEvents(new MinecartSpeedListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().log(Level.INFO, "MinecartImproved plugin has stopped");
    }
}
