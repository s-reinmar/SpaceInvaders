package screen;

import javax.swing.JPanel;

import view.MenuPanel;

/**
 * Ekran menu głównego. Wiąże {@link MenuPanel} z nawigacją do pozostałych ekranów.
 */
public class MenuScreen implements Screen {

    private final MenuPanel panel;

    public MenuScreen(ScreenNavigator navigator) {
        this.panel = new MenuPanel(
                () -> navigator.navigateTo(ScreenType.GAME),
                () -> navigator.navigateTo(ScreenType.INSTRUCTIONS),
                navigator::exitApplication
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
