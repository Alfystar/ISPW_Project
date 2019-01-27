package control;

import bean.UserInfoRegister;
import entity.Nickname;
import entity.TaxCode;
import entity.Utente;
import exceptions.NickNotDBEx;
import exceptions.TCNotExistEx;

import java.util.GregorianCalendar;

public interface DAOInterface {

    Utente createUser(UserInfoRegister infoReg);

    Utente loadFromDB(Nickname nickname) throws NickNotDBEx;

    void storeUserDB(Utente user);

    Boolean searchNickDB(Nickname nickname) throws NickNotDBEx;

    Boolean searchTC(TaxCode cf) throws TCNotExistEx;

    void destroy(Nickname nickname) throws NickNotDBEx;

    void deleteNTime(Nickname nickname, GregorianCalendar date) throws NickNotDBEx;


}
