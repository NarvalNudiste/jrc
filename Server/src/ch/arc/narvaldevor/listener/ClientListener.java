package ch.arc.narvaldevor.listener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ch.arc.narvaldevor.fxcontroller.ConnectController;
import ch.arc.narvaldevor.fxcontroller.SpykeController;
import ch.arc.narvaldevor.utils.Message;

public class ClientListener implements Runnable
{
	public SpykeController spykeController;
	private Socket socket;
	public String ipAdress;
	public int portNumber;
	public static String pseudo;
	private static ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean isConnect = false;

	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}
	public boolean isConnect() {
		return isConnect;
	}
	//Constructeur: On passe les donnée de connexion
	public ClientListener(String ip, int port, String pseudo, SpykeController spykeController) {
        ipAdress = ip;
        portNumber = port;
        ClientListener.pseudo = pseudo;
        this.spykeController = spykeController;
    }
	@Override
	public void run() 
	{
		try {
            socket = new Socket(ipAdress, portNumber);
            ConnectController.getInstance().changeScene();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
		
		try
		{
			connection();
			//Tant que la connexion est bien établie
			while(socket.isConnected())
			{
				//System.out.println("isConnect");
				Message message = null;
				message = (Message)ois.readObject();
				if(message != null)
            	{
					//System.out.println("Ca passe le if");
					System.out.println("Message recieved: " + message.getMessage()+" "+message.getStyle()+"\n");
					
            		if(message.getStyle().equals("connect"))
            		{
            			spykeController.serverMessage(message);
            			spykeController.updateList(message);
            		}
            		else if(message.getStyle().equals("message"))
            		{
            			spykeController.sendMessage(message);
            		}
            		else if(message.getStyle().equals("deconnection"))
            		{
            			spykeController.serverMessage(message);
            			spykeController.updateList(message);
            		}
            	}
			}

		}
		catch(IOException ex)
		{
			//spykeController.backToLogin();
			ex.getMessage();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//spykeController.backToLogin();
		}
		
		
	}
	/**
	 * Message envoyé par le user depuis la texteArea
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessage(String message) throws IOException 
	{
		Message messag = new Message();
		messag.setPseudo(pseudo);
		messag.setStyle("message");
		messag.setMessage(message);
        oos.writeObject(messag);
        oos.flush();
		
	}
	/**
	 * Message de connexion
	 * @throws IOException
	 */
	public static void connection() throws IOException 
	{
		Message message = new Message();
		message.setPseudo(pseudo);
		message.setStyle("connect");
		message.setMessage("Bienvenu dans le chat "+message.getPseudo()+" ..\n");
		oos.writeObject(message);
	}
}
