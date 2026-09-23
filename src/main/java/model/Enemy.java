package model;

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
    private Direction direction = Direction.RIGHT;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveHorizontally() {
        x += SPEED * direction.getStep();
    }

    /** Odwraca kierunek ruchu formacji i przesuwa przeciwnika niżej. */
    public void reverseDirectionAndDropDown() {
        direction = direction.opposite();
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

    /** Kierunek poziomego ruchu przeciwnika w formacji. */
    private enum Direction {
        LEFT(-1),
        RIGHT(1);

        private final int step;

        Direction(int step) {
            this.step = step;
        }

        /** Zwraca przesunięcie na osi X odpowiadające temu kierunkowi. */
        int getStep() {
            return step;
        }

        /** Zwraca kierunek przeciwny do bieżącego. */
        Direction opposite() {
            return this == LEFT ? RIGHT : LEFT;
        }
    }
}
