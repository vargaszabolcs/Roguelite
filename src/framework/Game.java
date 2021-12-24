package framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {
    private static final int windowWidth  = 1024;
    private static final int windowHeight  = 1024;

    protected Scene _currentScene = null;
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
        }
    }

    public Scene getCurrentScene() {
        return _currentScene;
    }

    public void setCurrentScene(Scene scene) {
        if (_currentScene != null)
            this._currentScene.onDisabled();

        _currentScene = scene;
        scene.onEnabled();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (_currentScene != null)
            _currentScene.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _currentScene.actionPerformed(e);
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
