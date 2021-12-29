package game.ui;

import framework.FontManager;
import framework.UI;
import game.entities.Enemy;

import java.awt.*;

public class EnemyUI implements UI {
    private final Enemy _enemy;

    public EnemyUI(Enemy enemy) {
        _enemy = enemy;
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
        g2d.setFont(FontManager.getInstance().getFont("Forward", 10));
        g2d.setColor(Color.WHITE);
        g2d.drawString("HP: " + _enemy.getHealthPoints(), _enemy.posX - (float)(_enemy.width / 6), _enemy.posY);
    }
}
