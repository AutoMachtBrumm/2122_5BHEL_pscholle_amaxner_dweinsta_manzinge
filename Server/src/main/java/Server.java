import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

public class Server {
    private ServerSocket serverSocket;
    private Vector<Befragung> befragungen = new Vector<>();
    public void startServer() {
        try {
            serverSocket = new ServerSocket(10000);
            Socket socket = serverSocket.accept();
            ClientThread ct = new ClientThread(socket,befragungen);
            ct.start();
        } catch (SocketException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
