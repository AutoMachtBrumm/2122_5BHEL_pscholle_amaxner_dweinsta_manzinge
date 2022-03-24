package sample.client;

import sample.client.utils.QuestionHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(InetAddress address, int port) throws UnknownHostException {
        try {
            // Create Socket
            connectToServer(address, port);
            System.out.println("\nConnected to Server ...");
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
            throw new UnknownHostException("Cant Connect to Server");
        }
    }

    private void connectToServer(InetAddress address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
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

    public void startCommunication() {
        new Thread(() -> {
            String inputLine;
            try {
                while ((inputLine = reader.readLine()) != null) {
                    if ("ENDE".equals(inputLine)) break;
                    System.out.println("Server: " + inputLine);
                    String answer = QuestionHandler.getAnswer(inputLine);
                    sendDataToServer(answer);
                }
            } catch (IOException ex) {
                System.out.println("An error has occurred during data transmission: " + ex.getMessage());
            } finally {
                // Close Connection to Server
                closeServerConnection();
                System.out.println("Close Client - Server Connection");
            }
        }).start();
    }

    public void sendAuthenticationKey(String key) throws IOException {
        sendDataToServer(key);
        if(!"ACCEPT".equals(reader.readLine())){
            throw new IOException();
        }
        System.out.println("Server: ACCEPT");
    }

    public void sendDataToServer(String message) {
        writer.write(message);
        writer.flush();
        System.out.println("Client: " + message);
    }
}