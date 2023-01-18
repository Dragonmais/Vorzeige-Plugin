package de.dragonmais.plotrand.itembuilder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder setDisplayName(String name) {
        name = name.replaceAll("&", "§");
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int lvl, boolean ignoreLevelRestriction) {
        meta.addEnchant(enchantment, lvl, ignoreLevelRestriction);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
