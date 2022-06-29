package application;

import application.contrast.ContrastHelper;
import application.file.FileHelper;
import application.filter.FilterHelper;
import application.menu.MenuHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui {

    public Image image = null;
    public ImageView imageView = new ImageView();
    Slider slider = new Slider(0, 1, 0);

    public Gui(Stage stage) {
        // Генерация MenuBar.
        MenuBar menuBar = MenuHelper.generateMenuBar();
        // Настройка stage.
        slider.setMaxWidth(200);
        stage.setWidth(500);
        stage.setHeight(500);
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView);
        Scene scene = new Scene(new VBox(menuBar, root));
        stage.setScene(scene);
        stage.show();

        menuBar.getMenus().forEach(menu -> menu.getItems()
                .forEach(menuItem ->
                        menuItem.setOnAction((EventHandler<ActionEvent>) event -> {
                            String menuItemText = menuItem.getText();
                            switch (menuItemText) {
                                case "Select image":
                                    image = FileHelper.selectImage(imageView);
                                    break;
                                case "Grayscale":
                                    ContrastHelper.grayImage(image, imageView);
                                    break;
                            }
                            if (menu.getText().equals("Gradient")){
                                FilterHelper.selectFilter(menuItemText).applyFilter(image, imageView);
                            }
                            setRoot(root, menuItem);
                        })));

        // Изменение контраста.
        ContrastHelper.changeContrast(slider, imageView);
    }

    /**
     * Настройка элементов root.
     */
    private void setRoot(StackPane root, MenuItem menuItem) {
        if (menuItem.getText().equals("Color") && !root.getChildren().contains(slider)) {
            root.getChildren().add(slider);
        } else root.getChildren().remove(slider);
    }
}
