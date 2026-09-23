package window;

import javax.swing.JFrame;

import screen.ScreenCoordinator;
import screen.ScreenType;

/**
 * Główne okno aplikacji. Wyłącznie konfiguruje {@link JFrame} i deleguje
 * całą logikę przełączania ekranów do {@link ScreenCoordinator}.
 */
public class GalagaGame extends JFrame {

    public GalagaGame() {
        setTitle("Retro Space Shooter (Galaga Style)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ScreenCoordinator screenCoordinator = new ScreenCoordinator(this);
        add(screenCoordinator.getContainer());
        screenCoordinator.navigateTo(ScreenType.MENU);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
