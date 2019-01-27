package DAO;

import bean.UserInfoRegister;
import control.DAOInterface;
import entity.*;
import java.sql.*;

public class DAOClass implements DAOInterface {

    private static String UTENTE= "root";
    private static String PASSWORD= "0000";
    private static String DB_URL= "jdbc:mysql://localhost/Utente";
    private static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static Boolean searchNickDB(Nickname nickname){
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

    }



    public Utente createUser(UserInfoRegister infoReg){
        PublicData pubD= new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(),infoReg.getEmail(),infoReg.getBirthday(),infoReg.getGender());
        PrivateData priD= new PrivateData();
        PW pw= new PW(infoReg.getPw());
        Roles roles= new Roles();
        UserStatus userStatus= new UserStatus();
        Questions answers= new Questions(/*todo fare get della classe)*/);
    }

}
