import Fragen.FrageBool;
import Fragen.FrageNum;
import Fragen.FrageText;

import java.sql.SQLException;
import java.util.stream.Collectors;

//TODO-Scholler: Remove !

public class DB_Main {

    // terminal:
    // # ssh -p 22 pscholle@htl-steyr.ac.at -L 5432:xserv:5432

    // static final String url = "jdbc:postgresql://localhost:5432/pscholle";
    static final String url = "jdbc:postgresql://xserv:5432/pscholle";
    static final String user = "reader";
    static final String password = "reader";


    public static void main(String[] args) {

        try {
            DBConnector.connect(url, user, password);

            Befragung befragung = new Befragung(0, "Inserted From Java 5");

            befragung.addFrage(new FrageNum(0, 1, 122, "Was ist grün und schaut aus wie ein Dreieck ?", 0, 12));
            befragung.addFrage(new FrageText(0, 2, 123, "Was ist grün und schaut nicht aus wie ein Dreieck ?"));


            DBController.insertBefragung(befragung);

            befragung.getFragen().forEach(frage -> {

                try {

                    if (frage.getClass() == FrageNum.class) {
                        DBController.insertFrageNum((FrageNum) frage, befragung.getId());
                    }

                    if (frage.getClass() == FrageText.class) {
                        DBController.insertFrageText((FrageText) frage, befragung.getId());
                    }

                    if (frage.getClass() == FrageBool.class) {
                        DBController.insertFrageBool((FrageBool) frage, befragung.getId());
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            System.out.println(befragung.getFragen().stream().map(f -> (f.getId() + " - " + f.getText())).collect(Collectors.toList()));

            befragung.getFragen().forEach(frage -> {
                try {

                    if (frage.getClass() == FrageNum.class) {
                        DBController.insertAntwortNum(123, frage.getId());
                    }

                    if (frage.getClass() == FrageText.class) {
                        DBController.insertAntwortText("Bla Bli Blub", frage.getId());
                    }

                    if (frage.getClass() == FrageBool.class) {
                        DBController.insertAntwortBool(true, frage.getId());
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            DBConnector.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
