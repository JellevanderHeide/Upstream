/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles placing big fish on screen. Placements of these entities
 *  are randomized locations on the far right of the screen, bar the locations of river tiles.
 * 
*/

package org.example.entities.hazards;

import java.util.List;
import org.example.entities.player.SalmonPlayer;
import org.example.entities.tilemaps.RiverbedTile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class BigFish extends Hazard {
    public BigFish(Coordinate2D location, int speed, int spriteWidth, int spriteHeight, int damagePoints) {
        super("sprites/bigfish.png", location, new Size(spriteWidth, spriteHeight), spriteWidth, spriteHeight, damagePoints);
        setMotion(speed, Direction.LEFT);
    }

    /** 
     * Handles collision with the player.
     * 
     * @param collidingObjects  Objects that collision occured with.
     */
    @Override
    public void onCollision(List<Collider> collidingObjects) {
        for (Collider collider : collidingObjects) {
            if (collider instanceof SalmonPlayer) {
                break;
            } else if (collider instanceof RiverbedTile){
                remove();
            }
        }
    }

    /** 
     * Handles crossing the screen boundary.
     * 
     * @param border    Border of the current scene.
     */   
    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border.equals(SceneBorder.LEFT)) {
            remove();
        }
    }
}