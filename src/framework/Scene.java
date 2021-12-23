package framework;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Scene implements ActionListener, KeyListener {
    protected ArrayList<GameObject> gameObjects;
    protected Map map;

    public Scene() {
        gameObjects = new ArrayList<>();
        map = null;
    }

    public void onEnabled() {}
    public void onDisabled() {}

    public void update(int deltaTime) {
        if (map != null)
            map.update(deltaTime);

        gameObjects.stream()
                .filter(gameObject -> gameObject.updatingEnabled)
                .forEach(gameObject -> gameObject.update(deltaTime));
    }
    public void render(Graphics g) {
        if (map != null)
            map.draw(g);

        gameObjects.stream()
                .filter(gameObject -> gameObject.isVisible)
                .forEach(gameObject -> gameObject.draw(g));
    }

    public Map getMap() { return map; }
    public ArrayList<GameObject> getGameObjects() { return gameObjects; }

    @Override
    public void actionPerformed(ActionEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
