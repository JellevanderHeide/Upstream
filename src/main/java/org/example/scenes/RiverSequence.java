/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  This is the setup for the scene which is displayed when the player has started the gameplay loop
 *  either via the bootup screen, the death screen or the win screen.
 * 
*/

package org.example.scenes;

import org.example.Upstream;
import org.example.entities.player.SalmonPlayer;
import org.example.entities.playerinfo.*;
import org.example.entities.spawners.HazardSpawner;
import org.example.entities.spawners.PowerupSpawner;
import org.example.entities.tilemaps.GameTileMap;
import org.example.entities.timers.TimerUpdater;
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

    /** 
     * Sets up the basic necessities for the scene, the background and the music.
     */
    @Override
    public void setupScene() {
        setBackgroundAudio("audio/GamePlay.mp3");
        setBackgroundImage("backdrops/sea.png");
    }

    /** 
     * Sets up the entities for this scene. In this scene all hazards, powerups and info text fields are used, as 
     * well as a player character.
     */
    @Override
    public void setupEntities() {
        PlayerHealthText playerHealthText = new PlayerHealthText(new Coordinate2D(5, 5));
        PlayerShieldReadyText playerShieldReadyText = new PlayerShieldReadyText(new Coordinate2D(5, 30));
        PlayerSpeedReadyText playerSpeedReadyText = new PlayerSpeedReadyText(new Coordinate2D(5, 55));
        PlayerSurvivalTimeText playerSurvivalTimeText = new PlayerSurvivalTimeText(new Coordinate2D(5, this.getHeight() - 20));
        SalmonPlayer player = new SalmonPlayer(new Coordinate2D(50, getHeight() / 2), playerHealthText, playerShieldReadyText, playerSpeedReadyText, playerSurvivalTimeText, upstream, 3, 5);
        this.player = player;
        addEntity(playerHealthText);
        addEntity(playerShieldReadyText);
        addEntity(playerSpeedReadyText);
        addEntity(playerSurvivalTimeText);
        addEntity(player);
    }

    /** 
     * Sets up a tilemap for the scene. This utilized only the bottom two rows.
     */
    @Override
    public void setupTileMaps() {
        addTileMap(new GameTileMap(upstream));
    }

    /** 
     * Sets up the spawners for the entities which were set up in setupEntities. 
     * Three spawners are instantiated, Hazards, Powerups and a spawner which exploits
     * the interval functionality for a timer.
     */
    @Override
    public void setupEntitySpawners() {
        addEntitySpawner(new HazardSpawner(this.getWidth(), this.getHeight(), upstream));
        addEntitySpawner(new PowerupSpawner(this.getWidth(), this.getHeight(), upstream));
        addEntitySpawner(new TimerUpdater(player, this.upstream));
    }
}
