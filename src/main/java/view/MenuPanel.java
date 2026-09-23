package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

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
        add(SwingComponentFactory.createTitleLabel("RETRO SPACE SHOOTER"), gbc);

        gbc.gridy = 1;
        add(SwingComponentFactory.createButton("Start gry", onStart), gbc);

        gbc.gridy = 2;
        add(SwingComponentFactory.createButton("Instrukcje", onInstructions), gbc);

        gbc.gridy = 3;
        add(SwingComponentFactory.createButton("Wyjście", onExit), gbc);
    }
}
