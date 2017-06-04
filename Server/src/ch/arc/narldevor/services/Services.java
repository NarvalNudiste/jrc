package ch.arc.narldevor.services;

import java.io.PrintWriter;

import ch.arc.narvaldevor.utils.Message;

public interface Services 
{
	//Message de bienvenu
	public Message notification(Message message);
	//Supprimer un client de la liste
	public boolean removeClient(PrintWriter printWriter);
	//Envoyer le message à tous les clients connectés
	public void sendMessage(Message message);
	//Ajout au chat
	public Message welcome();
	//Supprimer un user de la liste
	public Message delete();
}
