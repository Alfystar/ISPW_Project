package DAO;

import bean.UserInfoRegister;
import control.DAOInterface;
import entity.*;

import java.util.GregorianCalendar;
import java.util.Random;
/* *
 * * ################################################################################################
 * * ################################################################################################
 * * ################################################################################################
 * * ################################################################################################
 * * ################################################################################################
 * *         ATTENZIONE CLASSE COMPLETAMENTE A CASO, ANCORA DA SVILUPPARE, NON USARE!!!!
 * * ################################################################################################
 * * ################################################################################################
 * * ################################################################################################
 * * ################################################################################################
 * * ################################################################################################
 * */
public class DAOClass implements DAOInterface {
    private DataSource dataSource;
    private ResultSet resSet;
    private Utente us;
    //todo: implementare bene questa classe con tutta la logica del pattern DAO, e con connessione al DB
    /*
    public Utente createUser(UserInfoRegister infoReg){
        PublicData pubD= new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(),infoReg.getEmail(),infoReg.getBirthday(),infoReg.getGender());
        PrivateData priD= new PrivateData();
        PW pw= new PW(infoReg.getPw());
        Roles roles= new Roles();
        //todo: da continuare;
    }*/

    @Override
    public Utente createUser(UserInfoRegister infoReg) {
        PublicData pubD = new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(), infoReg.getEmail(), infoReg.getBirthday(), infoReg.getGender());
        PrivateData priD = new PrivateData();
        PW pw = new PW(infoReg.getPw());
        Roles roles = new Roles();
        Questions answers= new Questions();
        this.us = new Utente(pubD,priD,pw,roles,answers);
        return us;
    }

    @Override
    public Utente loadFromDB(Nickname nickname){

        return this.us;
    }


    @Override
    public void storeUserDB(Utente user){

    }
    @Override
    public Boolean searchNickDB(Nickname nickname) {

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
}
