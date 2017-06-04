package ch.arc.narvaldevor.client;

import java.io.IOException;

public class UseClient 
{

	public static void main(String[] args) throws IOException 
	{
		ClientSide client = new ClientSide("Julien","127.0.0.1", 1234);
		//client.sendMessage();
	}

}
