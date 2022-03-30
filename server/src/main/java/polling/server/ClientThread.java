package polling.server;

import Befragung.*;


import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
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
                        writer.write("ACCEPT\n");
                        writer.flush();
                        break;
                    } else {
                        writer.write("WRONG\n");
                        writer.flush();
                    }
                }
                if (befragung == null) {
                    writer.write("Fragen.Befragung nicht gefunden\n");
                    writer.flush();
                }
            } while (befragung == null);


            for (Frage frage:
                 befragung.getFragen()) {
                writer.write(frage.toJson()+"\n");
                writer.flush();
                String answer=reader.readLine();
                if(frage instanceof FrageText){
                    DBController.insertAntwortText(answer,frage.getId());
                }else if(frage instanceof FrageBool){
                    boolean rv;
                    if (answer.equals("0"))
                        rv=false;
                    else
                        rv=true;

                    DBController.insertAntwortBool(rv,frage.getId());
                }else{
                    DBController.insertAntwortNum(Integer.parseInt(answer),frage.getId());
                }
            }

            writer.write("ENDE\n");
            writer.flush();


            socket.close();
        } catch (IOException e) {
        } catch (NullPointerException e) {
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
