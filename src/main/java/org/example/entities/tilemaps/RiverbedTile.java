/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles creating a tile to use in a TileMap for an Upstream game.
 * 
*/

package org.example.entities.tilemaps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class RiverbedTile extends SpriteEntity implements Collider {
    @SuppressWarnings("exports")
    public RiverbedTile(Coordinate2D location, Size size, String image) {
        super(image, location, size);
    }
}
