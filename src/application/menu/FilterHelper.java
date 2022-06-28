package application.menu;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class FilterHelper {
    /**
     * Матрицы фильтрации.
     */
    private static final double[][] FILTER_SCHARR_MX = {{3.0, 0, -3.0}, {10.0, 0, -10.0}, {3.0, 0, -3.0}};
    private static final double[][] FILTER_SCHARR_MY = {{3.0, 10.0, 3.0}, {0, 0, 0}, {-3.0, -10.0, -3.0}};
    private static final double[][] FILTER_SOBEL_MX = {{1.0, 0, -1.0}, {2.0, 0, -2.0}, {1.0, 0, -1.0}};
    private static final double[][] FILTER_SOBEL_MY = {{-1.0, -2.0, -1.0}, {0, 0, 0}, {1.0, 2.0, 1.0}};
    private static final double[][] FILTER_PREWITT_MX = {{1.0, 0, -1.0}, {1.0, 0, -1.0}, {1.0, 0, -1.0}};
    private static final double[][] FILTER_PRRWITT_MY = {{-1.0, -1.0, -1.0}, {0, 0, 0}, {1.0, 1.0, 1.0}};

    /**
     * Применение фильтра ScharrMx.
     */
    public static void filterScharrMx(Image image, ImageView imageView) {
        imageView.setImage(filter(FILTER_SCHARR_MX, image));
    }

    /**
     * Применение фильтра ScharrMy.
     */
    public static void filterScharrMy(Image image, ImageView imageView) {
        imageView.setImage(filter(FILTER_SCHARR_MY, image));
    }

    /**
     * Применение фильтра SobelMx.
     */
    public static void filterSobelMx(Image image, ImageView imageView) {
        imageView.setImage(filter(FILTER_SOBEL_MX, image));
    }

    /**
     * Применение фильтра SobelMy.
     */
    public static void filterSobelMy(Image image, ImageView imageView) {
        imageView.setImage(filter(FILTER_SOBEL_MY, image));
    }

    /**
     * Применение фильтра PrewittMx.
     */
    public static void filterPrewittMx(Image image, ImageView imageView) {
        imageView.setImage(filter(FILTER_PREWITT_MX, image));
    }

    /**
     * Применение фильтра PrewittMy.
     */
    public static void filterPrewittMy(Image image, ImageView imageView) {
        imageView.setImage(filter(FILTER_PRRWITT_MY, image));
    }

    /**
     * Вспомогательный метод для фильтров.
     */
    private static Image filter(double[][] filter, Image image) {
        if (image == null)
            return null;
        // Считываем пиксели изображения.
        PixelReader pixelReader = image.getPixelReader();
        // Создаем изображение, доступное для записи.
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int readY = 0; readY < image.getHeight(); readY++) {
            for (int readX = 0; readX < image.getWidth(); readX++) {
                double r = 0;
                double g = 0;
                double b = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (readX - i < 0 || readX - i > image.getWidth() - 1 || readY - j < 0
                                || readY - j > image.getHeight() - 1)
                            continue;
                        r += filter[i + 1][j + 1] * pixelReader.getColor(readX - i, readY - j).getRed();
                        g += filter[i + 1][j + 1] * pixelReader.getColor(readX - i, readY - j).getGreen();
                        b += filter[i + 1][j + 1] * pixelReader.getColor(readX - i, readY - j).getBlue();
                    }

                }
                r = (r < 0) ? 0 : r;
                r = (r > 1) ? 1 : r;
                g = (g < 0) ? 0 : g;
                g = (g > 1) ? 1 : g;
                b = (b < 0) ? 0 : b;
                b = (b > 1) ? 1 : b;
                int ir = (int) (r * 255);
                int ig = (int) (g * 255);
                int ib = (int) (b * 255);
                Color c1 = Color.rgb(ir, ig, ib);
                pixelWriter.setColor(readX, readY, c1);
            }
        }
        return wImage;
    }
}
