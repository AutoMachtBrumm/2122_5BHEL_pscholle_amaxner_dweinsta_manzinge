import Fragen.Frage;
import Fragen.FrageBool;
import Fragen.FrageNum;
import Fragen.FrageText;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.text.html.HTMLDocument;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.stream.Collectors;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        ImageView imageView = new ImageView();
        HBox hbox = new HBox(imageView);
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        Server server = new Server();

        Befragung befragung = new Befragung(1, "Befragung2");
        befragung.addFrage(new FrageText(0, 1, 10, "Wie gehts?"));
        befragung.addFrage(new FrageBool(0, 1, 10, "Alles gut?"));
        befragung.addFrage(new FrageNum(0, 1, 10, "Wie alt?", 0, 120));
        server.befragungen.add(befragung);
        server.startServer();

    }
}
