package org.example.entities.hazards;

import java.util.List;
import org.example.entities.player.SalmonPlayer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class BigFish extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided {
    private static int damagePoints = 1;
    private static int spriteWidth = 300;
    private static int spriteHeight = 300;

    public BigFish(Coordinate2D location, int speed) {
        super("sprites/bigfish.png", location, new Size(spriteWidth, spriteHeight));
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
        if (border.equals(SceneBorder.TOP)) {
            remove();
        }
    }

    public static int getDamagePoints() {
        return damagePoints;
    }

    public static int getSpriteWidth(){
        return spriteWidth;
    }

    public static int getSpriteHeight(){
        return spriteHeight;
    }
}