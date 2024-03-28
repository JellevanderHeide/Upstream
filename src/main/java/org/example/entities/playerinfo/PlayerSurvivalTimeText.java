/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles displaying and updating the survivaltime for a gameplay session.
 * 
*/

package org.example.entities.playerinfo;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerSurvivalTimeText extends TextEntity {
    public PlayerSurvivalTimeText(Coordinate2D location) {
        super(location);
        setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        setFill(Color.BLACK);
    }

    /** 
     * Sets the text to an up to date version of how much time remains for the current gameplay session
     * for the player to survive until they win the game.
     */
    public void setText(int secondsRemaining) {
        setText("Seconds remaining: " + String.valueOf(secondsRemaining));
    }
}
