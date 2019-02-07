package externalControl;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import entity.Nickname;
import entity.Roles;
import exceptions.UserNotExistEx;
import interfaces.RoleStatus;
import interfaces.UserProfileService;

import java.util.Arrays;
import java.util.GregorianCalendar;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class UserPageControl {

    private static UserPageControl instance;

    private UserProfileService usProfInt; // = new Facade();
    private RoleStatus rolInt;

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

        } catch(UserNotExistEx usEx){

            String[] array = new String[14];
            Arrays.fill(array, "ERROR");
            return array;
        }
    }

    public Boolean cancelUser(Nickname nick){
        try{
            usProfInt.cancelUser(nick);
            return TRUE;
        } catch (Exception e){ //todo: servono più eccezioni
            return FALSE;
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
