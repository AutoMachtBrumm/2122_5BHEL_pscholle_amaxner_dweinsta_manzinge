import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private ServerSocket serverSocket;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(10000);
            Socket socket = serverSocket.accept();
            ClientThread ct = new ClientThread(socket);
            ct.start();
            InetAddress adress = socket.getInetAddress();
        } catch (
                SocketException e) {

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
