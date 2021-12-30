package framework;

import game.core.RogueliteGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static game.core.RogueliteGame.gameScale;

/**
 * Texture2D: wrapper for BufferedImage with a bit of extra functionality
 */

public class Texture2D {
    private BufferedImage _bufferedImage;

    public boolean flipped = false;

    public Texture2D (final String path) {
        try {
            _bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            RogueliteGame.isRunning = false;
        }
    }

    public BufferedImage getRawImage() {
        return _bufferedImage;
    }

    public int getWidth() { return _bufferedImage.getWidth() * gameScale; }
    public int getHeight() { return _bufferedImage.getHeight() * gameScale; }

    // Simply draw the texture, taking care of scaling and flipping
    public void draw(int posX, int posY, Graphics2D graphics2D) {
        int width = flipped ? getWidth() * -1 : getWidth();
        int height = getHeight();
        posX = flipped ? posX + getWidth() : posX;
        graphics2D.drawImage(_bufferedImage, posX, posY, width, height, null);
    }

    // Draw the image only using the provided parameters, NO scaling
    public void draw(int posX, int posY, int width, int height, Graphics2D graphics2D) {
        graphics2D.drawImage(_bufferedImage, posX, posY, width, height, null);
    }
}
