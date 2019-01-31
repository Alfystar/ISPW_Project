package externalControl;

import entity.Nickname;
import entity.PW;
import interfaces.SystemInterface;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class LoginControl {

    private static LoginControl instance;

    private SystemInterface sysInt; // = new Facade();

    public static LoginControl getInstance() {
        if (instance == null)
            instance = new LoginControl();
        return instance;
    }

    private LoginControl() {
    }

    public Boolean login(String nickname, String password) {
        try {
            sysInt.login(new Nickname(nickname), new PW(password));
            return TRUE;
        }
        catch (Exception e){
            return FALSE;
        }
    }

}
