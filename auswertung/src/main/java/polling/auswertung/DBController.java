package polling.auswertung;

import Befragung.Befragung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static void getAuswertungBool(int id) throws SQLException {
        ResultSet resultSet = runQuerryWR("SELECT * FROM boolAuswertung(" + id + ")");
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


