package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(InetAddress address, int port) {
        try {

            // Create Socket
            this.socket = new Socket(address, port);
            // Starts ClientThread
            new ClientThread(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientThread extends Thread {

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    private boolean isRunning = false;

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        // Starts ClientThread
        isRunning = true;
        try {
            // Send Authentication Key
            sendDataToServ("Befragung1");

            String response;
            while (isRunning) {
                // Response --> Question
                response = getDataFromServ();
                System.out.println("Server: " + response);

                if (response.equals("ENDE")) {
                    isRunning = false;
                    System.out.println("Client-Server Connection closed");
                    break;
                }
                String answer = QuestionHandler.editJSON(response);
                System.out.println("Client: " + answer);
                sendDataToServ(answer);
            }
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        } finally {
            closeServerConnection();
        }
    }

    private void closeServerConnection() {
        try {
            if (!socket.isClosed()) {
                reader.close();
                writer.close();
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    private String getDataFromServ() throws IOException {
        StringBuilder inputString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        return inputString.toString();
    }

    private void sendDataToServ(String json) throws IOException {
        writer.write(json);
        writer.flush();
    }
}