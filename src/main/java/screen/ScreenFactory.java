package screen;

/**
 * Tworzy instancje {@link Screen} odpowiednie dla danego {@link ScreenType}.
 * Centralizuje wiedzę o tym, jaka klasa ekranu odpowiada za jaki typ,
 * dzięki czemu reszta aplikacji operuje wyłącznie na abstrakcji {@link Screen}.
 */
public class ScreenFactory {

    public Screen create(ScreenType type, ScreenNavigator navigator) {
        return switch (type) {
            case MENU -> new MenuScreen(navigator);
            case INSTRUCTIONS -> new InstructionsScreen(navigator);
            case GAME -> new GameScreen();
        };
    }
}
