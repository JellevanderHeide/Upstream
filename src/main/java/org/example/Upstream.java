package org.example;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import org.example.scenes.GameOver;
import org.example.scenes.GameWon;
import org.example.scenes.RiverSequence;
import org.example.scenes.StartScreen;

public class Upstream extends YaegerGame {
    private final int gameWidth = 1000;
    private final int gameHeight = 800;
    private final int widthTiles = gameWidth / 50;
    private final int heightTiles = gameHeight / 50;

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

    public int getTileHeight() {
        return this.heightTiles;
    }

    public int getTileWidth() {
        return this.widthTiles;
    }
}

// bestanden opruimen
// entiteitgroottes conform hun afbeeldingomvang
// commentaar in alle klasses
// in alle klasses auteur aangeven studentnummer datum en versie
// in upstream aangeven wat de bron is van de png's
// readme met game uitleg

// Ik kan er voor zorgen dat de blokken van de tiles aansluiten
// Ik kan er voor zorgen dat de tiles bewegen
// ik kan entities een kleinere hitbox geven om eerlijker te zijn
// ik kan de lijst met tiles sorteren en opruimen om efficienter te kunnen
// werken
// ik kan zoeken of ik niet onnodig objecten meegeef aan subobjecten
// ik kan er voor zorgen dat wanneer de vis de andere kant op zwemt, zijn sprite
// omdraait
// entiteiten blokkeren de tekst over de status van de speler
// entiteiten moeten de juiste sprites krijgen
