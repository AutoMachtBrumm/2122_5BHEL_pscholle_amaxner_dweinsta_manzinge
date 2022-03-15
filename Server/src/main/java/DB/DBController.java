package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Befragung;

public class DBController {


    public void insertBefragung(Befragung befragung) throws SQLException {

        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        String sqlQuery = "insert into polling.befragung(name, seconds) values ('" + befragung.getName() + "', "+ befragung.getSeconds() +")";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while(resultSet.next()){

            System.out.println(resultSet.toString());

        }
    }

}


