/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  This is the setup for the scene which is displayed when the player has survived the gameplay loop
 *  for a set amount of time. 
 * 
*/

package org.example.scenes;

import org.example.Upstream;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import org.example.entities.clickables.*;
import org.example.entities.other.Heart;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameWon extends StaticScene  {
    private Upstream upstream;

    public GameWon(Upstream upstream) {
        this.upstream = upstream;
    }

    /** 
     * Sets up the basic necessities for the scene, the background and the music.
     */
    @Override
    public void setupScene() {
        setBackgroundAudio("audio/GameWon.mp3");
        setBackgroundColor(Color.SALMON);
    }

    /** 
     * Sets up the entities for this scene. In this scene there are two buttons for either restarting or quitting the game
     * as well as some decorative entities.
     */
    @Override
    public void setupEntities() {
        addEntity(new Heart(new Coordinate2D(getWidth()/4, getHeight()/4), new Size(getWidth()/2, getWidth()/2)));
        TextEntity textEntity = (new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 5), "You won!"));
        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        textEntity.setFill(Color.RED);
        textEntity.setFont(Font.font("Arial", FontWeight.BOLD, 150));
        addEntity(textEntity);
        QuitButton quitButton = new QuitButton(this.upstream, new Coordinate2D(getWidth() / 5, getHeight() / 5*4));
        StartButton tryAgainButton = new StartButton(this.upstream, new Coordinate2D(getWidth() / 5 * 4, getHeight() / 5*4), "Play again");
        quitButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(quitButton);
        addEntity(tryAgainButton);
    }
}