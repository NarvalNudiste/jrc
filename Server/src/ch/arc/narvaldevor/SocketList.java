package ch.arc.narvaldevor;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by NarvalNudiste on 18/05/2017.
 */
public class SocketList extends Observable {
    //attr
    private ArrayList<Socket> sockets;

    //ctor
    public SocketList(){
        this.sockets = new ArrayList<Socket>();
    }

    //methods
    public void addSocket(Socket _socket){
        this.sockets.add(_socket);
        this.setChanged();
        notifyObservers();
    }

    //overrides
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }
    //getSet

    public ArrayList<Socket> getSockets() {
        return sockets;
    }

    public void setSockets(ArrayList<Socket> sockets) {
        this.sockets = sockets;
    }
}
