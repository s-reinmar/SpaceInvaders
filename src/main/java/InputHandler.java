import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Odpowiada wyłącznie za odczyt stanu klawiatury.
 * Nie zawiera żadnej logiki gry.
 */
public class InputHandler extends KeyAdapter {

    private boolean leftPressed;
    private boolean rightPressed;
    private final Runnable onShootRequested;

    public InputHandler(Runnable onShootRequested) {
        this.onShootRequested = onShootRequested;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            onShootRequested.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }
}
