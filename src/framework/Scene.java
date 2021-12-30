package framework;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Scene: base class of scenes
 * Takes care of Game Object updating and rendering.
 * Also handles maps.
 */

public class Scene implements KeyListener {
    protected ArrayList<GameObject> gameObjects;
    protected GameMap gameMap;

    protected boolean[] keys;

    public Scene() {
        gameObjects = new ArrayList<>();
        gameMap = null;
        keys = new boolean[65536];
    }

    public void onEnabled() {}
    public void onDisabled() {}

    public void update(double deltaTime) {
        if (gameMap != null)
            gameMap.update(deltaTime);

        gameObjects.stream()
                .filter(Objects::nonNull)
                .filter(gameObject -> gameObject.updatingEnabled)
                .forEach(gameObject -> gameObject.update(deltaTime));
    }
    public void render(Graphics g) {
        if (gameMap != null)
            gameMap.draw(g);

        gameObjects.stream()
                .filter(Objects::nonNull)
                .filter(gameObject -> gameObject.isVisible)
                .forEach(gameObject -> gameObject.draw(g));
    }

    public GameMap getMap() { return gameMap; }
    public ArrayList<GameObject> getGameObjects() { return gameObjects; }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        gameObject.keys = keys;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    // Java's input event system is turned into input polling system. Makes implementing actions based on input and delta time easier.
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
