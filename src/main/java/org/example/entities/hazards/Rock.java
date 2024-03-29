/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles placing rocks on screen. Placements of these entities
 *  is locked to a specific vertical location, and it may use one of six different sprites.
 * 
*/

package org.example.entities.hazards;

import java.util.List;
import java.util.Random;
import org.example.entities.player.SalmonPlayer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class Rock extends Hazard {
    public Rock(Coordinate2D location, int speed, int spriteWidth, int spriteHeight, int damagePoints) {
        super("sprites/rock" + String.valueOf(new Random().nextInt(1, 7)) + ".png", location.subtract(new Coordinate2D(0, spriteHeight)) , new Size(spriteWidth, spriteHeight), spriteWidth, spriteHeight, damagePoints);
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