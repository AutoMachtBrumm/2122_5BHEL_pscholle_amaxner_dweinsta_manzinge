package polling.server;

import Befragung.Frage;
import Befragung.FrageBool;
import Befragung.FrageNum;
import Befragung.FrageText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FrageDialog extends Dialog<Frage> {

    public FrageDialog() {
        this.setTitle("Neue Frage");
        ButtonType buttonTypeOk = new ButtonType("Bestätigen", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().add(buttonTypeOk);
        FrageDialogControl ui = new FrageDialogControl();
        this.getDialogPane().setContent(ui);
        this.setResultConverter(b -> {
            if (b == buttonTypeOk) {
                return ui.getFrage();
            }
            return null;
        });
    }
}

class FrageDialogControl extends GridPane {
    @FXML
    protected RadioButton textRadio;
    @FXML
    protected RadioButton boolRadio;
    @FXML
    protected RadioButton numericRadio;
    @FXML
    protected Label minFieldLab;
    @FXML
    protected Label maxFieldLab;
    @FXML
    protected TextField nrField;
    @FXML
    protected TextField textField;
    @FXML
    protected TextField secField;
    @FXML
    protected TextField minField;
    @FXML
    protected TextField maxField;
    public FrageDialogControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrageDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        textRadio.setOnAction(actionEvent -> {
            additionalVisible(false);
        });
        boolRadio.setOnAction(actionEvent -> {
            additionalVisible(false);
        });
        numericRadio.setOnAction(actionEvent -> {
            additionalVisible(true);
        });
    }

    public void additionalVisible(boolean isV){
        minField.setVisible(isV);
        maxField.setVisible(isV);
        minFieldLab.setVisible(isV);
        maxFieldLab.setVisible(isV);
    }

    public Frage getFrage(){
        if(textRadio.isSelected()){
            return new FrageText(0,Integer.parseInt(nrField.getText()),Integer.parseInt(secField.getText()),textField.getText());
        }else if(boolRadio.isSelected()){
            return new FrageBool(0,Integer.parseInt(nrField.getText()),Integer.parseInt(secField.getText()),textField.getText());
        }else{
            return new FrageNum(0,Integer.parseInt(nrField.getText()),Integer.parseInt(secField.getText()),textField.getText(),Integer.parseInt(minField.getText()),Integer.parseInt(maxField.getText()));
        }
    }


}

