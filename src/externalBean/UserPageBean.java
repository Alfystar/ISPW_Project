package externalBean;

import entity.Nickname;
import externalControl.UserPageControl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class UserPageBean {

public String[] getStringUsData(String nickN){

    UserPageControl controller = UserPageControl.getInstance();

    Nickname nick = new Nickname(nickN);

    return controller.obtainUserInfo(nick);

    }

    public Boolean cancelUser(String nickN){
        UserPageControl controller = UserPageControl.getInstance();

        Nickname nick = new Nickname(nickN);

        if(controller.cancelUser(nick)) return TRUE;
        else return FALSE;

    }

}
