package sample.client.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import sample.client.MainClient;

import java.io.IOException;

public class ViewControl {

    private static JSONObject jObj = null;

    public static void nextScene(ActionEvent event, String json) {
        if(!("ENDE".equals(json)) && json != null){
            ViewControl.setJSONObject(new JSONObject(json));
            // Change Scene
            ViewControl.changeScene(event);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainClient.class.getResource("EndView.fxml"));
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

    //Shows the operations view inside the root layout.
    private static void changeScene(ActionEvent event) {

        String url = switch (jObj.getString("typ")) {
            case "bool" -> "BoolQuestView.fxml";
            case "text" -> "TextQuestView.fxml";
            case "nume" -> "NumeQuestView.fxml";
            default -> throw new IllegalStateException("Unexpected value: " + jObj.getString("typ"));
        };

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource(url));
            AnchorPane questionView = loader.load();

            // Get Stage from ActionEvent
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(questionView, 750, 450));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setJSONObject(JSONObject jsonObject) {
        jObj = jsonObject;
    }

    public static String getQuestion() {
        return jObj.getString("text");
    }

    public static String[] getValueRange() {
        String[] range = new String[2];

        range[0] = String.valueOf(jObj.getInt("min"));
        range[1] = String.valueOf(jObj.getInt("max"));

        return range;
    }
}
