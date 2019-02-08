package control;

import DAO.DAOClass;
import DAO.DaemonDAO;
import bean.BasicUserInfo;
import bean.FactoryInfo;
import bean.RestrictUserInfo;
import bean.UserInfoRegister;
import entity.*;
import exceptions.NickNotDBEx;
import exceptions.UserNotExistEx;
import exceptions.WrongParameters;
import interfaces.RoleStatus;
import interfaces.SystemInterface;
import interfaces.UserProfileService;

import java.sql.SQLException;

public class FacadeSubSystem implements RoleStatus, SystemInterface, UserProfileService {

    private UserExpert usExp= new UserExpert();
    private FactoryInfo factInf= new FactoryInfo();
    public FacadeSubSystem() {

    }

    @Override
    public BasicUserInfo getBasicUserInfo(Nickname nick) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        PublicData pubD = user.getPublic();
        BasicUserInfo basUI = factInf.createBasic(pubD);
        return basUI;
    }

    @Override
    public RestrictUserInfo getRestrictedUserInfo(Nickname nick) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        PrivateData prD = user.getPrivate();
        RestrictUserInfo resUI = factInf.createRestrict(prD);
        return resUI;
    }

    @Override
    public Boolean doesNicknameExist(Nickname nick) throws UserNotExistEx {
        return this.usExp.isNickExist(nick);
    }

    @Override
    public Boolean doesTaxCodeExist(TaxCode tc) throws UserNotExistEx {
        return this.usExp.doesTaxCodeExist(tc);
    }

    @Override
    public void createUser(Nickname nick, UserInfoRegister dataCreate) throws WrongParameters, ClassNotFoundException {
        this.usExp.createUser(dataCreate);
        return;
    }

    @Override
    public void cancelUser(Nickname nick) throws UserNotExistEx {
        UserStatus usStat = UserStatus.CANCELLED;
        Utente user = this.getUtente(nick);
        user.setStatus(usStat);
        this.usExp.storeUser(user);
        //Fa partire la deleteNTime della DAOClass
        this.usExp.deleteUser(user.getPublic().getNick());
    }

    @Override
    public void deleteUser(Nickname nick) throws UserNotExistEx {
        this.usExp.destroyUser(nick);
    }

    @Override
    public void changeNotAnagraphicData(Nickname nick, ModifyDataString data) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        String d = data.get();

        if (data instanceof SocialStatus) {
            user.getPublic().getSocialStatus().set(d);
        } else if (data instanceof Email) {
            user.getPublic().getEmail().set(d);
        } else if (data instanceof SurfaceAddress) {
            user.getPrivate().getLocalAddress().set(d);
        } else if (data instanceof Nationality) {
            user.getPrivate().getNationality().set(d);
        } else if (data instanceof PhoneNumber) {
            user.getPrivate().getPhone().set(d);
        }
        this.usExp.storeUser(user);
        return;
    }

    @Override
    public void changePassword(Nickname nick, PW newP, PW oldP) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        if (user.changePw(oldP, newP)) {
            this.usExp.storeUser(user);
        } else {
            System.out.println("La vecchia password inserita non è corretta");
        }
    }

    @Override
    public Boolean login(Nickname nick, PW pw) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        if(user.getStatus()== UserStatus.BANNED){
            System.out.println("L'utente è stato bannato");
            return false;
        }
        if(user.getStatus()==UserStatus.CANCELLED){
            //todo: reindirizzare l'utente al recupero credenziali
            return false;
        }
        if (user.getPw().getPw().equals(pw.getPw())){
            return true;
        } else return false;

    }

    @Override
    public Boolean checkQuestion(Nickname nk, Questions q) throws UserNotExistEx
    {
        Utente us = getUtente(nk);
        return us.getQuestions().checkAnswers(q,4);
    }


    @Override
    public void forgottenPassword(Nickname nick, Questions answers, PW newPw) throws SQLException, ClassNotFoundException, UserNotExistEx{
       this.usExp.forgottenPassword(nick, answers, newPw);
    }

    @Override
    public Avatar getAvatar(Nickname nk) throws UserNotExistEx{
        Utente us = getUtente(nk);
        return us.getPublic().getAvatar();
    }

    @Override
    public void setAvatar(Nickname nk, int id) throws UserNotExistEx{
        this.usExp.setAvatar(nk,id);
    }

    @Override
    public void changeUrl(String ip) throws SQLException, ClassNotFoundException
    {
        DAOClass dao= new DAOClass(ip);
    }

    @Override
    public Roles getRoles(Nickname nick) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        Roles roles = user.getRole();
        return roles;
    }

    @Override
    public UserStatus getStatus(Nickname nick) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        UserStatus usStat = user.getStatus();
        return usStat;
    }

    @Override
    public void makeARenter(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException {
        Utente user= this.getUtente(nick);
        user.getRole().setRenter();
        this.usExp.storeUser(user);
    }

    @Override
    public void removeRentership(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException {
        Utente user= this.getUtente(nick);
        user.getRole().resetRenter();
        this.usExp.storeUser(user);    }

    @Override
    public void makeATenant(Nickname nick) throws UserNotExistEx, SQLException, ClassNotFoundException {
        Utente user= this.getUtente(nick);
        user.getRole().setTenant();
        this.usExp.storeUser(user);
    }

    @Override
    public void removeTenantship(Nickname nick) throws UserNotExistEx, SQLException,ClassNotFoundException {
        Utente user= this.getUtente(nick);
        user.getRole().resetTenant();
        this.usExp.storeUser(user);        }

    @Override
    public void changeUserStatus(Nickname nick, UserStatus newUsStat) throws UserNotExistEx, SQLException, ClassNotFoundException {
        this.usExp.changeUserStatus(nick, newUsStat);
    }

    @Override
    public Boolean isRenter(Nickname nick) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        return user.getRole().isRenter();
    }

    @Override
    public Boolean isTenant(Nickname nick) throws UserNotExistEx {
        Utente user = this.getUtente(nick);
        return user.getRole().isTenant();
    }

    @Override
    public Boolean isActive(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        if(user.getStatus()== UserStatus.ACTIVE){
            return true;
        } else return false;
    }

    @Override
    public Boolean isInactive(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        if(user.getStatus()== UserStatus.INACTIVE){
            return true;
        } else return false;
    }

    @Override
    public Boolean isCancelled (Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        if(user.getStatus()== UserStatus.CANCELLED){
            return true;
        } else return false;
    }

    @Override
    public Boolean isBanned(Nickname nick) throws UserNotExistEx{
        Utente user = this.getUtente(nick);
        if(user.getStatus()== UserStatus.BANNED){
            return true;
        } else return false;
    }

    private Utente getUtente(Nickname nick)throws UserNotExistEx {
        Utente user = this.usExp.getUser(nick);
        return user;
    }

}
