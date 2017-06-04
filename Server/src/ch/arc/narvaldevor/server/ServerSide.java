package ch.arc.narvaldevor.server;

import java.io.IOException;
import java.net.ServerSocket;
//import ch.arc.narvaldevor.User;
import ch.arc.narvaldevor.requesthandler.RequestHandler;

public class ServerSide
{
    
	private static int PORTNUMBER = 1234;
	private static ServerSocket listener;
	
	
	public static void main(String[] args) throws IOException
	{
		listener = new ServerSocket(PORTNUMBER);
		try
		{
			System.out.println("Attente de connexion...");
			while(true)
			{
				new RequestHandler(listener.accept()).start();
				System.out.println("Client connecté...");
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		finally
		{
			listener.close();
		}
	}
}
