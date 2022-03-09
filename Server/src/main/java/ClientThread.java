import Fragen.Frage;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

public class ClientThread extends Thread {
    private Socket socket;
    Vector<Befragung> befragungen;
    Befragung befragung;

    public ClientThread(Socket socket, Vector<Befragung> befragungen) {
        this.socket = socket;
        this.befragungen = befragungen;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            do {
                String befname = reader.readLine();

                for (Befragung bef :
                        befragungen) {
                    if (bef.getName().equals(befname)) {
                        befragung = bef;
                        break;
                    }
                }
                if (befragung == null) {
                    writer.write("Befragung nicht gefunden\n");
                    writer.flush();
                }
            } while (befragung == null);


            for (Frage frage:
                 befragung.getFragen()) {
                writer.write("");
            }

            socket.close();
        } catch (IOException e) {
        } catch (NullPointerException e) {
        }

    }
}
