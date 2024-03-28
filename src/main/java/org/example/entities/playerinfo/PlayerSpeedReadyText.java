/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles keeping track of and updating the status of  the speedboost availability for players.
 *  Essentially this class is used as an inventory space for the speed powerup.
 * 
*/

package org.example.entities.playerinfo;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerSpeedReadyText extends TextEntity {
    private boolean isReady;

    public PlayerSpeedReadyText(Coordinate2D location) {
        super(location);
        setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        setFill(Color.DARKORANGE);
    }

    /** 
     * Sets the shield availability and visual notifier to whatever boolean was offered to it.
     * 
     * @param speedReady   whether the shield should be set to available or not.
     */
    public void setAvailable(Boolean speedReady) {
        if (speedReady) {
            setText("Speedboost: READY");
            this.isReady = true;
        } else {
            setText("Speedboost: NOT READY");
            this.isReady = false;
        }
    }

    /** 
     * Sets the speed boost to active, making the player significantly faster for a short duration of time.
     */
    public void setActive() {
        setText("Speedboost: ACTIVATED");
        this.isReady = false;
    }

    /** 
     * A getter for the isReady variable, used to check whether the speed power up is ready to be used or not.
     * 
     * @return boolean      whether the speed power up is ready to be used or not.
     */
    public boolean isReady() {
        return this.isReady;
    }
}
