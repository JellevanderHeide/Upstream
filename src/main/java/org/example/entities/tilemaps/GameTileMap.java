/*
 * Author:          Jelle van der Heide - 1604408
 * Last updated:    28-3-2024
 * Version:         1.0
 * 
 * Global function description:
 *  Handles crafting a tilemap for the game. Tiles are randomly
 *  selected from a list of available tiles.
 * 
*/

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
    private List<Integer> upperTiles = Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
    private List<Integer> lowerTiles = Arrays.asList(1, 2, 3, 4);
    private Upstream upstream;

    public GameTileMap(Upstream upstream) {
        this.upstream = upstream;
        this.emptyTiles = new int[upstream.getTileWidth()];
        this.lowergGroundTiles = new int[upstream.getTileWidth()];
        this.uppergGroundTiles = new int[upstream.getTileWidth()];
        for(int i = 0; i < upstream.getTileWidth(); i++){
            emptyTiles[i] = 0;
        }
    }

    /** 
     * Sets up entities that are to be used in this tilemap.
     */
    @Override
    public void setupEntities() {
        for (int i = 1; i < 22; i++) {
            addEntity(i, RiverbedTile.class, "tiles/fishTile_" + String.valueOf(i) + ".png");
        }
    }

    /** 
     * Defines a tilemap by randomly selecting tiles for the bottom two rows. The height and 
     * width of this map define how many rows and columns the map gets to place.
     * 
     * @return int[][]      The generated tilemap for the Upstream scene.
     */
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
        return tileMap;
    }
}
