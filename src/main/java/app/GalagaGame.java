package app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.GameController;
import controller.InputHandler;
import model.Game;
import view.GamePanel;

/**
 * Punkt wejścia aplikacji. Składa (wiring) model (Game), widok (GamePanel)
 * i kontroler (GameController) w działające okno gry.
 */
public class GalagaGame extends JFrame {

    public GalagaGame() {
        setTitle("Retro Space Shooter (Galaga Style)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Game game = new Game();
        InputHandler inputHandler = new InputHandler(game::requestShoot);
        GamePanel panel = new GamePanel(game, inputHandler);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.requestFocusInWindow();

        GameController controller = new GameController(game, panel, inputHandler);
        controller.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GalagaGame::new);
    }
}
