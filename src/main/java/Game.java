import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model gry — przechowuje i aktualizuje stan wszystkich obiektów gry.
 * Nie zawiera żadnej logiki renderowania ani obsługi wejścia.
 */
public class Game {

    public static final int BOARD_WIDTH = 480;
    public static final int BOARD_HEIGHT = 640;

    private static final int PLAYER_MARGIN = 10;
    private static final int MAX_BULLETS_ON_SCREEN = 4;
    private static final int SCORE_PER_ENEMY = 100;

    private static final int ENEMY_ROWS = 4;
    private static final int ENEMY_COLS = 8;
    private static final int ENEMY_START_X = 60;
    private static final int ENEMY_START_Y = 50;
    private static final int ENEMY_COL_SPACING = 50;
    private static final int ENEMY_ROW_SPACING = 40;

    private final Player player;
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private int score;

    public Game() {
        this.player = new Player(BOARD_WIDTH / 2 - 15, BOARD_HEIGHT - 60);
        spawnEnemyWave();
    }

    private void spawnEnemyWave() {
        enemies.clear();
        for (int row = 0; row < ENEMY_ROWS; row++) {
            for (int col = 0; col < ENEMY_COLS; col++) {
                int x = ENEMY_START_X + col * ENEMY_COL_SPACING;
                int y = ENEMY_START_Y + row * ENEMY_ROW_SPACING;
                enemies.add(new Enemy(x, y));
            }
        }
    }

    /** Wykonuje jedną klatkę logiki gry. */
    public void update(boolean movePlayerLeft, boolean movePlayerRight) {
        updatePlayer(movePlayerLeft, movePlayerRight);
        updateBullets();
        updateEnemyFormation();

        score += CollisionDetector.detectAndResolve(bullets, enemies, SCORE_PER_ENEMY);

        if (enemies.isEmpty()) {
            spawnEnemyWave();
        }
    }

    private void updatePlayer(boolean moveLeft, boolean moveRight) {
        if (moveLeft) {
            player.moveLeft(PLAYER_MARGIN);
        }
        if (moveRight) {
            player.moveRight(BOARD_WIDTH - player.getWidth() - PLAYER_MARGIN);
        }
    }

    private void updateBullets() {
        bullets.forEach(Bullet::update);
        bullets.removeIf(Bullet::isOffScreen);
    }

    private void updateEnemyFormation() {
        boolean changeDirection = false;
        for (Enemy enemy : enemies) {
            enemy.moveHorizontally();
            if (enemy.hasReachedBound(BOARD_WIDTH)) {
                changeDirection = true;
            }
        }
        if (changeDirection) {
            for (Enemy enemy : enemies) {
                enemy.reverseDirectionAndDropDown();
            }
        }
    }

    /** Próbuje wystrzelić nowy pocisk, jeśli limit na ekranie nie został przekroczony. */
    public void requestShoot() {
        if (bullets.size() < MAX_BULLETS_ON_SCREEN) {
            bullets.add(player.shoot());
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<Bullet> getBullets() {
        return Collections.unmodifiableList(bullets);
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public int getScore() {
        return score;
    }
}
