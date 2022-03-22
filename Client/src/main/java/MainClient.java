import client.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClient extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Befragung");
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        initRootLayout();
    }

    //Initializes the root layout.
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("jfx/fxml/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout, 850, 530);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Shows the Student operations view inside the root layout.
    public void changeScene(String url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource(url));
            AnchorPane questionView = loader.load();
            // Set Student Operations view into the center of root layout.
            rootLayout.setCenter(questionView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);

        // Server - Address --> "localhost"
        // Server - Port    --> 10000

        try {
            new Client(InetAddress.getByName("localhost"), 10000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}