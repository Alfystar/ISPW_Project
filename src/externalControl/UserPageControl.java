package externalControl;

import control.FacadeSubSystem;
import entity.*;
import interfaces.RoleStatus;
import interfaces.SystemInterface;
import interfaces.UserProfileService;

import java.util.Arrays;
import java.util.GregorianCalendar;

public class UserPageControl{

    private static UserPageControl instance;

    private RoleStatus rolInt = new FacadeSubSystem();
    private SystemInterface sysInt = new FacadeSubSystem();
    private UserProfileService usProfInt = new FacadeSubSystem();

    private UserPageControl(){
    }

    public static UserPageControl getInstance(){
        if(instance == null)
            instance = new UserPageControl();
        return instance;
    }

    public String[] obtainUserInfo(Nickname nick){
        try{

            Avatar avatar = sysInt.getAvatar(nick);
            BasicUserInfo basObj = usProfInt.getBasicUserInfo(nick);
            RestrictUserInfo restObj = usProfInt.getRestrictedUserInfo(nick);
            Roles roles = rolInt.getRoles(nick);

            String tenant = "No";
            String renter = "No";

            if(roles.isTenant()) tenant = "Yes";
            if(roles.isRenter()) renter = "Yes";

            //prendo solo i dati che servono

            String[] resStr = {
                    avatar.getAvatarName(),
                    basObj.getName().get(),
                    basObj.getSurname().get(),
                    basObj.getTC().get(),
                    basObj.getNick().get(),
                    basObj.getEmail().get(),
                    gregCalToString(basObj.getBirthday()),
                    basObj.getGender().name(),
                    basObj.getSocialStatus().get(),
                    tenant, renter,
                    restObj.getPhone().get(),
                    restObj.getLocalAddress().get(),
                    restObj.getCityOfBirth().get(),
                    restObj.getNationality().get()};

            return resStr;

        }catch(Exception e){

            String[] array = new String[15];
            Arrays.fill(array, e.getMessage());
            return array;
        }
    }

    public String cancelUser(Nickname nick){
        try{
            usProfInt.cancelUser(nick);
            return "Successo";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String verifyBan(Nickname nick){
        try{
            if(rolInt.isBanned(nick)) return "Ban";
            else return "NotBan";
        }catch(Exception e){
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
