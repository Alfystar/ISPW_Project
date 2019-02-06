package externalControl;

import entity.*;
import interfaces.RoleStatus;
import interfaces.SystemInterface;
import interfaces.UserProfileService;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ChangeDataControl {

    private static ChangeDataControl instance;

    private SystemInterface sysInt; // = new Facade();
    private RoleStatus rolInt;

    public static ChangeDataControl getInstance() {
        if (instance == null)
            instance = new ChangeDataControl();
        return instance;
    }

    private ChangeDataControl() {
    }

    public Boolean changeData(Nickname nick, String email, String renter,
                              String tenant, String socialStatus,
                              String phoneNumber, String address, String birthPlace,
                              String nationality, String oldPW, String newPW) {
        try {
            if (!email.equals("")) sysInt.changeNotAnagraphicData(nick, new Email(email));

            if (tenant.toLowerCase().equals("yes")) rolInt.makeATenant(nick);
            else if (tenant.toLowerCase().equals("no")) rolInt.removeTenantship(nick);

            if (renter.toLowerCase().equals("yes")) rolInt.makeARenter(nick);
            else if (renter.toLowerCase().equals("no")) rolInt.removeRentership(nick);

            if (!socialStatus.equals("")) sysInt.changeNotAnagraphicData(nick, new SocialStatus(socialStatus));

            if (!phoneNumber.equals("")) sysInt.changeNotAnagraphicData(nick, new PhoneNumber(phoneNumber));

            if (!address.equals("")) sysInt.changeNotAnagraphicData(nick, new SurfaceAddress(address));

            if (!birthPlace.equals("")) sysInt.changeNotAnagraphicData(nick, new SurfaceAddress(birthPlace));

            if (!nationality.equals("")) sysInt.changeNotAnagraphicData(nick, new Nationality(nationality));

            if (!newPW.equals("")) sysInt.changePassword(nick, new PW(newPW), new PW(oldPW));

            return TRUE;
        }
        catch(Exception e){
            return FALSE;
        }
    }
}
