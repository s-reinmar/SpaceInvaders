package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Game;

/**
 * Ekran menu głównego. Wyłącznie prezentuje opcje i przekazuje zdarzenia
 * kliknięć dalej (poprzez callbacki), bez własnej logiki nawigacji ani gry.
 */
public class MenuPanel extends JPanel {

    public MenuPanel(Runnable onStart, Runnable onInstructions, Runnable onExit) {
        setPreferredSize(new Dimension(Game.BOARD_WIDTH, Game.BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(12, 0, 12, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        add(createTitleLabel(), gbc);

        gbc.gridy = 1;
        add(createButton("Start gry", onStart), gbc);

        gbc.gridy = 2;
        add(createButton("Instrukcje", onInstructions), gbc);

        gbc.gridy = 3;
        add(createButton("Wyjście", onExit), gbc);
    }

    private JLabel createTitleLabel() {
        JLabel title = new JLabel("RETRO SPACE SHOOTER", SwingConstants.CENTER);
        title.setForeground(Color.CYAN);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        return title;
    }

    private JButton createButton(String text, Runnable onClick) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(e -> onClick.run());
        return button;
    }
}
