package org.example.scenes;

import org.example.Upstream;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import org.example.entities.clickables.QuitButton;
import org.example.entities.clickables.TryAgainButton;
import org.example.entities.other.Heart;
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
        setBackgroundColor(Color.SALMON);
    }

    @Override
    public void setupEntities() {
        Heart heart = new Heart(new Coordinate2D(getWidth()/4, getHeight()/4), new Size(getWidth()/2, getWidth()/2));
        addEntity(heart);
        TextEntity textEntity = (new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 5), "You won!"));
        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        textEntity.setFill(Color.RED);
        textEntity.setFont(Font.font("Arial", FontWeight.BOLD, 150));
        addEntity(textEntity);

        QuitButton quitButton = new QuitButton(this.upstream, new Coordinate2D(getWidth() / 5, getHeight() / 5*4));
        TryAgainButton tryAgainButton = new TryAgainButton(this.upstream, new Coordinate2D(getWidth() / 5 * 4, getHeight() / 5*4));
        quitButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(quitButton);
        addEntity(tryAgainButton);
    }

    @Override
    public void setupTileMaps() {
        addTileMap(new GameTileMap(upstream));
    }
}