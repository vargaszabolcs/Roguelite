package framework;

import game.RogueliteGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static game.RogueliteGame.gameScale;

public class GameObject {
    public String name;
    public boolean updatingEnabled;
    public boolean isVisible;
    public float posX, posY;

    private Texture2D _texture;

    public GameObject(String name) {
        this.name = name;
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

}
