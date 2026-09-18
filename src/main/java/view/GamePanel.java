package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

    private final Game game;

    public GamePanel(Game game, InputHandler inputHandler) {
        this.game = game;
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
        g2d.setColor(Color.CYAN);
        g2d.fillRect(player.getX(), player.getY() + 10, player.getWidth(), player.getHeight() - 10);
        g2d.fillRect(player.getX() + 12, player.getY(), 6, 10); // Działo
    }

    private void drawBullets(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        for (Bullet bullet : game.getBullets()) {
            g2d.fillRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
    }

    private void drawEnemies(Graphics2D g2d) {
        for (Enemy enemy : game.getEnemies()) {
            g2d.setColor(Color.RED);
            g2d.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
            g2d.setColor(Color.WHITE);
            g2d.fillRect(enemy.getX() + 4, enemy.getY() + 4, 4, 4);
            g2d.fillRect(enemy.getX() + 17, enemy.getY() + 4, 4, 4);
        }
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("SCORE: " + game.getScore(), 20, 30);
    }
}
