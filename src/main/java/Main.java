import javax.swing.SwingUtilities;

import window.GalagaGame;

/**
 * Punkt wejścia aplikacji. Jedyną odpowiedzialnością tej klasy jest
 * uruchomienie okna gry na wątku Swing (Event Dispatch Thread).
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GalagaGame::new);
    }
}
