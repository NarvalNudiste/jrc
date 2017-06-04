package ch.arc.narvaldevor.utils;

import java.io.Serializable;

/**
 * 
 * @author charlesombangndo
 * Simple classe pour les informations du user
 *
 */
public class User implements Serializable
{

	public User(String pseudo)
	{
		this.pseudo = pseudo;
	}
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	private String pseudo;
}
