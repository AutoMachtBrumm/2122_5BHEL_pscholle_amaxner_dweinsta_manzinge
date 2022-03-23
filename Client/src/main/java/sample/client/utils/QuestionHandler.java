package sample.client.utils;

import java.util.Scanner;

import org.json.JSONObject;

public class QuestionHandler {

    public static String editJSON(String jsonString){

        JSONObject jObj = new JSONObject(jsonString);

        // Print Question
        System.out.println("Question: "+ jObj.getString("text"));

        // Wird später über grafische Oberfläche eingelesen
        Scanner scanner = new Scanner(System.in);
        System.out.print("Client: ");
        String result = scanner.nextLine();

        return result;
    }


}
