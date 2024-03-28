/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles keeping track of and updating the status of  the shield availability for players.
 *  Essentially this class is used as an inventory space for the shield powerup.
 * 
*/

package org.example.entities.playerinfo;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerShieldReadyText extends TextEntity {
    private boolean isReady;
    private boolean isActive;

    public PlayerShieldReadyText(Coordinate2D location) {
        super(location);
        setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        setFill(Color.DARKMAGENTA);
    }

    
    /** 
     * Sets the shield availability and visual notifier to whatever boolean was offered to it.
     * 
     * @param shieldReady   whether the shield should be set to available or not.
     */
    public void setAvailable(Boolean shieldReady) {
        if (shieldReady) {
            setText("Shield: READY");
            this.isReady = true;
        } else {
            setText("Shield: NOT READY");
            this.isActive = false;
            this.isReady = false;
        }
    }

    /** 
     * Sets the shield to active, making the player invulnerable to incoming damage for a short duration of time.
     */
    public void setActive() {
        setText("Shield: ACTIVATED");
        this.isActive = true;
        this.isReady = false;
    }

    /** 
     * A getter for the isReady variable, used to check whether the shield power up is ready to be used or not.
     * 
     * @return boolean      whether the shield is ready to be used or not.
     */
    public boolean isReady() {
        return this.isReady;
    }

    
    /** 
     * A getter for the isActive variable, used to check whether the shield power up is currently in use or not.
     * 
     * @return boolean      whether the shield is currently active or not.
     */
    public boolean isActive() {
        return this.isActive;
    }
}
