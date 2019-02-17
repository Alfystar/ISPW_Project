package externalControl;

import control.FacadeSubSystem;
import entity.*;
import interfaces.RoleStatus;
import interfaces.UserProfileService;

public class OtherSubSystemControl{

    private static OtherSubSystemControl instance = null;

    private UserProfileService usProfInt = new FacadeSubSystem();
    private RoleStatus rolInt = new FacadeSubSystem();

    private OtherSubSystemControl(){
    }

    public synchronized static OtherSubSystemControl getInstance(){
        if(instance == null)
            instance = new OtherSubSystemControl();
        return instance;
    }

    public String banUser(Nickname nick){
        try{
            rolInt.changeUserStatus(nick, UserStatus.BANNED);
            return "Nuovo stato: " + rolInt.getStatus(nick).name();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String unBanUser(Nickname nick){
        try{
            rolInt.changeUserStatus(nick, UserStatus.ACTIVE);
            return "Nuovo stato: " + rolInt.getStatus(nick).name();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String destroyUser(Nickname nick){
        try{
            usProfInt.deleteUser(nick);
            return "Utente " + nick.get() + " eliminato dal sistema.";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String[] obtainPubData(Nickname nick){
        try{
            BasicUserInfo basic = usProfInt.getBasicUserInfo(nick);

            String[] tmp = {basic.toString(), basic.getAvatar().getAvatarName()};
            return tmp;
        }catch(Exception e){

            String[] tmpEx = {e.getMessage(), null};
            return tmpEx;
        }
    }

    public String obtainPriData(Nickname nick){
        try{
            RestrictUserInfo restrict = usProfInt.getRestrictedUserInfo(nick);
            return restrict.toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String obtainRoleUser(Nickname nick){
        try{
            Roles role = rolInt.getRoles(nick);
            return role.toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String obtainStatusUser(Nickname nick){
        try{
            UserStatus status = rolInt.getStatus(nick);
            return status.name();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String modifyRole(Nickname nick, String tenant, String renter){
        try{
            if(tenant.toLowerCase().equals("yes")) rolInt.makeATenant(nick);
            else if(tenant.toLowerCase().equals("no")) rolInt.removeTenantship(nick);

            if(renter.toLowerCase().equals("yes")) rolInt.makeARenter(nick);
            else if(renter.toLowerCase().equals("no")) rolInt.removeRentership(nick);

            return "Nuovi ruoli: " + rolInt.getRoles(nick).toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }

}
