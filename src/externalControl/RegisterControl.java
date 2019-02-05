package externalControl;

import bean.UserInfoRegister;
import entity.*;
import interfaces.UserProfileService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class RegisterControl {
    private static RegisterControl instance;

    private UserProfileService UsProfInt; // = new Facade();

    public static RegisterControl getInstance() {
        if (instance == null)
            instance = new RegisterControl();
        return instance;
    }

    private RegisterControl() {
    }

    public Boolean register( String firstname, String lastname, String taxcode,
                             String nickname, String email, String bday,
                             String gender, String password, String question1,
                             String question2, String question3, String question4) {
        try {
            SimpleDateFormat sForm = new SimpleDateFormat("dd/MM/aaaa");
            Date birthdate = sForm.parse(bday);
            GregorianCalendar gCal = new GregorianCalendar();
            gCal.setTime(birthdate);
            String[] answers= {question1, question2, question3, question4};
            UserInfoRegister usInfoReg = new UserInfoRegister(new Name(firstname), new Name(lastname), new TaxCode(taxcode), new Nickname(nickname), new Email(email), gCal, Gender.valueOf(gender.toUpperCase()),new Questions(answers), new PW(password));
            UsProfInt.createUser(new Nickname(nickname), usInfoReg);
            return TRUE;
        }
        catch(Exception e){
            return FALSE;
        }
    }


}
