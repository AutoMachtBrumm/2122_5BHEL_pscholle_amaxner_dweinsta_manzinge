module polling.auswertung {
    requires javafx.controls;
    requires javafx.fxml;


    opens polling.auswertung to javafx.fxml;
    exports polling.auswertung;
}