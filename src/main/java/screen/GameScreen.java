package screen;

import javax.swing.JPanel;

import controller.GameController;
import controller.InputHandler;
import model.Game;
import model.GameLogic;
import view.GamePanel;

/**
 * Ekran rozgrywki. Tworzy nową, niezależną instancję gry (model + logika + widok + kontroler)
 * i zarządza jej cyklem życia.
 */
public class GameScreen implements Screen {

    private final GamePanel panel;
    private final GameController controller;

    public GameScreen() {
        Game game = new Game();
        GameLogic gameLogic = new GameLogic(game);
        InputHandler inputHandler = new InputHandler(gameLogic::requestShoot);

        this.panel = new GamePanel(game);
        this.panel.addKeyListener(inputHandler);
        this.controller = new GameController(gameLogic, panel, inputHandler);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void onEnter() {
        controller.start();
        panel.requestFocusInWindow();
    }

    @Override
    public void onExit() {
        controller.stop();
    }
}