package Befragung;

import org.json.JSONObject;

public class FrageNum extends Frage{
    private int minVal;
    private int maxVal;

    public FrageNum(int id, int nr, int seconds, String text,int minVal, int maxVal) {
        super(id, nr, seconds,text);
        this.maxVal=maxVal;
        this.minVal=minVal;
    }

    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }

    @Override
    public String toJson() {
        JSONObject obj = new JSONObject();

        obj.put("nr", nr);
        obj.put("typ", "nume");
        obj.put("text", text);
        obj.put("min",minVal);
        obj.put("max",maxVal);

        return obj.toString();

    }
}
