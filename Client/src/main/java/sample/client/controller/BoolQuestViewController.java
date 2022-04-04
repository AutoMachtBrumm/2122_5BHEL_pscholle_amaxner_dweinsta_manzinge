package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import sample.client.Client;
import sample.client.utils.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class BoolQuestViewController implements Initializable {

    @FXML
    public Label lbQuest;
    @FXML
    public ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuest.setText(ViewControl.getQuestion());
    }

    public void nextQuestion(ActionEvent actionEvent) {
        Client client = Client.client;

        // Get Answer from SelectedToggle
        String answer = toggleGroup.getSelectedToggle().getUserData().toString();
        client.sendDataToServer(answer);
        // Request Question and save it in ViewControl
        String json = client.getDataFromServer();
        // Change Scene
        ViewControl.nextScene(actionEvent, json);
    }
}
