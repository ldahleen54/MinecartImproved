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

public class MinecartSpeedListener implements Listener {
    private final JavaPlugin plugin;
    public MinecartSpeedListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getVehicle() instanceof Minecart) {
            Minecart minecart = (Minecart) event.getVehicle();
            // Set the minecart's maximum speed to a higher value
            minecart.setMaxSpeed(0.4);
            plugin.getLogger().info("Minecraft max speed set to: " + minecart.getMaxSpeed());
        }
    }


    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        if (!(event.getVehicle() instanceof Minecart)) return;

        Minecart minecart = (Minecart) event.getVehicle();

        if (isPoweredRail(minecart)) {
            // set speed up to 2.0
            if(!(minecart.getMaxSpeed() >= 1.8)) {
                minecart.setMaxSpeed(minecart.getMaxSpeed() + 0.01);
            }
            plugin.getLogger().info("powered rail Minecraft max speed set to: " + minecart.getMaxSpeed());
        } else {
            // detect if max speed is already 0
            if(!(minecart.getMaxSpeed() <= 0.4)) {
                minecart.setMaxSpeed(minecart.getMaxSpeed() - 0.15);
            }
            plugin.getLogger().info("not powered railMinecraft max speed set to: " + minecart.getMaxSpeed());
        }
    }

    private boolean isPoweredRail(Minecart minecart) {
        Block blockAt = minecart.getLocation().getBlock();
        Block blockBelow = blockAt.getRelative(BlockFace.DOWN);
        return blockAt.getType() == Material.POWERED_RAIL || blockBelow.getType() == Material.POWERED_RAIL;
    }
}