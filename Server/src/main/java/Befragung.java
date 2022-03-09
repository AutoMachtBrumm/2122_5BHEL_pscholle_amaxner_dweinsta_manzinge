import Fragen.Frage;

import java.util.List;
import java.util.Vector;

public class Befragung {
    private int id;
    private String name;
    private int seconds;
    private Vector<Frage> fragen= new Vector<>();

    public Befragung(int id, String name, int seconds, Vector<Frage> fragen) {
        this.id = id;
        this.name = name;
        this.seconds = seconds;
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

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public Vector<Frage> getFragen() {
        return fragen;
    }

    public void setFragen(Vector<Frage> fragen) {
        this.fragen = fragen;
    }
}
