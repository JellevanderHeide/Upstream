/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles the time-sensitive deactivation of timed events by exploiting the interval 
 *  of EntitySpawner objects.
 * 
*/

package org.example.entities.timers;

import java.time.Duration;
import java.time.Instant;
import org.example.Upstream;
import org.example.entities.player.SalmonPlayer;
import com.github.hanyaeger.api.entities.EntitySpawner;

public class TimerUpdater extends EntitySpawner {
    private int secondsLeft;
    private SalmonPlayer player;
    private Upstream upstream;

    public TimerUpdater(SalmonPlayer player, Upstream upstream) {
        super(1000);
        this.secondsLeft = 90;
        this.player = player;
        this.upstream = upstream;
    }

    /** 
     * By being called every set interval, this method manages to be a robust way
     * of keeping track of time differences to make sure specific events are 
     * deactivated after a set amount of time.
     */
    @Override
    protected void spawnEntities() {
        this.secondsLeft--;
        player.getPlayerSurvivalTime().setText(this.secondsLeft);
        if (this.secondsLeft <= 0) {
            this.secondsLeft = 90;
            this.upstream.setActiveScene(3);
        }
        if (Duration.between(player.getPlayerTimeShielded(), Instant.now()).toMillis() >= 5000 && player.getShielded()) {
            player.setUnshielded();
        }
        if (Duration.between(player.getPlayerTimeSpeedBoosted(), Instant.now()).toMillis() >= 5000 && player.getSpeedBoosted()) {
            player.setNormalSpeed();
        }
        if (Duration.between(player.getPlayerTimeHit(), Instant.now()).toMillis() >= 3000 && !player.getShielded()) {
            player.setSaturation(0);
        }
    }
}
