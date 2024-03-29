/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Places a heart image on screen.
 * 
*/

package org.example.entities.other;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Heart extends SpriteEntity {
    public Heart(Coordinate2D location, Size size) {
        super("backdrops/heart.png", location, size);
    }
}