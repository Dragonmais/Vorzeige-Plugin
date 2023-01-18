package de.dragonmais.plotrand.rand;

import java.util.List;

public class RandObjectArray {

    private final List<RandObject> randObjects;

    public RandObjectArray(List<RandObject> randObjects) {
        this.randObjects = randObjects;
    }

    public List<RandObject> getRandObjects() {
        return randObjects;
    }
}
