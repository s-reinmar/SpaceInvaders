/**
 * Model statku gracza.
 */
public class Player {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int SPEED = 5;

    private int x;
    private final int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft(int minX) {
        if (x > minX) {
            x -= SPEED;
        }
    }

    public void moveRight(int maxX) {
        if (x < maxX) {
            x += SPEED;
        }
    }

    /** Tworzy nowy pocisk wystrzelony z działa gracza. */
    public Bullet shoot() {
        return new Bullet(x + WIDTH / 2 - 2, y - 10);
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
