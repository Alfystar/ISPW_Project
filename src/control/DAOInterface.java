package control;

import bean.UserInfoRegister;
import entity.Nickname;
import entity.TaxCode;
import entity.Utente;
import exceptions.UserNotInDBException;

import java.util.GregorianCalendar;

public interface DAOInterface {

    Utente createUser(UserInfoRegister infoReg);

    Utente loadFromDB(Nickname nickname) throws UserNotInDBException;

    void storeUserDB(Utente user);

    Boolean searchNickDB(Nickname nickname) throws UserNotInDBException;

    Boolean searchTC(TaxCode cf) throws UserNotInDBException;

    void destroy(Nickname nickname) throws UserNotInDBException;

    void deleteNTime(Nickname nickname, GregorianCalendar date) throws UserNotInDBException;


}
