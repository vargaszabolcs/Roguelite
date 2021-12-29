package game.ui;

import framework.UI;
import game.entities.Player;

import java.awt.*;

public class PlayerUI implements UI {
    private Player _player;

    public PlayerUI(Player player) {
        _player = player;
    }

    @Override
    public void update(double deltaTime) {}

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Health
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Health: " + _player.getHealthPoints(), 10, 25);

        // Stats
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        g2d.drawString("ATK: " + _player.getAttackPoints(), 200, 25);
        g2d.drawString("DEF: " + _player.getDefensePoints(), 320, 25);
        g2d.drawString("EXP: " + _player.getExp(), 440, 25);
        g2d.drawString("LVL: " + _player.getLevel(), 560, 25);
    }
}
