package game;

import framework.FontManager;
import framework.Game;
import game.entities.Player;

public class RogueliteGame extends Game {
    public static final int gameScale = 2;
    public static final int tileSize = 16;

    private static RogueliteGame instance = null;

    private RogueliteGame() {
        super();

        FontManager.getInstance().createFont("Forward", "res/ui/FFFFORWA.ttf");

        setCurrentScene(new MainMenuScene(this));
    }

    public void setFirstLevel() {
        setCurrentScene(new RoomScene());
    }

    public void advanceToNextRoom(Player player, int nextRoomLevel) {
        setCurrentScene(new RoomScene(player, nextRoomLevel));
    }

    public static RogueliteGame getInstance()
    {
        if (instance == null)
            instance = new RogueliteGame();

        return instance;
    }
}
