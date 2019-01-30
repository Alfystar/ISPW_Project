package control;

import bean.UserInfoRegister;
import entity.Nickname;
import entity.TaxCode;
import entity.Utente;
import exceptions.NickNotDBEx;
import exceptions.TCNotExistEx;
import exceptions.WrongParameters;

import java.sql.SQLException;
import java.util.GregorianCalendar;

public interface DAOInterface {

    Utente createUser(UserInfoRegister infoReg) throws WrongParameters;

    Utente loadFromDB(Nickname nickname) throws NickNotDBEx, SQLException;

    void storeUserDB(Utente user) throws  SQLException;

    Boolean searchNickDB(Nickname nickname)  throws Exception;

    Boolean searchTC(TaxCode cf) throws Exception;

    void destroy(Nickname nickname) throws NickNotDBEx, SQLException;

    void deleteNTime(Nickname nickname, GregorianCalendar date) throws SQLException;


}
