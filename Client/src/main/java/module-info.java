module sample.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens sample.client to javafx.fxml;
    exports sample.client;
    exports sample.client.controller;
    opens sample.client.controller to javafx.fxml;
    exports sample.client.utils;
    opens sample.client.utils to javafx.fxml;
}