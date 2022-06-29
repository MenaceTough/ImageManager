package application.filter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Класс с реализацией фильтра PrewittMx.
 */
public class FilterPrewittMx implements Filter {

    @Override
    public void applyFilter(Image image, ImageView imageView) {
        imageView.setImage(FilterHelper.filter(FilterHelper.FILTER_PREWITT_MX, image));
    }
}
