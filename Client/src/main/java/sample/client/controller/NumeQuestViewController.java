package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import sample.client.Client;
import sample.client.utils.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class NumeQuestViewController implements Initializable {

    public Label lbQuest;
    public Label lbRating;
    public Slider availableReproSelector;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuest.setText(ViewControl.getQuestion());
        String[] range = ViewControl.getValueRange();
        String min = range[0];
        String max = range[1];
        lbRating.setText("RATE FROM " + min + " TO " + max);

        // Set Slider Range
        availableReproSelector.setMin(Double.parseDouble(min));
        availableReproSelector.setMax(Double.parseDouble(max));
    }

    public void nextQuestion(ActionEvent actionEvent) {
        Client client = Client.client;


        int value = (int) Math.round(availableReproSelector.getValue());

        // Get Answer from TextField
        client.sendDataToServer(Integer.toString(value));
        // Request Question and save it in ViewControl
        String json = client.getDataFromServer();
        // Change Scene
        ViewControl.nextScene(actionEvent, json);
    }
}
