package ldahleen54.minecartimproved;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.logging.Level;

public class MinecartSpeedListener implements Listener {
    private JavaPlugin plugin;
    public MinecartSpeedListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getVehicle() instanceof Minecart) {
            Minecart minecart = (Minecart) event.getVehicle();
            // Set the minecart's maximum speed to a higher value
            minecart.setMaxSpeed(1.8);
            plugin.getLogger().info("Minecraft max speed set to: " + minecart.getMaxSpeed());
        }
    }


    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        if (!(event.getVehicle() instanceof Minecart)) return;

        Minecart minecart = (Minecart) event.getVehicle();

        if (isPoweredRail(minecart)) {
            minecart.setMaxSpeed(1.8);
            plugin.getLogger().info("powered rail Minecraft max speed set to: " + minecart.getMaxSpeed());
        } else {
            minecart.setMaxSpeed(0.4);
            plugin.getLogger().info("not powered railMinecraft max speed set to: " + minecart.getMaxSpeed());
        }
    }

    private boolean isPoweredRail(Minecart minecart) {
        Block blockAt = minecart.getLocation().getBlock();
        Block blockBelow = blockAt.getRelative(BlockFace.DOWN);
        return blockAt.getType() == Material.POWERED_RAIL || blockBelow.getType() == Material.POWERED_RAIL;
    }
}