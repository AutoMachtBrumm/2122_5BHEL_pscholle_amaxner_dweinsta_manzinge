package sample.client.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.json.JSONObject;
import sample.client.MainClient;

import java.io.IOException;

public class ViewControl {

    private static BorderPane rootLayout;
    private static String result;

    private static JSONObject jObj;

    public static void initViewControl(BorderPane rootLayout) {
        ViewControl.rootLayout = rootLayout;
        ViewControl.result = null;
        ViewControl.jObj = null;
    }

    //Shows the operations view inside the root layout.
    public static void changeScene() {
        String url = "";

        switch (jObj.getString("typ")) {
            case "bool" -> url = "BoolQuestView.fxml";
            case "text" -> url = "TextQuestView.fxml";
            case "nume" -> url = "NumQuestView.fxml";
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource(url));
            AnchorPane questionView = loader.load();
            // Set Student Operations view into the center of root layout.
            rootLayout.setCenter(questionView);
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public static void setResult(String result) {
        ViewControl.result = result;
    }

    public static String getResult() {
        return ViewControl.result;
    }

    public static void setJSONObject(JSONObject jsonObject) {
        jObj = jsonObject;
    }

    public static String getQuestion() {
        return jObj.getString("text");
    }

    public static String[] getValueRange(){
        String[] range = new String[2];

        range[0] = jObj.getString("min");
        range[1] = jObj.getString("max");

        return range;
    }
}
