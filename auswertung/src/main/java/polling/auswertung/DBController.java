package polling.auswertung;

import Befragung.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DBController {

    public static List<Befragung> getBefragungen() throws SQLException {
        List<Befragung> befragungen = new ArrayList<>();
        ResultSet resultSet = runQuerryWR("SELECT * FROM polling.befragung");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            befragungen.add(new Befragung(id, name));
        }
        return befragungen;
    }

    public static List<Frage> getFragen(Befragung befragung) throws SQLException {
        List<Frage> fragen = new ArrayList<>();
        ResultSet resultSet = runQuerryWR("select * FROM polling.frage FULL OUTER JOIN polling.fragenum f on frage.id = f.frage_id WHERE befragung_id =" + befragung.getId());
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            int nr = resultSet.getInt("nr");
            int seconds = resultSet.getInt("seconds");
            String text = resultSet.getString("text");
            String type = resultSet.getString("type");
            switch (type){
                case "text":
                    fragen.add(new FrageText(id, nr, seconds, text));
                    break;
                case "nume":
                    int minval = resultSet.getInt("minval");
                    int maxval = resultSet.getInt("maxval");
                    fragen.add(new FrageNum(id, nr, seconds, text, minval, maxval));
                    break;
                case "bool":
                    fragen.add(new FrageBool(id, nr, seconds, text));
                    break;
                default:
                    System.err.println("Undefined type ='" + type + "' for Frage !");
                    break;
            }
        }
        return fragen;
    }

    public static HashMap<Boolean, Integer> getAuswertungBool(int id) throws SQLException {
        HashMap<Boolean, Integer> hashMap = new HashMap<>();
        ResultSet resultSet = runQuerryWR("SELECT * FROM boolAuswertung(" + id + ")");
        while(resultSet.next()){
            hashMap.put(resultSet.getBoolean("bool"), resultSet.getInt("count"));
        }
        return hashMap;
    }

    public static List<String> getAuswertungText(int id) throws SQLException {
        List<String> antworten = new ArrayList<>();
        ResultSet resultSet = runQuerryWR("SELECT antwort FROM polling.antworttext WHERE frage_id=" + id);
        while(resultSet.next()){
            antworten.add(resultSet.getString("antwort"));
        }
        return antworten;
    }

    public static List<Integer> getAuswertungNum(int id) throws SQLException {
        List<Integer> antworten = new ArrayList<>();
        ResultSet resultSet = runQuerryWR("SELECT antwort FROM polling.antwortnum WHERE frage_id =" + id);
        while(resultSet.next()){
            antworten.add(resultSet.getInt("antwort"));
        }
        return antworten;
    }

    public static HashMap<String, Integer> getAuswertungNumMinMax(int id) throws SQLException {
        HashMap<String, Integer> hashMap = new HashMap<>();
        ResultSet resultSet = runQuerryWR("SELECT minval, maxval FROM polling.fragenum WHERE frage_id =" + id);
        while(resultSet.next()){
            hashMap.put("min", resultSet.getInt("minval"));
            hashMap.put("max", resultSet.getInt("maxval"));
        }
        return hashMap;
    }

    /*
     *  Run SQL queries with returning ResultSet
     */
    protected static ResultSet runQuerryWR(String sqlQuerry) throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        return statement.executeQuery(sqlQuerry);
    }

    /*
     *  Run SQL queries without returning ResultSet
     */
    protected static void runQuerryWOR(String sqlQuerry) throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        statement.execute(sqlQuerry);
    }
}


