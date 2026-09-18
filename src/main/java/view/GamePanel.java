package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import controller.InputHandler;
import model.Bullet;
import model.Enemy;
import model.Game;
import model.Player;

/**
 * Widok gry — wyłącznie rysuje aktualny stan modelu (Game).
 * Nie zawiera logiki gry ani obsługi wejścia.
 */
public class GamePanel extends JPanel {

    private static final String PLAYER_SPRITE = "galaga_ship.png";
    private static final String ENEMY_SPRITE = "galaga_enemy_1.png";

    private final Game game;
    private final BufferedImage playerSprite;
    private final BufferedImage enemySprite;

    public GamePanel(Game game, InputHandler inputHandler) {
        this.game = game;
        this.playerSprite = SpriteLoader.load(PLAYER_SPRITE);
        this.enemySprite = SpriteLoader.load(ENEMY_SPRITE);
        setPreferredSize(new Dimension(Game.BOARD_WIDTH, Game.BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(inputHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame((Graphics2D) g);
    }

    private void drawGame(Graphics2D g2d) {
        drawPlayer(g2d);
        drawBullets(g2d);
        drawEnemies(g2d);
        drawScore(g2d);
    }

    private void drawPlayer(Graphics2D g2d) {
        Player player = game.getPlayer();
        g2d.drawImage(playerSprite, player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    }

    private void drawBullets(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        for (Bullet bullet : game.getBullets()) {
            g2d.fillRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
    }

    private void drawEnemies(Graphics2D g2d) {
        for (Enemy enemy : game.getEnemies()) {
            g2d.drawImage(enemySprite, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), null);
        }
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("SCORE: " + game.getScore(), 20, 30);
    }
}
