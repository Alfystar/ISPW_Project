package DAO;

import entity.*;
import bean.UserInfoRegister;
import control.DAOInterface;

import java.util.GregorianCalendar;


public class DAOMock implements DAOInterface{
    //Costruttore
    public DAOMock(){

    }

    @Override
    public Utente createUser(UserInfoRegister infoReg) {
        PublicData pubD = new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(), infoReg.getEmail(), infoReg.getBirthday(), infoReg.getGender());
        PrivateData priD = new PrivateData();
        PW pw = new PW(infoReg.getPw());
        Roles roles = new Roles();
        Questions answars= new Questions();
        Utente us = new Utente(pubD,priD,pw,roles,answars);
        //todo: continuare la Mock

        return us;
    }

    @Override
    Utente loadFromDB(Nickname nickname);
    @Override
    Boolean searchNickDB(Nickname nickname);
    @Override
    Boolean searchTC(TaxCode cf);
    @Override
    void destroy(Nickname nickname);
    @Override
    void deleteNTime(Nickname nickname, GregorianCalendar date);
    @Override
    Utente createUser(UserInfoRegister infoReg);
}
