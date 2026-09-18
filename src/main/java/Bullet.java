import java.awt.Rectangle;

/**
 * Model pocisku wystrzeliwanego przez gracza.
 */
public class Bullet {

    private static final int WIDTH = 4;
    private static final int HEIGHT = 12;
    private static final int SPEED = 10;

    private int x;
    private int y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= SPEED;
    }

    public boolean isOffScreen() {
        return y + HEIGHT < 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
