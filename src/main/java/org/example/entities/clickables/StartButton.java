/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles placement and updating a button to start an attempt
 *  from the boot screen, death screen or win screen.
 * 
*/

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

public class StartButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private Upstream upstream;

    public StartButton(Upstream upstream, Coordinate2D location, String text) {
        super(location, text);
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
        setFill(Color.GRAY);
        setFont(Font.font("Arial", FontWeight.BOLD, 75));
        this.upstream = upstream;
    }

    /**
     * Switches scene to the start of the game, initiated from either StartScreen, GameOver or GameWon scenes.
     * 
     * @param button                the mousebutton which was used to press this button.
     * @param coordinate2d          the coordinates of the button.
     */
    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
        upstream.setActiveScene(1);
    }

    /** 
     * Switches the size and colour of this button when the mouse hovers over it.
     */
    @Override
    public void onMouseEntered() {
        setFill(Color.DARKGREEN);
        setFont(Font.font("Arial", FontWeight.BOLD, 78));
    }

    /** 
     * Switches the size and colour of this button when the mouse leaves it's location.
     */
    @Override
    public void onMouseExited() {
        setFill(Color.GRAY);
        setFont(Font.font("Arial", FontWeight.BOLD, 75));
    }   
}
