package de.dragonmais.plotrand.rand.configuration;

import de.dragonmais.plotrand.PlotRand;
import de.dragonmais.plotrand.rand.RandObject;
import de.dragonmais.plotrand.rand.RandObjectArray;
import de.dragonmais.plotrand.utils.JsonMeta;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class RandConfiguration {

    private final File file;
    private final JsonMeta jsonMeta;

    public RandConfiguration(PlotRand plugin) {
        file = plugin.getDataFolder().toPath().resolve("rands.json").toFile();

        if(!file.exists()) {
            copyDefaults();
        }
        this.jsonMeta = JsonMeta.loadConfig(file);
    }

    private void copyDefaults() {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("config.json");
            Files.copy(stream, file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RandObject> randObjects(){
        return jsonMeta.getObject("raends", RandObjectArray.class).getRandObjects();
    }
}
