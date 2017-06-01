package ch.arc.narvaldevor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by NarvalNudiste on 18/05/2017.
 */
public class Server implements Observer{
    private SocketList sockets;
    
    public Server(){
        sockets = new SocketList();
        sockets.addObserver(this);

        ClientHandler test = new ClientHandler(sockets, 2018);
        test.run();

    }

    @Override
    public void update(Observable o, Object arg) {
        writeToAll("chaussette");
    }

    private void writeToAll(String message){
        for (Socket s : sockets.getSockets()){
            PrintWriter writer = null;
            try{
                writer = new PrintWriter(s.getOutputStream());
                writer.println(message);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                writer.close();
            }
        }
    }
}
