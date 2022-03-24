package sample.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.client.utils.ViewControl;

import java.io.IOException;

public class MainClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("QUESTIONING");
        primaryStage.initStyle(StageStyle.DECORATED);

        BorderPane rootLayout = loadRootLayout();
        ViewControl.initViewControl(rootLayout);

        primaryStage.setScene(new Scene(rootLayout, 850, 530));
        primaryStage.show();
    }

    //Initializes the root layout.
    public BorderPane loadRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("RootLayout.fxml"));
            return loader.load();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}