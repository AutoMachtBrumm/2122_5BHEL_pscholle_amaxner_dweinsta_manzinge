package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static Socket socket = null;
    private static OutputStreamWriter output = null;
    private static InputStreamReader input = null;

    public static void connectToServer(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        output = new OutputStreamWriter(socket.getOutputStream());
        input = new InputStreamReader(socket.getInputStream());
    }

    public static void closeServerConnection() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public static String readStringFromServ() {
        StringBuilder input = new StringBuilder();
        BufferedReader reader = new BufferedReader(Client.input);
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line);
            }
            return input.toString();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return null;
    }

    public static void writeStringToServ(String json) {
        BufferedWriter writer = new BufferedWriter(output);
        try {
            writer.write(json);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}