package ch.arc.narldevor.services;

import java.io.PrintWriter;

public interface ServerServices 
{
	//Ajouter un client
	public int addClient(PrintWriter printWriter);
	//Supprimer un client de la liste
	public boolean removeClient(PrintWriter printWriter);
	//Envoyer le message à tous les clients connectés
	public void sendMessageToAllClients(String message);	
}
