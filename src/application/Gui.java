package application;

import application.menu.ContrastHelper;
import application.menu.FileHelper;
import application.menu.FilterHelper;
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
    int contrastSliderId;

    public Gui(Stage stage) {
        // Генерация MenuBar.
        MenuBar menuBar = MenuHelper.generateMenuBar();
        // Настройка stage.
        Slider slider = new Slider(0, 1, 0);
        contrastSliderId = slider.hashCode();
        slider.setMaxWidth(200);
        stage.setWidth(500);
        stage.setHeight(500);
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView);
        Scene scene = new Scene(new VBox(menuBar, root));
        stage.setScene(scene);
        stage.show();

        root.setOnMousePressed(event -> {

        });
        menuBar.getMenus().forEach(menu -> menu.getItems()
                .forEach(menuItem ->
                        menuItem.setOnAction((EventHandler<ActionEvent>) event -> {
                    String menuItemText = menuItem.getText();
                    switch (menuItemText) {
                        case ("Select Image"):
                            image = FileHelper.selectImage(imageView);
                            break;
                        case ("Grayscale"):
                            ContrastHelper.grayImage(image, imageView);
                            break;
                        case ("Scharr_mx"):
                            FilterHelper.filterScharrMx(image, imageView);
                            break;
                        case ("Scharr_my"):
                            FilterHelper.filterScharrMy(image, imageView);
                            break;
                        case ("Sobel_mx"):
                            FilterHelper.filterSobelMx(image, imageView);
                            break;
                        case ("Sobel_my"):
                            FilterHelper.filterSobelMy(image, imageView);
                            break;
                        case ("Prewitt_mx"):
                            FilterHelper.filterScharrMx(image, imageView);
                            break;
                        case ("Prewitt_my"):
                            FilterHelper.filterScharrMy(image, imageView);
                            break;
                        case ("Color"):
                            if (!root.getChildren().contains(slider)) {
                                root.getChildren().add(slider);
                            }
                            break;
                    }
                    setRoot(root, menuItem);
                })));

        // Изменение контраста.
        ContrastHelper.changeContrast(slider, imageView);
    }
        // Настройка элементов root.
    private void setRoot(StackPane root, MenuItem selectedMenuItem) {
        if (!selectedMenuItem.getText().equals("Color")) {
            Slider slider = (Slider) root.getChildren().stream().filter(element -> element.hashCode() == contrastSliderId).findFirst().orElse(null);
            if (slider != null) {
                root.getChildren().remove(slider);
            }
        }
    }
}
