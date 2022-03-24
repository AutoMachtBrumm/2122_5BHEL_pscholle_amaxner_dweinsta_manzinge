package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.client.utils.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class NumQuestViewController implements Initializable {

    public TextField tFAnswer;
    public Label lbQuest;
    public Label lbRating;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuest.setText(ViewControl.getQuestion());
        String min = ViewControl.getValueRange()[0];
        String max = ViewControl.getValueRange()[1];
        lbRating.setText("RATE FROM " + min + " TO " + max);
    }

    public void nextQuestion(ActionEvent actionEvent) {
        ViewControl.setResult(tFAnswer.getText());
    }
}
