package framework;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * GameMap: base class for maps
 * Maps can have update and render logic
 */

public class GameMap {
    protected Map<String, Texture2D> mapTextures;

    public GameMap() {
        mapTextures = new HashMap<>();
    }

    public void update(double deltaTime) {}
    public void draw(Graphics graphics) {}
}
