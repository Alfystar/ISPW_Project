package externalControl;

import bean.UserInfoRegister;
import control.FacadeSubSystem;
import entity.*;
import interfaces.UserProfileService;

import java.util.GregorianCalendar;

public class RegisterControl {
    private static RegisterControl instance;

    private UserProfileService usProfInt = new FacadeSubSystem();

    public static RegisterControl getInstance() {
        if (instance == null)
            instance = new RegisterControl();
        return instance;
    }

    private RegisterControl() {
    }

    public String register( String firstname, String lastname, String taxcode,
                             String nickname, String email, String bday,
                             String gender, String password, String question1,
                             String question2, String question3, String question4) {
        try {
            GregorianCalendar gCal = stringToGregCal(bday);
            String[] answers= {question1, question2, question3, question4};
            UserInfoRegister usInfoReg = new UserInfoRegister(new Name(firstname), new Name(lastname),
                                                            new TaxCode(taxcode), new Nickname(nickname),
                                                            new Email(email), gCal, Gender.valueOf(gender.toUpperCase()),
                                                            new Questions(answers), new PW(password));
            usProfInt.createUser(new Nickname(nickname), usInfoReg);
            return "Successo";
        }
        catch(Exception e){
            return e.getMessage();
        }

    }

    private GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int days = Integer.parseInt(splitDate[2]);
        GregorianCalendar gc = new GregorianCalendar(year, month-1, days);
        return gc;
    }

}
