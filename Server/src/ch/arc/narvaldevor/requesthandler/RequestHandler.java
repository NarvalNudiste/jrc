package ch.arc.narvaldevor.requesthandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.arc.narvaldevor.server.ServerSide;

public class RequestHandler implements Runnable {

	private Socket client;
	private ServerSide server = null;
	private int numberOfClient;
	private int userId;
	public RequestHandler(Socket client, ServerSide server)
	{
		this.server = server;
		this.client = client;
	}
	
	@Override
	public void run() {
		
		try(BufferedReader bufferin = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream())))
		{
			//On ajoute le client dans la liste coté serveur et on récupère le nombre de clients
			numberOfClient = server.addClient(writer);
			this.setUserId(server.getLastId());

			//System.out.println("Nom du thread: "+Thread.currentThread().getName());
			String message;
			
			while((message = bufferin.readLine()) != null)
			{
				if (message.contains("/setNickname")){
					String nick = message.replace("/setNickname", "");
					server.setUserNickName(this.userId, nick);
				}

				System.out.println("Le client "+numberOfClient+" dit: "+message);
				//On envoie le message à tous les clients connecté via la méthode sendAll...coté serveur
				server.sendMessageToAllClients(message);
				writer.flush();
			}
		} 
		catch (IOException e) 
		{
			e.getMessage();
		}
		catch (Exception ex) 
		{
			ex.getMessage();
		}

	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
