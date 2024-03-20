package org.example;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;

public class Upstream extends YaegerGame {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Upstream");
        setSize(new Size(1000, 800));

    }

    @Override
    public void setupScenes() {
        addScene(0, new StartScreen(this));
        addScene(1, new RiverSequence(this));
        addScene(2, new GameOver(this));
        addScene(3, new GameWon(this));
    }
}
