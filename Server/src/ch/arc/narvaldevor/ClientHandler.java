package ch.arc.narvaldevor;

import javafx.beans.Observable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NarvalNudiste on 18/05/2017.
 */
public class ClientHandler implements Runnable {

    private SocketList sockets;
    private int porc;
    private ServerSocket serverSocket = null;
    
    public ClientHandler(SocketList _sockets, int _porc){
        this.sockets = _sockets;
        this.porc = _porc;
    }
    @Override
    public void run() {
        while (true){
            try{
                serverSocket = new ServerSocket(porc);
                System.out.println("socket created, waiting for client .. ");
                sockets.addSocket(serverSocket.accept());
                System.out.println("Connection accepted");
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setSockets(SocketList _sockets) {
        this.sockets = _sockets;
    }
}
