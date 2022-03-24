package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.client.Client;

import java.io.IOException;
import java.net.InetAddress;
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

    /* DEFINE SERVER ADDRESS AND PORTS */
    final Integer PORT = 10000;
    final String ADDRESS = "localhost";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCon.setDisable(true);
        try {
            client = new Client(InetAddress.getByName(ADDRESS), PORT);
            btnCon.setDisable(false);
        } catch (UnknownHostException ex) {
            System.out.println("UnknownHostException: " + ex.getMessage());
            lbERROR.setText("Cant Connect to Server. Please try again later");
        }
    }

    public void startConnection(ActionEvent actionEvent) {
        try {
            // Send Authentication Key
            System.out.println("Sending Authentication Key ...");
            client.sendAuthenticationKey(tFKey.getText());
            // Start Data Transfer and closes connection if transfer is done
            client.startCommunication();
        } catch (IOException ex) {
            System.out.println("I/O error: A-KEY might be wrong");
            lbERROR.setText("A-KEY might be wrong");
        }
    }
}
