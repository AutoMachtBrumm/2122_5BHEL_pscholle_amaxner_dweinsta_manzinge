package polling.server;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("surface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Controller controller=fxmlLoader.getController();
        stage.setTitle("Polling Server");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            controller.closeServer();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}