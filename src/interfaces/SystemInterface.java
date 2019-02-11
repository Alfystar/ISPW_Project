package interfaces;

import entity.*;
import exceptions.UserBannedEx;
import exceptions.UserNotExistEx;

import java.sql.SQLException;

public interface SystemInterface{

    void changeNotAnagraphicData(Nickname nk, ModifyDataString data) throws UserNotExistEx;

    void changePassword(Nickname nk, PW newPw, PW oldPw) throws UserNotExistEx;

    Boolean login(Nickname nk, PW passW) throws UserNotExistEx, SQLException, UserBannedEx;

    Boolean checkQuestion(Nickname nk, Questions q) throws UserNotExistEx;

    void forgottenPassword(Nickname nk, Questions answers, PW newPW) throws SQLException, ClassNotFoundException, UserNotExistEx;

    Avatar getAvatar(Nickname nk) throws UserNotExistEx;

    void setAvatar(Nickname nk, int id) throws UserNotExistEx;

    void changeHost(String ip) throws ClassNotFoundException;

    String getLastHost() throws ClassNotFoundException;

}
