package org.example.entities.playerinfo;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerSpeedReadyText extends TextEntity {
    private boolean isReady;

    public PlayerSpeedReadyText(Coordinate2D location) {
        super(location);

        setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        setFill(Color.DARKMAGENTA);
    }

    public void setAvailable(Boolean shieldReady) {
        if (shieldReady) {
            setText("Speedboost: READY");
            this.isReady = true;
        } else {
            setText("Speedboost: NOT READY");
            this.isReady = false;
        }
    }

    public void setActive() {
        setText("Speedboost: ACTIVATED");
        this.isReady = false;
    }

    public boolean isReady() {
        return this.isReady;
    }
}
