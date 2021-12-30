package game.ui;

import framework.FontManager;
import framework.UI;
import game.entities.Player;

import java.awt.*;

public class PlayerUI implements UI {
    private final Player _player;

    public PlayerUI(Player player) {
        _player = player;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int posY = 30;

        // Health
        g2d.setFont(FontManager.getInstance().getFont("Forward", 22));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Health: " + _player.getHealthPoints(), 10, posY);

        // Stats
        g2d.setFont(FontManager.getInstance().getFont("Forward", 18));
        g2d.drawString("ATK: " + _player.getAttackPoints(), 200, posY);
        g2d.drawString("DEF: " + _player.getDefensePoints(), 320, posY);
        g2d.drawString("EXP: " + _player.getExp(), 440, posY);
        g2d.drawString("LVL: " + _player.getLevel(), 560, posY);
    }
}
