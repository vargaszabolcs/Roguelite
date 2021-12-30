package game.core;

import framework.FontManager;
import framework.Game;
import game.entities.Player;
import game.scenes.MainMenuScene;
import game.scenes.RoomScene;

public class RogueliteGame extends Game {
    public static final int tileSize = 16;

    private static RogueliteGame instance = null;

    private RogueliteGame() {
        super();

        // Load font into game
        FontManager.getInstance().createFont("Forward", "res/graphics/ui/FFFFORWA.ttf");

        openMainMenu();
    }

    public void openMainMenu() { requestSceneSwitch(new MainMenuScene(this)); }
    public void setFirstLevel() {
        requestSceneSwitch(new RoomScene());
    }
    public void advanceToNextRoom(Player player, int nextRoomLevel) {
        requestSceneSwitch(new RoomScene(player, nextRoomLevel));
    }

    public static RogueliteGame getInstance()
    {
        if (instance == null)
            instance = new RogueliteGame();

        return instance;
    }
}
