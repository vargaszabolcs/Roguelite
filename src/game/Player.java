package game;

import framework.GameObject;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    private int _movementSpeed = 5;


    public Player () {
        super("player");

        updatingEnabled = true;
        setTexture("res/player.png");
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        handleMovement(deltaTime);
    }

    private void handleMovement(double deltaTime) {
        if (keys[KeyEvent.VK_W]) {
            posY -= _movementSpeed * deltaTime;
        } else if (keys[KeyEvent.VK_S]) {
            posY += _movementSpeed * deltaTime;
        }

        if (keys[KeyEvent.VK_A]) {
            posX -= _movementSpeed * deltaTime;
        } else if (keys[KeyEvent.VK_D]) {
            posX += _movementSpeed * deltaTime;
        }
    }
}
