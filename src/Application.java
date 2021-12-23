import framework.Game;
import game.RogueliteGame;

import javax.swing.*;

public class Application extends JFrame {
    public Application () {
        super("Roguelite");

        Game game = new RogueliteGame();
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
