package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import sample.client.utils.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class BoolQuestViewController implements Initializable {

    public Label lbQuest;
    public ToggleGroup rbtnGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuest.setText(ViewControl.getQuestion());
    }

    public void nextQuestion(ActionEvent actionEvent) {
        ViewControl.setResult(rbtnGroup.getSelectedToggle().selectedProperty().getName());
    }
}
