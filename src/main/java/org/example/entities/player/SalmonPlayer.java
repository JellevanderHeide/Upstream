/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  This is the playable character and it's variables. It manages collisions, variable flow
 *  changes in status, inventory and input for the player. 
 * 
*/

package org.example.entities.player;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.example.Upstream;
import org.example.entities.hazardnodamage.*;
import org.example.entities.hazards.*;
import org.example.entities.playerinfo.*;
import org.example.entities.powerups.*;
import org.example.entities.tilemaps.RiverbedTile;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;

public class SalmonPlayer extends DynamicSpriteEntity implements SceneBorderTouchingWatcher, Collider, Collided, KeyListener, Newtonian {
    private PlayerHealthText playerHealthText;
    private PlayerShieldReadyText playerShieldReadyText;
    private PlayerSpeedReadyText playerSpeedReadyText;
    private PlayerSurvivalTimeText playerSurvivalTimeText;
    private Instant timeHit, timeImmovable, timeSpeedBoosted, timeShielded;
    private int healthPoints, speed;
    private Upstream upstream;
    private boolean shielded, speedBoosted;
    private SoundClip hit = new SoundClip("audio/hit.mp3"), powerup = new SoundClip("audio/powerup.mp3"), slowed = new SoundClip("audio/slowed.mp3");

    public SalmonPlayer(Coordinate2D location, PlayerHealthText playerHealthText, PlayerShieldReadyText playerShieldReadyText, PlayerSpeedReadyText playerSpeedReadyText, PlayerSurvivalTimeText playerSurvivalTimeText, Upstream upstream, int speed, int healthPoints) {
        super("sprites/playerfish.png", location, new Size(100, 50), 1, 2);
        this.speed = speed;
        this.timeHit = Instant.now();
        this.timeImmovable = Instant.now();
        this.timeSpeedBoosted = Instant.now();
        this.timeShielded = Instant.now();
        this.healthPoints = healthPoints;
        this.playerHealthText = playerHealthText;
        this.playerShieldReadyText = playerShieldReadyText;
        this.playerSpeedReadyText = playerSpeedReadyText;
        this.playerSurvivalTimeText = playerSurvivalTimeText;
        this.upstream = upstream;
        this.shielded = false;
        playerHealthText.setText(String.valueOf(healthPoints));
        playerSurvivalTimeText.setText(90);
        playerShieldReadyText.setAvailable(false);
        playerSpeedReadyText.setAvailable(false);
        setFrictionConstant(0.1);
        setGravityConstant(0);
    }
    
