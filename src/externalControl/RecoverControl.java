package externalControl;

import entity.Nickname;
import entity.PW;
import entity.Questions;
import interfaces.SystemInterface;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class RecoverControl {

    private static RecoverControl instance;

    private SystemInterface sysInt; // = new Facade();

    public static RecoverControl getInstance() {
        if (instance == null)
            instance = new RecoverControl();
        return instance;
    }

    private RecoverControl() {
    }

    public Boolean recover(String question1, String question2,String question3,
                           String question4,String nickname, String newPW) {
        try {
            String[] answers= {question1, question2, question3, question4};
            sysInt.forgottenPassword(new Nickname(nickname),new Questions(answers), new PW(newPW));
            return TRUE;
        }
        catch (Exception e){ //todo: gestire eccezioni
            return FALSE;
        }
    }
}
