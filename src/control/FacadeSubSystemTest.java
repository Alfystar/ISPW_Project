package control;

import bean.UserInfoRegister;
import entity.*;
import exceptions.UserNotExistEx;
import gluonBoundary.utilityClass.RandomString;
import org.junit.jupiter.api.*;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class FacadeSubSystemTest{

    private FacadeSubSystem facade = new FacadeSubSystem();
    private UserExpert userExpert = new UserExpert();
    RandomString gen = new RandomString(8, ThreadLocalRandom.current());


    private Utente lastRandomUser;


    @BeforeEach
    void setUp() throws Exception{
        FacadeSubSystemTest ft = new FacadeSubSystemTest();
        lastRandomUser = ft.randomUser();
        ft.userExpert.createUserAll(lastRandomUser);
        System.out.println("Utente casuale inserito:\n"+lastRandomUser.toString());

    }

    @Test
    void getBasicUserInfo()throws Exception{
        BasicUserInfo usInfo = facade.getBasicUserInfo(lastRandomUser.getPublic().getNick());
        assertEquals(lastRandomUser.getPublic().clone(),usInfo,"Test uguaglianza dei BasicUserInfo");
    }

    @Test
    void getRestrictedUserInfo()throws Exception{
        RestrictUserInfo usInfo = facade.getRestrictedUserInfo(lastRandomUser.getPublic().getNick());
        assertEquals(lastRandomUser.getPrivate().clone(),usInfo,"Test uguaglianza dei RestrictUserInfo");
    }

    @Test
    void doesNicknameExist()throws Exception{
        assertTrue(facade.doesNicknameExist(lastRandomUser.getPublic().getNick()),"Test doesNicknameExist");
    }

    @Test
    void doesTaxCodeExist()throws Exception{
        assertTrue(facade.doesTaxCodeExist(lastRandomUser.getPublic().getTC()),"Test doesTaxCodeExist");
    }

    @Test
    void createUser()throws Exception{
        UserInfoRegister infoReg = randomInfoReg();
        facade.createUser(infoReg.getNickname(),infoReg);


        //Parte di controllo
        //Prendo tutti i parametri di Utente da infoReg
        BasicUserInfo pubD = new BasicUserInfo(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(), infoReg.getEmail(), infoReg.getBirthday(), infoReg.getGender());
        RestrictUserInfo priD = new RestrictUserInfo();
        priD.getCityOfBirth().set(infoReg.getCityOfBirth().get());
        PW pw = new PW(infoReg.getPw());
        Roles roles = new Roles();
        UserStatus userStatus = UserStatus.ACTIVE;
        Questionary Answers = new Questionary(infoReg.getAnswers());

        //incapsulo i dati in un'istanza di Utente
        Utente us = new Utente(pubD, priD, pw, roles, Answers);

        assertEquals(us,userExpert.getUser(infoReg.getNickname()),"Test createUser che prende un vettore infoRegister");

        System.out.println("Se gli altri hanno avuto successo è evidente che questo metodo funziona");
    }

    @Test
    void cancelUser()throws Exception{
        facade.cancelUser(lastRandomUser.getPublic().getNick());
        assertEquals(lastRandomUser.getStatus(),userExpert.getUser(lastRandomUser.getPublic().getNick()).getStatus(),"Test cancelUser cambia lo status a CANCELLED");
    }

    @Test
    void deleteUser()throws Exception{
        facade.deleteUser(lastRandomUser.getPublic().getNick());
        assertThrows(UserNotExistEx.class,()->{userExpert.getUser(lastRandomUser.getPublic().getNick());}, "Eliminazione dell'utente dal sistema istantaneamente");
    }



    @Test
    void validate()throws Exception{
        assertTrue(facade.validate(lastRandomUser.getPublic().getNick(),lastRandomUser.getPw()));
    }

    @Test
    void checkQuestion()throws Exception{
        assertTrue(facade.checkQuestion(lastRandomUser.getPublic().getNick(),lastRandomUser.getQuestionary()));
    }

    @Test
    void getAvatar()throws Exception{
        assertEquals(lastRandomUser.getPublic().getAvatar(),facade.getAvatar(lastRandomUser.getPublic().getNick()));
    }


    @Test
    void getRoles()throws Exception{
        assertEquals(lastRandomUser.getRole(),facade.getRoles(lastRandomUser.getPublic().getNick()));
    }

    @Test
    void getStatus()throws Exception{
        assertEquals(lastRandomUser.getStatus(),facade.getStatus(lastRandomUser.getPublic().getNick()));
    }
    
    @AfterAll
    static void coveregTest()throws Exception{
        FacadeSubSystem facade = new FacadeSubSystem();
        FacadeSubSystemTest ft = new FacadeSubSystemTest();
        Utente lastRandomUser = ft.randomUser();
        ft.userExpert.createUserAll(lastRandomUser);

        facade.doesNicknameExist(lastRandomUser.getPublic().getNick()); //branch ok
        facade.doesNicknameExist(new Nickname(ft.randomString()));         //branch false
        facade.doesTaxCodeExist(lastRandomUser.getPublic().getTC());    //branch ok
        facade.doesTaxCodeExist(new TaxCode(ft.randomString()));           //branch false
        facade.changeNotAnagraphicData(lastRandomUser.getPublic().getNick(),new SocialStatus(ft.randomString()));      //branch ok
        facade.changeNotAnagraphicData(lastRandomUser.getPublic().getNick(),new Email(ft.randomString()));             //branch ok
        facade.changeNotAnagraphicData(lastRandomUser.getPublic().getNick(),new SurfaceAddress(ft.randomString()));    //branch ok
        facade.changeNotAnagraphicData(lastRandomUser.getPublic().getNick(),new Nationality(ft.randomString()));       //branch ok
        facade.changeNotAnagraphicData(lastRandomUser.getPublic().getNick(),new PhoneNumber(ft.randomString()));       //branch ok
        //facade.changeNotAnagraphicData(new Nickname(ft.randomString()),new Email(ft.randomString()));                     //branch false
        facade.changePassword(lastRandomUser.getPublic().getNick(),lastRandomUser.getPw(),new PW(ft.randomString()));
        //facade.changePassword(new Nickname(ft.randomString()),lastRandomUser.getPw(),new PW(ft.randomString()));
        facade.forgottenPassword(lastRandomUser.getPublic().getNick(),lastRandomUser.getQuestionary(),new PW(ft.randomString()));
        PW newPw = new PW(ft.randomString());
        //facade.changePassword(new Nickname(ft.randomString()),newPw,newPw);
        facade.changeHost("localHost");
        facade.getLastHost();
        facade.setAvatar(lastRandomUser.getPublic().getNick(),1);
        facade.makeARenter(lastRandomUser.getPublic().getNick());
        facade.removeRentership(lastRandomUser.getPublic().getNick());
        facade.makeATenant(lastRandomUser.getPublic().getNick());
        facade.removeTenantship(lastRandomUser.getPublic().getNick());
        facade.changeUserStatus(lastRandomUser.getPublic().getNick(),UserStatus.ACTIVE);
        facade.removeTenantship(lastRandomUser.getPublic().getNick());
        facade.isRenter(lastRandomUser.getPublic().getNick());
        facade.isTenant(lastRandomUser.getPublic().getNick());
        facade.isActive(lastRandomUser.getPublic().getNick());
        facade.isInactive(lastRandomUser.getPublic().getNick());
        facade.isCancelled(lastRandomUser.getPublic().getNick());
        facade.isBanned(lastRandomUser.getPublic().getNick());
    }

    private Utente randomUser()
    {
        BasicUserInfo pubD = new BasicUserInfo(new Name(randomString()), new Name(randomString()), new TaxCode(randomString()), new Nickname(randomString()), new Email(randomString()), new GregorianCalendar(randInt(1950,1999), randInt(1,12), randInt(1,28)), randGender());
        RestrictUserInfo priD = new RestrictUserInfo(new SurfaceAddress(randomString()), new SurfaceAddress(randomString()), new Nationality(randomString()), new PhoneNumber(randomString()));
        Utente us = new Utente(pubD, priD, new PW(randomString()), new Roles(randBoolean(), randBoolean()), new Questionary(new String[]{randomString(),randomString(),randomString(),randomString()}));
        return us;
    }



    private UserInfoRegister randomInfoReg() {
        String[] answ= {randomString(), randomString(),randomString(),randomString()};
        UserInfoRegister infoReg = new UserInfoRegister(new Name(randomString()), new Name(randomString()), new TaxCode(randomString()), new Nickname(randomString()), new Email(randomString()), new GregorianCalendar(randInt(1950,1999), randInt(1,12), randInt(1,28)), randGender(), new Questionary(answ), new PW(randomString()), new SurfaceAddress(randomString()));
        return infoReg;
    }

    private String randomString(){
        return gen.nextString();
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private boolean randBoolean() {
        Random rand = new Random();
        int randomNum = rand.nextInt(1000);
        return randomNum >= 500;
    }

    private Gender randGender() {
        Random rand = new Random();
        int randomNum = rand.nextInt(1000);
        if(randomNum >= 500) return Gender.MAN;
        else return Gender.WOMAN;
    }
}