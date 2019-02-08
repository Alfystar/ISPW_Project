package DAO;

import bean.UserInfoRegister;
import control.DAOInterface;
import entity.*;
import exceptions.NickNotDBEx;
import exceptions.WrongParameters;

import java.sql.*;
import java.util.Calendar;
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
    public DAOClass() throws ClassNotFoundException{
        //loading dinamico del driver mysql
        try {
            if (nonFatta == true) {
                //STEP 2: Register JDBC driver
                Class.forName(DRIVER_CLASS_NAME);
                nonFatta= false;
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    public DAOClass(String ip) throws ClassNotFoundException{
        this();
        this.changeUrl(ip);
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
                this.saveUser(us);

            }catch(SQLException se){
                throw new WrongParameters("wrong parameters",se);
            }
            return us;
    }

    @Override
    public Boolean searchNickDB(Nickname nickname) throws SQLException {
        try {
            this.openConn();
            String sql = "SELECT nick FROM user where nick = " +
                    "\""+(nickname.get())+"\" "+ " ;";
            System.out.println(sql);
            this.stmt.executeQuery(sql);
            System.out.println("searchNickDB query executed");

            ResultSet rs= this.stmt.executeQuery(sql);
            if (!rs.first())
                return false;
            else return true;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            throw se;
        }  finally {
            //finally block used to close resources
            this.closeConn();
        }
    }

    @Override
    public Utente loadFromDB(Nickname nickname) throws NickNotDBEx, SQLException{
        this.openConn();
        String sql= "SELECT * FROM user where nick= " +
        "\""+nickname.get()+"\" "+ " ;";
        System.out.println(sql);
        ResultSet rs= this.stmt.executeQuery(sql);
        System.out.println("loadFromDB query executed");

        if (!rs.first()) throw  new NickNotDBEx("nickname not found");

        //estraggo da rs i dati e le chiavi per raggiungere le altre tabelle
        TaxCode taxCode= new TaxCode(rs.getString(2));
        Integer prD_id= rs.getInt(3);
        UserStatus userStatus = UserStatus.valueOf((String) rs.getObject(4));
        PW pw= new PW(rs.getString(5));
        Integer answ_id= rs.getInt(6);
        Byte bRoles= rs.getByte(7);
        byte bR = (byte)bRoles.intValue();
        Roles r= new Roles((bR&1)==1,(bR&2) == 2);

        //eseguo la query per PublicData
        String sqlPubD= "SELECT * FROM publicdata where taxCode =" +
                "\""+taxCode.get()+"\" "+ " ;";
        System.out.println(sqlPubD);
        rs= this.stmt.executeQuery(sqlPubD);
        rs.first();
        PublicData pubD= this.buidPubD(rs, taxCode, nickname);

        //eseguo la query per PrivateData
        String sqlPrD= "SELECT * FROM privatedata where idPrD = " +
                "\""+prD_id+"\" " +" ;";

        rs= this.stmt.executeQuery(sqlPrD);
        rs.first();
        PrivateData prD= this.buildPrD(rs, prD_id,nickname);

        //eseguo la query per Answers
        String sqlIdAnsw= "SELECT * FROM answers where idAnsw = " +
                "\""+answ_id+"\" "+ " ;";
        System.out.println(sqlIdAnsw);
        rs =this.stmt.executeQuery(sqlIdAnsw);
        rs.first();
        Questions q= this.buildAnsw(rs,answ_id, nickname);
        //chiudo la connessione col DB
        this.closeConn();
        //creo un'istanza di utente con tutit i dati ottenuti
        Utente us= new Utente(pubD, prD, pw, r, userStatus, q);
        return us;
    }

    @Override
    public void updateUser(Utente user) throws SQLException, NickNotDBEx{
        this.openConn();
        PublicData puB= user.getPublic();
        PrivateData prD= user.getPrivate();
        UserStatus usStat= user.getStatus();
        PW pw= user.getPw();
        Roles roles= user.getRole();

        //Faccio una query su user, per ottenere la chiave di accesso a prD;
        String sql= "SELECT prD_id FROM user WHERE nick= " + "\""+puB.getNick().get()+"\" "+ ";";
        System.out.println(sql);
        ResultSet rs = this.stmt.executeQuery(sql);
        if(!rs.first())
        {
            throw new NickNotDBEx("UPDATE impossibile, utente non più presente");
        }
        long prD_id = rs.getLong("prD_id");

        //Faccio update nel DB di una tabella PublicData
        String sqlPubD= "UPDATE  publicdata SET " +
                "socStat = " + "\""+(puB.getSocialStatus().get())+"\" " +" ," +
                "usImg=" + "\""+(puB.getAvatar().getAvatarName())+"\" " +" ," +
                "email =" + "\""+(puB.getEmail().get())+"\" " +
                "WHERE taxCode= " + "\""+puB.getTC().get()+"\" "+";";
        System.out.println(sqlPubD);
        this.stmt.executeQuery(sqlPubD);
        System.out.println("publicData update executed");

        //Faccio update nel DB di una tabella di PrivateData
        String sqlPrD= "UPDATE privatedata SET " +
                "phone =" +  "\""+(prD.getPhone().get())+"\" "+ " ," +
                "address =" + "\""+(prD.getLocalAddress().get())+"\" "+ " ," +
                "cityOfBirth =" + "\""+(prD.getCityOfBirth().get())+"\" "+ " ," +
                "nationality =" + "\""+(prD.getNationality().get())+"\" " +
                "WHERE idPrD =" + "\""+prD_id+"\" "+";";
        System.out.println(sqlPrD);
        this.stmt.executeQuery(sqlPrD);
        System.out.println("privateData update executed");

        //Faccio update dell'utente
        String sqlUpUs = "UPDATE user SET " +
                "userStatus =" + "\""+(usStat)+"\" "+ " ,"  +
                "pw =" + "\""+(pw.getPw())+"\" "+ " ,"  +
                "roles =" + "\""+(roles.rlBIN())+"\" " +
                "WHERE nick =" + "\""+puB.getNick().get()+"\" "+";";
        System.out.println(sqlUpUs);
        stmt.executeQuery(sqlUpUs);
        System.out.println("user update executed");
        this.closeConn();
        return;
    }

    private PublicData buidPubD(ResultSet rs, TaxCode taxCode, Nickname nickname) throws SQLException{
        Name name= new Name(rs.getString(2));
        Name surname= new Name(rs.getString(3));

        String birthday= rs.getString(4);
        GregorianCalendar birthD= stringToGregCal(birthday);

        Gender gender= Gender.valueOf((String) rs.getObject(5));
        SocialStatus socStatus= new SocialStatus(rs.getString(6));
        Avatar avatar= new Avatar(rs.getString(7));
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
    public void saveUser(Utente user) throws SQLException{
        this.openConn();
        PublicData puB= user.getPublic();
        PrivateData prD= user.getPrivate();
        Questions answ= user.getQuestions();
        UserStatus usStat= user.getStatus();
        PW pw= user.getPw();
        Roles roles= user.getRole();
        String bDay= gregCalToString(puB.getBirthday());

        //Inserisco nel DB una tabella PublicData
        String sqlPubD= "INSERT INTO  publicdata(taxCode, name, surname, birthday, gender, socStat, usImg, email) " +
                "VALUES (" +
                "\""+(puB.getTC().get())+"\" "+ " ," +
                "\""+(puB.getName().get())+"\" " + " ," +
                "\""+(puB.getSurname().get())+"\" " +" ," +
                "\""+bDay+"\" " +" ," +
                "\""+(puB.getGender().toString())+"\" " +" ," +
                "\""+(puB.getSocialStatus().get())+"\" " +" ," +
                "\""+(puB.getAvatar().getAvatarName())+"\" " +" ," +
                "\""+(puB.getEmail().get())+"\" " + ");" ;
        System.out.println(sqlPubD);
        this.stmt.executeQuery(sqlPubD);
        System.out.println("publicData insert executed");

        //Inserisco nel DB una tabella di PrivateData
        String sqlPrD= "INSERT INTO privatedata(phone, address, cityOfBirth,nationality)" +
                "VALUES (" +
                "\""+(prD.getPhone().get())+"\" "+ " ," +
                "\""+(prD.getLocalAddress().get())+"\" "+ " ," +
                "\""+(prD.getCityOfBirth().get())+"\" "+ " ," +
                "\""+(prD.getNationality().get())+"\" " + ");";
        System.out.println(sqlPrD);
        this.stmt.executeQuery(sqlPrD);
        System.out.println("privateData insert executed");
        //Prendo l'id della tabella appena generata
        String sqlPrId= "SELECT LAST_INSERT_ID() FROM privatedata;";
        ResultSet rs = this.stmt.executeQuery(sqlPrId);
        rs.first();
        long prD_id = rs.getLong("LAST_INSERT_ID()");

        //Inserisco nel DB una tabella di Answers
        String[] vect = answ.getAnswers();
        String sqlAnsw = "INSERT INTO answers(answ1,answ2,answ3,answ4)" +
                "VALUES (" +
                "\"" + vect[0]+ "\" "+ " ," +
                "\"" + vect[1]+ "\" "+ " ," +
                "\"" + vect[2]+ "\" "+ " ," +
                "\"" + vect[3]+ "\" "+ ");";
        System.out.println(sqlAnsw);
        this.stmt.executeQuery(sqlAnsw);
        System.out.println("answers insert executed");

        //Prendo l'id della tabella appena generata
        String sqlAId = "SELECT LAST_INSERT_ID();";
        rs = this.stmt.executeQuery(sqlAId);
        rs.first();
        long answ_id = rs.getLong("LAST_INSERT_ID()");
        //Creo una tabella di Utente nel DB
        String sqlNewUs = "INSERT INTO user(nick,pubD_Tc,prD_id,userStatus,pw,answ_id,roles)" +
                "VALUES (" +
                "\""+(puB.getNick().get())+"\" "+ " ,"  +
                "\""+(puB.getTC().get())+"\" "+ " ,"  +
                "\""+(prD_id)+"\" "+ " ,"  +
                "\""+(usStat)+"\" "+ " ,"  +
                "\""+(pw.getPw())+"\" "+ " ,"  +
                "\""+(answ_id)+"\" "+ " ,"  +
                "\""+(roles.rlBIN())+ "\" " + ");";
        System.out.println(sqlNewUs);
        stmt.executeQuery(sqlNewUs);
        System.out.println("user insert executed");
        this.closeConn();
        return;
    }

    @Override
    public Boolean searchTC(TaxCode cf) throws SQLException {
        try {
            this.openConn();
            String sql = "SELECT taxCode FROM publicdata where taxCode = " +
                    "\""+cf.get()+"\" "+ " ;";
            System.out.println(sql);
            ResultSet rs = this.stmt.executeQuery(sql);
            System.out.println("searchTC query executed");
            if (!rs.first())
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

    @Override
    public void destroy(Nickname nickname) throws NickNotDBEx, SQLException{
        this.openConn();
        String sql= "SELECT * FROM user where nick=  " +
                "\""+nickname.get()+"\" "+ " ;";
        System.out.println(sql);
        ResultSet rs= this.stmt.executeQuery(sql);
        System.out.println("destroy query executed");
        if (!rs.first()) throw  new NickNotDBEx("nickname not found");

        //Prendo tutte chiavi delle tabelle collegate a Utente, e le distruggo
        String tc= rs.getString(2);
        int prD_id= rs.getInt(3);
        int answ_id = rs.getInt(6);

        String sqlPubD= "DELETE FROM publicdata where taxCode = " +
                "\""+tc+"\" "+ " ;";
        System.out.println(sqlPubD);
        this.stmt.executeQuery(sqlPubD);
        System.out.println("publicData deleted");

        String sqlPrD= "DELETE FROM privatedata where idPrD = " +
                "\""+prD_id+"\" "+ " ;";
        System.out.println(sqlPrD);
        this.stmt.executeQuery(sqlPrD);
        System.out.println("privateData deleted");

        String sqlAnsw= "DELETE FROM answers where idAnsw = " +
                "\""+answ_id+"\" "+ " ;";
        System.out.println(sqlAnsw);
        this.stmt.executeQuery(sqlAnsw);
        System.out.println("answers deleted");

        //Ora distruggo l'istanza di Utente
        String sqlUs= "DELETE FROM user where nick = " +
                "\""+nickname.get()+"\" "+ " ;";
        System.out.println(sqlUs);
        this.stmt.executeQuery(sqlUs);
        System.out.println("user destroyed");

        return;
    }

    @Override
    public void deleteNTime(Nickname nickname, GregorianCalendar date) throws SQLException {
        this.openConn();
        String strDate= gregCalToString(date);

        String sql= "INSERT INTO dateevent VALUES (idDate,nick)= " +
                "\""+strDate+"\" "+ "," +
                "\""+nickname.get()+"\" "+ " ;";
        System.out.println(sql);
        this.stmt.executeQuery(sql);

        System.out.println("date inserted");
        this.closeConn();
        return;
    }

    public void deleteByDeamon(GregorianCalendar today) throws SQLException{
        this.openConn();
        String oggi= gregCalToString(today);

        String sql= "SELECT nick FROM dateevent where idDate<= " +
                "\""+oggi+"\" "+ " ;";
        System.out.println(sql);
        this.stmt.executeQuery(sql);
        System.out.println("deleteByDeamon query executed");
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
        this.openConn();
        String sql= "SELECT * FROM deletesession;";
        this.stmt.executeQuery(sql);
        System.out.println("nextDeleteSession query executed");
        ResultSet rs= this.stmt.executeQuery(sql);
        if(!rs.first()) {
            java.util.Date tomorrow = GregorianCalendar.getInstance().getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.HOUR,1);
            return new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE));
        }
        String nextDate= rs.getString(1);
        GregorianCalendar nextDelS =stringToGregCal(nextDate);
        return  nextDelS;

    }

    public void removeDataEvent(Nickname nick) throws SQLException{
        this.openConn();
        String sql= "DELETE * FROM dateevent WHERE nick =" +
                "\""+nick.get()+"\" "+ " ;";
        this.stmt.executeQuery(sql);
        System.out.println("removeDataEvent query executed");
        this.closeConn();
    }

    private GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int days = Integer.parseInt(splitDate[2]);
        GregorianCalendar gc= new GregorianCalendar(year, month-1, days);
        return  gc;

    }

    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }

    public void changeUrl(String ip){
        this.DB_URL= "jdbc:mysql://" + ip + "/user";
    }

    public Boolean testNet() throws SQLException
    {
        this.openConn();
        String sql = "/* ping */ SELECT 1";
        ResultSet rs = this.stmt.executeQuery(sql);
        System.out.println("testNet query executed");
        if(rs.first()) return true;
        else return false;
    }

    private void openConn() throws SQLException
    {
        //STEP 3: Open a connection
        conn = DriverManager.getConnection(DB_URL, UTENTE, PASSWORD);
        System.out.println("Connected database successfully...");
        //STEP 4: create Statement
        stmt = conn.createStatement();
        System.out.println("Create Statement ...");
    }

    private void closeConn() {
        try {
            if (stmt != null) {
                conn.close(); }
        } catch (SQLException se) {
        }// do nothing
        System.out.println("Close Statement...");

        try {
            if (conn != null) {
                conn.close(); }
        } catch (SQLException se) {
            se.printStackTrace(); }
        System.out.println("Close connection wiht database...");
    }

    public static void main(String[] argv){
        DAOClass dao = null;
        try{
            dao = new DAOClass("10.200.146.92");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(-1);
        }
        PublicData pubD = new PublicData(new Name("Marta"), new Name("Caggiano"), new TaxCode("cggmrt"), new Nickname("caggy") ,new Email("marta.caggiano@hotmail"), new GregorianCalendar(1998,2,3), Gender.WOMAN);
        PrivateData priD = new PrivateData(new SurfaceAddress("Alatri casa"), new SurfaceAddress("alatri"), new Nationality("Italiana"), new PhoneNumber("077152345678"));
        Utente us1 = new Utente(pubD,priD,new PW("12345"),new Roles(false,true),new Questions(new String[] {"abaco","ballerina","coniglio","destra"}));

         pubD = new PublicData(new Name("Filippo"), new Name("Badalamenti"), new TaxCode("bflflp"), new Nickname("bal") ,new Email("filippo@gmail.com"), new GregorianCalendar(1997,5,25), Gender.MAN);
         priD = new PrivateData(new SurfaceAddress("collegio"), new SurfaceAddress("Roma"), new Nationality("Italiano"), new PhoneNumber("064586325"));
        Utente us2 = new Utente(pubD,priD,new PW("qwerty"),new Roles(true,false),new Questions(new String[] {"aa","bb","cc","dd"}));

         pubD = new PublicData(new Name("Emanuele"), new Name("Alfano"), new TaxCode("lfnmnl"), new Nickname("alfy") ,new Email("alfystar1701@gmail.com"), new GregorianCalendar(1997,7,31), Gender.MAN);
         priD = new PrivateData(new SurfaceAddress("ciamarra"), new SurfaceAddress("roma"), new Nationality("Italiano"), new PhoneNumber("3333071117"));
        Utente us3 = new Utente(pubD,priD,new PW("lele"),new Roles(true,true),new Questions(new String[] {"astro","balocco","cavolfiore","dentista"}));

         pubD = new PublicData(new Name("elisa"), new Name("alfano"), new TaxCode("lfnlsa"), new Nickname("sorreta") ,new Email("elisa@gmail.com"), new GregorianCalendar(2000,9,12), Gender.WOMAN);
         priD = new PrivateData(new SurfaceAddress("ciamarra"), new SurfaceAddress("Roma"), new Nationality("Italiana"), new PhoneNumber("45628731982"));
        Utente us4 = new Utente(pubD,priD,new PW("sissi"),new Roles(),new Questions(new String[] {"aaaa","bbbb","ccccc","ddddd"}));

         pubD = new PublicData(new Name("Marco"), new Name("alfano"), new TaxCode("lfnmrc"), new Nickname("topo65") ,new Email("marco@gmail.com"), new GregorianCalendar(1965,7,18), Gender.MAN);
         priD = new PrivateData(new SurfaceAddress("ciamarra"), new SurfaceAddress("Roma"), new Nationality("Italiano"), new PhoneNumber("333222568748"));
        Utente us5 = new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions(new String[] {"gara","cavoletti","piangere","dividere"}));

        Utente[] users ={us1,us2,us3,us4,us5};

        //todo: crea e salva
        //todo: loadFromDB
        //todo: printa se sono uguali
        //todo: deleate(?)
        int caso=1;
        switch (caso)
        {
            case 1: //save in DB
                for (int i = 0; i < users.length; i++) {
                    try {
                        System.out.println(users[i].toString());
                        dao.saveUser(users[i]);
                    } catch (SQLException se) {
                        se.printStackTrace();
                        i++;
                        //System.exit(-1);
                    }
                }
                break;
            case 2: //load from DB
                Utente usLoad=null;
                try {
                    usLoad = dao.loadFromDB(new Nickname("alfy"));
                    System.out.println(usLoad.printUser());
                }catch (SQLException|NickNotDBEx e)
                {
                    e.printStackTrace();
                }
                break;
            case 3: //search nick test
                String[] nickSearch = {"caggy", "giug", "pippo"};
                for (int i = 0; i < nickSearch.length; i++) {
                    try {
                        System.out.println(nickSearch[i]+" Is Found:"+dao.searchNickDB(new Nickname(nickSearch[i])));
                    } catch (SQLException se) {
                        se.printStackTrace();
                        i++;
                    }
                }
                break;
            case 4://search TaxCode test
                String[] taxSearch = {"dsafgdsfg", "asdasd", "normale"};
                for (int i = 0; i < taxSearch.length; i++) {
                    try {
                        System.out.println(taxSearch[i]+" Is Found:"+dao.searchTC(new TaxCode(taxSearch[i])));
                    } catch (SQLException se) {
                        se.printStackTrace();
                        i++;
                    }
                }
                break;
            case 5: //test destroy istantaneo
                try {
                    dao.destroy(new Nickname("thadhtht"));
                    System.out.println("User Destroy");
                }catch (SQLException|NickNotDBEx se) {
                    se.printStackTrace();
                }
                break;
            case 6: //test updateUser
                try {
                   dao.updateUser(us3);
                }catch (Exception se){
                    se.printStackTrace();
                }
                break;
        }
    }


}
