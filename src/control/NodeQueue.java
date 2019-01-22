package control;
import entity.*;
public class NodeQueue {
    private Nickname nick;
    private Utente us;

    public NodeQueue(Utente user)
    {
        setUs(user);
    }


    public Utente getUs() {
        return us;
    }

    public void setUs(Utente us) {
        this.nick=us.getPublic().getNickname();
        this.us = us;
    }

    public Nickname getNick() {
        return nick;
    }

    public void deleteInfo()
    {
        this.nick=null;
        this.us=null;
    }

    @Override
    public String toString ()
    {
        return this.us.toString();
    }
}
