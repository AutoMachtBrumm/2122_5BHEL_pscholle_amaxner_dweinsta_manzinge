package sample.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    /* DEFINE SERVER ADDRESS AND PORTS */
    private static final Integer PORT = 10000;
    private static final String ADDRESS = "localhost";

    public static Client client;

    private Client(InetAddress address, int port) throws UnknownHostException {
        try {
            // Create Socket and Buffers
            connectToServer(address, port);
            System.out.println("\nConnected to Server ...");
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
            throw new UnknownHostException("Cant Connect to Server");
        }
    }

    public static Client getClient() throws UnknownHostException {
        if (client == null) {
            client = new Client(InetAddress.getByName(ADDRESS), PORT);
        }
        return client;
    }

    private void connectToServer(InetAddress address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    public void closeServerConnection() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public void sendAuthenticationKey(String key) throws Exception {
        sendDataToServer(key);
        if ("ACCEPT".equals(reader.readLine())) {
            System.out.println("Server: ACCEPT");
        } else if ("NOTACTIVE".equals(reader.readLine())){
            System.out.println("Server: NOTACTIVE");
            throw new Exception("This Questioning is not ACTIVE");
        } else {
            throw new Exception("A-Key might be wrong");
        }
    }

    public String getDataFromServer() {
        String inputLine = null;
        try {
            inputLine = reader.readLine();
            System.out.println("Server: " + inputLine);
        } catch (IOException ex) {
            System.out.println("An error has occurred during data transmission: " + ex.getMessage());
        }
        return inputLine;
    }

    public void sendDataToServer(String message) {
        writer.write(message + "\n");
        writer.flush();
        System.out.println("Client: " + message);
    }
}