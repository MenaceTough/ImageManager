package application.menu;

import javafx.scene.control.Slider;
import javafx.scene.effect.Lighting;
import javafx.scene.image.*;

/**
 * Класс хелпер для работы с контрастом.
 */
public class ContrastHelper {

    /**
     * Изменение контраста.
     */
    public static void changeContrast(Slider menuItem, ImageView imageView) {
        menuItem.valueProperty().addListener((observable, oldValue, newValue) -> {
            Lighting lighting = new Lighting();
            lighting.setDiffuseConstant(menuItem.getValue());
            imageView.setEffect(lighting);
        });
    }

    /**
     * Преобразование изображения в серый.
     */
    public static Image grayImage(Image image, ImageView imageView) {
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = pixelReader.getArgb(x, y);
                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);
                int grayLevel = (int) (0.2162 * (double) red + 0.7152 * (double) green
                        + 0.0722 * (double) blue) / 3;

                // Интенсивность серого.
                grayLevel = 255 - grayLevel;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                // Градации серого.
                pixelWriter.setArgb(x, y, -gray);
            }
            imageView.setImage(wImage);
            image = wImage;
        }
        return null;
    }

}
