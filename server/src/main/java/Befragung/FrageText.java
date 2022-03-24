package Befragung;

import org.json.JSONObject;

public class FrageText extends Frage {

    public FrageText(int id, int nr, int seconds, String text) {
        super(id, nr, seconds, text);
    }



    @Override
    public String toJson() {
        JSONObject obj = new JSONObject();

        obj.put("nr", nr);
        obj.put("typ", "text");
        obj.put("text", text);

        return obj.toString();

    }
}
