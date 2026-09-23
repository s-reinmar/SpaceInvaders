package model;

/**
 * Klasa odpowiedzialna za przetwarzanie logiki gry oraz obsługę kontroli/wejścia.
 */
public class GameLogic {

    private static final int PLAYER_MARGIN = 10;
    private static final int MAX_BULLETS_ON_SCREEN = 4;
    private static final int SCORE_PER_ENEMY = 100;

    private static final int ENEMY_ROWS = 4;
    private static final int ENEMY_COLS = 8;
    private static final int ENEMY_START_X = 60;
    private static final int ENEMY_START_Y = 50;
    private static final int ENEMY_COL_SPACING = 50;
    private static final int ENEMY_ROW_SPACING = 40;

    private final Game game;

    public GameLogic(Game game) {
        this.game = game;
        spawnEnemyWave();
    }

    public void spawnEnemyWave() {
        game.getEnemies().clear();
        for (int row = 0; row < ENEMY_ROWS; row++) {
            for (int col = 0; col < ENEMY_COLS; col++) {
                int x = ENEMY_START_X + col * ENEMY_COL_SPACING;
                int y = ENEMY_START_Y + row * ENEMY_ROW_SPACING;
                game.getEnemies().add(new Enemy(x, y));
            }
        }
    }

    /** Resetuje stan całej gry. */
    public void restart() {
        game.resetScore();
        game.getBullets().clear();
        spawnEnemyWave();
    }

    /** Przetwarza wejście gracza i aktualizuje stan w pojedynczej klatce. */
    public void handleInputAndGameLoop(boolean moveLeft, boolean moveRight) {
        update(moveLeft, moveRight);
    }

    /** Wykonuje jedną klatkę logiki gry. */
    public void update(boolean movePlayerLeft, boolean movePlayerRight) {
        updatePlayer(movePlayerLeft, movePlayerRight);
        updateBullets();
        updateEnemyFormation();

        int pointsGained = CollisionDetector.detectAndResolve(game.getBullets(), game.getEnemies(), SCORE_PER_ENEMY);
        game.addScore(pointsGained);

        if (game.getEnemies().isEmpty()) {
            spawnEnemyWave();
        }
    }

    private void updatePlayer(boolean moveLeft, boolean moveRight) {
        if (moveLeft) {
            game.getPlayer().moveLeft(PLAYER_MARGIN);
        }
        if (moveRight) {
            game.getPlayer().moveRight(Game.BOARD_WIDTH - game.getPlayer().getWidth() - PLAYER_MARGIN);
        }
    }

    private void updateBullets() {
        game.getBullets().forEach(Bullet::update);
        game.getBullets().removeIf(Bullet::isOffScreen);
    }

    private void updateEnemyFormation() {
        boolean changeDirection = false;
        for (Enemy enemy : game.getEnemies()) {
            enemy.moveHorizontally();
            if (enemy.hasReachedBound(Game.BOARD_WIDTH)) {
                changeDirection = true;
            }
        }
        if (changeDirection) {
            for (Enemy enemy : game.getEnemies()) {
                enemy.reverseDirectionAndDropDown();
            }
        }
    }

    /** Obsługa wejścia dla akcji strzału. */
    public void requestShoot() {
        if (game.getBullets().size() < MAX_BULLETS_ON_SCREEN) {
            game.getBullets().add(game.getPlayer().shoot());
        }
    }

    public Game getGame() {
        return game;
    }
}