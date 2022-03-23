package sample.client;

import sample.client.utils.QuestionHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(InetAddress address, int port) {
        try {

            // Create Socket
            this.socket = new Socket(address, port);
            System.out.println("\nConnected to Server ...");
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
            System.out.println("Sending Authentication Key ...");
            // Send Authentication Key
            sendDataToServ("Befragung1");

            String response;
            while (isRunning) {
                // Response --> Question

                response = reader.readLine();
                System.out.println("Server:" + response);

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

    private void sendDataToServ(String string) throws IOException {
        System.out.println("Client: " + string);
        writer.write(string);
        writer.flush();
    }
}