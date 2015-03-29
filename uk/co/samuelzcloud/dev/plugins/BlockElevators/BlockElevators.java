package uk.co.samuelzcloud.dev.plugins.BlockElevators;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.samuelzcloud.dev.plugins.BlockElevators.Executor.BEExecutor;
import uk.co.samuelzcloud.dev.plugins.BlockElevators.Listeners.PlayerListener;
import uk.co.samuelzcloud.dev.plugins.BlockElevators.Utilities.CustomLogger;

/**
 * Created by Samuel on 29/03/2015.
 * uk.co.samuelzcloud.dev.plugins.BlockElevators - BlockElevators
 */
public class BlockElevators extends JavaPlugin {

    // Define the Server and Plugin Manager variable.
    private Server server;
    private PluginManager manager;

    // Define the Custom logger variable.
    private CustomLogger log;

    @Override
    public void onEnable() {
        // Copy the default configuration file to plugins folder and save it.
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        // Initiate the Server and Plugin Manager variables.
        this.server = this.getServer();
        this.manager = this.getServer().getPluginManager();

        // Initiate the Custom Logger variable.
        this.log = new CustomLogger(this);

        // Define the DeathStare command executor.
        this.getCommand("BlockElevators").setExecutor(new BEExecutor(this));

        // Register the Player Listener events.
        this.getManager().registerEvents(new PlayerListener(this), this);
    }

    /** Getters **/
    // Method to get the Server variable.
    public Server getPluginServer() {
        return this.server;
    }

    // Method to get the Plugin Manager variable.
    public PluginManager getManager() {
        return this.manager;
    }

    // Method to get the Custom Logger variable.
    public CustomLogger getLog() {
        return this.log;
    }

}
