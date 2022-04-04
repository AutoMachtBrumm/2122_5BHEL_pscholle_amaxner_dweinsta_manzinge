package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.client.Client;
import sample.client.utils.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class TextQuestViewController implements Initializable {

    @FXML
    public TextField tFAnswer;
    @FXML
    public Label lbQuest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuest.setText(ViewControl.getQuestion());
    }

    public void nextQuestion(ActionEvent actionEvent) {
        Client client = Client.client;

        // Get Answer from TextField
        client.sendDataToServer(tFAnswer.getText());
        // Request Question and save it in ViewControl
        String json = client.getDataFromServer();
        // Change Scene
        ViewControl.nextScene(actionEvent, json);
    }
}
