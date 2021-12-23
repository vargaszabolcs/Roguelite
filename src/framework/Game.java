package framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {
    protected Scene _currentScene = null;
    static public boolean isRunning = true;

    public Game() {
        setPreferredSize(new Dimension(1000, 800));
        setBackground(Color.CYAN);
    }

    public void run() {
        long lastTime = System.nanoTime();


        while (isRunning) {
            long time = System.nanoTime();
            int deltaTime = (int) ((time - lastTime) / 1000000);
            lastTime = time;

            if (_currentScene != null) {
                _currentScene.update(deltaTime);
                repaint();
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
