package org.example.entities.playerinfo;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerShieldReadyText extends TextEntity {
    private boolean isReady;

    public PlayerShieldReadyText(Coordinate2D location) {
        super(location);

        setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        setFill(Color.DARKMAGENTA);
    }

    public void setAvailable(Boolean shieldReady) {
        if (shieldReady) {
            setText("Shield: READY");
            this.isReady = true;
        } else {
            setText("Shield: NOT READY");
            this.isReady = false;
        }
    }

    public boolean isReady(){
        return this.isReady;
    }
}
