package org.example.entities.tilemaps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class RiverbedTile extends SpriteEntity {
    public RiverbedTile(Coordinate2D location, Size size, String image) {
        super(image, location, size);
    }

}
