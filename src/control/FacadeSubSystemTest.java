package control;

import DAO.DAOClass;
import bean.BasicUserInfo;
import bean.FactoryInfo;
import bean.RestrictUserInfo;
import bean.UserInfoRegister;
import entity.*;
import exceptions.UserNotExistEx;
import gluonBoundary.utilityClass.RandomString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class FacadeSubSystemTest{

    private FacadeSubSystem facade = new FacadeSubSystem();
    private UserExpert userExpert = new UserExpert();
    private FactoryInfo factInf = new FactoryInfo();
    RandomString gen = new RandomString(8, ThreadLocalRandom.current());


    private Utente lastRandomUser;


    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception{
        FacadeSubSystemTest ft = new FacadeSubSystemTest();
        lastRandomUser = ft.randomUser();
        ft.userExpert.createUserAll(lastRandomUser);
        System.out.println("Utente casuale inserito:\n"+lastRandomUser.toString());

    }

    @org.junit.jupiter.api.Test
    void getBasicUserInfo()throws Exception{
        BasicUserInfo usInfo = facade.getBasicUserInfo(lastRandomUser.getPublic().getNick());
        assertEquals(factInf.createBasic(lastRandomUser.getPublic()),usInfo,"Test uguaglianza dei BasicUserInfo");
    }

    @org.junit.jupiter.api.Test
    void getRestrictedUserInfo()throws Exception{
        RestrictUserInfo usInfo = facade.getRestrictedUserInfo(lastRandomUser.getPublic().getNick());
        assertEquals(factInf.createRestrict(lastRandomUser.getPrivate()),usInfo,"Test uguaglianza dei RestrictUserInfo");
    }

    @org.junit.jupiter.api.Test
    void doesNicknameExist()throws Exception{
        assertTrue(facade.doesNicknameExist(lastRandomUser.getPublic().getNick()),"Test doesNicknameExist");
    }

    @org.junit.jupiter.api.Test
    void doesTaxCodeExist()throws Exception{
        assertTrue(facade.doesTaxCodeExist(lastRandomUser.getPublic().getTC()),"Test doesTaxCodeExist");
    }

    @org.junit.jupiter.api.Test
    void createUser()throws Exception{
        UserInfoRegister infoReg = randomInfoReg();
        facade.createUser(infoReg.getNickname(),infoReg);


        //Parte di controllo
        //Prendo tutti i parametri di Utente da infoReg
        PublicData pubD = new PublicData(infoReg.getName(), infoReg.getSurname(), infoReg.getCf(), infoReg.getNickname(), infoReg.getEmail(), infoReg.getBirthday(), infoReg.getGender());
        PrivateData priD = new PrivateData();
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

    @org.junit.jupiter.api.Test
    void cancelUser()throws Exception{
        facade.cancelUser(lastRandomUser.getPublic().getNick());
        assertEquals(lastRandomUser.getStatus(),userExpert.getUser(lastRandomUser.getPublic().getNick()).getStatus(),"Test cancelUser cambia lo status a CANCELLED");
    }

    @org.junit.jupiter.api.Test
    void deleteUser()throws Exception{
        facade.deleteUser(lastRandomUser.getPublic().getNick());
        assertThrows(UserNotExistEx.class,()->{userExpert.getUser(lastRandomUser.getPublic().getNick());}, "Eliminazione dell'utente dal sistema istantaneamente");
    }



    @org.junit.jupiter.api.Test
    void validate()throws Exception{
        assertTrue(facade.validate(lastRandomUser.getPublic().getNick(),lastRandomUser.getPw()));
    }

    @org.junit.jupiter.api.Test
    void checkQuestion()throws Exception{
        assertTrue(facade.checkQuestion(lastRandomUser.getPublic().getNick(),lastRandomUser.getQuestionary()));
    }

    @org.junit.jupiter.api.Test
    void getAvatar()throws Exception{
        assertEquals(lastRandomUser.getPublic().getAvatar(),facade.getAvatar(lastRandomUser.getPublic().getNick()));
    }


    @org.junit.jupiter.api.Test
    void getRoles()throws Exception{
        assertEquals(lastRandomUser.getRole(),facade.getRoles(lastRandomUser.getPublic().getNick()));
    }

    @org.junit.jupiter.api.Test
    void getStatus()throws Exception{
        assertEquals(lastRandomUser.getStatus(),facade.getStatus(lastRandomUser.getPublic().getNick()));
    }

    private Utente randomUser()
    {
        PublicData pubD = new PublicData(new Name(randomString()), new Name(randomString()), new TaxCode(randomString()), new Nickname(randomString()), new Email(randomString()), new GregorianCalendar(randInt(1950,1999), randInt(1,12), randInt(1,28)), randGender());
        PrivateData priD = new PrivateData(new SurfaceAddress(randomString()), new SurfaceAddress(randomString()), new Nationality(randomString()), new PhoneNumber(randomString()));
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