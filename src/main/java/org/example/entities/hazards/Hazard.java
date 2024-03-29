/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  A basic interface for hazards that inflict damage.
 * 
*/

package org.example.entities.hazards;

import org.example.entities.player.SalmonPlayer;

public interface Hazard {
    public int getSpriteHeight();
    public int getSpriteWidth();
    public void doDamage(SalmonPlayer player);
}
