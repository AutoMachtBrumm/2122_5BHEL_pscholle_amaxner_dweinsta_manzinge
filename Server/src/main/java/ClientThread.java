import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

public class ClientThread extends Thread{
    private Socket socket;
    Vector<Befragung> befragungen;
    public ClientThread(Socket socket, Vector<Befragung> befragungen) {
        this.socket = socket;
        this.befragungen=befragungen;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String befragung=reader.readLine();

            socket.close();
        } catch (IOException e) {
        } catch (NullPointerException e) {
        }

    }
}
