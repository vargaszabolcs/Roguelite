package game;

import framework.GameObject;
import framework.Scene;

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

    public void takeDamage(Entity attackingEntity) {
        if (healthPoints > 0) {
            healthPoints -= Math.max(attackingEntity.attackPoints - defensePoints, 1);
            healthPoints = Math.max(healthPoints, 0);
            if (healthPoints == 0) {
                die();
            }
        }
    }

    public int getHealthPoints() { return healthPoints; }
    public int getAttackPoints() { return attackPoints; }
    public int getDefensePoints() { return defensePoints; }

    public void die() {}
}
