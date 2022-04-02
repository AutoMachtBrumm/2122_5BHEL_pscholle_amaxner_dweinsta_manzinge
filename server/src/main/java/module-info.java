module polling.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;



    opens polling.server to javafx.fxml;
    exports polling.server;
    exports Befragung;
    opens Befragung to javafx.fxml;
}