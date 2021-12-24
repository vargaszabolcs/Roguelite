package game;

import framework.Scene;

public class RoomScene extends Scene {
    private Player player = new Player();

    @Override
    public void onEnabled() {
        super.onEnabled();
        System.out.println("enabled");

        gameMap = new RandomRoomMap(32, 16);

        gameObjects.add(player);
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
