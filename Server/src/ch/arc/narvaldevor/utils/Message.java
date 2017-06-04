package ch.arc.narvaldevor.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {

    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String pseudo;
    private String message;
    private ArrayList<User> list;
    private ArrayList<User> users;
    private int userCount;
    private String style;
    
    public String getStyle()
    {
    	return style;
    }
    
    public void setStyle(String style)
    {
    	this.style = style;
    }
    
	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public Message() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ArrayList<User> getUserList() {
        return list;
    }

    public void setUserlist(HashMap<String, User> userList) {
        this.list = new ArrayList<>(userList.values());
    }



    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
