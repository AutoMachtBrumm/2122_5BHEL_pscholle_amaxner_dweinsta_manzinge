package Fragen;

import java.util.Vector;

public class FrageText extends Frage{
    private Vector<String> antwortText= new Vector<>();

    public FrageText(int id, int nr, String text) {
        super(id, nr, text);
    }

    public Vector<String> getAntwortText() {
        return antwortText;
    }

    public void addAntwortText(String v) {
        antwortText.add(v);
    }

    @Override
    public void toJson() {

    }
}
