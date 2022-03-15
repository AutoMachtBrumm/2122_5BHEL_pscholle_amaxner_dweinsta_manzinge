import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

public class Server {
    private ServerSocket serverSocket;
    public Vector<Befragung> befragungen = new Vector<>();
    public void startServer() {
        try {
            while (true) {
                serverSocket = new ServerSocket(10000);
                Socket socket = serverSocket.accept();
                ClientThread ct = new ClientThread(socket, befragungen);
                ct.start();
            }
        } catch (SocketException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ConsoleReaderThread extends Thread{
    ServerSocket socket;
    public ConsoleReaderThread(ServerSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String input = null;
            while (!input.equals("Exit")){
                input=System.console().readLine();
            }
        }
}
