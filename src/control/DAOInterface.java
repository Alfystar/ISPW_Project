package control;

import bean.UserInfoRegister;
import entity.Nickname;
import entity.TaxCode;
import entity.Utente;
import exceptions.NickNotDBEx;
import exceptions.WrongParameters;

import java.sql.SQLException;
import java.util.GregorianCalendar;

public interface DAOInterface{

    Utente createUser(UserInfoRegister infoReg) throws WrongParameters;

    Utente loadFromDB(Nickname nickname) throws NickNotDBEx, SQLException;

    void saveUser(Utente user) throws SQLException;

    void updateUser(Utente user) throws SQLException, NickNotDBEx;

    Boolean searchNickDB(Nickname nickname) throws SQLException;

    Boolean searchTC(TaxCode cf) throws SQLException;

    void destroy(Nickname nickname) throws NickNotDBEx, SQLException;

    void deleteNTime(Nickname nickname, GregorianCalendar date) throws SQLException;

    void removeDataEvent(Nickname nick) throws SQLException;

    void changeHost(String ip);

    String getLastHost();
}
