package ch.arc.narvaldevor.requesthandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import ch.arc.narldevor.services.Services;
import ch.arc.narvaldevor.fxcontroller.ConnectController;
import ch.arc.narvaldevor.utils.Message;
import ch.arc.narvaldevor.utils.User;
import java.net.SocketException;

public class RequestHandler extends Thread implements Services {

    private Socket client;
    private String pseudo;
    private User user;
    private static HashSet<ObjectOutputStream> writers = new HashSet<>();
    private static final HashMap<String, User> pseudoHm = new HashMap<>();
    private static ArrayList<User> users = new ArrayList<>();

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        try (InputStream is = client.getInputStream();
                ObjectInputStream input = new ObjectInputStream(is);
                OutputStream os = client.getOutputStream();
                ObjectOutputStream output = new ObjectOutputStream(os)) {
            out = output;
            Message message = (Message) input.readObject();
            if(verified(message)){
                writers.add(output);
                notification(message);
                //On balance le message de bienvenue à la connexion du client
                welcome();

                while (this.client.isConnected()) {
                    //System.out.println("Handler connect");
                    Message messageInput = (Message) input.readObject();
                    if (messageInput != null) {
                        if (messageInput.getStyle().equals("connect")) {
                            this.welcome();
                        } else if (messageInput.getStyle().equals("message")) {
                            this.sendMessage(messageInput);
                        } else if (messageInput.getStyle().equals("signal")) {
                            this.notification(messageInput);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        } finally {
            this.closeConnection(out);
        }
    }

    @Override
    public Message notification(Message message) {
        Message message_ = new Message();
        message_.setMessage("a rejoint le chat\n");
        message_.setStyle("signal");
        message_.setPseudo(message.getPseudo());
        //Envoie du message
        sendMessage(message);
        return message;
    }

    /**
     *
     * @param message On va parcourir la liste de listener, en envoyer le style
     * de message
     */
    @Override
    public void sendMessage(Message message) {
        for (ObjectOutputStream oos : writers) {
            message.setUserlist(pseudoHm);
            message.setUsers(users);
            System.out.println(oos.toString() + " " + message.getPseudo() + " " + message.getUsers().toString());
            try {
                oos.writeObject(message);
                oos.reset();
            } catch (Exception ex) {
                //ex.getMessage();
                ConnectController.getInstance().getErrorLabel().setText("Utilisateur déjà existant");
                ConnectController.getInstance().getErrorLabel().setVisible(true);
                this.closeConnection(oos);
            }
        }

    }

    /**
     * On va tester si le user spécifié avec ce pseudo est déjà connecté au
     * serveur
     *
     * @param message
     */
    private synchronized boolean verified(Message message) {
        if (!pseudoHm.containsKey(message.getPseudo())) {
            pseudo = message.getPseudo();
            user = new User(this.pseudo);
            //Ajout du user à la liste
            users.add(user);
            //Ajout à la map
            pseudoHm.put(pseudo, user);
            return true;
        } else {
            System.out.println("USer déjà connté au serveur");
            return false;
        }
    }

    @Override
    public boolean removeClient(PrintWriter printWriter) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Informe qu'un utilisateur rejoint le chat (s'est connecté au seveur)
     */
    @Override
    public Message welcome() {
        Message message = new Message();
        message.setMessage("Bienvenue dans le chat ");
        message.setStyle("connection");
        message.setPseudo("server");
        //Envoie du message
        sendMessage(message);
        return message;
    }

    @Override
    public Message delete() {
        Message message = new Message();
        message.setMessage("Un utilisateur a quitté le chat\n");
        message.setStyle("deconnection");
        message.setPseudo("server");
        message.setUserlist(pseudoHm);
        sendMessage(message);
        return message;
    }

    /**
     * Supprimer l'objet writer et fermer la connexion lorsque l'utilisateur est
     * déconnecté
     */
    private synchronized void closeConnection(ObjectOutputStream writer) {
        if (pseudo != null) {
            pseudoHm.remove(pseudo);
            System.out.println("L'utilisateur: " + pseudo + " a étét supprimé");
        }
        if (user != null) {
            users.remove(user);
            System.out.println("Objet supprimé");
        }
        if (writer != null) {
            writers.remove(writer);
            System.out.println("Writer supprimé");
        }
        try {
            this.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
