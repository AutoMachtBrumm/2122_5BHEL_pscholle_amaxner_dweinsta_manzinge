import client.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClient {

    public static void main(String[] args) {

        // Server - Address --> "localhost / 127.0.0.1"
        // Server - Port    --> 10000

        try {
            new Client(InetAddress.getByName("127.0.0.1"), 10000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
