package uk.co.samuelzcloud.dev.plugins.BlockElevators.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import uk.co.samuelzcloud.dev.plugins.BlockElevators.BlockElevators;

/**
 * Created by Samuel on 29/03/2015.
 * uk.co.samuelzcloud.dev.plugins.BlockElevators.Listeners - PlayerListener
 */
public class PlayerListener implements Listener {

    private BlockElevators plugin;

    public PlayerListener(BlockElevators plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Block standingOn = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

        if (!(player.isSneaking())) return;

        if (!(standingOn.getType() == Material.valueOf(this.plugin.getConfig().getString("ElevatorBlock")))) return;

        standingOn = standingOn.getRelative(BlockFace.DOWN, 3);

        int i = 16;

        while ((i > 0) && !(standingOn.getType() == Material.valueOf(this.plugin.getConfig().getString("ElevatorBlock"))) || !(standingOn.getRelative(BlockFace.UP).getType().isTransparent()) || !(standingOn.getRelative(BlockFace.UP, 2).getType().isTransparent()) ) {
            i--;
            standingOn = standingOn.getRelative(BlockFace.DOWN);
        }

        if (i > 0) {
            if (!(this.plugin.getLog().checkPermission(player, "BlockElevators.Use"))) {
                this.plugin.getLog().sendFormattedMessage(player, this.plugin.getConfig().getString("NoPermission"));
                return;
            }

            Location loc = player.getLocation();
            loc.setY(loc.getY() - 16 - 3.0D + i);
            player.teleport(loc);
            player.getWorld().playSound(loc, Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block standingOn = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

        if (!(event.getFrom().getY() < event.getTo().getY())) return;

        if (!(standingOn.getType() == Material.valueOf(this.plugin.getConfig().getString("ElevatorBlock")))) return; // Use config for item type?

        standingOn = standingOn.getRelative(BlockFace.UP, 3);

        int i = 16;

        while ((i > 0) && !(standingOn.getType() == Material.valueOf(this.plugin.getConfig().getString("ElevatorBlock"))) || !(standingOn.getRelative(BlockFace.UP).getType().isTransparent()) || !(standingOn.getRelative(BlockFace.UP, 2).getType().isTransparent()) ) {
            i--;
            standingOn = standingOn.getRelative(BlockFace.UP);
        }

        if (i > 0) {
            if (!(this.plugin.getLog().checkPermission(player, "BlockElevators.Use"))) {
                this.plugin.getLog().sendFormattedMessage(player, this.plugin.getConfig().getString("NoPermission"));
                return;
            }

            Location loc = player.getLocation();
            loc.setY(loc.getY() + 16 + 3.0D - i);
            player.teleport(loc);
            player.getWorld().playSound(loc, Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
        }

    }
}
