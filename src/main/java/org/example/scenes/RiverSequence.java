package org.example.scenes;

import org.example.Upstream;
import org.example.entities.player.SalmonPlayer;
import org.example.entities.playerinfo.PlayerHealthText;
import org.example.entities.playerinfo.PlayerShieldReadyText;
import org.example.entities.playerinfo.PlayerSpeedReadyText;
import org.example.entities.playerinfo.PlayerSurvivalTimeText;
import org.example.entities.spawners.HazardSpawner;
import org.example.entities.spawners.PowerupSpawner;
import org.example.entities.spawners.TimerUpdater;
import org.example.entities.tilemaps.GameTileMap;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;

public class RiverSequence extends DynamicScene implements EntitySpawnerContainer, TileMapContainer {
    private Upstream upstream;
    private SalmonPlayer player;

    public RiverSequence(Upstream upstream) {
        this.upstream = upstream;
    }

    @Override
    public void setupScene() {
        // setBackgroundAudio("audio/GamePlay.mp3");
        setBackgroundImage("backdrops/fishTile_089.png");
    }

    @Override
    public void setupEntities() {
        PlayerHealthText playerHealthText = new PlayerHealthText(new Coordinate2D(5, 5));
        PlayerShieldReadyText playerShieldReadyText = new PlayerShieldReadyText(new Coordinate2D(5, 30));
        PlayerSpeedReadyText playerSpeedReadyText = new PlayerSpeedReadyText(new Coordinate2D(5, 55));
        PlayerSurvivalTimeText playerSurvivalTimeText = new PlayerSurvivalTimeText(new Coordinate2D(5, this.getHeight()-20));
        SalmonPlayer player = new SalmonPlayer(new Coordinate2D(50, getHeight() / 2), playerHealthText,
                playerShieldReadyText, playerSpeedReadyText, playerSurvivalTimeText, upstream);
        this.player = player;
        addEntity(playerHealthText);
        addEntity(playerShieldReadyText);
        addEntity(playerSpeedReadyText);
        addEntity(playerSurvivalTimeText);
        addEntity(player);
    }

    @Override
    public void setupTileMaps() {
        addTileMap(new GameTileMap(upstream));
    }

    @Override
    public void setupEntitySpawners() {
        addEntitySpawner(new HazardSpawner(this.getWidth(), this.getHeight(), upstream));
        addEntitySpawner(new PowerupSpawner(this.getWidth(), this.getHeight(), upstream));
        addEntitySpawner(new TimerUpdater(player, this.upstream));
    }
}
