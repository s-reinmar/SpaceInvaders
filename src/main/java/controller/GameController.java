package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import model.Game;
import view.GamePanel;

/**
 * Kontroler spinający model (Game) z widokiem (GamePanel) oraz wejściem (InputHandler).
 * Odpowiada za pętlę gry napędzaną timerem Swing.
 */
public class GameController implements ActionListener {

    private static final int FRAME_DELAY_MS = 16; // ~60 FPS

    private final Game game;
    private final GamePanel panel;
    private final InputHandler inputHandler;
    private final Timer timer;

    public GameController(Game game, GamePanel panel, InputHandler inputHandler) {
        this.game = game;
        this.panel = panel;
        this.inputHandler = inputHandler;
        this.timer = new Timer(FRAME_DELAY_MS, this);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update(inputHandler.isLeftPressed(), inputHandler.isRightPressed());
        panel.repaint();
    }
}
