package polling.auswertung;

import Befragung.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class FxController implements Initializable {
    @FXML
    public ListView listViewBefragungen;
    @FXML
    public TextField textFieldAntwortenNum;
    @FXML
    public TextField textFieldAntwortenText;
    @FXML
    public Label lblStatus;
    @FXML
    public Button buttonLoadBefragung;
    public ListView listViewFragen;
    public Label lblAnzAntworten;
    public AnchorPane anchorPaneAuswertung;
    @FXML
    private TextField textFieldAntwortenBool;

    // ssh -p 22 pscholle@htl-steyr.ac.at -L 5432:xserv:5432
    private String url = "jdbc:postgresql://localhost:5432/pscholle";
    // private String url = "jdbc:postgresql://xserv:5432/pscholle";
    private String user = "reader";
    private String password = "reader";

    private boolean connected = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDB(this.url, this.user, this.password);
    }

    public void btnBefragungenLaden(ActionEvent actionEvent) {
        if(connected){
            try {
                listViewBefragungen.setItems(FXCollections.observableArrayList(DBController.getBefragungen()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onListViewBefragungenClicked(MouseEvent mouseEvent) {
        if(listViewBefragungen.getSelectionModel().getSelectedItem() != null) {
            Befragung befragung = (Befragung) listViewBefragungen.getSelectionModel().getSelectedItem();
            try {
                listViewFragen.setItems(FXCollections.observableArrayList(DBController.getFragen(befragung)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onListViewFragenClicked(MouseEvent mouseEvent) {
        if(listViewFragen.getSelectionModel().getSelectedItem() != null) {
            Frage frage = (Frage) listViewFragen.getSelectionModel().getSelectedItem();

            if(frage.getClass() == FrageText.class){
                System.out.println("Fetching 'Antworten' for 'text' Frage id=" + frage.getId());
                auswertungText(frage.getId());
            }
            if(frage.getClass() == FrageNum.class){
                System.out.println("Fetching 'Antworten' for 'num' Frage id=" + frage.getId());
                auswertungNum(frage.getId());
            }
            if(frage.getClass() == FrageBool.class){
                System.out.println("Fetching 'Antworten' for 'bool' Frage id=" + frage.getId());
                auswertungBool(frage.getId());
            }
        }
    }

    public void auswertungText(int id){
        anchorPaneAuswertung.getChildren().clear();

    }

    public void auswertungBool(int id){
        anchorPaneAuswertung.getChildren().clear();
        try {
            HashMap<Boolean, Integer> hashMap = DBController.getAuswertungBool(id);
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("true", (hashMap.get(true)) == null ? 0 : hashMap.get(true)),
                            new PieChart.Data("false", (hashMap.get(false)) == null ? 0 : hashMap.get(false))
                    );
            PieChart pieChart = new PieChart(pieChartData);
            anchorPaneAuswertung.getChildren().add(pieChart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void auswertungNum(int id){
        anchorPaneAuswertung.getChildren().clear();

    }

    private void connectToDB(String url, String user, String password){
        try {
            DBConnector.connect(url, user, password);
            connected = true;
            System.out.println("Connected to '" + url + "' as '" + user + "' !");

            lblStatus.setText("Online");
            lblStatus.setTextFill(Color.web("green"));

        } catch (ClassNotFoundException | SQLException e) {

            connected = false;
            System.err.println("Error while connecting to '" + url + "' as '" + user + "' !\"");
            lblStatus.setText("Can't connect to server - Offline !");
            lblStatus.setTextFill(Color.web("red"));
            e.printStackTrace();
        }
    }

}