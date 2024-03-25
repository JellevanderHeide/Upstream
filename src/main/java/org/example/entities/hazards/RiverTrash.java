package org.example.entities.hazards;

import java.util.List;
import org.example.entities.player.SalmonPlayer;
import org.example.entities.tilemaps.RiverbedTile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class RiverTrash extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, Collider, Collided, Newtonian{
    private static int damagePoints = 1;
    private static int spriteWidth = 200;
    private static int spriteHeight = 200;

    public RiverTrash(Coordinate2D location, int speed) {
        super("sprites/garbagebag.png", location.subtract(new Coordinate2D(spriteHeight, 0)), new Size(spriteWidth, spriteHeight));
        setFrictionConstant(0.2);
        setGravityConstant(0.35);
    }

    @Override
    public void onCollision(List<Collider> collidingObjects) {
        for (Collider collider : collidingObjects) {
            if (collider instanceof SalmonPlayer) {
                break;
            } else if(collider instanceof RiverbedTile){
                remove();
            }
        }
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border.equals(SceneBorder.BOTTOM)) {
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