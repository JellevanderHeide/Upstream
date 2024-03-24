package org.example.entities.tilemaps;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.example.Upstream;

import com.github.hanyaeger.api.scenes.TileMap;

public class GameTileMap extends TileMap {
    private int[] lowergGroundTiles;
    private int[] uppergGroundTiles;
    private int[] emptyTiles;
    private List<Integer> upperTiles = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 16, 17, 18, 19, 20);
    private List<Integer> lowerTiles = Arrays.asList(1, 2, 11, 12);
    private Upstream upstream;

    public GameTileMap(Upstream upstream) {
        this.upstream = upstream;
        emptyTiles = new int[upstream.getTileWidth()];
        lowergGroundTiles = new int[upstream.getTileWidth()];
        uppergGroundTiles = new int[upstream.getTileWidth()];
        for(int i = 0; i < upstream.getTileWidth(); i++){
            emptyTiles[i] = 0;
        }
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
        int[][] tileMap = new int[upstream.getTileHeight()][upstream.getTileWidth()];
        for (int i = 0; i < upstream.getTileWidth(); i++) {
            Random random = new Random();
            lowergGroundTiles[i] = lowerTiles.get(random.nextInt(lowerTiles.size()));
            uppergGroundTiles[i] = upperTiles.get(random.nextInt(upperTiles.size()));
        }
        for(int j = 0; j < upstream.getTileHeight()-2; j++){
            tileMap[j] = emptyTiles;
        }
        tileMap[upstream.getTileHeight()-2] = uppergGroundTiles;
        tileMap[upstream.getTileHeight()-1] = lowergGroundTiles;
        System.out.println(tileMap.toString());
        return tileMap;
    }
}
