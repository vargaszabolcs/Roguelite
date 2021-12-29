package framework;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class Scene implements ActionListener, KeyListener {
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<UI> uis;
    protected GameMap gameMap;

    protected boolean[] keys;

    public Scene() {
        gameObjects = new ArrayList<>();
        uis = new ArrayList<>();
        gameMap = null;
        keys = new boolean[65536];
    }

    public void onEnabled() {}
    public void onDisabled() {}

    public void update(double deltaTime) {
        if (gameMap != null)
            gameMap.update(deltaTime);

        uis.stream().filter(Objects::nonNull)
                .forEach(go -> go.update(deltaTime));

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
    public void actionPerformed(ActionEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
