package screen;

/**
 * Kontrakt nawigacji między ekranami. Ekrany zależą wyłącznie od tego interfejsu,
 * nie od konkretnej implementacji okna aplikacji.
 */
public interface ScreenNavigator {

    /** Przełącza aplikację na wskazany ekran. */
    void navigateTo(ScreenType type);

    /** Kończy działanie aplikacji. */
    void exitApplication();
}
