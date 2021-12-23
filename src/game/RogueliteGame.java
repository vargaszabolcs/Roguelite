package game;

import framework.Game;
import framework.Scene;

public class RogueliteGame extends Game {
    public RogueliteGame() {
        super();

        Scene roomScene = new RoomScene();
        setCurrentScene(roomScene);
    }
}
