package org.example.entities.other;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
public class Heart extends DynamicSpriteEntity {

    public Heart(Coordinate2D location, Size size) {
        super("backdrops/heart.png", location, size);
    }
}