package org.example.entities.clickables;

import org.example.Upstream;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TryAgainButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private Upstream upstream;

    public TryAgainButton(Upstream upstream, Coordinate2D location) {
        super(location, "Try again!");
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
        setFill(Color.GRAY);
        setFont(Font.font("Arial", FontWeight.BOLD, 75));
        this.upstream = upstream;
    }

    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
        upstream.setActiveScene(1);
    }

    @Override
    public void onMouseEntered() {
        setFill(Color.DARKGREEN);
        setFont(Font.font("Arial", FontWeight.BOLD, 85));
    }

    @Override
    public void onMouseExited() {
        setFill(Color.GRAY);
        setFont(Font.font("Arial", FontWeight.BOLD, 75));
    }   
}