package polling.server;


import Befragung.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(){
        Server server = new Server();
        Befragung befragung = new Befragung(1, "Befragung2");
        befragung.addFrage(new FrageText(0, 1, 10, "Wie gehts?"));
        befragung.addFrage(new FrageBool(0, 1, 10, "Alles gut?"));
        befragung.addFrage(new FrageNum(0, 1, 10, "Wie alt?", 0, 120));
        server.befragungen.add(befragung);
        server.start();
    }
}