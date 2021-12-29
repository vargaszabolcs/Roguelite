package game;

import framework.Scene;
import game.entities.Enemy;
import game.entities.Player;

import java.awt.*;
import java.util.ArrayList;

import static framework.Game.windowHeight;
import static framework.Game.windowWidth;
import static game.RogueliteGame.tileSize;

public class RoomScene extends Scene {
    public static final int roomSize = 32;

    private Player _player;
    private ArrayList<Enemy> _enemies = new ArrayList<>();

    public ArrayList<Enemy> getEnemies() { return _enemies; }

    @Override
    public void onEnabled() {
        super.onEnabled();

        _player = new Player(this);
        _player.posX = (float)(windowWidth / 2);
        _player.posY = (float)(windowHeight / 4);
        gameMap = new RandomRoomMap(32, tileSize);

        addGameObject(_player);
        System.out.println(_player.toString());

        Enemy newEnemy = new Enemy("minotaur", 150, 2, 5, "res/minotaurus.png", this);
        newEnemy.posX = 60;
        newEnemy.posY = 60;
        _enemies.add(newEnemy);
        addGameObject(newEnemy);
    }

    @Override
    public void onDisabled() {
        super.onDisabled();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

    }

    @Override
    public void render(Graphics g) {
        super.render(g);

    }
}
