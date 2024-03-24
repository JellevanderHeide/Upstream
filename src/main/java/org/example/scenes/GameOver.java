package org.example.scenes;

import org.example.Upstream;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import org.example.entities.clickables.QuitButton;
import org.example.entities.clickables.TryAgainButton;
import org.example.entities.tilemaps.GameTileMap;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver extends StaticScene implements TileMapContainer {
    private Upstream upstream;

    public GameOver(Upstream upstream) {
        this.upstream = upstream;
    }

    @Override
    public void setupScene() {
        // setBackgroundAudio("audio/StartScreen.mp3");
        setBackgroundColor(Color.BLACK);
    }

    @Override
    public void setupEntities() {
        TextEntity textEntity = (new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 3), "You died!"));
        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        textEntity.setFill(Color.RED);
        textEntity.setFont(Font.font("Arial", FontWeight.BOLD, 150));
        addEntity(textEntity);

        QuitButton quitButton = new QuitButton(this.upstream, new Coordinate2D(getWidth() / 3, getHeight() / 2));
        TryAgainButton tryAgainButton = new TryAgainButton(this.upstream, new Coordinate2D(getWidth() / 3 * 2, getHeight() / 2));
        quitButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(quitButton);
        addEntity(tryAgainButton);
    }

    @Override
    public void setupTileMaps() {
        addTileMap(new GameTileMap(upstream));
    }
}