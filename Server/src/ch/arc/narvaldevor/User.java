package ch.arc.narvaldevor;

import java.io.PrintWriter;

/**
 * Created by NarvalNudiste on 18/05/2017.
 */
public class User {
    private String nickname;
    private int id;
    PrintWriter printWriter;
    public User(PrintWriter _p){
        this.printWriter = _p;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
