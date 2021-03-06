package DAO;

import bean.UserInfoRegister;
import control.DAOInterface;
import entity.*;
import exceptions.WrongParameters;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DAOMock implements DAOInterface{


    private Utente utente1;

    //Costruttore
    public DAOMock(){


        BasicUserInfo pubD1 = new BasicUserInfo(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy"), new Email("ema@gmail.com"), new GregorianCalendar(97, 7, 31), Gender.MAN);
        RestrictUserInfo priD1 = new RestrictUserInfo(new SurfaceAddress("Roma"), new SurfaceAddress("Termini"), new Nationality("IT"), new PhoneNumber("3334233142"));
        this.utente1 = new Utente(pubD1, priD1, new PW("testPass1"), new Roles(TRUE, FALSE), new Questionary(new String[]{"a", "b", "c", "d"}));
    }

    @Override
    public Utente createUser(UserInfoRegister infoReg) throws WrongParameters{
        BasicUserInfo pubD = new BasicUserInfo(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(), infoReg.getEmail(), infoReg.getBirthday(), infoReg.getGender());
        RestrictUserInfo priD = new RestrictUserInfo();
        PW pw = new PW(infoReg.getPw());
        Roles roles = new Roles();
        Questionary answers = new Questionary(new String[]{"a", "b", "c", "d"});
        Utente us = new Utente(pubD, priD, pw, roles, answers);
        return us;
    }

    @Override
    public Utente loadFromDB(Nickname nickname){

        return this.utente1;
    }

    @Override
    public void updateUser(Utente user) throws SQLException{}

    @Override
    public void saveUser(Utente user){

    }

    @Override
    public Boolean searchNickDB(Nickname nickname){

        Random rand = new Random();
        return rand.nextBoolean();
    }

    @Override
    public Boolean searchTC(TaxCode cf){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    @Override
    public void destroy(Nickname nickname){}

    @Override
    public void deleteNTime(Nickname nickname, GregorianCalendar date){}

    @Override
    public void removeDataEvent(Nickname nick) throws SQLException{}

    @Override
    public void changeHost(String ip){}

    @Override
    public String getLastHost(){return "localHost";}


}
