package externalControl;

import control.FacadeSubSystem;
import entity.*;
import interfaces.RoleStatus;
import interfaces.SystemInterface;

public class ChangeDataControl{

    private static ChangeDataControl instance;

    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolInt = new FacadeSubSystem();

    private ChangeDataControl(){
    }

    public static ChangeDataControl getInstance(){
        if(instance == null)
            instance = new ChangeDataControl();
        return instance;
    }

    public String changeData(Nickname nick, String avatar, String email,
            String socialStatus, String phoneNumber, String address,
            String nationality, String oldPW, String newPW){
        try{

            if((!avatar.equals("")) && (avatar.equals("1") ||
                    avatar.equals("2") || avatar.equals("3") ||
                    avatar.equals("4") || avatar.equals("5") ||
                    avatar.equals("6"))) sysInt.setAvatar(nick, Integer.parseInt(avatar) - 1);

            if(!email.equals("")) sysInt.changeNotAnagraphicData(nick, new Email(email));

            if(!socialStatus.equals("")) sysInt.changeNotAnagraphicData(nick, new SocialStatus(socialStatus));

            if(!phoneNumber.equals("")) sysInt.changeNotAnagraphicData(nick, new PhoneNumber(phoneNumber));

            if(!address.equals("")) sysInt.changeNotAnagraphicData(nick, new SurfaceAddress(address));

            if(!nationality.equals("")) sysInt.changeNotAnagraphicData(nick, new Nationality(nationality));

            if(!newPW.equals("")){
                if(newPW.equals(oldPW)) return "La nuova PW è uguale alla precedente";
                sysInt.changePassword(nick, new PW(newPW), new PW(oldPW));
            }

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
}
