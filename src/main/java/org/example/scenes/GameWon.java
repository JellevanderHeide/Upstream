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

public class GameWon extends StaticScene implements TileMapContainer {
    private Upstream upstream;

    public GameWon(Upstream upstream) {
        this.upstream = upstream;
    }

    @Override
    public void setupScene() {
        // setBackgroundAudio("audio/StartScreen.mp3");
        setBackgroundImage("backdrops/fishTile_089.png");
    }

    @Override
    public void setupEntities() {
        ArrayList<Color> textColours = new ArrayList<Color>(Arrays.asList(Color.DARKGRAY, Color.LIGHTGRAY));
        ArrayList<TextEntity> textEntities = new ArrayList<TextEntity>();
        for (int i = 0; i < 2; i++) {
            textEntities.add(new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 3), "Upstream"));
            TextEntity currentTextEntity = textEntities.get(i);
            currentTextEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
            currentTextEntity.setFill(textColours.get(i));
            currentTextEntity.setFont(Font.font("Arial", FontWeight.BOLD, 110 - (i * 5)));
            addEntity(currentTextEntity);
        }

        StartButton startButton = new StartButton(this.upstream, new Coordinate2D(getWidth() / 2, getHeight() / 3 * 2));
        startButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(startButton);
    }

    @Override
    public void setupTileMaps() {
        addTileMap(new GameTileMap(upstream));
    }
}
