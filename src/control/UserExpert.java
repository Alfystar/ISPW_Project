package control;

import DAO.DAOClass;
import bean.UserInfoRegister;
import entity.*;
import exceptions.*;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class UserExpert{
    private Queue coda;
    private DAOInterface daoFace;

    public UserExpert(){
        try{
            this.coda = Queue.getQueueSingletonInstance();
            this.daoFace = new DAOClass();
        }catch(ClassNotFoundException c){
            c.printStackTrace();
            System.exit(-1);
        }
    }

    /*
    Cerca un utente, o la prende dalla coda o
    lo materializza in ram col dao e poi lo ritorna
     */
    public Utente getUser(Nickname nk) throws UserNotExistEx{
        try{
            return searchUserRam(nk);
        }catch(NickNotQEx qEx){
            System.err.println(qEx.getMessage());
        }
        try{
            return loadUserDB(nk);
        }catch(NickNotDBEx dbEx){
            throw new UserNotExistEx("getUser failed", dbEx.getCause());
        }catch(SQLException se){
            se.printStackTrace();
            throw new UserNotExistEx("getUser failed", se.getCause());
        }
    }

    public Boolean isNickExist(Nickname nk) throws UserNotExistEx{
        //todo implementarla con due thread
        try{
            return isNickExistRam(nk);
        }catch(NickNotQEx qEx){
            System.err.println(qEx.getMessage());
        }
        try{
            return isNickExistDB(nk);
        }catch(NickNotDBEx dbEx){
            throw new UserNotExistEx("Nick not present in DB", dbEx.getCause());
        }
    }

    public Boolean doesTaxCodeExist(TaxCode tc) throws UserNotExistEx{
        //todo implementarla con due thread
        try{
            return isTCExistRam(tc);
        }catch(TCNotExistQEx qEx){
            System.err.println(qEx.getMessage());
        }
        try{
            return this.daoFace.searchTC(tc);
        }catch(SQLException dbEx){
            throw new UserNotExistEx("TaxCode not present in DB", dbEx.getCause());
        }

    }

    public void deleteUser(Nickname nk) throws UserNotExistEx{
        try{
            Utente user = getUser(nk);
            user.setStatus(UserStatus.CANCELLED);

            GregorianCalendar cal = new GregorianCalendar();
            cal.add(GregorianCalendar.YEAR, 10);
            daoFace.deleteNTime(nk, cal);
            try{
                daoFace.updateUser(user);
            }catch(NickNotDBEx e){
                throw new UserNotExistEx(e);
            }
            coda.remove(nk);
        }catch(SQLException se){
            throw new UserNotExistEx("Nick not present in DB", se.getCause());
        }
    }

    public void destroyUser(Nickname nk) throws UserNotExistEx{
        try{
            daoFace.destroy(nk);
            coda.remove(nk);
        }catch(NickNotDBEx dbEx){
            throw new UserNotExistEx("destroyUser failed", dbEx.getCause());
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void storeUser(Utente us){
        try{
            daoFace.updateUser(us);
            addUserQueue(us);
        }catch(SQLException|NickNotDBEx se){
            se.printStackTrace();
        }
    }

    public void createUser(UserInfoRegister userInfo) throws WrongParameters{
        Utente user = this.daoFace.createUser(userInfo);
        System.out.println(gregCalToString(user.getPublic().getBirthday()));
        coda.add(user);
    }

    public void forgottenPassword(Nickname nick, Questions answers, PW newPw) throws UserNotExistEx{
        Utente user = this.getUser(nick);
        Questions correctAnsw = user.getQuestions();
        if(correctAnsw.checkAnswers(answers, 4)){
            user.changePw(user.getPw(), newPw);
            this.storeUser(user);
        }else{
            System.out.println("Le risposte fornite sono sbagliate");
        }
    }

    public void changeUserStatus(Nickname nick, UserStatus newUsStat) throws UserNotExistEx{
        Utente user = this.getUser(nick);
        user.setStatus(newUsStat);
        this.storeUser(user);
    }


    public void setAvatar(Nickname nk, int id) throws UserNotExistEx{
        Utente us = this.getUser(nk);
        us.getPublic().getAvatar().setMyIcon(id);
        this.storeUser(us);

    }

    public void recoverProfile(Nickname nk) throws UserNotExistEx, SQLException{
        Utente us = this.getUser(nk);
        us.setStatus(UserStatus.ACTIVE);
        daoFace.removeDataEvent(nk);
        this.storeUser(us);
    }

    private Utente loadUserDB(Nickname nk) throws NickNotDBEx, SQLException{
        try{
            Utente us = daoFace.loadFromDB(nk);
            addUserQueue(us);
            return us;
        }catch(NickNotDBEx e){
            throw new NickNotDBEx("Nickname not in DB");
        }
    }

    private Utente searchUserRam(Nickname nk) throws NickNotQEx{
        try{
            return coda.find(nk);
        }catch(NickNotQEx qEx){
            System.err.println("Search in Queue failed.");
            throw qEx;
        }
    }

    private Boolean isNickExistDB(Nickname nk) throws NickNotDBEx{
        try{
            return daoFace.searchNickDB(nk);
        }catch(SQLException sq){
            System.err.println("SQL error");
            throw new NickNotDBEx(sq);
        }catch(Exception e){
            System.err.println("Java error during search");
            throw new NickNotDBEx(e);
        }
    }

    private Boolean isNickExistRam(Nickname nk) throws NickNotQEx{
        if(coda.find(nk) == null) return FALSE;
        else return TRUE;
    }

    private Boolean isTCExistRam(TaxCode tc) throws TCNotExistQEx{
        if(coda.find(tc) == null) return FALSE;
        else return TRUE;
    }

    private void addUserQueue(Utente user){
        coda.add(user);
    }

    private GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int days = Integer.parseInt(splitDate[2]);
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, days);
        return gc;

    }

    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }


    //todo: scrivere un metodo privato che verifichi che i parametri di infoRegister siano adatti
}
