package Fragen;

import org.json.JSONObject;

import java.util.Vector;

public class FrageBool extends Frage {

    public FrageBool(int id, int nr,int zeit, String text) {
        super(id, nr,zeit, text);
    }


    @Override
    public String toJson() {
        JSONObject obj = new JSONObject();

        obj.put("nr", nr);
        obj.put("typ", "bool");
        obj.put("zeit",seconds);
        obj.put("text", text);

        return obj.toString();

    }
}
