package ch.arc.narvaldevor.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide
{
	boolean isConnect = false;
	
	public boolean isConnect()
	{
		return this.isConnect;
	}
	public ClientSide(String username, String ip, int portNumber)
	{
		try (Socket echoSocket = new Socket(ip, portNumber);
    		
			  PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			  BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			  BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) 
		{
			//if(echoSocket.isConnected())
			//{
				//isConnect = true;
			//}
			String userInput;
			while ((userInput = stdIn.readLine()) != null) 
			{
				out.println(userInput);
				System.out.println(username +" dit: "+ in.readLine());
      
			}
		}
		catch (UnknownHostException ex) 
		{
			ex.getMessage();
		}
		catch (IOException ex) 
		{
			ex.getMessage();
		} 
	}
}

