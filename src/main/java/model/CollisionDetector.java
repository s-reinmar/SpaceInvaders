package model;

import java.util.Iterator;
import java.util.List;

/**
 * Wykrywa i rozwiązuje kolizje pomiędzy pociskami a przeciwnikami.
 */
public class CollisionDetector {

    // Stałe określające granice/marginesy strefy kolizji pocisku
    private static final int BULLET_BOUND_OFFSET_X = 0;
    private static final int BULLET_BOUND_OFFSET_Y = 0;

    private CollisionDetector() {
        // klasa narzędziowa
    }

    /**
     * Usuwa trafione pociski i przeciwników, zwracając liczbę zdobytych punktów.
     */
    public static int detectAndResolve(List<Bullet> bullets, List<Enemy> enemies, int scorePerHit) {
        int scoreGained = 0;

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();

                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bulletIterator.remove();
                    enemyIterator.remove();
                    scoreGained += scorePerHit;
                    break;
                }
            }
        }

        return scoreGained;
    }
}