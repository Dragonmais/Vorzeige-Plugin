package de.dragonmais.plotrand.manager;

import de.dragonmais.plotrand.itembuilder.ItemBuilder;
import de.dragonmais.plotrand.rand.RandObject;
import de.dragonmais.plotrand.rand.configuration.RandConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private final List<ItemStack> items = new ArrayList<>();

    public InventoryManager(RandConfiguration randConfiguration) {
        items.addAll(loadItemList(randConfiguration.randObjects()));
    }

    public Inventory createInv(int rows, String name, Player plr) {
        @NotNull Inventory inv = Bukkit.createInventory(null, rows * 9, name);

        for (int i = 0; i < items.size() && i < rows * 9 - 9; i++) {
            if (plr.hasPermission("plotrand." + items.get(i).getType().name())) {
                inv.addItem(items.get(i));
            }
        }
        return inv;
    }


    public List<ItemStack> loadItemList(List<RandObject> randObjects) {
        List<ItemStack> items = new ArrayList<>();

        for (RandObject randObject : randObjects) {
            ItemStack build = new ItemBuilder(randObject.getMaterial()).setDisplayName(randObject.getName()).build();
            items.add(build);
        }

        return items;
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
