package uk.co.samuelzcloud.dev.plugins.BlockElevators.Executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.samuelzcloud.dev.plugins.BlockElevators.BlockElevators;

/**
 * Created by Samuel on 29/03/2015.
 * uk.co.samuelzcloud.dev.plugins.BlockElevators.Executor - BEExecutor
 */
public class BEExecutor implements CommandExecutor {

    private BlockElevators plugin;

    public BEExecutor(BlockElevators plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender has the correct permission.
        if (!(this.plugin.getLog().checkPermission(sender, "BlockElevators.Info"))) {
            this.plugin.getLog().sendFormattedMessage(sender, this.plugin.getConfig().getString("NoPermission"));
            return true;
        }

        // Send the sender the information about plugin.
        this.plugin.getLog().sendPlainMessage(sender, "&3" + this.plugin.getLog().fullLine());
        this.plugin.getLog().sendPlainMessage(sender, "&3-- &6" + this.plugin.getLog().getInfo("Name") + " Information");
        this.plugin.getLog().sendPlainMessage(sender, "&3" + this.plugin.getLog().fullLine());

        this.plugin.getLog().sendPlainMessage(sender, "&3-- &b\u25CF &eVersion: &7" + this.plugin.getLog().getInfo("Version"));
        this.plugin.getLog().sendPlainMessage(sender, "&3-- &b\u25CF &eAuthors: &7" + this.plugin.getLog().getInfo("Author"));
        this.plugin.getLog().sendPlainMessage(sender, "&3-- &b\u25CF &eWebsite: &7" + this.plugin.getLog().getInfo("Website"));
        String desc = this.plugin.getLog().getInfo("Description");
        this.plugin.getLog().sendPlainMessage(sender, "&3-- &b\u25CF &eDescription: &7" + desc.substring(0, (desc.length() > 33) ? 33 : desc.length()) + "...");
        this.plugin.getLog().sendPlainMessage(sender, "&3" + this.plugin.getLog().fullLine());

        // Check to see if the sender is a player.
        if (sender instanceof Player) {
            this.plugin.getLog().logInfo(sender.getName() + " (" + ((Player) sender).getUniqueId().toString() + ") just viewed the plugin information.");
        } else {
            this.plugin.getLog().logInfo(sender.getName() + " just viewed the plugin information.");
        }

        return true;
    }
}
