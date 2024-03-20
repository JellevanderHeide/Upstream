package org.example.entities.clickables;

import org.example.Upstream;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartButton extends TextEntity implements MouseButtonPressedListener {
    private Upstream upstream;

    public StartButton(Upstream upstream, Coordinate2D location) {
        super(location, "Start");
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
        setFill(Color.RED);
        setFont(Font.font("Arial", FontWeight.BOLD, 75));
        this.upstream = upstream;
    }

    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
        upstream.setActiveScene(1);
    }
}
