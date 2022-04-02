package sample.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("This is a Client - Application");
        primaryStage.initStyle(StageStyle.DECORATED);

        AnchorPane rootLayout = loadRootLayout();

        // Set Login Scene
        primaryStage.setScene(new Scene(rootLayout, 750, 450));
        primaryStage.show();
    }

    //Initializes the RootLayout.
    public AnchorPane loadRootLayout() {
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