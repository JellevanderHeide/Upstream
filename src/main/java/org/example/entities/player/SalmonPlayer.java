package org.example.entities.player;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.example.Upstream;
import org.example.entities.hazards.BigFish;
import org.example.entities.hazards.FishHook;
import org.example.entities.hazards.FishNet;
import org.example.entities.hazards.Rapids;
import org.example.entities.hazards.RiverTrash;
import org.example.entities.hazards.Rock;
import org.example.entities.playerinfo.PlayerHealthText;
import org.example.entities.playerinfo.PlayerShieldReadyText;
import org.example.entities.playerinfo.PlayerSpeedReadyText;
import org.example.entities.playerinfo.PlayerSurvivalTimeText;
import org.example.entities.powerups.ShieldPowerup;
import org.example.entities.powerups.SmallFish;
import org.example.entities.tilemaps.RiverbedTile;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;

public class SalmonPlayer extends DynamicSpriteEntity
        implements SceneBorderTouchingWatcher, Collider, Collided, KeyListener, Newtonian {
    private PlayerHealthText playerHealthText;
    private PlayerShieldReadyText playerShieldReadyText;
    private PlayerSpeedReadyText playerSpeedReadyText;
    private PlayerSurvivalTimeText playerSurvivalTimeText;
    private Instant timeHit;
    private Instant timeImmovable;
    private int healthPoints;
    private int speed;
    private Upstream upstream;

    public SalmonPlayer(Coordinate2D location, PlayerHealthText playerHealthText,
            PlayerShieldReadyText playerShieldReadyText, PlayerSpeedReadyText playerSpeedReadyText, PlayerSurvivalTimeText playerSurvivalTimeText, Upstream upstream) {
        super("sprites/playerfish.png", location, new Size(100, 50));
        this.speed = 3;
        this.timeHit = Instant.now();
        this.timeImmovable = Instant.now();
        this.healthPoints = 5;
        this.playerHealthText = playerHealthText;
        this.playerShieldReadyText = playerShieldReadyText;
        this.playerSpeedReadyText = playerSpeedReadyText;
        this.playerSurvivalTimeText = playerSurvivalTimeText;
        this.upstream = upstream;
        setFrictionConstant(0.1);
        setGravityConstant(0);
        playerHealthText.setText("Health: " + String.valueOf(healthPoints));
        playerSurvivalTimeText.setText(90);
        playerShieldReadyText.setAvailable(false);
        playerSpeedReadyText.setAvailable(false);
    }

    public PlayerSurvivalTimeText getPlayerSurvivalTime(){
        return this.playerSurvivalTimeText;
    }

    @Override
    public void onCollision(List<Collider> collidingObjects) {
        boolean bottomCollision = false;

        for (Collider collider : collidingObjects) {
            if (collider instanceof RiverbedTile) {
                bottomCollision = true;
            } else if (collider instanceof BigFish && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                this.timeHit = Instant.now();
                this.setHealth(this.healthPoints - BigFish.getDamagePoints());                                                                       
            } else if (collider instanceof FishHook && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                this.timeHit = Instant.now();
                this.setHealth(this.healthPoints - FishHook.getDamagePoints());  
            }else if (collider instanceof FishNet && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                this.upstream.setActiveScene(2);
            }else if (collider instanceof Rapids && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {                                                                      
                this.speed = 0;
                this.timeImmovable = Instant.now();
            }else if (collider instanceof RiverTrash && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                this.timeHit = Instant.now();
                this.setHealth(this.healthPoints - RiverTrash.getDamagePoints());  
            }else if (collider instanceof Rock && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                this.timeHit = Instant.now();
                this.setHealth(this.healthPoints - Rock.getDamagePoints());  
            }else if (collider instanceof ShieldPowerup) {
                playerShieldReadyText.setAvailable(true);
            }else if (collider instanceof SmallFish) {
                this.setHealth(this.healthPoints - SmallFish.getDamagePoints());
                playerSpeedReadyText.setAvailable(true);
            }
        }
        if (bottomCollision) {
            setAnchorLocationY(getLocationInScene().getY() - 5);
        }
    }

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

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if(this.speed != 0){
            if (pressedKeys.contains(KeyCode.W)) {
                setMotion(speed, Direction.UP);
            } else if (pressedKeys.contains(KeyCode.A)) {
                setCurrentFrameIndex(0);
                setMotion(speed, Direction.LEFT);
            } else if (pressedKeys.contains(KeyCode.S)) {
                setMotion(speed, Direction.DOWN);
            } else if (pressedKeys.contains(KeyCode.D)) {
                setCurrentFrameIndex(1);
                setMotion(speed, Direction.RIGHT);
            }
        } else {
            if(Duration.between(this.timeImmovable, Instant.now()).toMillis() >= 3000){
                this.speed = 3;
            }
        }
    }

    public void setHealth(int healthPoints) {
        if (healthPoints == 0) {
            this.upstream.setActiveScene(2);
        }
        this.healthPoints = healthPoints;
        this.playerHealthText.setText("Health: " + String.valueOf(healthPoints));
    }
}
