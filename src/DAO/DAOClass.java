package DAO;

import bean.UserInfoRegister;
import control.DAOInterface;
import entity.*;
import exceptions.*;

import java.sql.*;
import java.util.GregorianCalendar;

public class DAOClass implements DAOInterface {

    private static String UTENTE= "root";
    private static String PASSWORD= "0000";
    private static String DB_URL= "jdbc:mysql://localhost/Utente";
    private static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    @Override
    public Utente createUser(UserInfoRegister infoReg){
        PublicData pubD= new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(),infoReg.getEmail(),infoReg.getBirthday(),infoReg.getGender());
        PrivateData priD= new PrivateData();
        PW pw= new PW(infoReg.getPw());
        Roles roles= new Roles();
        UserStatus userStatus= UserStatus.ACTIVE;
        Questions answers= new Questions(infoReg.getAnswers());
        return new Utente(pubD,priD,pw,roles,answers);

    }

    @Override
    public Boolean searchNickDB(Nickname nickname) throws NickNotDBEx {
        return true;
        /*
        Statement stmt= null;
        Connection conn= null;

        //loading dinamico del driver mysql
        Class.forName(DRIVER_CLASS_NAME);
        //apertura connessione
        conn = DriverManager.getConnection(DB_URL, UTENTE, PASSWORD);

        //creazione ed esecuzione della query
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String sql= "SELECT nickname"+
                "FROM user where nickname = ' "
                + nickname + "';";
        ResultSet rs= stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return false;
        else return true;
        */
    }



    @Override
    public Utente loadFromDB(Nickname nickname) throws NickNotDBEx{
        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy") ,new Email("ema@gmail.com"), new GregorianCalendar(97,7,31), Gender.MAN);
        PrivateData priD = new PrivateData();
        return new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions(new String[] {"a","b","c","d"}));
    }

    @Override
    public void storeUserDB(Utente user)
    {

    }

    @Override
    public Boolean searchTC(TaxCode cf) throws TCNotExistEx{
        return true;
    }

    @Override
    public void destroy(Nickname nickname) throws NickNotDBEx{

    }

    @Override
    public void deleteNTime(Nickname nickname, GregorianCalendar date) throws NickNotDBEx
    {

    }


}
