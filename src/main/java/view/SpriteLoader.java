package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;

/**
 * Odpowiada wyłącznie za wczytywanie obrazków sprite'ów z classpath (resources)
 * i buforowanie ich w pamięci, aby nie odczytywać pliku przy każdej klatce.
 */
public final class SpriteLoader {

    private static final Map<String, BufferedImage> CACHE = new ConcurrentHashMap<>();

    private SpriteLoader() {
        // klasa narzędziowa
    }

    /**
     * Wczytuje obraz o podanej nazwie pliku z katalogu resources (classpath root).
     * Wynik jest buforowany, więc kolejne wywołania z tą samą nazwą nie czytają pliku ponownie.
     */
    public static BufferedImage load(String resourceFileName) {
        return CACHE.computeIfAbsent(resourceFileName, SpriteLoader::readFromClasspath);
    }

    private static BufferedImage readFromClasspath(String resourceFileName) {
        String path = "/" + resourceFileName;
        try (InputStream stream = SpriteLoader.class.getResourceAsStream(path)) {
            if (stream == null) {
                throw new IOException("Nie znaleziono zasobu na classpath: " + path);
            }
            return ImageIO.read(stream);
        } catch (IOException e) {
            throw new UncheckedIOException("Nie udało się wczytać sprite'a: " + path, e);
        }
    }
}
