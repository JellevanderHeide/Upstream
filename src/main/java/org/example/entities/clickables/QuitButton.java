/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles placement and updating a button to quit the application from a win screen
 *  or a death screen.
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

public class QuitButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private Upstream upstream;

    public QuitButton(Upstream upstream, Coordinate2D location) {
        super(location, "Quit");
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
        setFill(Color.GRAY);
        setFont(Font.font("Arial", FontWeight.BOLD, 75));
        this.upstream = upstream;
    }

    /**
     * Closes out the entire application, initiated from either the GameOver or GameWon scenes.
     * 
     * @param button                the mousebutton which was used to press this button.
     * @param coordinate2d          the coordinates of the button.
     */
    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
        upstream.quit();
    }

    /** 
     * Switches the size and colour of this button when the mouse hovers over it.
     */    
    @Override
    public void onMouseEntered() {
        setFill(Color.DARKRED);
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