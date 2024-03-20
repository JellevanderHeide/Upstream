package org.example.entities.tilemaps;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.github.hanyaeger.api.scenes.TileMap;

public class GameTileMap extends TileMap {
    private int tileWidth;
    private int[] lowergGroundTiles;
    private int[] uppergGroundTiles;
    private int[] emptyTiles;
    private List<Integer> upperTiles = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 16, 17, 18, 19, 20);
    private List<Integer> lowerTiles = Arrays.asList(1, 2, 11, 12);

    public GameTileMap(int tileWidth) {
        System.out.println("knolraap");
        this.tileWidth = tileWidth;
        lowergGroundTiles = new int[tileWidth];
        uppergGroundTiles = new int[tileWidth];
        emptyTiles = new int[tileWidth];
    }

    @Override
    public void setupEntities() {
        for (int i = 36, entityID = 1; i < 90; i++) {
            if (i < 46 || (i > 53 && i < 64) || i > 87) {
                addEntity(entityID, RiverbedTile.class, "tiles/fishTile_0" + String.valueOf(i) + ".png");
                entityID++;
            }
        }
    }

    @Override
    public int[][] defineMap() {
        for (int i = 0; i < tileWidth; i++) {
            Random random = new Random();
            lowergGroundTiles[i] = lowerTiles.get(random.nextInt(lowerTiles.size()));
            uppergGroundTiles[i] = upperTiles.get(random.nextInt(upperTiles.size()));
            emptyTiles[i] = 0;
        }

        return new int[][] {
                emptyTiles, emptyTiles, emptyTiles, emptyTiles, emptyTiles, emptyTiles,
                emptyTiles, emptyTiles, emptyTiles, emptyTiles, emptyTiles, emptyTiles,
                uppergGroundTiles,
                lowergGroundTiles
        };
    }
}
