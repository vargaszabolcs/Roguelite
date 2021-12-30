package framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Game: Core class of the framework.
 * Manages scenes, sets up window.
 * Also contains main game loop.
 */

public class Game extends JPanel implements KeyListener {
    public static final int windowWidth  = 1024;
    public static final int windowHeight  = 1024;
    public static final int gameScale = 2;

    private Scene _queuedScene = null;
    private Scene _currentScene = null;
    static public boolean isRunning = true;

    public Game() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.CYAN);
    }

    public void run() {
        // Delta time
        long lastLoopTime = System.nanoTime();
        final int targetFps = 60;
        final long optimalTime = 1000000000 / targetFps;
        long lastFpsTime = 0;


        while (isRunning) {
            // Delta time
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double deltaTime = updateLength / ((double)optimalTime);

            lastFpsTime += updateLength;
            if(lastFpsTime >= 1000000000){
                lastFpsTime = 0;
            }

            // Update and render
            if (_currentScene != null) {
                _currentScene.update(deltaTime);
                paintImmediately(0, 0, windowWidth, windowHeight);
            }

            // Switch scenes if there are queued scenes waiting
            if (_queuedScene != null) {
                switchScene();
            }
        }
    }

    public Scene getCurrentScene() {
        return _currentScene;
    }

    private void switchScene() {
        if (_currentScene != null)
            this._currentScene.onDisabled();

        _currentScene = _queuedScene;
        _queuedScene.onEnabled();
        _queuedScene = null;
    }

    // Queues up the requested scene. The scene will only be switched at the end of the game loop to avoid errors.
    public void requestSceneSwitch(Scene scene) {
        _queuedScene = scene;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (_currentScene != null)
            _currentScene.render(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        _currentScene.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        _currentScene.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        _currentScene.keyReleased(e);
    }
}
