package org.example.entities.playerinfo;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerSurvivalTimeText extends TextEntity {
    public PlayerSurvivalTimeText(Coordinate2D location) {
        super(location);
        setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        setFill(Color.BLACK);
    }

    public void setText(int secondsRemaining) {
        setText("Seconds remaining: " + String.valueOf(secondsRemaining));
    }
}
