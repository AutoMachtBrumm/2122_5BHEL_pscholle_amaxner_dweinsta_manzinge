package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

public class Client {

    private Socket socket = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    public Client(InetAddress address, int port){

        try {
            // Creates Socket for Client & Buffers
            connectToServer(address, port);

            // Send Authentication Key
            sendDataToServ("Befragung1");

            String jsonString = "";

            // Repeat process until Server send "ENDE"
            while(jsonString.equals("ENDE")) {
                // Receive --> JSON String form Server
                jsonString = getDataFromServ();
                System.out.println(jsonString);
                String answer = QuestionHandler.editJSON(jsonString);
                sendDataToServ(answer);
            }

            // Close Socket
            closeServerConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        // Create Buffers
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private void closeServerConnection() throws IOException {
        if (!socket.isClosed()) {
            reader.close();
            writer.close();
            socket.close();
        }
    }

    private String getDataFromServ() {
        StringBuilder inputString = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            return inputString.toString();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return null;
    }

    private void sendDataToServ(String json) {
        try {
            writer.write(json);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}