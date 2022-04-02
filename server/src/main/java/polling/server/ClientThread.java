package polling.server;

import Befragung.*;


import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ClientThread extends Thread {
    private Socket socket;
    List<Befragung> befragungen;
    Befragung befragung;

    public ClientThread(Socket socket, List<Befragung> befragungen) {
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

                for (Befragung bef : befragungen) {
                    if (bef.getName().equals(befname)) {
                        if(bef.isActive()){
                            befragung = bef;
                            break;
                        }else {
                            writer.write("NOTACTIVE\n");
                            writer.flush();
                            break;
                        }
                    }
                }
                if (befragung == null) {
                    writer.write("WRONG\n");
                    writer.flush();
                }
            } while (befragung == null);
            writer.write("ACCEPT\n");
            writer.flush();

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
