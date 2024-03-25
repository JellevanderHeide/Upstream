package org.example.entities.spawners;

import java.util.Random;

import org.example.Upstream;
import org.example.entities.powerups.ShieldPowerup;
import org.example.entities.powerups.SmallFish;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;

public class PowerupSpawner extends EntitySpawner {
    private double sceneWidth;
    private double sceneHeight;
    private Upstream upstream;
    final int SHIELD = 1, SPEED = 2;

    public PowerupSpawner(double sceneWidth, double sceneHeight, Upstream upstream) {
        super(3000);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.upstream = upstream;
    }

    @Override
    protected void spawnEntities() {
        Random random = new Random();
        int powerUpType = random.nextInt(1, 10);
        switch (powerUpType) {
            case SHIELD:
                spawn(new ShieldPowerup(new Coordinate2D(sceneWidth,
                        random.nextDouble(0, sceneHeight / upstream.getTileHeight() * (upstream.getTileHeight() - 2))),
                        4));
                break;
            case SPEED:
                spawn(new SmallFish(new Coordinate2D(sceneWidth,
                        random.nextDouble(0, sceneHeight / upstream.getTileHeight() * (upstream.getTileHeight() - 2))),
                        4));
                break;
            default:
                break;
        }
    }
}
