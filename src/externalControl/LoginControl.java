package externalControl;

import control.FacadeSubSystem;
import entity.Nickname;
import entity.PW;
import interfaces.SystemInterface;

public class LoginControl {

    private static LoginControl instance;

    private SystemInterface sysInt = new FacadeSubSystem();

    public static LoginControl getInstance() {
        if (instance == null)
            instance = new LoginControl();
        return instance;
    }

    private LoginControl() {
    }

    public String login(String nickname, String password) {
        try {
            if(sysInt.login(new Nickname(nickname), new PW(password))) return "Successo";
            else return "Nickname o password non corretti, riprova.";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

}
