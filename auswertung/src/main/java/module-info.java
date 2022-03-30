module polling.auswertung {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens polling.auswertung to javafx.fxml;
    exports polling.auswertung;
}