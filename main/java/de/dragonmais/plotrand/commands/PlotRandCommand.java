package de.dragonmais.plotrand.commands;

import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.plot.Plot;
import de.dragonmais.plotrand.PlotRand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlotRandCommand implements CommandExecutor {

    private final PlotRand plugin;

    public PlotRandCommand(PlotRand plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player))
            return false;

        PlotAPI api = plugin.getPlotAPIHandler().getApi();
        
        Plot plot = api.wrapPlayer(player.getName()).getCurrentPlot();

        if (plot == null) {
            player.sendMessage("§cDu bist auf keinem Plot");
            return false;
        }

        if (plot.hasOwner() && plot.isOwner(player.getUniqueId())) {
            player.openInventory(plugin.getInventoryManager().createInv(6, "§cPlot Rand", player));
        } else {
            player.sendMessage("§cDas Plot gehört dir nicht");
        }

        return false;
    }
}