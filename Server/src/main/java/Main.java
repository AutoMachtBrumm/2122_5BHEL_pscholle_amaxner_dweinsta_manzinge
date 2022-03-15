import Fragen.FrageBool;
import Fragen.FrageNum;
import Fragen.FrageText;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        Befragung befragung=new Befragung(1,"Befragung1");
        befragung.addFrage(new FrageText(0,1,10,"Wie gehts?"));
        befragung.addFrage(new FrageBool(0,1,10,"Alles gut?"));
        befragung.addFrage(new FrageNum(0,1,10,"Wie alt?",0,120));
        server.befragungen.add(befragung);
        server.startServer();

    }
}
