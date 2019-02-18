package control;

import DAO.DAOClass;
import bean.PrototypeData;
import bean.UserInfoRegister;
import entity.*;
import exceptions.UserBannedEx;
import exceptions.UserNotExistEx;
import exceptions.WrongParameters;
import interfaces.RoleStatus;
import interfaces.SystemInterface;
import interfaces.UserProfileService;

import java.sql.SQLException;

public class FacadeSubSystem implements RoleStatus, SystemInterface, UserProfileService{

    private UserExpert usExp = new UserExpert();

    public FacadeSubSystem(){}

    @Override
    public BasicUserInfo getBasicUserInfo(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        PrototypeData protD = user.getPublic();
        return (BasicUserInfo) protD.clone();
    }

    @Override
    public RestrictUserInfo getRestrictedUserInfo(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        PrototypeData protD = user.getPrivate();
        return (RestrictUserInfo) protD.clone();
    }

    @Override
    public Boolean doesNicknameExist(Nickname nick) throws UserNotExistEx{
        return this.usExp.isNickExist(nick);
    }

    @Override
    public Boolean doesTaxCodeExist(TaxCode tc) throws UserNotExistEx{
        return this.usExp.doesTaxCodeExist(tc);
    }

    @Override
    public void createUser(Nickname nick, UserInfoRegister dataCreate) throws WrongParameters{
        this.usExp.createUser(dataCreate);
    }

    @Override
    public void cancelUser(Nickname nick) throws UserNotExistEx{
        //Fa partire la deleteNTime della DAOClass e mette a cancelled lo stato
        this.usExp.deleteUser(nick);
    }

    @Override
    public void deleteUser(Nickname nick) throws UserNotExistEx{
        this.usExp.destroyUser(nick);
    }

    @Override
    public void changeNotAnagraphicData(Nickname nick, ModifyDataString data) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        String d = data.get();

        if(data instanceof SocialStatus){
            user.getPublic().getSocialStatus().set(d);
        }else if(data instanceof Email){
            user.getPublic().getEmail().set(d);
        }else if(data instanceof SurfaceAddress){
            user.getPrivate().getLocalAddress().set(d);
        }else if(data instanceof Nationality){
            user.getPrivate().getNationality().set(d);
        }else if(data instanceof PhoneNumber){
            user.getPrivate().getPhone().set(d);
        }
        this.usExp.storeUser(user);
    }

    @Override
    public void changePassword(Nickname nick, PW newP, PW oldP) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        if(user.changePw(oldP, newP)){
            this.usExp.storeUser(user);
        }else{
            System.out.println("La vecchia password inserita non è corretta");
        }
    }

    @Override
    public Boolean validate(Nickname nick, PW pw) throws UserNotExistEx, SQLException, UserBannedEx{
        Utente user = this.getUtente(nick);
        if(user.getStatus() == UserStatus.BANNED){
            throw new UserBannedEx("Utente Bannato");
        }
       if(user.getStatus() == UserStatus.CANCELLED){
            this.usExp.reActiveProfile(user); //recupera le credenziali
            return false;
        }
        return user.comparePw(pw);
    }

    @Override
    public Boolean checkQuestion(Nickname nk, Questionary q) throws UserNotExistEx{
        Utente us = getUtente(nk);
        return us.getQuestionary().checkAnswers(q, 4);
    }


    @Override
    public void forgottenPassword(Nickname nick, Questionary answers, PW newPw) throws UserNotExistEx{
        this.usExp.forgottenPassword(nick, answers, newPw);
    }

    @Override
    public Avatar getAvatar(Nickname nk) throws UserNotExistEx{
        Utente us = getUtente(nk);
        return us.getPublic().getAvatar();
    }

    @Override
    public void setAvatar(Nickname nk, int id) throws UserNotExistEx{
        this.usExp.setAvatar(nk, id);
    }

    @Override
    public void changeHost(String ip) throws ClassNotFoundException{
        new DAOClass(ip);
    }

    @Override
    public String getLastHost() throws ClassNotFoundException{
        return new DAOClass().getLastHost();
    }

    @Override
    public Roles getRoles(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getRole();
    }

    @Override
    public UserStatus getStatus(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getStatus();
    }

    @Override
    public void makeARenter(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException{
        Utente user = this.getUtente(nick);
        user.getRole().setRenter();
        this.usExp.storeUser(user);
    }

    @Override
    public void removeRentership(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException{
        Utente user = this.getUtente(nick);
        user.getRole().resetRenter();
        this.usExp.storeUser(user);
    }

    @Override
    public void makeATenant(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException{
        Utente user = this.getUtente(nick);
        user.getRole().setTenant();
        this.usExp.storeUser(user);
    }

    @Override
    public void removeTenantship(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException{
        Utente user = this.getUtente(nick);
        user.getRole().resetTenant();
        this.usExp.storeUser(user);
    }

    @Override
    public void changeUserStatus(Nickname nick, UserStatus newUsStat) throws UserNotExistEx, SQLException, ClassNotFoundException{
        this.usExp.changeUserStatus(nick, newUsStat);
    }

    @Override
    public Boolean isRenter(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getRole().isRenter();
    }

    @Override
    public Boolean isTenant(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getRole().isTenant();
    }

    @Override
    public Boolean isActive(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getStatus() == UserStatus.ACTIVE;
    }

    @Override
    public Boolean isInactive(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getStatus() == UserStatus.INACTIVE;
    }

    @Override
    public Boolean isCancelled(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getStatus() == UserStatus.CANCELLED;
    }

    @Override
    public Boolean isBanned(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        return user.getStatus() == UserStatus.BANNED;
    }

    private Utente getUtente(Nickname nick) throws UserNotExistEx{
        return this.usExp.getUser(nick);
    }

}
