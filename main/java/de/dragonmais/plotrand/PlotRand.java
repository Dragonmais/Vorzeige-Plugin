package de.dragonmais.plotrand;

import de.dragonmais.plotrand.commands.PlotRandCommand;
import de.dragonmais.plotrand.listener.InventoryListener;
import de.dragonmais.plotrand.manager.InventoryManager;
import de.dragonmais.plotrand.rand.configuration.RandConfiguration;
import de.dragonmais.plotrand.utils.PlotAPIHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlotRand extends JavaPlugin {

    private InventoryManager inventoryManager;
    private PlotAPIHandler plotAPIHandler;

    @Override
    public void onLoad() {
        RandConfiguration randConfiguration = new RandConfiguration(this);
        this.inventoryManager = new InventoryManager(randConfiguration);
        this.plotAPIHandler = new PlotAPIHandler(this);
    }

    public void onEnable() {

        plotAPIHandler = new PlotAPIHandler();

        getCommand("plotrand").setExecutor(new PlotRandCommand(this));


        // Events
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new InventoryListener(this), this);
    }

    public void onDisable() {

    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    public PlotAPIHandler getPlotAPIHandler() {
        return plotAPIHandler;
    }


}
