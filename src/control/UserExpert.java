package control;

import DAO.DAOMock;
import entity.Nickname;
import entity.Utente;
import control.Queue;
import control.DAOInterface.*;

import java.util.GregorianCalendar;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class UserExpert {
    private Queue coda;
    private DAOInterface daoFace;

    public UserExpert() {

        this.coda = Queue.getQueueSingletonInstance();
        this.daoFace = new DAOMock();

    }


    /*
    Cerca un utente, o la prende dalla coda o
    lo materializza in ram col dao e poi la ritorna
     */
    //todo eccezione se l'utente non è trovato

    public Utente getUser(Nickname nk) {

        Utente us = searchUserRam(nk);

        if( us != null ) return us;
        else return loadUserDB(nk);
    }

    public Boolean isNickExist(Nickname nk) {
        //todo implementarla con due thread

        if (isNickExistRam(nk) == TRUE) return TRUE;
        else return isNickExistDB(nk);

    }

    public void deleteUser(Nickname nk) {

        GregorianCalendar cal = new GregorianCalendar();
        cal.add(GregorianCalendar.YEAR, 10);
        daoFace.deleteNTime(nk, cal);
        coda.remove(nk);
    }

    public void destroyUser(Nickname nk){

        daoFace.destroy(nk);
        coda.remove(nk);
    }

    public void storeUser(Utente us){

        daoFace.storeUserDB(us);
        addUserQueue(us);
    }

    private Utente loadUserDB(Nickname nk){

        Utente us = daoFace.loadFromDB(nk);
        addUserQueue(us);
        return us;
    }

    private Utente searchUserRam(Nickname nk){

        return coda.find(nk);
    }

    private Boolean isNickExistDB(Nickname nk){

        return daoFace.searchNickDB(nk);
    }

    private Boolean isNickExistRam(Nickname nk){ // todo: verifica utilità

        if(coda.find(nk) == null) return FALSE;
        else return TRUE;
    }

    private void addUserQueue(Utente user){ // todo: verifica utilità
        coda.add(user);
    }

}
