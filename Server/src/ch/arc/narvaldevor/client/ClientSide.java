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
	private Socket echoSocket;
	PrintWriter printerOut;
	
	public PrintWriter getPrinterOut() {
		return printerOut;
	}
	public void setPrinterOut(PrintWriter printerOut) {
		this.printerOut = printerOut;
	}

	BufferedReader bufferedIn;
	BufferedReader stdIn;
	private String username;
	
	public boolean isConnect()
	{
		return this.isConnect;
	}
	public ClientSide(String username, String ip, int portNumber)
	{
		this.username = username;
		try 
				
		{
			echoSocket = new Socket(ip, portNumber);
			printerOut = new PrintWriter(echoSocket.getOutputStream(), true);
			bufferedIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			if(echoSocket.isConnected())
			{
				isConnect = true;
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
	
	public void sendMessage(String message)
	{
		printerOut.println(this.username+" dit: "+message);
	}
}

