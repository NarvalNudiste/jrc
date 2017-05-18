package ch.arc.narvaldevor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by NarvalNudiste on 04/05/2017.
 */
public class SimpleClient {
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader io = null;

        System.out.println("Client is starting");
        try {
            socket = new Socket("127.0.0.1", 2018);
            System.out.println("CLIENT - Reading from server");
            io = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("CLIENT - from server: " + io.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                io.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
