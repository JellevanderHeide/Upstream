/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  This is the setup for the scene which is displayed when the player has lost
 *  all their health points in the gameplay loop. It allows for the player to either
 *  start another run, or shut down the application.
 * 
*/

package org.example.scenes;

import org.example.Upstream;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import org.example.entities.clickables.QuitButton;
import org.example.entities.clickables.StartButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver extends StaticScene {
    private Upstream upstream;

    public GameOver(Upstream upstream) {
        this.upstream = upstream;
    }

    /** 
     * Sets up the basic necessities for the scene, the background and the music.
     */
    @Override
    public void setupScene() {
        setBackgroundAudio("audio/GameLost.mp3");
        setBackgroundColor(Color.BLACK);
    }

    /** 
     * Sets up the entities for this scene. In this scene there are two buttons for either restarting or quitting the game
     * as well as a text notifying the player of their failure.
     */
    @Override
    public void setupEntities() {
        TextEntity textEntity = (new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 3), "You died!"));
        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        textEntity.setFill(Color.RED);
        textEntity.setFont(Font.font("Arial", FontWeight.BOLD, 150));
        addEntity(textEntity);

        QuitButton quitButton = new QuitButton(this.upstream, new Coordinate2D(getWidth() / 3, getHeight() / 2));
        StartButton tryAgainButton = new StartButton(this.upstream, new Coordinate2D(getWidth() / 3 * 2, getHeight() / 2), "Try again");
        quitButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(quitButton);
        addEntity(tryAgainButton);
    }
}