package game;

import framework.Scene;

public class RoomScene extends Scene {

    @Override
    public void onEnabled() {
        super.onEnabled();
        System.out.println("enabled");

        gameObjects.add(new Player());
    }

    @Override
    public void onDisabled() {
        super.onDisabled();
    }
}
