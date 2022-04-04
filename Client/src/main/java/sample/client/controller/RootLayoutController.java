package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import sample.client.Client;
import sample.client.utils.ViewControl;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    @FXML
    public Button btnCon;
    @FXML
    public Label lbERROR;
    @FXML
    public TextField tFKey;

    Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCon.setDisable(true);
        try {
            // Creates Client and Connect to Server
            client = Client.getClient();
            btnCon.setDisable(false);

            // Server Connection get closed by Window close
            Stage stage = (Stage) btnCon.getScene().getWindow();
            stage.setOnCloseRequest(windowEvent -> {
                Client.client.closeServerConnection();
            });
        } catch (UnknownHostException ex) {
            System.out.println("UnknownHostException: " + ex.getMessage());
            lbERROR.setText("Cant Connect to Server. Please try again later");
        }
    }

    public void startConnection(ActionEvent actionEvent) {
        try {
            // Send Authentication Key
            System.out.println("Sending Authentication Key ...\n");
            client.sendAuthenticationKey(tFKey.getText());

            // Request Question and save it in ViewControl
            String json = client.getDataFromServer();
            ViewControl.setJSONObject(new JSONObject(json));
            // Change Scene
            ViewControl.nextScene(actionEvent, json);
        } catch (Exception ex) {
            // Throws IOException if Authentication Key is wrong
            System.out.println(ex.getMessage());
            lbERROR.setText(ex.getMessage());
        }
    }
}
