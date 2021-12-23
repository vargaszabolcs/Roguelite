package game;

import framework.GameObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    Player () {
        super("player");

        setTexture("res/floor.png");
    }
}
