package game.ui;

import framework.FontManager;
import framework.UI;
import game.effects.FadeEffectApplicable;
import game.effects.FadeInEffect;

import java.awt.*;

import static framework.Game.windowHeight;
import static framework.Game.windowWidth;

public class LoseUI implements UI, FadeEffectApplicable {
    private float _alpha = 0.0f;
    private float _fadeInSpeed = 0.00000003f;

    public LoseUI() {
        Thread effect = new Thread(new FadeInEffect(this));
        effect.start();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = ((Graphics2D) g);

        g2d.setComposite(AlphaComposite.SrcOver.derive(_alpha));

        g2d.setFont(FontManager.getInstance().getFont("Forward", 100));
        g2d.setColor(Color.RED);
        g2d.drawString("YOU DIED", windowWidth / 5, windowHeight / 2);

        g2d.setComposite(AlphaComposite.SrcOver.derive(1f));
    }

    @Override
    public float getAlpha() {
        return _alpha;
    }

    @Override
    public float getFadingSpeed() {
        return _fadeInSpeed;
    }

    @Override
    public void setAlpha(float newAlpha) {
        _alpha = newAlpha;
        _alpha = Math.min(_alpha, 1f);
    }
}
