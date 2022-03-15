import client.Client;
import client.QuestionHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClient {

    public static void main(String[] args) {

        // Server - Address --> "localhost"
        // Server - Port    --> 10000

        try {
            new Client(InetAddress.getLocalHost(), 10000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
