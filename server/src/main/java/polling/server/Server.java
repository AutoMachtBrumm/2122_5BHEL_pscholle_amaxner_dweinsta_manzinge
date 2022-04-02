package polling.server;

import Befragung.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Vector;

public class Server extends Thread{



    private ServerSocket serverSocket;
    public ObservableList<Befragung> befragungen = FXCollections.observableArrayList();
    private boolean frunning;

    public void run() {
        try {
            frunning = true;

            while (frunning) {
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

    public void stopNow() {
        try {
            frunning = false;
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

