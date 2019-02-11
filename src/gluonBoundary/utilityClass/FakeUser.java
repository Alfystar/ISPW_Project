package gluonBoundary.utilityClass;

import bean.UserInfoRegister;
import control.FacadeSubSystem;
import entity.*;
import exceptions.UserNotExistEx;
import exceptions.WrongParameters;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class FakeUser implements Runnable{
    private static Vector<UserInfoRegister> users = new Vector<>();
    RandomString gen = new RandomString(8, ThreadLocalRandom.current());

    FacadeSubSystem facade = new FacadeSubSystem();

    private int timeMin=500;
    private int timeMax=1000;

    private int fakeType=0;

    public FakeUser(int typeFake)
    {
        fakeType=typeFake;
    }

    @Override
    public void run(){
        switch(fakeType)
        {
            case 0: //registerFake
                while(true){
                    System.out.println("registerFake restart");
                    registerFake();
                }

            case 1: //adminFake
                while(true){
                    System.out.println("adminFake restart");
                    adminDeleteFake();
                }

            case 2: //otherFake
                while(true){
                    System.out.println("otherFake restart");
                    otherFake();
                }

        }

    }

    /*
    private Utente randomUser()
    {
        PublicData pubD = new PublicData(new Name(randomString()), new Name(randomString()), new TaxCode(randomString()), new Nickname(randomString()), new Email(randomString()), new GregorianCalendar(1998, 2, 3), Gender.WOMAN);
        PrivateData priD = new PrivateData(new SurfaceAddress(randomString()), new SurfaceAddress("alatri"), new Nationality("Italiana"), new PhoneNumber("077152345678"));
        Utente us = new Utente(pubD, priD, new PW("12345"), new Roles(false, true), new Questions(new String[]{randomString(),randomString(),randomString(),randomString()}));
        users.add(us);
        return us;
    }*/

    private UserInfoRegister randomInfoReg() {
        String[] answ= {randomString(), randomString(),randomString(),randomString()};
        UserInfoRegister infoReg = new UserInfoRegister(new Name(randomString()), new Name(randomString()), new TaxCode(randomString()), new Nickname(randomString()), new Email(randomString()), new GregorianCalendar(1998, 2, 3), Gender.WOMAN, new Questions(answ), new PW(randomString()), new SurfaceAddress(randomString()));
        users.add(infoReg);
        return infoReg;
    }

    private String randomString(){
        return gen.nextString();
    }

    private void registerFake() {
        try {
            UserInfoRegister usInfoReg= this.randomInfoReg();
            this.facade.createUser(usInfoReg.getNickname(), usInfoReg);
            Thread.sleep(randInt(timeMin,timeMax));
        }catch (Exception e){
            System.err.println("##registerFake");
            e.printStackTrace();
        }
    }

    private void adminDeleteFake(){
        try{
            if(users.isEmpty())
            {
                System.out.println("**adminDeleteFake empty");
                Thread.sleep(randInt(timeMin,timeMax));
                return;
            }
            int randUser = randInt(0,users.size()-1);
            UserInfoRegister us= users.remove(randUser);
            facade.deleteUser(us.getNickname());
            System.out.println("**adminDeleteFake eliminato: "+ us.getNickname().get());
            Thread.sleep(randInt(timeMin,timeMax));
        }catch(Exception e)
        {
            System.err.println("##adminDeleteFake");
            e.printStackTrace();
        }
    }

    private void otherFake(){
        try{

            Thread.sleep(randInt(timeMin,timeMax));
        }catch(Exception e)
        {
            System.err.println("##otherFake");
            e.printStackTrace();
        }

    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
