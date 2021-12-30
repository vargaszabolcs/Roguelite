package game.entities;

import framework.AudioManager;
import framework.Scene;
import game.core.Entity;
import game.core.RogueliteGame;
import game.scenes.RoomScene;
import game.ui.LoseUI;
import game.ui.PlayerUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Player extends Entity {
    private int _movementSpeed = 5;
    private int _level = 1;
    private int _exp = 0;
    private int _expCap = 100;
    private boolean _alreadyAttacked = false;
    private boolean _isDead = false;

    public int getLevel() { return _level; }
    public int getExp() { return _exp; }
    public boolean isDead() { return _isDead; }

    public void addExp(int exp) {
        _exp += exp;
        if (_exp >= _expCap) {
            levelUp();
            _exp = Math.max(_exp - _expCap, 0);
        }
    }

    public void levelUp() {
        _level++;
        healthPoints = 100;
        attackPoints++;
        defensePoints++;
        _expCap += 10;
    }

    public Player (Scene parentScene) {
        super("player", 100, 10, 1, parentScene);

        healthPoints = 100;
        attackPoints = 10;

        updatingEnabled = true;
        setTextureAndCollider("res/graphics/player.png");

        ui = new PlayerUI(this);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        // Only handle movement if alive, else watch for ENTER, SPACE and ESC keys to return to the main menu
        if (!_isDead)
            handleMovement(deltaTime);
        else if (keys[KeyEvent.VK_ENTER] || keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ESCAPE])
            RogueliteGame.getInstance().openMainMenu();
    }

    // Handles fighting
    public void fight(Enemy enemy) {
        if (_alreadyAttacked)
            return;

        _alreadyAttacked = true;
        AudioManager.playSound("res/audio/hit.wav");
        takeDamage(enemy);

        // Deal damage to enemy, if enemy dies, give experience to player.
        if (enemy.takeDamage(this)) {
            addExp(new Random().nextInt(50 - 20) + 20);
        }

        // Logging
        // System.out.println(name + "(" + healthPoints + ") attacking " + enemy.name + "(" + enemy.getHealthPoints() + ")");
    }

    private void handleMovement(double deltaTime) {
        float newPosX = posX, newPosY = posY;

        // Move up/down
        if (keys[KeyEvent.VK_W]) {
            newPosY -= _movementSpeed * deltaTime;
        } else if (keys[KeyEvent.VK_S]) {
            newPosY += _movementSpeed * deltaTime;
        }

        // Move left/right
        if (keys[KeyEvent.VK_A]) {
            newPosX -= _movementSpeed * deltaTime;
            // Flip texture based on the last movement direction
            if (getTexture().flipped) {
                getTexture().flipped = false;
            }
        } else if (keys[KeyEvent.VK_D]) {
            newPosX += _movementSpeed * deltaTime;
            if (!getTexture().flipped) {
                getTexture().flipped = true;
            }
        }

        // Check if player is trying to move out of playable area, if yes, don't apply new position coords
        if (((RoomScene) getParentScene()).getPlayableArea().contains(newPosX, newPosY)) {
            // Trigger fight if player collides with an enemy and prevent moving into enemy
            for (Enemy enemy : ((RoomScene) getParentScene()).getEnemies()) {
                if (enemy.isColliding(new Rectangle(Math.round(newPosX), Math.round(newPosY), width, height))) {
                    fight(enemy);
                    return;
                }
            }

            posX = newPosX;
            posY = newPosY;
            _alreadyAttacked = false;
        }
    }

    @Override
    public void die() {
        super.die();
        _isDead = true;

        // Change ui to lose ui
        ui = new LoseUI();
    }
}
