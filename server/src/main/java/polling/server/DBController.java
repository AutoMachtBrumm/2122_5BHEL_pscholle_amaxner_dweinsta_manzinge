package polling.server;

import Befragung.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBController {

    public static void insertBefragung(Befragung befragung) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.befragung(name) values ('" + befragung.getName() + "')  RETURNING id");
        resultSet.next();
        befragung.setId(resultSet.getInt("id"));
        for (Frage frage: befragung.getFragen()) {
            insertFrage(frage,befragung.getId());
        }
    }

    public static void insertFrage(Frage frage,int befID) throws SQLException {
        if(frage instanceof FrageText){
            insertFrageText((FrageText) frage,befID);
        }else if(frage instanceof FrageBool){
            insertFrageBool((FrageBool) frage,befID);
        }else if(frage instanceof FrageNum){
            insertFrageNum((FrageNum) frage,befID);
        }
    }

    public static void insertFrageBool(FrageBool frageBool, int befragung_id) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.frage(nr, text, type, befragung_id) values (" + frageBool.getNr() + ",'" + frageBool.getText() + "', 'bool', " + befragung_id + ") RETURNING id");
        resultSet.next();
        frageBool.setId(resultSet.getInt("id"));
    }

    public static void insertFrageText(FrageText frageText, int befragung_id) throws SQLException {
        ResultSet resultSet = runQuerryWR("insert into polling.frage(nr, text, type, befragung_id) values (" + frageText.getNr() + ",'" + frageText.getText() + "', 'text', " + befragung_id + ") RETURNING id");
        resultSet.next();
        frageText.setId(resultSet.getInt("id"));
    }


    public static void insertFrageNum(FrageNum frageNum, int befragung_id) throws SQLException {
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

    public static void deleteFrage(int frageID) throws SQLException {
        runQuerryWOR("delete from polling.frage where id="+frageID);
    }

    public static void deleteBefragung(int befragungID) throws SQLException {
        runQuerryWOR("delete from polling.befragung where id="+befragungID);
    }

    public static List<Befragung> getBefragungen() throws SQLException {
        List<Befragung> befragungen = new ArrayList<>();
        ResultSet resultSet = runQuerryWR("SELECT * FROM polling.befragung");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Befragung bef=new Befragung(id, name);
            bef.getFragen().addAll(getFragen(id));
            befragungen.add(bef);

        }
        return befragungen;
    }

    public static List<Frage> getFragen(int befragungID) throws SQLException {
        List<Frage> fragen = new ArrayList<>();
        ResultSet resultSet = runQuerryWR("SELECT * FROM polling.frage where befragung_id="+befragungID+" order by nr asc");
        while(resultSet.next()){
            String type = resultSet.getString("type");
            int id=resultSet.getInt("id");
            int nr=resultSet.getInt("nr");
            int seconds=resultSet.getInt("seconds");
            String text=resultSet.getString("text");
            switch (type){
                case "text":
                    fragen.add(new FrageText(id,nr,seconds,text));
                    break;
                case "bool":
                    fragen.add(new FrageBool(id,nr,seconds,text));
                    break;
                case "nume":
                    ResultSet resultSetMinMax = runQuerryWR("SELECT * FROM polling.fragenum where frage_id="+id);
                    resultSetMinMax.next();
                    int min=resultSetMinMax.getInt("minval");
                    int max=resultSetMinMax.getInt("maxval");
                    fragen.add(new FrageNum(id,nr,seconds,text,min,max));
                    break;
            }
        }
        return fragen;
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


