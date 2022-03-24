package polling.server;

import Befragung.*;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class JSONConverter {

    public static List<Befragung> toBefragung(String loc){

        List<Befragung> befragungen = new ArrayList<>();
        File file = new File(loc);

        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);

            System.out.println(json);

        } catch (IOException e) {

            e.printStackTrace();

        }


        return befragungen;
    }
}
