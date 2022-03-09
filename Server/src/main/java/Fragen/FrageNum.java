package Fragen;

import java.util.Vector;

public class FrageNum extends Frage{
    private int minVal;
    private int maxVal;
    private Vector<Integer> antwortVal= new Vector<>();

    public FrageNum(int id, int nr, String text) {
        super(id, nr, text);
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

    public Vector<Integer> getAntwortVal() {
        return antwortVal;
    }

    public void addAntwortVal(int val) {
        antwortVal.add(val);
    }
}
