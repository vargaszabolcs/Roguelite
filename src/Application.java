import framework.Game;
import game.core.RogueliteGame;

import javax.swing.*;

public class Application extends JFrame {
    public Application () {
        super("Roguelite");

        Game game = RogueliteGame.getInstance();
        add(game);
        addKeyListener(game);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        game.run();
    }

    public static void main(String[] args) {
        Application app = new Application();
    }
}
