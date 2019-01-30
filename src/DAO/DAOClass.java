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
    private static String DB_URL= "jdbc:mysql://localhost/user";
    private static String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
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
            }
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, UTENTE, PASSWORD);
            System.out.println("Connected database successfully...");
            //STEP 4: create Statement
            stmt = conn.createStatement();
            System.out.println("Connecting to a selected database...");
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
        rs =this.stmt.executeQuery(sqlIdAnsw);
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
        GregorianCalendar birthD= stringToGregCal(birthday);

        Gender gender= (Gender) rs.getObject(5);
        SocialStatus socStatus= new SocialStatus(rs.getString(6));
        Avatar avatar= (Avatar) rs.getObject(7);
        Email email= new Email(rs.getString(8));

        //creo un'istanza di PubD
        PublicData pubD= new PublicData(name, surname, birthD, gender, taxCode, socStatus, avatar, email,nickname);
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

        String sqlPubD= "INSERT INTO publicdata(taxCode, name, surname, birthday, gender, socStat, usImg, email) " +
                "VALUES (" +
                "\" "+(puB.getFiscalCode().get())+"\" "+ " ," +
                "\" "+(puB.getName().get())+"\" " + " ," +
                "\" "+(puB.getSurname().get())+"\" " +" ," +
                //"\" "+(puB.getBirthday().getGregorianChange())+"\" " +" ," +
                //todo: allungare il varchar di birthday a 15 nel db
                "\" "+"2001-01-1"+"\" " +" ," +
                //"\" "+(puB.getGender().toString())+"\" " +" ," +
                "\" "+(Gender.MAN)+"\" " +" ," +
                "\" "+(puB.getSocialStatus().get())+"\" " +" ," +
                "\" "+(puB.getAvatar())+"\" " +" ," +
                "\" "+(puB.getEmail().get())+"\" " + ");" ;
        this.stmt.executeQuery(sqlPubD);
        System.out.println("publicData insert executed");

        //Inserisco nel DB una tabella di PrivateData
        String sqlPrD= "INSERT INTO privatedata(phone, address, cityOfBirth,nationality)" +
                "VALUES (" +
                "\" "+(prD.getPhone().get())+"\" "+ " ," +
                "\" "+(prD.getLocalAddress().get())+"\" "+ " ," +
                "\" "+(prD.getCityOfBirth().get())+"\" "+ " ," +
                "\" "+(prD.getNationality().get())+"\" " + ");";
        this.stmt.executeQuery(sqlPrD);
        System.out.println("privateData insert executed");
        //Prendo l'id della tabella appena generata
        String sqlPrId= "SELECT LAST_INSERT_ID();";
        ResultSet rs= this.stmt.executeQuery(sqlPrId);
        int prD_id= rs.getInt(1);

        //Inserisco nel DB una tabella di Answers
        String [] vect= answ.getAnswers();
        String sqlAnsw= "INSERT INTO answers" +
                "VALUES (answ1,answ2,answ3,answ4)" +
                vect[0] + vect[1] + vect[2] + vect[3] + " ;";
        //Prendo l'id della tabella appena generata
        String sqlAId= "SELECT LAST_INSERT_ID();";
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

        String strDate= gregCalToString(date);

        String sql= "INSERT INTO dateevent VALUES (idDate,nick)= " +strDate + nickname + " ;";
        this.stmt.executeQuery(sql);
        System.out.println("date inserted");
        return;
    }

    public void deleteByDeamon(GregorianCalendar today) throws SQLException{

        String oggi= gregCalToString(today);

        String sql= "SELECT nick" + "FROM dateevent " + "where date<=  " +oggi+ " ;";
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

    public GregorianCalendar nextDeleteSession() throws SQLException{
        String sql= "SELECT * FROM deletesession;";
        this.stmt.executeQuery(sql);
        System.out.println("query executed");
        ResultSet rs= this.stmt.executeQuery(sql);
        String nextDate= rs.getString(1);

        GregorianCalendar nextDelS =stringToGregCal(nextDate);
        return  nextDelS;

    }

    public GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int days = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);

        GregorianCalendar gc= new GregorianCalendar(year, month, days);
        return  gc;
    }

    public String gregCalToString(GregorianCalendar gc){
        Date d= gc.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String s = dateFormat.format(d);
        return s;
    }

//todo: scrivere un metodo che permetta di modificare il DB_URL, passandogli una stringa

    public static void main(String[] argv){
        DAOClass dao = null;
        try{
            dao = new DAOClass();

        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
            System.exit(-1);
        }
        //todo: crea e salva
        //todo: loadFromDB
        //todo: printa se sono uguali
        //todo: deleate(?)

        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy") ,new Email("ema@gmail.com"), new GregorianCalendar(97,7,31), Gender.MAN);
        PrivateData priD = new PrivateData(new SurfaceAddress("cbduhw"), new SurfaceAddress("Roma"), new Nationality("Italiana"), new PhoneNumber("077152345678"));
        Utente us = new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions(new String[] {"a","b","c","d"}));
        System.out.println(us.printUser());

        try {
            dao.storeUserDB(us);

        }catch (SQLException se)
        {
            se.printStackTrace();
            System.exit(-1);
        }


    }
}
