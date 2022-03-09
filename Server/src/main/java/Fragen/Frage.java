package Fragen;

public abstract class Frage
{
    private int id;
    private int nr;
    private String text;

    public Frage(int id, int nr, String text) {
        this.id = id;
        this.nr = nr;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

