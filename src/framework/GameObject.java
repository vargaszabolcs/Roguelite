package framework;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameObject implements KeyListener {
    public String name;
    public boolean updatingEnabled;
    public boolean isVisible;
    public float posX, posY;

    private Texture2D _texture;

    protected boolean[] keys;

    public GameObject(String name) {
        this.name = name;
        keys = new boolean[65536];
        init();
    }

    public GameObject() {
        this.name = "new game object";
        init();
    }

    private void init() {
        isVisible = true;
        updatingEnabled = false;
        posX = 0;
        posY = 0;
        _texture = null;
    }

    public Texture2D getTexture() { return _texture; }

    public void setTexture(String path) {
        _texture = new Texture2D(path);
    }

    public void update(double deltaTime) {}
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (_texture != null)
            _texture.draw(Math.round(posX), Math.round(posY), g2d);
    }

    @Override
    public String toString() {
        return "GameObject '" + name + "' => x: " + posX + ", y: " + posY;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
