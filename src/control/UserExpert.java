package control;

import DAO.DAOMock;
import entity.Nickname;
import entity.Utente;
import exceptions.NickNotDBEx;
import exceptions.NickNotQEx;
import exceptions.UserNotExistEx;

import java.sql.SQLException;
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

    public Utente getUser(Nickname nk) throws UserNotExistEx {

        try {
            return searchUserRam(nk);
        }
        catch (NickNotQEx qEx) {
            System.err.println(qEx.getMessage());
        }
        try {
            return loadUserDB(nk);
        }
        catch (NickNotDBEx dbEx) {
            throw new UserNotExistEx("getUser failed", dbEx.getCause());
        }
    }

    public Boolean isNickExist(Nickname nk) throws UserNotExistEx{
        //todo implementarla con due thread
        try {
            return isNickExistRam(nk);
        }
        catch (NickNotQEx qEx) {
            System.err.println(qEx.getMessage());
        }
        try{
            return isNickExistDB(nk);
        }
        catch (NickNotDBEx dbEx){
            throw new UserNotExistEx("isNickExistFailed", dbEx.getCause());
        }
    }

    public void deleteUser(Nickname nk) throws UserNotExistEx{
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(GregorianCalendar.YEAR, 10);
            daoFace.deleteNTime(nk, cal);
            coda.remove(nk);
        }
        catch (NickNotDBEx dbEx) {
            throw new UserNotExistEx("deleteUser failed", dbEx.getCause());
        }
    }
    public void destroyUser(Nickname nk) throws UserNotExistEx{
        try {
            daoFace.destroy(nk);
            coda.remove(nk);
        }
        catch (NickNotDBEx dbEx) {
            throw new UserNotExistEx("destroyUser failed", dbEx.getCause());
        }
    }
    public void storeUser(Utente us){

        daoFace.storeUserDB(us);
        addUserQueue(us);
    }

    private Utente loadUserDB(Nickname nk) throws NickNotDBEx {

        try {
            Utente us = daoFace.loadFromDB(nk);
            addUserQueue(us);
            return us;
        }
        catch (NickNotDBEx e){
            throw new NickNotDBEx("Nickname not in DB");
        }
    }
    private Utente searchUserRam(Nickname nk) throws NickNotQEx{

        try {
            return coda.find(nk);
        }
        catch (NickNotQEx qEx){
            System.err.println("Search in Queue failed.");
            throw qEx;
        }
    }

    private Boolean isNickExistDB(Nickname nk) throws NickNotDBEx{

        try {
            return daoFace.searchNickDB(nk);
        }
        catch (SQLException sq){
            System.err.println("SQL error");
            throw new NickNotDBEx(sq);
        }
        catch (Exception e){
            System.err.println("Java error during search");
            throw new NickNotDBEx(e);
        }

    }

    private Boolean isNickExistRam(Nickname nk) throws NickNotQEx{

        if(coda.find(nk) == null) return FALSE;
        else return TRUE;
    }

    private void addUserQueue(Utente user){
        coda.add(user);
    }


    //todo: scrivere un metodo privato che verifichi che i parametri di infoRegister siano adatti
}
