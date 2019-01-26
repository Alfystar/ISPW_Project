package control;

import DAO.DAOMock;
import entity.Nickname;
import entity.Utente;
import exceptions.NickNotDBEx;

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

    public Utente getUser(Nickname nk) throws Exception{
        //todo: verificare che il nick ESISTA

        try {
            return searchUserRam(nk);
        }
        catch (NickNotDBEx e) {
        }
        try {
            return loadUserDB(nk);
        } catch (NickNotDBEx e) {
            throw new
        }
    }

    public Boolean isNickExist(Nickname nk) throws Exception{
        //todo implementarla con due thread
        try {
            if (isNickExistRam(nk) == TRUE) return TRUE;
            else return isNickExistDB(nk);
        }
        catch (NickNotDBEx e){
            throw e;
        }
    }

    public void deleteUser(Nickname nk) throws Exception{
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(GregorianCalendar.YEAR, 10);
            daoFace.deleteNTime(nk, cal);
            coda.remove(nk);
        }
        catch (NickNotDBEx e) {
            throw e;
        }
    }
    public void destroyUser(Nickname nk) throws Exception{
        try {
            daoFace.destroy(nk);
            coda.remove(nk);
        }
        catch (NickNotDBEx e) {
            throw e;
        }
    }
    public void storeUser(Utente us){

        daoFace.storeUserDB(us);
        addUserQueue(us);
    }

    private Utente loadUserDB(Nickname nk) throws Exception {
        //todo verificare esistenza nick
        try {
            Utente us = daoFace.loadFromDB(nk);
            addUserQueue(us);
            return us;
        }
        catch (NickNotDBEx e){
            throw e;
        }
    }
    private Utente searchUserRam(Nickname nk){

        return coda.find(nk);
    }

    private Boolean isNickExistDB(Nickname nk)throws Exception{

        try {
            return daoFace.searchNickDB(nk);
        }
        catch (NickNotDBEx e){
            throw e;
        }
    }

    private Boolean isNickExistRam(Nickname nk){

        if(coda.find(nk) == null) return FALSE;
        else return TRUE;
    }

    private void addUserQueue(Utente user){
        coda.add(user);
    }

}
