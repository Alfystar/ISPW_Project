package externalBean;

import entity.Nickname;
import externalControl.UserPageControl;

public class UserPageBean{

    public String[] getStringUsData(String nickN){

        UserPageControl controller = UserPageControl.getInstance();

        Nickname nick = new Nickname(nickN);

        return controller.obtainUserInfo(nick);

    }

    public String cancelUser(String nickN){
        UserPageControl controller = UserPageControl.getInstance();

        Nickname nick = new Nickname(nickN);

        return controller.cancelUser(nick);
    }

    public String allowActions(String nick){
        UserPageControl controller = UserPageControl.getInstance();

        Nickname nickN = new Nickname(nick);

        return controller.verifyBan(nickN);
    }


}
