package DAO;

import bean.*;
import control.*;
import entity.*;
import exceptions.*;
import javafx.scene.image.Image;

import javax.management.relation.Role;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DAOClass implements DAOInterface {

    private static String UTENTE= "root";
    private static String PASSWORD= "0000";
    private static String DB_URL= "jdbc:mysql://localhost/Utente";
    private static String DRIVER_CLASS_NAME = "ISPW Project.drivers";
    //inizializzazione
    private Statement stmt= null;
    private Connection conn= null;
    private static Boolean nonFatta= true;

    //Costruttore
    public DAOClass() throws ClassNotFoundException,SQLException{
        //loading dinamico del driver mysql
        try {
            if (nonFatta == true) {
                //STEP 2: Register JDBC driver
                Class.forName(DRIVER_CLASS_NAME);
                nonFatta= false;
                //STEP 3: Open a connection
                stmt = conn.createStatement();
            }
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, UTENTE, PASSWORD);
            System.out.println("Connected database successfully...");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }
        catch (SQLException se){
            se.printStackTrace();
            throw se;
        }
    }

    @Override
    public Utente createUser(UserInfoRegister infoReg)throws WrongParameters{

            //Prendo tutti i parametri di Utente da infoReg
            PublicData pubD = new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(), infoReg.getEmail(), infoReg.getBirthday(), infoReg.getGender());
            PrivateData priD = new PrivateData();
            PW pw = new PW(infoReg.getPw());
            Roles roles = new Roles();
            UserStatus userStatus = UserStatus.ACTIVE;
            Questions answers = new Questions(infoReg.getAnswers());

            //incapsulo i dati in un'istanza di Utente
            Utente us= new Utente(pubD, priD, pw, roles, answers);
            //Verifica delle eccezioni
            try{
                this.storeUserDB(us);

            }catch(SQLException se){
                throw new WrongParameters("wrong parameters",se);
            }
            return us;
    }

    @Override
    public Boolean searchNickDB(Nickname nickname) throws Exception {
        try {
            String sql = "SELECT nick" + "FROM user " + "where nick = " + nickname + ";";

            this.stmt.executeQuery(sql);
            System.out.println("query executed");

            ResultSet rs= this.stmt.executeQuery(sql);
            if (rs.first())
                return false;
            else return true;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            throw se;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            throw e;
        } finally {
            //finally block used to close resources
            this.closeConn();
        }
    }

    private void closeConn() {
            try {
                if (stmt != null) {
                    conn.close(); }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close(); }
            } catch (SQLException se) {
                se.printStackTrace(); }
    }

    @Override
    public Utente loadFromDB(Nickname nickname) throws NickNotDBEx, SQLException{
        String sql= "SELECT *" + "FROM user " + "where nick=  " +nickname+ " ;";

        this.stmt.executeQuery(sql);
        System.out.println("query executed");

        ResultSet rs= this.stmt.executeQuery(sql);
        if (!rs.first()) throw  new NickNotDBEx("nickname not found");

        //estraggo da rs i dati e le chiavi per raggiungere le altre tabelle
        TaxCode taxCode= new TaxCode(rs.getString(2));
        Integer prD_id= rs.getInt(3);
        UserStatus userStatus= (UserStatus) rs.getObject(4);
        PW pw= new PW(rs.getString(5));
        Integer answ_id= rs.getInt(6);
        Byte bRoles= rs.getByte(7);
        byte bR = (byte)bRoles.intValue();
        Roles r= new Roles((bR&1)==1,(bR&2) == 2);

        //eseguo la query per PublicData
        String sqlPubD= "SELECT *" + "FROM publicdata" + "where taxCode = " + taxCode + " ;";
        rs= this.stmt.executeQuery(sqlPubD);
        PublicData pubD= this.buidPubD(rs, taxCode, nickname);

        //eseguo la query per PrivateData
        String sqlPrD= "SELECT *" + "FROM privatedata" + "where idPrD = " + prD_id + " ;";
        rs= this.stmt.executeQuery(sqlPrD);
        PrivateData prD= this.buildPrD(rs, prD_id,nickname);

        //eseguo la query per Answers
        String sqlIdAnsw= "SELECT *" + "FROM answers" + "where idAnsw = " + answ_id + " ;";
        rs =this.stmt.executeQuery(sqlPrD);
        Questions q= this.buildAnsw(rs,answ_id, nickname);
        //chiudo la connessione col DB
        this.closeConn();
        //creo un'istanza di utente con tutit i dati ottenuti
        Utente us= new Utente(pubD, prD, pw, r, q);
        return us;
    }

    private PublicData buidPubD(ResultSet rs, TaxCode taxCode, Nickname nickname) throws SQLException{
        Name name= new Name(rs.getString(2));
        Name surname= new Name(rs.getString(3));
        String birthday= rs.getString(4);
        //Conversione data
        String[] splitDate = birthday.split("/");
        int days = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);

        Gender gender= (Gender) rs.getObject(5);
        SocialStatus socStatus= new SocialStatus(rs.getString(6));
        Avatar avatar= (Avatar) rs.getObject(7);
        Email email= new Email(rs.getString(8));

        //creo un'istanza di PubD
        PublicData pubD= new PublicData(name, surname, new GregorianCalendar(year, month, days), gender, taxCode, socStatus, avatar, email,nickname);
        return pubD;
    }

    private PrivateData buildPrD(ResultSet rs, Integer prD_id, Nickname nickname)throws SQLException{
        PhoneNumber phone= new PhoneNumber(rs.getString(2));
        SurfaceAddress address= new SurfaceAddress(rs.getString(3));
        SurfaceAddress cityOfBirth= new SurfaceAddress(rs.getString(4));
        Nationality nat= new Nationality(rs.getString(5));

        //creo un'istanza di PrD
        PrivateData prD= new PrivateData(address, cityOfBirth,nat,phone);
        return prD;
    }

    private Questions buildAnsw(ResultSet rs, Integer answ_id, Nickname nickname)throws SQLException{
        String answ1= rs.getString(2);
        String answ2= rs.getString(3);
        String answ3= rs.getString(4);
        String answ4= rs.getString(5);
        String[] answers= {answ1,answ2,answ3, answ4};
        Questions q= new Questions(answers);
        return q;
    }

    @Override
    public void storeUserDB(Utente user) throws SQLException{
        PublicData puB= user.getPublic();
        PrivateData prD= user.getPrivate();
        Questions answ= user.getQuestions();
        UserStatus usStat= user.getStatus();
        PW pw= user.getPw();
        Roles roles= user.getRole();

        //Inserisco nel DB una tabella PublicData
        String sqlPubD= "INSERT INTO publicdata" +
                "VALUES (taxCode, name, surname, birthday, gender, socStat, usImg, email)" +
                (puB.getFiscalCode().get()) + (puB.getName().get()) + (puB.getSurname().get()) + (puB.getBirthday().getGregorianChange()) +
                (puB.getGender()) + (puB.getSocialStatus().get()) + (puB.getAvatar()) + (puB.getEmail().get()) + " ;";
        System.out.println("publicData insert executed");

        //Inserisco nel DB una tabella di PrivateData
        String sqlPrD= "INSERT INTO privatedata" +
                "VALUES (phone, address, cityOfBirth,nationality)" +
                (prD.getPhone().get()) + (prD.getLocalAddress().get()) + (prD.getCityOfBirth().get()) + (prD.getNationality().get())+ " ;";
        System.out.println("privateData insert executed");
        //Prendo l'id della tabella appena generata
        String sqlPrId= "SELECT LAST_INSERT_ID;";
        ResultSet rs= this.stmt.executeQuery(sqlPrId);
        int prD_id= rs.getInt(1);

        //Inserisco nel DB una tabella di Answers
        String [] vect= answ.getAnswers();
        String sqlAnsw= "INSERT INTO answers" +
                "VALUES (answ1,answ2,answ3,answ4)" +
                vect[0] + vect[1] + vect[2] + vect[3] + " ;";
        //Prendo l'id della tabella appena generata
        String sqlAId= "SELECT LAST_INSERT_ID;";
        rs= this.stmt.executeQuery(sqlAId);
        int answ_id= rs.getInt(1);

        //Creo una tabella di Utente nel DB
        String sqlNewUs= "INSERT INTO user" +
                "VALUES (nick,pubD_Tc,prD_id,userStatus,pw,answ_id,roles)" +
                (puB.getNickname().get()) + (puB.getFiscalCode().get()) + prD_id + usStat + (pw.getPw()) + answ_id + roles + " ;";
        rs= this.stmt.executeQuery(sqlNewUs);
        System.out.println("user insert executed");
        this.closeConn();
        return;
    }

    @Override
    public Boolean searchTC(TaxCode cf) throws Exception, SQLException {
        try {
            String sql = "SELECT taxCode" + "FROM publicdata " + "where taxCode = " + cf + ";";

            this.stmt.executeQuery(sql);
            System.out.println("query executed");

            ResultSet rs = this.stmt.executeQuery(sql);
            if (rs.first())
                return false;
            else return true;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            throw se;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            throw e;
        } finally {
            //finally block used to close resources
            this.closeConn();
            return true;
        }
    }

    @Override
    public void destroy(Nickname nickname) throws NickNotDBEx, SQLException{
        String sql= "SELECT *" + "FROM user " + "where nick=  " +nickname+ " ;";

        this.stmt.executeQuery(sql);
        System.out.println("query executed");

        ResultSet rs= this.stmt.executeQuery(sql);
        if (!rs.first()) throw  new NickNotDBEx("nickname not found");

        //Prendo tutte chiavi delle tabelle collegate a Utente, e le distruggo
        String tc= rs.getString(2);
        int prD_id= rs.getInt(3);
        int answ_id = rs.getInt(6);

        String sqlPubD= "DELETE FROM publicdata " + "where taxCode = " + tc + ";";
        this.stmt.executeQuery(sqlPubD);
        System.out.println("publicData deleted");

        String sqlPrD= "DELETE FROM privatedata where prD_id = " + prD_id + " ;";
        this.stmt.executeQuery(sqlPrD);
        System.out.println("privateData deleted");

        String sqlAnsw= "DELETE FROM answers where answ_id = " + answ_id + " ;";
        this.stmt.executeQuery(sqlAnsw);
        System.out.println("answers deleted");

        //Ora distruggo l'istanza di Utente
        String sqlUs= "DELETE FROM user where nick = " + nickname + " ;";
        this.stmt.executeQuery(sqlUs);
        System.out.println("user destroyed");

        return;
    }

    @Override
    public void deleteNTime(Nickname nickname, GregorianCalendar date) throws SQLException {
        //Conversione di GregorianDate in Date, e da Date a String
        Date d= date.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        //to convert Date to String, use format method of SimpleDateFormat class.
        String strDate = dateFormat.format(date);

        String sql= "INSERT INTO dateevent VALUES (idDate,nick)= " +strDate + nickname + " ;";
        this.stmt.executeQuery(sql);
        System.out.println("date inserted");

        return;
    }

    public void deleteByDeamon(GregorianCalendar today) throws SQLException{
        //Converto GregorianCalendar in String
        Date d= today.getTime(); //todo: da riguardare: perchè non si usa d??
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        //to convert Date to String, use format method of SimpleDateFormat class.
        String strDate = dateFormat.format(date);
        String sql= "SELECT nick" + "FROM dateevent " + "where date<=  " +today+ " ;";
        this.stmt.executeQuery(sql);
        System.out.println("query executed");

        ResultSet rs= this.stmt.executeQuery(sql);

        try {
            while (rs.next()) {
                Nickname nick = new Nickname(rs.getString(2));
                destroy(nick);
            }
        } catch (NickNotDBEx nickNotEx){
            System.out.println("Non dovresti essere qui!!");
        }
        finally {
            this.closeConn();
        }
        return;
    }


//todo: scrivere un metodo che permetta di modificare il DB_URL, passandogli una stringa

    //todo: una classe che implementa runnable, in cui un demonThread singleton si va a guardare periodicamente dataevent
}
