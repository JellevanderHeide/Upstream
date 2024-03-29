/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  This is the main class for the entire Upstream application.
 *  here all scenes are set up, and the game is launched from here as well.
 *  additionally, here we can find the settings for window size, and set the size of the tiles which are 
 *  to be used in the tilemaps down the road. 
 * 
 * !_! While you're here, be sure to read the README for additional information on how to play, !_!
 * !_! where to find the original sprites and sound clips, and a list of known bugs.            !_!   
 * 
*/

package org.example;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import org.example.scenes.GameOver;
import org.example.scenes.GameWon;
import org.example.scenes.RiverSequence;
import org.example.scenes.StartScreen;

public class Upstream extends YaegerGame {
    private final int GAMEWIDTH = 1000;
    private final int GAMEHEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * Sets up the game within a window conform the given dimensions, as well as the initial title for said window.
     */
    @Override
    public void setupGame() {
        setGameTitle("Upstream");
        setSize(new Size(GAMEWIDTH, GAMEHEIGHT));

    }

    /** 
     * Sets up the scenes which are to be used by the application.
     */
    @Override
    public void setupScenes() {
        addScene(0, new StartScreen(this));
        addScene(1, new RiverSequence(this));
        addScene(2, new GameOver(this));
        addScene(3, new GameWon(this));
    }

    /** 
     * A simple getter to calculate which tileheight to use, calculated by dividing gameheight by 50.
     */
    public int getTileHeight() {
        return this.GAMEHEIGHT / 50;
    }

    /** 
     * A simple getter to calculate which tileheight to use, calculated by dividing gameheight by 50.
     */
    public int getTileWidth() {
        return this.GAMEWIDTH / 50;
    }
}