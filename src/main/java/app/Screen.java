package app;

import javax.swing.JPanel;

/**
 * Reprezentuje pojedynczy ekran aplikacji (menu, instrukcje, gra...).
 * Każda implementacja odpowiada wyłącznie za własny panel oraz własny cykl życia
 * (np. start/stop pętli gry), bez wiedzy o pozostałych ekranach.
 */
public interface Screen {

    /** Zwraca panel Swing, który ma zostać wyświetlony dla tego ekranu. */
    JPanel getPanel();

    /** Wywoływane tuż przed pokazaniem ekranu użytkownikowi. */
    default void onEnter() {
        // domyślnie brak akcji
    }

    /** Wywoływane tuż przed opuszczeniem ekranu (np. zatrzymanie timera gry). */
    default void onExit() {
        // domyślnie brak akcji
    }
}
