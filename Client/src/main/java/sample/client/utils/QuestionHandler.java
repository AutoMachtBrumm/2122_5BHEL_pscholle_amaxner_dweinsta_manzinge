package sample.client.utils;

import org.json.JSONObject;

public class QuestionHandler {

    public static String getAnswer(String jsonString) {
        String answer;

        ViewControl.setJSONObject(new JSONObject(jsonString));
        // Plot right FXML-FILE to RootLayout
        ViewControl.changeScene();
        while(true){
            if (ViewControl.getResult() != null) {
                answer = ViewControl.getResult();
                break;
            }
        }
        ViewControl.setResult(null);
        return answer;
    }
}