    /** 
     * Handled collision with other entities and sets in motion whichever behaviour
     * is required for such an event.
     * 
     * @param collidingObjects      The objects the player is currently colliding with.
     */
    @Override
    public void onCollision(List<Collider> collidingObjects) {
        boolean bottomCollision = false;
        for (Collider collider : collidingObjects) {
            if (collider instanceof RiverbedTile) {
                bottomCollision = true;
            }
            if (!shielded) {
                // BigFish collision
                if (collider instanceof BigFish && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                    this.timeHit = Instant.now();
                    hit.play();
                    setSaturation(-1);
                    ((BigFish)collider).doDamage(this);
                // FishHook collision
                } else if (collider instanceof FishHook && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                    this.timeHit = Instant.now();
                    hit.play();
                    setSaturation(-1);
                    ((FishHook)collider).doDamage(this);
                // FishNet collision
                } else if (collider instanceof FishNet && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                    this.upstream.setActiveScene(2);
                    hit.play();
                // Rapids collision
                } else if (collider instanceof Rapids && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000 && Duration.between(this.timeImmovable, Instant.now()).toMillis() >= 3000 ) {
                    slowed.play();
                    this.speed = 0;
                    this.timeImmovable = Instant.now();
                // RiverTrash collision
                } else if (collider instanceof RiverTrash && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                    this.timeHit = Instant.now();
                    hit.play();
                    setSaturation(-1);
                    ((RiverTrash)collider).doDamage(this);
                // Rock collision
                } else if (collider instanceof Rock && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                    this.timeHit = Instant.now();
                    hit.play();
                    setSaturation(-1);
                    ((Rock)collider).doDamage(this);
                // Shield collision
                } else if (collider instanceof ShieldPowerup) {
                    playerShieldReadyText.setAvailable(true);
                    powerup.play();
                // SmallFish collision
                } else if (collider instanceof SmallFish) {
                    ((SmallFish)collider).heal(this);
                    powerup.play();
                    playerSpeedReadyText.setAvailable(true);
                }
            }
        }
        // RiverBed collision
        if (bottomCollision) {
            setAnchorLocationY(getLocationInScene().getY() - 5);
        }
    }

    
    /** 
     * Handles the player not being able to cross the scene boundariees.
     * 
     * @param border    the border of the current scene.
     */
    @Override
    public void notifyBoundaryTouching(SceneBorder border) {
        switch (border) {
            case TOP:
                setAnchorLocationY(getLocationInScene().getY() + 5);
                setMotion(3, Direction.DOWN);
                break;
            case LEFT:
                setAnchorLocationX(getLocationInScene().getX() + 5);
                setMotion(3, Direction.RIGHT);
                break;
            case BOTTOM:
                setAnchorLocationX(getLocationInScene().getY() - 5);
                setMotion(3, Direction.UP);
                break;
            case RIGHT:
                setAnchorLocationX(getLocationInScene().getX() - 5);
                setMotion(3, Direction.LEFT);
                break;
            default:
                break;
        }
    }

    
    /** 
     * Handles key presses. These interactions either move the player character or activate a power-up if
     * it is available.
     * 
     * @param pressedKeys       the keys that are currently being pressed.
     */
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (this.speed != 0) {
            // Move up
            if (pressedKeys.contains(KeyCode.W)) {
                setMotion(speed, Direction.UP);
            // Move left
            } else if (pressedKeys.contains(KeyCode.A)) {
                setCurrentFrameIndex(0);
                setMotion(speed, Direction.LEFT);
            // Move down
            } else if (pressedKeys.contains(KeyCode.S)) {
                setMotion(speed, Direction.DOWN);
            // Move right
            } else if (pressedKeys.contains(KeyCode.D)) {
                setCurrentFrameIndex(1);
                setMotion(speed, Direction.RIGHT);
            // Activate shield
            } else if (pressedKeys.contains(KeyCode.Q)) {
                if (playerShieldReadyText.isReady()) {
                    shielded = true;
                    timeShielded = Instant.now();
                    setSaturation(-1);
                    playerShieldReadyText.setActive();
                }
            // Activate speedboost
            } else if (pressedKeys.contains(KeyCode.E)) {
                if (playerSpeedReadyText.isReady()) {
                    speedBoosted = true;
                    setSaturation(1);
                    timeSpeedBoosted = Instant.now();
                    playerSpeedReadyText.setActive();
                    this.speed = this.speed * 2;
                }
            }
        } else {
            // Reset speed after being immovable for three seconds or more.
            if (Duration.between(this.timeImmovable, Instant.now()).toMillis() >= 3000) {
                setNormalSpeed();
            }
        }
    }

    
    /** 
     * This is essentially a glorified setter for the health variable. However, this method also
     * updates the HUD text for current health, and changes thee scenes if the player ends up dying.
     * 
     * @param healthPoints      the health value that should be set for the player.
     */
    public void setHealth(int healthPoints) {
        if (healthPoints <= 0) {
            this.upstream.setActiveScene(2);
        }
        this.healthPoints = healthPoints;
        this.playerHealthText.setText("Health: " + String.valueOf(healthPoints));
    }

    
    /** 
     * A getter for the healthPoints variable.
     * 
     * @return int      the current healthPoints for this player.
     */
    public int getHealth(){
        return this.healthPoints;
    }

    /** 
     * A partial setter for the shielded boolean. This sets the boolean to false, and makes sure the player model is no longer greyed out. 
     * It also updates the HUD text to notify the player the shield is no longer active.
     */
    public void setUnshielded() {
        playerShieldReadyText.setAvailable(false);
        setSaturation(0);
        this.shielded = false;
    }

    /** 
     * A partial setter for the speed boolean. This sets the boolean to false, and makes sure the player is no longer speed boosted. 
     * It also updates the HUD text to notify the player the speedboost is no longer active.
     */
    public void setNormalSpeed() {
        playerSpeedReadyText.setAvailable(false);
        setSaturation(0);
        this.speedBoosted = false;
        this.speed = 3;
    }

    /** 
     * This is a getter to check whether the player is currently speedboosted.
     * 
     * @return boolean      whether the player is speedboosted.
     */
    public boolean getSpeedBoosted() {
        return this.speedBoosted;
    }

    /** 
     * This is a getter to check whether the player is currently shielded.
     * 
     * @return boolean      whether the player is shielded.
     */
    public boolean getShielded() {
        return this.shielded;
    }

    /** 
     * This is a getter to check how many seconds the player has survived it's current run.
     * 
     * @return PlayerSurvivalTimeText      The object that is used to display the survivaltime variable in the HUD.
     */
    public PlayerSurvivalTimeText getPlayerSurvivalTime() {
        return this.playerSurvivalTimeText;
    }

    /** 
     *  This is a getter to check how long ago the player was speedboosted
     * 
     * @return Instant      the millis time the player was speedboosted at.
     */
    public Instant getPlayerTimeSpeedBoosted() {
        return this.timeSpeedBoosted;
    }
    
    /** 
     *  This is a getter to check how long ago the player was shielded
     * 
     * @return Instant      the millis time the player was shielded at.
     */
    public Instant getPlayerTimeShielded() {
        return this.timeShielded;
    }
    /** 
     *  This is a getter to check how long ago the player was hit by a hazardous entity.
     * 
     * @return Instant      the millis time the player was hit by another hazardous entity.
     */
    public Instant getPlayerTimeHit() {
        return this.timeHit;
    }
}