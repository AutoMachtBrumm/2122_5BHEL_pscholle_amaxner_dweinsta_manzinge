package Befragung;

import java.util.Vector;

public class Befragung {
    private int id;
    private String name;
    private Vector<Frage> fragen= new Vector<>();
    private boolean active=false;

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
        if(!numAlreadyTaken(frage.nr)){
            this.fragen.add(frage);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean numAlreadyTaken(int nr){
        for (Frage frg: fragen) {
            if(frg.nr==nr){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
