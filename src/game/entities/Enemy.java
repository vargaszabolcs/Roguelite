package game.entities;

import framework.Scene;
import game.Entity;
import game.RoomScene;
import game.ui.EnemyUI;

import java.util.Random;

public class Enemy extends Entity {
    public Enemy(String name, int healthPoints, int attackPoints, int defensePoints, String texture, Scene parentScene) {
        super(name, healthPoints, attackPoints, defensePoints, parentScene);
        setTextureAndCollider(texture);
        updatingEnabled = true;

        ui = new EnemyUI(this);
        getTexture().flipped = new Random().nextBoolean();
    }

    @Override
    public void die() {
        super.die();

        isVisible = false;
        updatingEnabled = false;
        setCollider(null);
        ((RoomScene) getParentScene()).decreaseEnemyCount();
    }
}
