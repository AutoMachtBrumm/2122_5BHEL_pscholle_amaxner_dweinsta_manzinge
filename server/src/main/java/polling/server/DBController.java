package polling.server;

import Befragung.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

    public static void insertBefragung(Befragung befragung) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.befragung(name) values ('" + befragung.getName() + "')  RETURNING id");
        resultSet.next();
        befragung.setId(resultSet.getInt("id"));
        for (Frage frage: befragung.getFragen()) {
            if(frage instanceof FrageText){
                insertFrageText((FrageText) frage,befragung.getId());
            }else if(frage instanceof FrageBool){
                insertFrageBool((FrageBool) frage,befragung.getId());
            }else if(frage instanceof FrageNum){
                insertFrageNum((FrageNum) frage,befragung.getId());
            }
        }

    }

    private static void insertFrageBool(FrageBool frageBool, int befragung_id) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.frage(nr, text, type, befragung_id) values (" + frageBool.getNr() + ",'" + frageBool.getText() + "', 'bool', " + befragung_id + ") RETURNING id");
        resultSet.next();
        frageBool.setId(resultSet.getInt("id"));
    }

    protected static void insertFrageText(FrageText frageText, int befragung_id) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.frage(nr, text, type, befragung_id) values (" + frageText.getNr() + ",'" + frageText.getText() + "', 'text', " + befragung_id + ") RETURNING id");
        resultSet.next();
        frageText.setId(resultSet.getInt("id"));
    }


    protected static void insertFrageNum(FrageNum frageNum, int befragung_id) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.frage(nr, text, type, befragung_id) values (" + frageNum.getNr() + ",'" + frageNum.getText() + "', 'nume', " + befragung_id + ") RETURNING id");
        resultSet.next();
        frageNum.setId(resultSet.getInt("id"));
        // add MinVal && Maxval to SQL table
        runQuerryWOR("insert into polling.fragenum(frage_id, minval, maxval) values (" + frageNum.getId() + ", " + frageNum.getMinVal() + ", " + frageNum.getMaxVal() + ")");
    }

    public static void insertAntwortBool(boolean antwort, int frage_id) throws SQLException {
        runQuerryWOR("insert into polling.antwortbool(frage_id, antwort)  values (" + frage_id + ", " + antwort + ")");
    }

    public static void insertAntwortText(String antwort, int frage_id) throws SQLException {
        runQuerryWOR("insert into polling.antworttext(frage_id, antwort)  values (" + frage_id + ", '" + antwort + "')");
    }

    public static void insertAntwortNum(int antwort, int frage_id) throws SQLException {
        runQuerryWOR("insert into polling.antwortnum(frage_id, antwort)  values (" + frage_id + ", " + antwort + ")");
    }

    public static void getAuswertungBool(FrageBool frageBool) throws SQLException {
        ResultSet resultSet = runQuerryWR("SELECT * FROM boolAuswertung("+frageBool.getId()+")");
        while(resultSet.next()){
            System.out.println(resultSet.getInt("count"));
            System.out.println(resultSet.getBoolean("bool"));
        }
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


