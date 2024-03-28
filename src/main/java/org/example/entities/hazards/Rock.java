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
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class Rock extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided, Hazard {
    private int spriteWidth;
    private int spriteHeight; 
    private final int damagePoints = 1;

    public Rock(Coordinate2D location, int speed, int spriteWidth, int spriteHeight) {
        super("sprites/rock" + String.valueOf(new Random().nextInt(1, 7)) + ".png", location.subtract(new Coordinate2D(0, spriteHeight)) , new Size(spriteWidth, spriteHeight));
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
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

    /** 
     * Lowers the player health for the player it was provided with.
     * 
     * @param player    A player whose health should be lowered.
     */
    @Override
    public void doDamage(SalmonPlayer player) {
        player.setHealth(player.getHealth() - this.damagePoints);
    }
}