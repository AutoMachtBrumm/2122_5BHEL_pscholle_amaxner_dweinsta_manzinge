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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.SQLException;
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
    public AnchorPane AnchorPaneCharts;
    @FXML
    public Button buttonLoadBefragung;
    @FXML
    private TextField textFieldAntwortenBool;

    private String url = "jdbc:postgresql://xserv:5432/pscholle";
    private String user = "reader";
    private String password = "reader";

    private boolean connected = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDB(this.url, this.user, this.password);
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

    public void btnBefragungenLaden(ActionEvent actionEvent) {
        if(connected){

            ObservableList<Befragung> items = FXCollections.observableArrayList ();
            try {
                List<Befragung> befragungen = DBController.getBefragungen();
                System.out.println(befragungen);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}