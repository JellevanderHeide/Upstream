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

public class FishNet extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided {
    private static int spriteWidth = 100;
    private static int spriteHeight = 300;

    public FishNet(Coordinate2D location, int speed) {
        super("sprites/fishnet.png", location, new Size(spriteWidth, spriteHeight));
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

    public static int getSpriteWidth(){
        return spriteWidth;
    }

    public static int getSpriteHeight(){
        return spriteHeight;
    }
}