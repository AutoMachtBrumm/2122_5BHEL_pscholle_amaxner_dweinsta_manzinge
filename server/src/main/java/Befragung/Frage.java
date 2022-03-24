package Befragung;

public abstract class Frage
{
    protected int id;
    protected int nr;
    protected int seconds;
    protected String text;

    public Frage(int id, int nr, int seconds, String text) {
        this.id = id;
        this.nr = nr;
        this.seconds=seconds;
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

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract String toJson();
}

