package app;

import java.awt.CardLayout;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Punkt wejścia aplikacji. Pełni rolę "composition root": tworzy okno,
 * deleguje tworzenie ekranów do {@link ScreenFactory} i przełącza je
 * przez {@link CardLayout}, implementując {@link ScreenNavigator}.
 */
public class GalagaGame extends JFrame implements ScreenNavigator {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel screens = new JPanel(cardLayout);
    private final ScreenFactory screenFactory = new ScreenFactory();

    /** Ekrany, które mają zachować stan pomiędzy wizytami (menu, instrukcje). */
    private final Map<ScreenType, Screen> cachedScreens = new EnumMap<>(ScreenType.class);
    /** Panele aktualnie dodane do CardLayout, potrzebne do ich podmiany. */
    private final Map<ScreenType, JPanel> attachedPanels = new EnumMap<>(ScreenType.class);

    private Screen currentScreen;

    public GalagaGame() {
        setTitle("Retro Space Shooter (Galaga Style)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(screens);
        navigateTo(ScreenType.MENU);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void navigateTo(ScreenType type) {
        if (currentScreen != null) {
            currentScreen.onExit();
        }

        Screen screen = resolveScreen(type);
        attachPanel(type, screen.getPanel());

        currentScreen = screen;
        cardLayout.show(screens, type.name());
        screen.onEnter();
    }

    /**
     * Zwraca ekran dla danego typu. Menu i instrukcje są tworzone raz i buforowane
     * (zachowują stan), natomiast ekran gry jest tworzony od nowa przy każdym wejściu,
     * aby zawsze rozpoczynać świeżą rozgrywkę.
     */
    private Screen resolveScreen(ScreenType type) {
        if (type == ScreenType.GAME) {
            return screenFactory.create(type, this);
        }
        return cachedScreens.computeIfAbsent(type, t -> screenFactory.create(t, this));
    }

    private void attachPanel(ScreenType type, JPanel panel) {
        JPanel previous = attachedPanels.get(type);
        if (previous == panel) {
            return;
        }
        if (previous != null) {
            screens.remove(previous);
        }
        screens.add(panel, type.name());
        attachedPanels.put(type, panel);
    }

    @Override
    public void exitApplication() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GalagaGame::new);
    }
}
