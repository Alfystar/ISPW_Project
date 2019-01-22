package interfaces;

import entity.ModifyDataString;
import entity.Nickname;
import entity.PW;
import entity.Questions;

public interface SystemInterface {

    void changeNotAnagraphicData(Nickname nk, ModifyDataString data);

    void changePassword(Nickname nk, ModifyDataString data);

    void login(Nickname nk, PW passW);

    void forgottenPassword(Nickname nk, Questions answers, PW newPW);



}
