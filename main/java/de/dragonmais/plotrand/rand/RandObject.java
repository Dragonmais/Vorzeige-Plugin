package de.dragonmais.plotrand.rand;

import org.bukkit.Material;

public class RandObject {

    private final String name;
    private final Material material;

    public RandObject(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }
}
