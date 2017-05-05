package vk.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vk.bd.BD;
import vk.controllers.MainController;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/mainWindow.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Vk Loocker");
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(400);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();



        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
