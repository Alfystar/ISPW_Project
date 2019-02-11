package control;

import entity.Nickname;
import entity.TaxCode;
import entity.Utente;

public class NodeQueue{
    private Nickname nick;
    private TaxCode tc;
    private Utente us;

    public NodeQueue(Utente user){
        setUs(user);
    }


    public Utente getUs(){
        return us;
    }

    public void setUs(Utente us){
        this.nick = us.getPublic().getNick();
        this.tc = us.getPublic().getTC();
        this.us = us;
    }

    public Nickname getNick(){
        return nick;
    }

    public TaxCode getTC(){
        return tc;
    }

    public void deleteInfo(){
        this.nick = null;
        this.tc = null;
        this.us = null;
    }

    @Override
    public String toString(){
        return this.us.toString();
    }
}
