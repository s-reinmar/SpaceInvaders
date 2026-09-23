package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Kontener danych gry — odpowiada wyłącznie za przechowywanie stanu obiektów.
 */
public class Game {

    public static final int BOARD_WIDTH = 480;
    public static final int BOARD_HEIGHT = 640;

    private final Player player;
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private int score;

    public Game() {
        this.player = new Player(BOARD_WIDTH / 2 - 15, BOARD_HEIGHT - 60);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void resetScore() {
        this.score = 0;
    }
}