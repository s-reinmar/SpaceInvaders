package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.GameController;
import controller.InputHandler;
import model.Game;
import view.GamePanel;
import view.InstructionsPanel;
import view.MenuPanel;

/**
 * Punkt wejścia aplikacji. Zarządza przełączaniem ekranów (menu, instrukcje, gra)
 * oraz składa (wiring) model, widok i kontroler gry, gdy użytkownik ją uruchomi.
 */
public class GalagaGame extends JFrame {

    private static final String SCREEN_MENU = "menu";
    private static final String SCREEN_INSTRUCTIONS = "instructions";
    private static final String SCREEN_GAME = "game";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel screens = new JPanel(cardLayout);

    private GamePanel gamePanel;
    private GameController gameController;

    public GalagaGame() {
        setTitle("Retro Space Shooter (Galaga Style)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        MenuPanel menuPanel = new MenuPanel(this::startGame, this::showInstructions, this::exitGame);
        InstructionsPanel instructionsPanel = new InstructionsPanel(this::showMenu);

        screens.add(menuPanel, SCREEN_MENU);
        screens.add(instructionsPanel, SCREEN_INSTRUCTIONS);

        add(screens);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        showMenu();
    }

    private void showMenu() {
        if (gameController != null) {
            gameController.stop();
        }
        cardLayout.show(screens, SCREEN_MENU);
    }

    private void showInstructions() {
        cardLayout.show(screens, SCREEN_INSTRUCTIONS);
    }

    /** Tworzy (przy pierwszym uruchomieniu) lub resetuje rozgrywkę i pokazuje ekran gry. */
    private void startGame() {
        Game game = new Game();
        InputHandler inputHandler = new InputHandler(game::requestShoot);

        if (gamePanel != null) {
            screens.remove(gamePanel);
        }

        gamePanel = new GamePanel(game, inputHandler);
        screens.add(gamePanel, SCREEN_GAME);

        gameController = new GameController(game, gamePanel, inputHandler);
        gameController.start();

        cardLayout.show(screens, SCREEN_GAME);
        gamePanel.requestFocusInWindow();
    }

    private void exitGame() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GalagaGame::new);
    }
}
