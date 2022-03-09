package Fragen;

import java.util.Vector;

public class FrageBool extends Frage{
    private Vector<Boolean> antwortBool=new Vector<>();

    public FrageBool(int id, int nr, String text) {
        super(id, nr, text);
    }

    public Vector<Boolean> getAntwortBool() {
        return antwortBool;
    }

    public void addAntwortBool(boolean v) {
        antwortBool.add(v);
    }
}
