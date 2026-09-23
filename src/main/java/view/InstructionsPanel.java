package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Game;

/**
 * Ekran instrukcji. Wyłącznie prezentuje tekst i przycisk powrotu do menu.
 */
public class InstructionsPanel extends JPanel {

    private static final String INSTRUCTIONS_TEXT =
            "Sterowanie:\n\n"
            + "STRZAŁKA W LEWO / W PRAWO - poruszanie statkiem\n"
            + "SPACJA - strzał\n\n"
            + "Cel gry:\n"
            + "Zestrzel wszystkie statki wroga, aby zdobywać punkty.\n"
            + "Po zniszczeniu całej fali pojawia się kolejna.";

    public InstructionsPanel(Runnable onBack) {
        setPreferredSize(new java.awt.Dimension(Game.BOARD_WIDTH, Game.BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JLabel title = SwingComponentFactory.createTitleLabel("INSTRUKCJE", 24);
        add(title, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(INSTRUCTIONS_TEXT);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(textArea, BorderLayout.CENTER);

        JButton backButton = SwingComponentFactory.createButton("Powrót do menu", onBack);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
