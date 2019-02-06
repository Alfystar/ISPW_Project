package externalControl;

import bean.BasicUserInfo;
import entity.Nickname;
import interfaces.UserProfileService;

public class UserPageControl {

    private static UserPageControl instance;

    private UserProfileService usProfInt; // = new Facade();

    public static UserPageControl getInstance() {
        if (instance == null)
            instance = new UserPageControl();
        return instance;
    }

    private UserPageControl() {
    }

    public String[] obtainBasicUsInfo(Nickname nick){}

}
