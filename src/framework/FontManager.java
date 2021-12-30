package framework;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FontManager: Load font/fonts into a hash map, the font only needs to be loaded once,
 * then can be used as many times as required.
 * Singleton design
 */

public class FontManager {
    private static FontManager instance = null;

    private final Map<String, Font> _fonts;

    public void createFont(final String name, final String path) {
        if (_fonts.containsKey(name)) {
            System.out.println("Font already loaded!");
            return;
        }

        try {
            _fonts.put(name, Font.createFont(Font.TRUETYPE_FONT, new File(path)));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont(String name, float size) {
        try {
            return _fonts.get(name).deriveFont(size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private FontManager() {
        _fonts = new HashMap<>();
    }

    public static FontManager getInstance()
    {
        if (instance == null)
            instance = new FontManager();

        return instance;
    }
}
