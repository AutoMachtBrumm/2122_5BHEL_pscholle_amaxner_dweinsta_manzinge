import client.Client;

import java.io.IOException;
import java.net.InetAddress;

public class MainClient {

    public static void main(String[] args) {

        // Server - Address --> "localhost"
        // Server - Port    --> 10000

        try {
            Client.connectToServer(InetAddress.getLocalHost(), 10000);

            String message = "Befragung1";

            // Send Request to Server
            Client.writeStringToServ(message);

            // Receive Answer from Server
            String receiveString = Client.readStringFromServ();

            System.out.println(receiveString);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
