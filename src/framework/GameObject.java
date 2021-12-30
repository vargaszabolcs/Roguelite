package framework;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * GameObject: a renderable object that can be placed in the game and can have logic.
 * Ticking/Updating is tuned off by default for performance reasons
 */

public class GameObject implements KeyListener {
    public String name;
    public boolean updatingEnabled;
    public boolean isVisible;
    public float posX, posY;
    public int width, height;

    private Texture2D _texture;
    private Rectangle _collider;
    private Scene _parentScene;

    protected UI ui;
    protected boolean[] keys;

    public GameObject(String name, Scene parentScene) {
        this.name = name;
        init(parentScene);
    }

    public GameObject(Scene parentScene) {
        this.name = "new game object";
        init(parentScene);
    }

    private void init(Scene parentScene) {
        _parentScene = parentScene;
        isVisible = true;
        updatingEnabled = false;
        posX = 0;
        posY = 0;
        width = 0;
        height = 0;
        _collider = null;
        _texture = null;
        ui = null;
    }

    public Texture2D getTexture() { return _texture; }
    public Rectangle getCollider() { return _collider; }
    public Scene getParentScene() { return _parentScene; }

    public void setParentScene(Scene parentScene) {
        _parentScene = parentScene;
    }

    // Set texture and also set width and height properties of this object. BEWARE: if width and height were already set, they will be overridden!
    public void setTexture(String path) {
        _texture = new Texture2D(path);
        width = _texture.getWidth();
        height = _texture.getHeight();
    }

    // Set collider data
    public void setCollider(Rectangle rectangle) {
        _collider = rectangle;
    }
    public void setCollider(int width, int height) {
        _collider = new Rectangle(Math.round(posX), Math.round(posY), width, height);
    }
    // Collider data need to be updated every tick
    public void updateColliderPosition() {
        if (_collider != null) {
            _collider.x = Math.round(posX);
            _collider.y = Math.round(posY);
        }
    }
    // Set texture and collider data based on set texture
    public void setTextureAndCollider(String path) {
        setTexture(path);
        setCollider(_texture.getWidth(), _texture.getHeight());
    }

    // Test collision between this object and given gameObject/rectangle
    public boolean isColliding(GameObject gameObject) {
        if (_collider == null || gameObject.getCollider() == null) {
            return false;
        }

       return _collider.intersects(gameObject.getCollider());
    }

    public boolean isColliding(Rectangle rectangle) {
        if (_collider == null) {
            return false;
        }

        return _collider.intersects(rectangle);
    }

    public void update(double deltaTime) {
        updateColliderPosition();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (_texture != null)
            _texture.draw(Math.round(posX), Math.round(posY), g2d);
        if (ui != null)
            ui.render(g);
    }

    @Override
    public String toString() {
        return "GO '" + name + "' => x: " + posX + ", y: " + posY;
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
