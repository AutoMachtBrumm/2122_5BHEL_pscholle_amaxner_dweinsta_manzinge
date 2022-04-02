package polling.server;


import Befragung.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Optional;

public class Controller {

    static final String url = "jdbc:postgresql://localhost:5432/pscholle";
    static final String user = "reader";
    static final String password = "reader";

    public ListView befList;
    public CheckBox checkBoxActive;
    public ListView frageList;

    public Server server;

    public void initialize() {
        try {
            DBConnector.connect(url, user, password);
            server = new Server();

            server.befragungen.addAll(DBController.getBefragungen());
            befList.setItems(server.befragungen);
            befList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Befragung selectBef = ((Befragung) newValue);
                checkBoxActive.setSelected(selectBef.isActive());
                frageList.setItems(FXCollections.observableArrayList(selectBef.getFragen()));
                frageList.getSelectionModel().select(0);
            });
            befList.getSelectionModel().select(0);
            server.start();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void checkBoxChange(ActionEvent actionEvent) {
        Befragung currentBef = (Befragung) befList.getSelectionModel().getSelectedItem();
        currentBef.setActive(checkBoxActive.isSelected());
    }

    public void newBefragung(ActionEvent actionEvent) {
        try {
            NameDialog dialog = new NameDialog();
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Befragung newBef = new Befragung(0, result.get());
                DBController.insertBefragung(newBef);
                server.befragungen.add(newBef);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeServer() {
        server.stopNow();
    }

    public void deleteBefragung(ActionEvent actionEvent) {
        try {
            Befragung currentBef = (Befragung) befList.getSelectionModel().getSelectedItem();
            DBController.deleteBefragung(currentBef.getId());
            server.befragungen.remove(currentBef);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newFrage(ActionEvent actionEvent) {
        try {
            Befragung currentBef = (Befragung) befList.getSelectionModel().getSelectedItem();
            FrageDialog dialog = new FrageDialog();
            Optional<Frage> result = dialog.showAndWait();
            if (result.isPresent()) {
                Frage newFrg = result.get();
                if (currentBef.numAlreadyTaken(newFrg.getNr())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Number already taken");
                    alert.setHeaderText("Es exisitert bereits eine Frage mit dieser Nummer");
                    alert.showAndWait();
                }else{
                    DBController.insertFrage(newFrg, currentBef.getId());
                    currentBef.addFrage(newFrg);
                    currentBef.getFragen().sort(Comparator.comparingInt(o -> o.getNr()));
                    frageList.setItems(FXCollections.observableArrayList(currentBef.getFragen()));
                    frageList.getSelectionModel().select(newFrg);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFrage(ActionEvent actionEvent) {
        try {
            Befragung currentBef = (Befragung) befList.getSelectionModel().getSelectedItem();
            Frage currentFrg = (Frage) frageList.getSelectionModel().getSelectedItem();
            DBController.deleteFrage(currentFrg.getId());
            currentBef.getFragen().remove(currentFrg);
            frageList.setItems(FXCollections.observableArrayList(currentBef.getFragen()));
            frageList.getSelectionModel().select(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}