package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection conn;

    public static Connection connect(String connectionurl,String user, String password) throws ClassNotFoundException, SQLException {

        if(conn!=null){
            throw new SQLException("Connection already exists");
        }

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(connectionurl, user, password);
        return conn;
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void close() throws SQLException {
        conn.close();
        conn=null;
    }
}

