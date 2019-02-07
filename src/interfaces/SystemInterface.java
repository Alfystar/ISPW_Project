package interfaces;

import entity.*;
import exceptions.NickNotDBEx;
import exceptions.UserNotExistEx;

import java.sql.SQLException;

public interface SystemInterface {

    void changeNotAnagraphicData(Nickname nk, ModifyDataString data) throws UserNotExistEx;

    void changePassword(Nickname nk,PW newPw, PW oldPw ) throws UserNotExistEx;

    Boolean login(Nickname nk, PW passW) throws UserNotExistEx;

    void forgottenPassword(Nickname nk, Questions answers, PW newPW) throws SQLException, ClassNotFoundException, NickNotDBEx, UserNotExistEx;

    Avatar getAvatar(Nickname nk) throws UserNotExistEx;

    void setAvatar(Nickname nk, int id) throws UserNotExistEx;
    //todo comandi per cambiare avatar

}
