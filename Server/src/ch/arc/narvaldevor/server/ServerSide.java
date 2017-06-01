package ch.arc.narvaldevor.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.arc.narldevor.services.ServerServices;
import ch.arc.narvaldevor.User;
import ch.arc.narvaldevor.requesthandler.RequestHandler;

public class ServerSide implements ServerServices
{
	//Membres de la classe
	private Socket clientSocket;
	//private ServerSocket serverSocket;
	private int numberOfClients;
	private List<PrintWriter> clientList = new ArrayList<PrintWriter>();
	private List<User> userList = new ArrayList<User>();
	private int portNumber = 1234;
	private ExecutorService threadPool = null;
	
	//Constructeur
	
	public ServerSide()
	{
		run();
	}
	
	public void run()
	{
		//On crée tout d'abord la socket
		try(ServerSocket serverSocket = new ServerSocket(portNumber))
		{
			threadPool = Executors.newFixedThreadPool(5);
			System.out.println("En attente de client ...");
			
			//Bouble écoutant les demande de connexion
			while(true)
			{
				this.clientSocket = serverSocket.accept();
				System.out.println("Client: "+this.numberOfClients);
				//Instanciation du Thread chargé des requêtes
				Runnable worker = new RequestHandler(this.clientSocket, this);
				//Exécution
				threadPool.execute(worker);
			}
		} 
		catch (IOException ex) 
		{
			System.out.println(ex.getMessage());
			System.exit(1);
		}
		finally 
		  {
	        if (threadPool != null) 
	        {
	        	threadPool.shutdown();
	        }
		  }
	}
	public int getLastId(){
		return this.userList.size()-1;
	}

	@Override
	public int addClient(PrintWriter printWriter) 
	{
		
		this.numberOfClients++;
		this.clientList.add(printWriter);
		
		return this.numberOfClients;
	}

	public void addUser(PrintWriter _p){
		this.userList.add(new User(_p));
		this.userList.get(userList.size()-1).setId(this.numberOfClients);
		this.numberOfClients++;
	}
	@Override
	public boolean removeClient(PrintWriter printWriter){
		this.numberOfClients--;
		return this.clientList.remove(printWriter);
	}

	public void setUserNickName(int id, String nick){
		userList.get(id).setNickname(nick);
		System.out.println("user n#" + id + " nickname : " + userList.get(id).getNickname());
	}
	@Override
	public void sendMessageToAllClients(String message) {
		
		for (PrintWriter printWriter: this.clientList){
			printWriter.println(message);
		}
		
	}
}
