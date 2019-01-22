package DAO;

import bean.UserInfoRegister;
import control.DAOInterface;
import entity.*;

public class DAOClass implements DAOInterface {
    private DataSource dataSource;
    private ResultSet resSet;

    //todo: implementare bene questa classe con tutta la logica del pattern DAO, e con connessione al DB

    public Utente createUser(UserInfoRegister infoReg){
        PublicData pubD= new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(),infoReg.getEmail(),infoReg.getBirthday(),infoReg.getGender());
        PrivateData priD= new PrivateData();
        PW pw= new PW(infoReg.getPw());
        Roles roles= new Roles();
        //todo: da continuare;
    }

}
