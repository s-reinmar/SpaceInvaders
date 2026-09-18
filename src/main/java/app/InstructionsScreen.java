package app;

import javax.swing.JPanel;

import view.InstructionsPanel;

/**
 * Ekran instrukcji. Wiąże {@link InstructionsPanel} z powrotem do menu głównego.
 */
public class InstructionsScreen implements Screen {

    private final InstructionsPanel panel;

    public InstructionsScreen(ScreenNavigator navigator) {
        this.panel = new InstructionsPanel(() -> navigator.navigateTo(ScreenType.MENU));
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
