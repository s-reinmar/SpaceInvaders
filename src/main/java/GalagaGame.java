import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GalagaGame extends JFrame {

    public GalagaGame() {
        setTitle("Retro Space Shooter (Galaga Style)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GalagaGame::new);
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int BOARD_WIDTH = 480;
    private final int BOARD_HEIGHT = 640;
    private Timer timer;

    // Gracz
    private int playerX = BOARD_WIDTH / 2 - 15;
    private int playerY = BOARD_HEIGHT - 60;
    private int playerWidth = 30;
    private int playerHeight = 20;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private int score = 0;

    // Listy obiektów
    private ArrayList<Rectangle> bullets;
    private ArrayList<Enemy> enemies;
    private Random random;

    private class Enemy {
        int x, y, width, height;
        double direction = 1; // 1 w prawo, -1 w lewo

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 25;
            this.height = 20;
        }
    }

    public GamePanel() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new TAdapter());

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        random = new Random();

        initEnemies();

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    private void initEnemies() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 8; col++) {
                enemies.add(new Enemy(60 + col * 50, 50 + row * 40));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    private void updateGame() {
        // Ruch gracza
        if (leftPressed && playerX > 10) playerX -= 5;
        if (rightPressed && playerX < BOARD_WIDTH - playerWidth - 10) playerX += 5;

        // Ruch pocisków
        Iterator<Rectangle> bIt = bullets.iterator();
        while (bIt.hasNext()) {
            Rectangle bullet = bIt.next();
            bullet.y -= 10;
            if (bullet.y < 0) {
                bIt.remove();
            }
        }

        // Ruch i logika wrogów
        boolean changeDirection = false;
        for (Enemy enemy : enemies) {
            enemy.x += (int)(2 * enemy.direction);
            if (enemy.x <= 20 || enemy.x >= BOARD_WIDTH - 45) {
                changeDirection = true;
            }
        }

        if (changeDirection) {
            for (Enemy enemy : enemies) {
                enemy.direction *= -1;
                enemy.y += 15; // Zejdź niżej
            }
        }

        // Kolizje: pocisk -> wróg
        Iterator<Rectangle> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Rectangle bullet = bulletIter.next();
            Iterator<Enemy> enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
                Enemy enemy = enemyIter.next();
                Rectangle enemyRect = new Rectangle(enemy.x, enemy.y, enemy.width, enemy.height);
                if (bullet.intersects(enemyRect)) {
                    bulletIter.remove();
                    enemyIter.remove();
                    score += 100;
                    break;
                }
            }
        }

        // Reset fali jeśli wszyscy zginięci
        if (enemies.isEmpty()) {
            initEnemies();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Rysowanie gracza (Niebieski statek)
        g2d.setColor(Color.CYAN);
        g2d.fillRect(playerX, playerY + 10, playerWidth, playerHeight - 10);
        g2d.fillRect(playerX + 12, playerY, 6, 10); // Działo

        // Rysowanie pocisków (Żółte)
        g2d.setColor(Color.YELLOW);
        for (Rectangle bullet : bullets) {
            g2d.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

        // Rysowanie wrogów (Czerwone)
        g2d.setColor(Color.RED);
        for (Enemy enemy : enemies) {
            g2d.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
            // Proste detali wroga
            g2d.setColor(Color.WHITE);
            g2d.fillRect(enemy.x + 4, enemy.y + 4, 4, 4);
            g2d.fillRect(enemy.x + 17, enemy.y + 4, 4, 4);
            g2d.setColor(Color.RED);
        }

        // Interfejs (Wynik)
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("SCORE: " + score, 20, 30);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) leftPressed = true;
            if (key == KeyEvent.VK_RIGHT) rightPressed = true;
            if (key == KeyEvent.VK_SPACE) {
                // Strzał
                if (bullets.size() < 4) { // Limit pocisków na ekranie
                    bullets.add(new Rectangle(playerX + playerWidth / 2 - 2, playerY - 10, 4, 12));
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) leftPressed = false;
            if (key == KeyEvent.VK_RIGHT) rightPressed = false;
        }
    }
}
