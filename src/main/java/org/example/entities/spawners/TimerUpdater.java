package org.example.entities.spawners;
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

    @Override
    protected void spawnEntities() {
        this.secondsLeft--;
        player.getPlayerSurvivalTime().setText(this.secondsLeft);
        if(this.secondsLeft <= 0){
            this.upstream.setActiveScene(4);
        }
    }
}
