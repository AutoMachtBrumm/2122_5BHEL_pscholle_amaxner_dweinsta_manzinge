package client;

import org.json.JSONObject;

import java.util.Scanner;

public class QuestionHandler {

    private static String result;

    public static String editJSON(String jsonString){

        JSONObject jObj = new JSONObject(jsonString);

        // Print Question
        System.out.println(jObj.getString("text"));


        // Wird später über grafische Oberfläche eingelesen
        Scanner scanner = new Scanner(System.in);
        result = scanner.nextLine();

        return result;
    }


}
