/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles placing hazards on screen. Placing entities takes
 *  place depending on the interval provided.
 * 
*/

package org.example.entities.spawners;

import java.util.Random;
import org.example.Upstream;
import org.example.entities.hazardnodamage.FishNet;
import org.example.entities.hazardnodamage.Rapids;
import org.example.entities.hazards.*;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;

public class HazardSpawner extends EntitySpawner {
    private double sceneWidth;
    private double sceneHeight;
    private Upstream upstream;
    private final int BIGFISH = 1, FISHHOOK = 2, FISHNET = 3, RAPIDS = 4, RIVERTRASH = 5, ROCK = 6;

    public HazardSpawner(double sceneWidth, double sceneHeight, Upstream upstream) {
        super(3000);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.upstream = upstream;
    }

    /** 
     * Handles spawning hazards on screen. Every so many seconds a random hazard is spawned. 
     * Placement is dependent on the type of hazard.
     */
    @Override
    public void spawnEntities() {
        Random random = new Random();
        int hazardType = random.nextInt(BIGFISH, ROCK+1);
        switch (hazardType) {
            case BIGFISH:
                spawn(new BigFish(new Coordinate2D(sceneWidth, random.nextDouble(0, sceneHeight / upstream.getTileHeight() * upstream.getTileHeight() - 2)), 4, 240, 192, 1));
                break;
            case FISHHOOK:
                spawn(new FishHook(new Coordinate2D(sceneWidth, 0), 4, 120, 261, 2));         
                break;
            case FISHNET:
                spawn(new FishNet(new Coordinate2D(sceneWidth, random.nextDouble(0, sceneHeight / upstream.getTileHeight() * upstream.getTileHeight() - 2)), 4, 135, 171));         
                break;
            case RAPIDS:
                spawn(new Rapids(new Coordinate2D(sceneWidth, random.nextDouble(0, sceneHeight / upstream.getTileHeight() * upstream.getTileHeight() - 2)), 4, 192, 192));       
            case RIVERTRASH:
                spawn(new RiverTrash(new Coordinate2D(random.nextDouble(0, sceneWidth), 0), 2, 180, 210, 1));
                break;
            case ROCK:
                spawn(new Rock(new Coordinate2D(sceneWidth, sceneHeight / upstream.getTileHeight() * (upstream.getTileHeight() - 1)), 4, 200, 200, 1));         
                break;
            default:
                break;
        }
    }
}
