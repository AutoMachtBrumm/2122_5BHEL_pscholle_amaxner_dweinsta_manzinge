import Fragen.Frage;

import java.util.List;
import java.util.Vector;

public class Befragung {
    private int id;
    private String name;
    private Vector<Frage> fragen= new Vector<>();

    public Befragung(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Frage> getFragen() {
        return fragen;
    }

    public void addFrage(Frage frage) {
        this.fragen.add(frage);
    }
}
