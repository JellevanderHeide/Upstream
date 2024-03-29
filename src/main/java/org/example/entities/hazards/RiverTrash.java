/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles placing rivertrash on screen. Placements of these entities
 *  is locked to the top of the screen and will be decided randomly.
 * 
*/

package org.example.entities.hazards;

import java.util.List;
import org.example.entities.player.SalmonPlayer;
import org.example.entities.tilemaps.RiverbedTile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class RiverTrash extends Hazard implements Newtonian{
    public RiverTrash(Coordinate2D location, int speed, int spriteWidth, int spriteHeight, int damagePoints) {
        super("sprites/garbagebag.png", location.subtract(new Coordinate2D(spriteWidth, 0)), new Size(spriteWidth, spriteHeight), spriteWidth, spriteHeight, damagePoints);
        setFrictionConstant(0.2);
        setGravityConstant(0.35);
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
            } else if(collider instanceof RiverbedTile){
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
        if (border.equals(SceneBorder.BOTTOM)) {
            remove();
        }
    }
}