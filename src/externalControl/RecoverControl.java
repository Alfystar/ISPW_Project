package externalControl;

import control.FacadeSubSystem;
import entity.Nickname;
import entity.PW;
import entity.Questionary;
import interfaces.SystemInterface;

public class RecoverControl{

    private static RecoverControl instance;

    private SystemInterface sysInt = new FacadeSubSystem();

    private RecoverControl(){
    }

    public static RecoverControl getInstance(){
        if(instance == null)
            instance = new RecoverControl();
        return instance;
    }

    public String recover(String question1, String question2, String question3,
            String question4, String nickname, String newPW){
        try{
            String[] answers = {question1, question2, question3, question4};

            Questionary tmpQuestionary = new Questionary(answers);
            Nickname nick = new Nickname(nickname);

            if(!sysInt.checkQuestion(nick, tmpQuestionary)) return "Risposte non valide, riprova";

            sysInt.forgottenPassword(nick, tmpQuestionary, new PW(newPW));
            return "Successo";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
