package framework;

import game.RogueliteGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static game.RogueliteGame.gameScale;

public class Texture2D {
    private BufferedImage bufferedImage;

    public Texture2D (String path) {
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            RogueliteGame.isRunning = false;
        }
    }

    public BufferedImage getRawImage() {
        return bufferedImage;
    }

    public void draw(int posX, int posY, Graphics2D graphics2D) {
        graphics2D.drawImage(bufferedImage, posX, posY, (int) (bufferedImage.getWidth() * gameScale), (int) (bufferedImage.getHeight() * gameScale), null);
    }
}
