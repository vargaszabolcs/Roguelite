package game.core;

import framework.GameObject;
import framework.Scene;

/**
 * Entity: A Game Object that has health, attack and defense points.
 * Can also be killed.
 */

public class Entity extends GameObject {
    protected int healthPoints;
    protected int attackPoints;
    protected int defensePoints ;

    public Entity(String name, int healthPoints, int attackPoints, int defensePoints, Scene parentScene) {
        super(name, parentScene);
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }

    public Entity(int healthPoints, int attackPoints, int defensePoints, Scene parentScene) {
        super(parentScene);
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }

    // Deals damage, returns true if dies from attack
    public boolean takeDamage(Entity attackingEntity) {
        if (healthPoints > 0) {
            healthPoints -= Math.max(attackingEntity.attackPoints - defensePoints, 1);
            healthPoints = Math.max(healthPoints, 0);
            if (healthPoints == 0) {
                die();
                return true;
            }
        }
        return false;
    }

    public int getHealthPoints() { return healthPoints; }
    public int getAttackPoints() { return attackPoints; }
    public int getDefensePoints() { return defensePoints; }

    public void die() {}
}
