import java.awt.Rectangle;

/**
 * Model pojedynczego przeciwnika poruszającego się w formacji.
 */
public class Enemy {

    private static final int WIDTH = 25;
    private static final int HEIGHT = 20;
    private static final int SPEED = 2;
    private static final int DROP_STEP = 15;
    private static final int LEFT_BOUND = 20;
    private static final int RIGHT_MARGIN = 45;

    private int x;
    private int y;
    private int direction = 1; // 1 = w prawo, -1 = w lewo

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveHorizontally() {
        x += SPEED * direction;
    }

    /** Odwraca kierunek ruchu formacji i przesuwa przeciwnika niżej. */
    public void reverseDirectionAndDropDown() {
        direction *= -1;
        y += DROP_STEP;
    }

    public boolean hasReachedBound(int boardWidth) {
        return x <= LEFT_BOUND || x >= boardWidth - RIGHT_MARGIN;
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
