package game;

import framework.GameMap;
import framework.Texture2D;

import java.awt.*;

import static game.RogueliteGame.gameScale;

public class RandomRoomMap extends GameMap {
    private int size;

    private int tileSize;

    private final int[][] mapData;

    public RandomRoomMap (int size, int tileSize) {
        this.size = size;
        this.tileSize = (int) (tileSize * gameScale);

        mapData = new int[size][size];

        initTextures();
        generateMapData();
    }

    private void initTextures() {
        mapTextures.put("floor", new Texture2D("res/tiles/floor.png"));
        mapTextures.put("wall", new Texture2D("res/tiles/wall.png"));
    }

    private void generateMapData() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                mapData[y][x] = generateMapTile(x, y);
                System.out.print(mapData[y][x]);
            }
            System.out.println();
        }
    }

    private int generateMapTile (int x, int y) {
        // Top and bottom wall
        if (y == 0 || y == size - 1) {
            return 1;
        }
        // First and last column
        if (x == 0 || x == size - 1) {
            return 1;
        }

        return 0;
    }

    private Texture2D tileCodeToTexture(int tile) {
        switch (tile){
            case 0:
                return mapTextures.get("floor");
            case 1:
                return mapTextures.get("wall");
            default:
                System.out.println("Wrong tile code! Code: " + tile);
                return mapTextures.get("floor");
        }
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                tileCodeToTexture(mapData[y][x]).draw(x * tileSize, y * tileSize, (Graphics2D) graphics);
            }
        }

    }
}
