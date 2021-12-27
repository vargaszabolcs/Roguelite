package game;

import framework.Game;
import framework.Scene;

public class RogueliteGame extends Game {
    public static final double gameScale = 2;
    public static final int tileSize = 16;

    public RogueliteGame() {
        super();

        Scene roomScene = new RoomScene();
        setCurrentScene(roomScene);
    }
}
