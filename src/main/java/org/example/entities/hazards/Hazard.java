/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  A basic interface for hazards that inflict damage.
 * 
*/

package org.example.entities.hazards;

import org.example.entities.player.SalmonPlayer;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Hazard extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided{
    private int damagePoints;
    private int spriteWidth;
    private int spriteHeight;

    protected Hazard(String sprite, Coordinate2D location, Size size, int spriteWidth, int spriteHeight, int damagePoints) {
        super(sprite, location, size);
        this.damagePoints = damagePoints;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
    }

    /** 
     * Spriteheight getter.
     * 
     * @return int      the spriteheight.
     */
    public int getSpriteHeight(){
        return this.spriteHeight;
    }

    /** 
     * Spritewidth getter.
     * 
     * @return int      the spritewidth.
     */
    public int getSpriteWidth(){
        return this.spriteWidth;
    }

    /** 
     * A method for inflicting damage on a player, as much as the damagePoints
     * are set at.
     */
    public void doDamage(SalmonPlayer player){
        player.setHealth(player.getHealth() - this.damagePoints);
    }
}
