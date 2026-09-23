package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Fabryka wspólnych, stylizowanych komponentów Swing używanych przez ekrany
 * menu i instrukcji (przyciski, tytuły), aby uniknąć duplikacji kodu.
 */
public final class SwingComponentFactory {

    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 26);

    private SwingComponentFactory() {
        // klasa narzędziowa
    }

    /** Tworzy stylizowany przycisk wykonujący podaną akcję po kliknięciu. */
    public static JButton createButton(String text, Runnable onClick) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.addActionListener(e -> onClick.run());
        return button;
    }

    /** Tworzy wyśrodkowaną, cyjanową etykietę tytułową o podanym tekście i rozmiarze czcionki. */
    public static JLabel createTitleLabel(String text, int fontSize) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setForeground(Color.CYAN);
        title.setFont(new Font("Arial", Font.BOLD, fontSize));
        return title;
    }

    /** Tworzy wyśrodkowaną, cyjanową etykietę tytułową z domyślnym rozmiarem czcionki. */
    public static JLabel createTitleLabel(String text) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setForeground(Color.CYAN);
        title.setFont(TITLE_FONT);
        return title;
    }
}
