package application.file;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Класс хелпер для работы с файлами.
 */
public class FileHelper {

    /**
     * Выбор изображения.
     */
    public static Image selectImage(ImageView imageView) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String strFile = file.getPath();
                File f = new File(strFile);
                Image image = new Image(f.toURI().toString());
                imageView.setImage(image);
                return image;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
