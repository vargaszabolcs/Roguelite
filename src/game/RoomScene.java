package game;

import framework.Scene;
import game.entities.Enemy;
import game.entities.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static framework.Game.windowHeight;
import static framework.Game.windowWidth;
import static game.RogueliteGame.gameScale;
import static game.RogueliteGame.tileSize;

public class RoomScene extends Scene {
    public static final int roomSize = 32;


    private Player _player;
    private ArrayList<Enemy> _enemies = new ArrayList<>();
    private Rectangle _playableArea = new Rectangle(
            tileSize * gameScale,
            tileSize * gameScale,
            (roomSize * tileSize * gameScale) - (tileSize * gameScale) * 3,
            (roomSize * tileSize * gameScale) - (tileSize * gameScale) * 3
    );

    public Rectangle getPlayableArea() { return _playableArea; }
    public ArrayList<Enemy> getEnemies() { return _enemies; }

    @Override
    public void onEnabled() {
        super.onEnabled();

        _player = new Player(this);
        _player.posX = (float)(windowWidth / 2);
        _player.posY = (float)(windowHeight / 4);
        gameMap = new RoomMap(32, tileSize);

        addGameObject(_player);

        generateEnemies(3);
    }

    private void generateEnemies(int numberOfEnemies) {
        Random random = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy newEnemy = new Enemy("minotaur", 150, 2, 5, "res/minotaurus.png", this);
            newEnemy.posX = _playableArea.x + random.nextInt((_playableArea.x + _playableArea.width) - _playableArea.x);
            newEnemy.posY = _playableArea.y + random.nextInt((_playableArea.y + _playableArea.height) - _playableArea.y);
            System.out.println(newEnemy);
            _enemies.add(newEnemy);
            addGameObject(newEnemy);
        }
    }
}
