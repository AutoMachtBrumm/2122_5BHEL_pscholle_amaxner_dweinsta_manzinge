package sample.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.client.Client;
import sample.client.MainClient;

import java.io.IOException;

public class EndView {


    public void returnToStart(ActionEvent event) {
        Client.client.closeServerConnection();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("RootLayout.fxml"));
            AnchorPane questionView = loader.load();

            // Get Stage from ActionEvent
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(questionView, 750, 450));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
