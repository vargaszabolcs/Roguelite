package game;

import framework.Scene;
import game.entities.Enemy;
import game.entities.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static framework.Game.windowHeight;
import static framework.Game.windowWidth;
import static game.RogueliteGame.*;

public class RoomScene extends Scene {
    public static final int roomSize = 32;


    private final Player _player;
    private final ArrayList<Enemy> _enemies = new ArrayList<>();
    private final Rectangle _playableArea = new Rectangle(
            tileSize * gameScale,
            tileSize * gameScale,
            (roomSize * tileSize * gameScale) - (tileSize * gameScale) * 3,
            (roomSize * tileSize * gameScale) - (tileSize * gameScale) * 3
    );
    private int _enemiesAlive = -1;
    private final int _roomLevel;

    public Rectangle getPlayableArea() { return _playableArea; }
    public ArrayList<Enemy> getEnemies() { return _enemies; }

    public RoomScene(Player player, int roomLevel) {
        this._player = player;
        _player.setParentScene(this);
        _roomLevel = roomLevel;
    }

    public RoomScene() {
        _player = new Player(this);
        _roomLevel = 1;
    }

    @Override
    public void onEnabled() {
        super.onEnabled();

        _player.posX = (float)(windowWidth / 2);
        _player.posY = (float)(windowHeight / 4);

        gameMap = new RoomMap(32, tileSize);

        addGameObject(_player);

        generateEnemies(2 + (new Random().nextInt(_roomLevel) + _roomLevel));
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (_enemiesAlive == 0) {
            getInstance().advanceToNextRoom(_player, _roomLevel + 1);
            System.out.println("wow");
        }
    }

    public void decreaseEnemyCount () {
        _enemiesAlive--;
    }

    private void generateEnemies(int numberOfEnemies) {
        _enemiesAlive = numberOfEnemies;
        Random random = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            int enemyType = random.nextInt(2);
            Enemy newEnemy = switch (enemyType) {
                case 0 -> new Enemy("minotaur", 120, 2 + Math.round((float) (_roomLevel / 2)), 5 + _roomLevel, "res/graphics/minotaurus.png", this);
                case 1 -> new Enemy("hound", 50, 6 + _roomLevel, Math.round((float) (_roomLevel / 2)), "res/graphics/hound.png", this);
                default -> null;
            };

            newEnemy.posX = _playableArea.x + random.nextInt((_playableArea.x + _playableArea.width) - _playableArea.x);
            newEnemy.posY = _playableArea.y + random.nextInt((_playableArea.y + _playableArea.height) - _playableArea.y);
            System.out.println(newEnemy);
            _enemies.add(newEnemy);
            addGameObject(newEnemy);
        }
    }
}
