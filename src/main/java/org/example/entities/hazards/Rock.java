package org.example.entities.hazards;

import java.util.List;
import java.util.Random;
import org.example.entities.player.SalmonPlayer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class Rock extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided {
    private static int damagePoints = 1;

    public Rock(Coordinate2D location, int speed) {
        super("sprites/rock" + String.valueOf(new Random().nextInt(1, 7)) + ".png", location.subtract(new Coordinate2D(0, 100)) , new Size(200, 200));
        setMotion(speed, Direction.LEFT);
    }

    @Override
    public void onCollision(List<Collider> collidingObjects) {
        for (Collider collider : collidingObjects) {
            if (collider instanceof SalmonPlayer) {
                break;
            }
        }
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border.equals(SceneBorder.LEFT)) {
            remove();
        }
    }

    public static int getDamagePoints() {
        return damagePoints;
    }



}