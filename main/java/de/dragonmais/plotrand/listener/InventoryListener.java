package de.dragonmais.plotrand.listener;

import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.configuration.ConfigurationUtil;
import com.plotsquared.core.plot.BlockBucket;
import com.plotsquared.core.plot.Plot;
import de.dragonmais.plotrand.PlotRand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    private final PlotRand plugin;

    public InventoryListener(PlotRand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.isRightClick()) {
            return;
        }

        if (e.isLeftClick()) {

            PlotAPI api = plugin.getPlotAPIHandler().getApi();

            Plot plot = api.wrapPlayer(player.getName()).getCurrentPlot();

            if (!e.getView().getTitle().startsWith("§cPlot")) {
                return;
            }
            e.setCancelled(true);
            if (!plugin.getInventoryManager().getItems().contains(e.getCurrentItem())) {
                return;
            }

            if (plot == null) {
                player.sendMessage("§cDu bist auf keinem Plot");
                player.closeInventory();
                return;
            }

            if (plot.hasOwner() && plot.isOwner(player.getUniqueId())) {

                if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {

                    ItemStack currentItem = e.getCurrentItem();

                    String id = String.valueOf(ConfigurationUtil.BLOCK_BUCKET.parseString(currentItem.getType().name()));

                    if (currentItem.getType() == Material.BARRIER)
                        id = "AIR:0";

                    BlockBucket plotBlocks = ConfigurationUtil.BLOCK_BUCKET.parseString(id);

                    updatePlotBorder(plot, plotBlocks);

                    player.sendMessage("§6Dein Rand wurde zu " + currentItem.getItemMeta().getDisplayName() + " §6geändert");

                    player.closeInventory();
                }
            } else {
                player.sendMessage("§cDas Plot gehört dir nicht");
                player.closeInventory();
            }

        }


    }
    private void updatePlotBorder(Plot plot, BlockBucket plotBlocks) {
        for (Plot plots : plot.getConnectedPlots()) {
            plots.getPlotModificationManager().setComponent("border", plotBlocks.toPattern(), null, null);
        }
    }
}
