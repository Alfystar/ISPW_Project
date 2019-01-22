package control;

import entity.Nickname;
import entity.Utente;

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

    }

    public void deleteUser(Nickname nk) {

    }

    public void destroyUser(Nickname nk){

    }

    public void storUser(Utente us){

    }

    private Utente loadUserDB(Nickname nk){

    }

    private Utente searchUserRam(Nickname nk){

    }

    private Boolean isNickExistDB(Nickname nk){

    }

    private Boolean isNickExistRam(Nickname nk){

    }

    private void addUserQueue(Utente user){

    }

}
