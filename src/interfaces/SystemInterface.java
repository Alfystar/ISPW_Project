package interfaces;

import entity.ModifyDataString;
import entity.Nickname;
import entity.PW;
import entity.Questions;
import exceptions.NickNotDBEx;
import exceptions.UserNotExistEx;

import java.sql.SQLException;

public interface SystemInterface {

    void changeNotAnagraphicData(Nickname nk, ModifyDataString data) throws UserNotExistEx;

    void changePassword(Nickname nk,PW newPw, PW oldPw ) throws UserNotExistEx;

    Boolean login(Nickname nk, PW passW) throws UserNotExistEx;

    void forgottenPassword(Nickname nk, Questions answers, PW newPW) throws SQLException, ClassNotFoundException, NickNotDBEx, UserNotExistEx;



}
