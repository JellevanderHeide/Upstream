package org.example.entities.spawners;

import java.util.Random;

import org.example.entities.hazards.BigFish;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;

public class HazardSpawner extends EntitySpawner {
    private double sceneWidth;
    private double sceneHeight;
    final int BEARCLAW = 1, BIGFISH = 2, FISHNET = 3, RAPIDS = 4, RIVERTRASH = 5, WOODBEAM = 6;

    public HazardSpawner(double sceneWidth, double sceneHeight) {
        super(3000);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    @Override
    protected void spawnEntities() {
        Random random = new Random();
        int hazardType = random.nextInt(2, 3);
        switch (hazardType) {
            case BIGFISH:
                spawn(new BigFish(new Coordinate2D(sceneWidth, random.nextDouble(0, sceneHeight)), 4));
                break;
            default:
                break;
        }
    }
}
