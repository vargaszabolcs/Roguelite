package framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {
    public static final int windowWidth  = 1024;
    public static final int windowHeight  = 1024;

    private Scene _queuedScene = null;
    private Scene _currentScene = null;
    static public boolean isRunning = true;

    public Game() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.CYAN);
    }

    public void run() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long lastFpsTime = 0;


        while (isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double deltaTime = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            if(lastFpsTime >= 1000000000){
                lastFpsTime = 0;
            }

            if (_currentScene != null) {
                _currentScene.update(deltaTime);
                paintImmediately(0, 0, windowWidth, windowHeight);
            }
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

    public void setCurrentScene(Scene scene) {
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
