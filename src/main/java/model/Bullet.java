package model;

import java.awt.Rectangle;

/**
 * Model pocisku wystrzeliwanego przez gracza.
 */
public class Bullet {

    private static final int BULLET_WIDTH = 4; // Szerokość pocisku
    private static final int BULLET_HEIGHT = 12; // Wysokość pocisku
    private static final int BULLET_SPEED = 10; // Prędkość pocisku (ilość pikseli, o które pocisk przesuwa się w górę w każdej aktualizacji)

    private int x;
    private int y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= BULLET_SPEED;
    }

    public boolean isOffScreen() {
        return y + BULLET_HEIGHT < 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return BULLET_WIDTH;
    }

    public int getHeight() {
        return BULLET_HEIGHT;
    }
}
