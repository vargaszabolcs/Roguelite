package game;

import framework.FontManager;
import framework.Scene;
import framework.Texture2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static framework.Game.*;

public class MainMenuScene extends Scene {
    private final RogueliteGame _gameInstance;
    private final ArrayList<JComponent> _components = new ArrayList<>();

    private final Texture2D _bg;

    public MainMenuScene(RogueliteGame game) {
        _gameInstance = game;
        _bg = new Texture2D("res/ui/menuBG.png");
    }

    @Override
    public void onEnabled() {
        super.onEnabled();
        _gameInstance.setLayout(new GridBagLayout());
        Font font = FontManager.getInstance().getFont("Forward", 12);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(font);
        startGameButton.addActionListener(e -> _gameInstance.setFirstLevel());
        JButton optionsButton = new JButton("Options");
        optionsButton.setFont(font);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(font);
        exitButton.addActionListener(e -> {
            isRunning = false;
            System.exit(0);
        });

        addComponent(startGameButton, 1, 0);
        addComponent(optionsButton, 1, 1);
        addComponent(exitButton, 1, 2);
    }

    private void addComponent(JComponent component, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 200;
        c.ipady = 20;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 10, 0);

        _gameInstance.add(component, c);
        _components.add(component);
        component.setFocusable(false);
    }

    @Override
    public void onDisabled() {
        super.onDisabled();

        for (JComponent component : _components) {
            _gameInstance.remove(component);
        }
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        _bg.draw(0, 0, windowWidth, windowHeight, (Graphics2D) g);
    }
}
