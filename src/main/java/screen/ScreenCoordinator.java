package screen;

import java.awt.CardLayout;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Koordynuje przełączanie ekranów aplikacji przy użyciu {@link CardLayout}.
 * Odpowiada wyłącznie za tworzenie/cache'owanie ekranów (przez {@link ScreenFactory}),
 * podmianę paneli w kontenerze oraz wywoływanie cyklu życia ekranów
 * ({@link Screen#onEnter()} / {@link Screen#onExit()}). Nie zna nic o oknie
 * aplikacji poza tym, że może je zamknąć na żądanie ({@link #exitApplication()}).
 */
public class ScreenCoordinator implements ScreenNavigator {

    private final JFrame window;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);
    private final ScreenFactory screenFactory = new ScreenFactory();

    /** Ekrany, które mają zachować stan pomiędzy wizytami (menu, instrukcje). */
    private final Map<ScreenType, Screen> cachedScreens = new EnumMap<>(ScreenType.class);
    /** Panele aktualnie dodane do CardLayout, potrzebne do ich podmiany. */
    private final Map<ScreenType, JPanel> attachedPanels = new EnumMap<>(ScreenType.class);

    private Screen currentScreen;

    public ScreenCoordinator(JFrame window) {
        this.window = window;
    }

    /** Zwraca kontener Swing, który należy dodać do okna aplikacji. */
    public JPanel getContainer() {
        return container;
    }

    @Override
    public void navigateTo(ScreenType type) {
        if (currentScreen != null) {
            currentScreen.onExit();
        }

        Screen screen = resolveScreen(type);
        attachPanel(type, screen.getPanel());

        currentScreen = screen;
        cardLayout.show(container, type.name());
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
            container.remove(previous);
        }
        container.add(panel, type.name());
        attachedPanels.put(type, panel);
    }

    @Override
    public void exitApplication() {
        window.dispose();
        System.exit(0);
    }
}
