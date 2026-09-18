# Retro Space Shooter (Galaga Style)

Prosta gra 2D w stylu Galaga napisana w Javie z użyciem `javax.swing` / `java.awt`.
Projekt powstał jako refaktoryzacja jednoplikowego prototypu do architektury
zgodnej z zasadami OOP i wzorcem **MVC** (Model-View-Controller).

## Uruchomienie

Wymagania: JDK 23+, Maven.

```bash
mvn compile
mvn exec:java -Dexec.mainClass="app.GalagaGame"
```

lub bezpośrednio:

```bash
javac -d target/classes -encoding UTF-8 $(find src/main/java -name "*.java")
cp src/main/resources/*.png target/classes/
java -cp target/classes app.GalagaGame
```

## Sterowanie

- **Strzałka w lewo / w prawo** — poruszanie statkiem
- **Spacja** — strzał

## Architektura

Projekt jest podzielony na cztery pakiety zgodnie z zasadą pojedynczej
odpowiedzialności (SRP) i separacją warstw MVC:

```
src/main/java/
├── app/          — punkt wejścia aplikacji (main), nawigacja między ekranami
│   └── GalagaGame.java
├── model/        — stan gry i logika domenowa (bez zależności od UI)
│   ├── Game.java              — główny stan gry, pętla aktualizacji
│   ├── Player.java             — model statku gracza
│   ├── Enemy.java              — model pojedynczego przeciwnika
│   ├── Bullet.java             — model pocisku
│   └── CollisionDetector.java  — wykrywanie kolizji pocisk↔wróg
├── view/         — wyłącznie renderowanie (Swing), zero logiki gry
│   ├── GamePanel.java          — rysuje aktualny stan Game
│   ├── MenuPanel.java          — ekran menu głównego
│   ├── InstructionsPanel.java  — ekran instrukcji
│   └── SpriteLoader.java       — wczytywanie i buforowanie obrazków sprite'ów
└── controller/   — spina model z widokiem i wejściem
    ├── GameController.java     — pętla gry (Timer, ~60 FPS)
    └── InputHandler.java       — obsługa klawiatury

src/main/resources/
├── galaga_ship.png       — sprite statku gracza
└── galaga_enemy_1.png    — sprite przeciwnika
```

**Zasady projektowe:**
- `model` nie zależy od żadnej klasy Swing/AWT — logika gry jest w pełni testowalna bez GUI.
- `view` tylko odczytuje stan modelu i rysuje go — nie modyfikuje stanu gry.
- `controller` jest jedynym miejscem łączącym `model` z `view` i wejściem z klawiatury.
- Grafika statków wykorzystuje własne sprite'y (PNG z prawdziwym kanałem alfa) zamiast
  prostych kształtów rysowanych metodami `fillRect`.

## Historia rozwoju projektu

Historia commitów w tym repozytorium odzwierciedla kolejne etapy refaktoryzacji,
od pierwotnego prototypu do obecnej architektury:

1. **Initial single-class prototype** — pierwotny, "wygenerowany" kod: jedna klasa
   `GalagaGame` z zagnieżdżonymi klasami `GamePanel`/`Enemy`, bez podziału odpowiedzialności.
2. **Refactor: split monolithic class into SRP classes** — wydzielenie modeli
   (`Player`, `Enemy`, `Bullet`, `Game`), logiki kolizji, obsługi wejścia i kontrolera
   pętli gry do osobnych klas (płaska struktura, bez pakietów).
3. **Refactor: reorganize into MVC package structure** — podział na pakiety
   `app`/`model`/`view`/`controller` zgodnie z wzorcem MVC.
4. **Add main menu screen** — dodanie ekranu startowego (Start gry / Instrukcje / Wyjście)
   z nawigacją opartą o `CardLayout`.
5. **Replace primitive shapes with custom sprites** — podmiana rysowania `fillRect`
   na własne obrazki sprite'ów wczytywane przez `SpriteLoader`.
6. **Fix sprite backgrounds** — usunięcie fałszywej przezroczystości (szachownicy)
   z plików PNG i nadanie im prawdziwego kanału alfa.

Każdy z powyższych punktów odpowiada osobnemu commitowi w historii `git log`,
dzięki czemu można prześledzić (lub cofnąć się do) dowolnego etapu refaktoryzacji.
