package de.dragonmais.plotrand.utils;

import com.plotsquared.core.PlotAPI;
import de.dragonmais.plotrand.PlotRand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PlotAPIHandler {

    private PlotAPI api;

    private PlotRand plugin;

    public PlotAPIHandler(PlotRand plugin) {
        this.plugin = plugin;
    }

    public PlotAPIHandler() {
        if (Bukkit.getPluginManager().getPlugin("PlotSquared") == null) {
            Bukkit.getPluginManager().disablePlugin((Plugin) this);
            return;
        }

        api = new PlotAPI();
    }

    public PlotAPI getApi() {
        return api;
    }

}