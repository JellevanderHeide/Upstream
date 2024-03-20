package org.example.entities.player;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.example.Upstream;
import org.example.entities.hazards.BigFish;
import org.example.entities.playerinfo.PlayerHealthText;
import org.example.entities.playerinfo.PlayerShieldReadyText;
import org.example.entities.playerinfo.PlayerSpeedReadyText;
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
    private Instant timeHit;
    private int healthPoints;
    private int speed;
    private Upstream upstream;

    public SalmonPlayer(Coordinate2D location, PlayerHealthText playerHealthText,
            PlayerShieldReadyText playerShieldReadyText, PlayerSpeedReadyText playerSpeedReadyText, Upstream upstream) {
        super("sprites/fishTile_077.png", location, new Size(100, 50));
        this.speed = 3;
        this.timeHit = Instant.now();
        this.healthPoints = 5;
        this.playerHealthText = playerHealthText;
        this.playerShieldReadyText = playerShieldReadyText;
        this.playerSpeedReadyText = playerSpeedReadyText;
        this.upstream = upstream;
        setFrictionConstant(0.1);
        setGravityConstant(0);
        playerHealthText.setText("Health: " + String.valueOf(healthPoints));
        playerShieldReadyText.setAvailable(false);
        playerSpeedReadyText.setAvailable(false);
    }

    @Override
    public void onCollision(List<Collider> collidingObjects) {
        boolean bottomCollision = false;

        for (Collider collider : collidingObjects) {
            if (collider instanceof RiverbedTile) {
                bottomCollision = true;
            } else if (collider instanceof BigFish
                    && Duration.between(this.timeHit, Instant.now()).toMillis() >= 3000) {
                this.timeHit = Instant.now();
                this.setHealth(this.healthPoints - 1);
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
    }

    public void setHealth(int healthPoints) {
        this.healthPoints = healthPoints;
        this.playerHealthText.setText("Health: " + String.valueOf(healthPoints));
        if (healthPoints == 0) {
            this.upstream.setActiveScene(2);
        }
    }
}
