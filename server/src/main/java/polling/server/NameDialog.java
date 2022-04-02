package polling.server;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.*;
import java.nio.file.Files;

public class NameDialog extends Dialog<String> {

    public NameDialog() {
        this.setTitle("Neue Befragung");
        ButtonType buttonTypeOk = new ButtonType("Bestätigen", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().add(buttonTypeOk);
        NameDialogControl ui = new NameDialogControl();
        this.getDialogPane().setContent(ui);
        this.setResultConverter(b -> {
            if (b == buttonTypeOk) {
                return ui.getName();
            }
            return null;
        });
    }
}

class NameDialogControl extends VBox {
    @FXML
    protected TextField nameField;
    public NameDialogControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NameDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public String getName() {
        return nameField.getText();
    }


}

