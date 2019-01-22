package DAO;

import entity.*;
import bean.UserInfoRegister;
import control.DAOInterface;

import java.util.GregorianCalendar;


public class DAOMock implements DAOInterface{
    //Costruttore
    public DAOMock(){

    }

    public Utente createUser(UserInfoRegister infoReg){
        PublicData pubD= new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(),infoReg.getEmail(),infoReg.getBirthday(),infoReg.getGender());
        PrivateData priD= new PrivateData();
        PW pw= new PW(infoReg.getPw());
        Roles roles= new Roles();
        //todo: continuare la Mock
}
