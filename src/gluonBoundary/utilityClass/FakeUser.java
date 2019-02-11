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
    private Vector<UserInfoRegister> users = new Vector<>();
    RandomString gen = new RandomString(8, ThreadLocalRandom.current());

    FacadeSubSystem facade = new FacadeSubSystem();

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
                while(true){registerFake();}

            case 1: //adminFake
                while(true){adminDeleteFake();}

            case 2: //otherFake
                while(true){otherFake();}

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
            Thread.sleep(randInt(100,500));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void adminDeleteFake(){
        try{
            int randUser = randInt(0,users.size()-1);
            UserInfoRegister us=users.remove(randUser);
            facade.deleteUser(us.getNickname());
            Thread.sleep(randInt(100,500));
        }catch(UserNotExistEx e)
        {
            System.err.println("##adminDeleteFake ha provato a eliminare un utente già eliminato");
        }catch(InterruptedException e)
        {

        }
    }

    private void otherFake(){

    }


    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
