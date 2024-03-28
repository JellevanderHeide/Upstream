/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  This is the setup for the scene which is displayed when the player  started
 *  the application. It displays only a start button
 * 
*/

package org.example.scenes;

import java.util.ArrayList;
import java.util.Arrays;
import org.example.Upstream;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import org.example.entities.clickables.StartButton;
import org.example.entities.tilemaps.GameTileMap;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartScreen extends StaticScene implements TileMapContainer {
    private Upstream upstream;

    public StartScreen(Upstream upstream) {
        this.upstream = upstream;
    }

    /** 
     * Sets up the basic necessities for the scene, the background and the music.
     */
    @Override
    public void setupScene() {
        setBackgroundAudio("audio/StartScreen.mp3");
        setBackgroundImage("backdrops/sea.png");
    }

    /** 
     * Sets up the entities for this scene. In this scene there is a single button to start the game.
     * Additionally, a concept riverbed is generated and the main title is generated.
     */
    @Override
    public void setupEntities() {
        ArrayList<Color> textColours = new ArrayList<Color>(Arrays.asList(Color.DARKGRAY, Color.LIGHTGRAY));
        ArrayList<TextEntity> textEntities = new ArrayList<TextEntity>();
        // Generate two overlapping blocks of text for an interesting visual effect.
        for (int i = 0; i < 2; i++) {
            textEntities.add(new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 3), "Upstream"));
            TextEntity currentTextEntity = textEntities.get(i);
            currentTextEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
            currentTextEntity.setFill(textColours.get(i));
            currentTextEntity.setFont(Font.font("Arial", FontWeight.BOLD, 110 - (i * 5)));
            addEntity(currentTextEntity);
        }
        StartButton startButton = new StartButton(this.upstream, new Coordinate2D(getWidth() / 2, getHeight() / 3 * 2), "Start");
        startButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(startButton);
    }

    /** 
     * Sets up a tilemap for the scene. This utilized only the bottom two rows.
     */
    @Override
    public void setupTileMaps() {
        addTileMap(new GameTileMap(upstream));
    }
}
