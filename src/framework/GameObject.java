package framework;

import game.RogueliteGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject {
    public String name;
    public boolean updatingEnabled;
    public boolean isVisible;
    public float posX, posY;

    private BufferedImage _texture;

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

    public BufferedImage getTexture() { return _texture; }

    public void setTexture(String path) {
        try {
            _texture = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            RogueliteGame.isRunning = false;
        }
    }

    public void update(int deltaTime) {}
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (_texture != null)
            g2d.drawImage(_texture, Math.round(posX), Math.round(posY), null);
    }

}
