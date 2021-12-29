package game.entities;

import framework.Scene;
import game.Entity;
import game.RoomScene;
import game.ui.PlayerUI;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Player extends Entity {
    private int _movementSpeed = 5;
    private boolean _alreadyAttacked = false;


    public Player (Scene parentScene) {
        super("player", 100, 10, 1, parentScene);

        healthPoints = 100;
        attackPoints = 10;

        updatingEnabled = true;
        setTextureAndCollider("res/player.png");

        ui = new PlayerUI(this);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        handleMovement(deltaTime);
    }

    public void fight(Enemy enemy) {
        if (_alreadyAttacked)
            return;

        _alreadyAttacked = true;
        takeDamage(enemy);
        enemy.takeDamage(this);
        System.out.println(name + "(" + healthPoints + ") attacking " + enemy.name + "(" + enemy.getHealthPoints() + ")");
    }

    private void handleMovement(double deltaTime) {
        float newPosX = posX, newPosY = posY;

        if (keys[KeyEvent.VK_W]) {
            newPosY -= _movementSpeed * deltaTime;
        } else if (keys[KeyEvent.VK_S]) {
            newPosY += _movementSpeed * deltaTime;
        }

        if (keys[KeyEvent.VK_A]) {
            newPosX -= _movementSpeed * deltaTime;
            if (getTexture().flipped) {
                getTexture().flipped = false;
            }
        } else if (keys[KeyEvent.VK_D]) {
            newPosX += _movementSpeed * deltaTime;
            if (!getTexture().flipped) {
                getTexture().flipped = true;
            }
        }

        if (((RoomScene) getParentScene()).getPlayableArea().contains(newPosX, newPosY)) {
            for (Enemy enemy : ((RoomScene) getParentScene()).getEnemies()) {
                if (enemy.isCollidingWithRect(new Rectangle(Math.round(newPosX), Math.round(newPosY), width, height))) {
                    fight(enemy);
                    return;
                }
            }

            posX = newPosX;
            posY = newPosY;
            _alreadyAttacked = false;
        }
    }
}
