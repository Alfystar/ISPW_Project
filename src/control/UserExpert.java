package control;

import entity.Nickname;
import entity.Utente;
import control.Queue;
import control.DAOInterface.*;

public class UserExpert {
    private Queue coda;

    public UserExpert() {
         this.coda = Queue.getQueueSingletonInstance();
    }


    /*
    Cerca un utente, o la prende dalla coda o
    lo materializza in ram col dao e poi la ritorna
     */
    //todo eccezione se l'utente non è trovato

    public Utente getUser(Nickname nk) {



    }

    public Boolean isNickExist(Nickname nk) {
        //todo implementarla con due thread

    }

    public void deleteUser(Nickname nk) {

    }

    public void destroyUser(Nickname nk){
        coda.remove(nk);

    }

    public void storeUser(Utente us){

    }

    private Utente loadUserDB(Nickname nk){

    }

    private Utente searchUserRam(Nickname nk){
        return coda.find(nk);
    }

    private Boolean isNickExistDB(Nickname nk){

    }

    private Boolean isNickExistRam(Nickname nk){

    }

    private void addUserQueue(Utente user){
        coda.add(user);
    }

}
