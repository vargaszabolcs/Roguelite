package game;

import framework.Scene;

import static framework.Game.windowHeight;
import static framework.Game.windowWidth;
import static game.RogueliteGame.tileSize;

public class RoomScene extends Scene {
    private Player player;

    @Override
    public void onEnabled() {
        super.onEnabled();

        player = new Player();
        player.posX = (float) (windowWidth / 2);
        player.posY = (float) (windowHeight / 4);
        gameMap = new RandomRoomMap(32, tileSize);

        gameObjects.add(player);
        System.out.println(player.toString());
    }

    @Override
    public void onDisabled() {
        super.onDisabled();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

    }
}
