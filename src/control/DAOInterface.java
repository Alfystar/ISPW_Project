package control;

import bean.UserInfoRegister;
import entity.Nickname;
import entity.TaxCode;
import entity.Utente;

import java.util.GregorianCalendar;

public interface DAOInterface {
    Utente loadFromDB(Nickname nickname);
    Boolean searchNickDB(Nickname nickname);
    Boolean searchTC(TaxCode cf);
    void destroy(Nickname nickname);
    void deleteNTime(Nickname nickname, GregorianCalendar date);
    Utente createUser(UserInfoRegister infoReg);
}
