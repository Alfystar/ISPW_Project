package externalControl;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import control.FacadeSubSystem;
import entity.Nickname;
import entity.Roles;
import interfaces.RoleStatus;
import interfaces.UserProfileService;

import java.util.Arrays;
import java.util.GregorianCalendar;

public class UserPageControl {

    private static UserPageControl instance;

    private UserProfileService usProfInt = new FacadeSubSystem();
    private RoleStatus rolInt = new FacadeSubSystem();

    public static UserPageControl getInstance() {
        if (instance == null)
            instance = new UserPageControl();
        return instance;
    }

    private UserPageControl() {
    }

    public String[] obtainUserInfo(Nickname nick){
        try {
            BasicUserInfo basObj = usProfInt.getBasicUserInfo(nick);
            RestrictUserInfo restObj = usProfInt.getRestrictedUserInfo(nick);
            Roles roles = rolInt.getRoles(nick);

            String tenant = "No";
            String renter = "No";

            if(roles.isTenant()) tenant = "Yes";
            if(roles.isRenter()) renter = "Yes";

            //prendo solo i dati che servono

            String[] resStr = { basObj.getName().get(),
                    basObj.getSurname().get(),
                    basObj.getTaxCode().get(),
                    basObj.getNickname().get(),
                    basObj.getEmail().get(),
                    gregCalToString(basObj.getBirthday()),
                    basObj.getGender().name(),
                    basObj.getsocialStatus().get(),
                    tenant, renter,
                    restObj.getPhoneNumber().get(),
                    restObj.getAddress().get(),
                    restObj.getCityOfBirth().get(),
                    restObj.getNationality().get() };

            return resStr;

        } catch(Exception e){

            String[] array = new String[14];
            Arrays.fill(array, e.getMessage());
            return array;
        }
    }

    public String cancelUser(Nickname nick){
        try{
            usProfInt.cancelUser(nick);
            return "Successo";
        } catch (Exception e){
            return e.getMessage();
        }
    }


    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }

}
