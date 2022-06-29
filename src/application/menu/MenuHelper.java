package application.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.*;

/**
 * Класс хелпер для работы с меню.
 */
public class MenuHelper {
    private static final List<String> FILE_ELEMENTS = Arrays.asList("Select image");
    private static final List<String> CONTRAST_ELEMENTS = Arrays.asList("Grayscale", "Color");
    private static final List<String> GRADIENT_ELEMENTS = Arrays.asList("Scharr_mx", "Scharr_my", "Sobel_mx", "Sobel_my", "Prewitt_mx", "Prewitt_my");

    private static final Map<String, List<String>> MAPPING_MENU = new HashMap<String, List<String>>() {{
        put("File", FILE_ELEMENTS);
        put("Contrast", CONTRAST_ELEMENTS);
        put("Gradient", GRADIENT_ELEMENTS);
    }};

    /**
     * Генерация меню.
     */
    public static MenuBar generateMenuBar() {
        MenuBar menuBar = new MenuBar();
        for (Map.Entry<String, List<String>> entry : MAPPING_MENU.entrySet()) {
            // Добавляем элемент меню.
            Menu menu = new Menu(entry.getKey());
            // К элементам меню добавляем подэлементы.
            entry.getValue().forEach(menuElement -> menu.getItems().add(new MenuItem(menuElement)));
            menuBar.getMenus().add(menu);
        }
        return menuBar;
    }
}
