package application.filter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface Filter {
    /**
     * Применение фильтра.
     */
    void applyFilter(Image image, ImageView imageView);
}
