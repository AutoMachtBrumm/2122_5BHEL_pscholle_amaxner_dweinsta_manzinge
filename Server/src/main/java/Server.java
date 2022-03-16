import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Server {

    static final String url = "jdbc:postgresql://xserv:5432/pscholle";
    static final String user = "reader";
    static final String password = "reader";

    private ServerSocket serverSocket;
    public Vector<Befragung> befragungen = new Vector<>();
    private boolean frunning;

    public void startServer() {
        try {
            frunning = true;
            DBConnector.connect(url, user, password);

            for (Befragung befragung:
                 befragungen) {
                DBController.insertBefragung(befragung);
            }

            ConsoleReaderThread crt = new ConsoleReaderThread(this);
            crt.start();
            while (frunning) {
                serverSocket = new ServerSocket(10000);
                Socket socket = serverSocket.accept();
                ClientThread ct = new ClientThread(socket, befragungen);
                ct.start();
            }
        } catch (SocketException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stopNow() {
        try {
            frunning = false;
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ConsoleReaderThread extends Thread {
    Server server;

    public ConsoleReaderThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            String input = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                input = br.readLine();
                switch (input) {
                    case "Exit":
                        server.stopNow();
                }
            } while (!input.equals("Exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
