/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Places and moves rapids from right to left, which slows the player to
 *  a halt for three seconds when touched.
 * 
*/

package org.example.entities.hazardnodamage;

import java.util.List;
import org.example.entities.player.SalmonPlayer;
import org.example.entities.tilemaps.RiverbedTile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class Rapids extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided, HazardNoDamage {
    private int spriteWidth;
    private int spriteHeight;

    public Rapids(Coordinate2D location, int speed, int spriteWidth, int spriteHeight) {
        super("sprites/rapids.png", location, new Size(spriteWidth, spriteHeight), 8, 5);
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        setAutoCycle(125);
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

    /** 
     * Spritewidth getter.
     * 
     * @return int      the spritewidth.
     */
    public int getSpriteWidth(){
        return spriteWidth;
    }

    /** 
     * Spriteheight getter.
     * 
     * @return int      the spriteheight.
     */
    public int getSpriteHeight(){
        return spriteHeight;
    }
}