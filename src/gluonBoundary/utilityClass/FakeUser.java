package gluonBoundary.utilityClass;

import entity.*;

import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class FakeUser implements Runnable{
    private Vector<Utente> users = new Vector<>();
    RandomString gen = new RandomString(8, ThreadLocalRandom.current());

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
                while(true){}
                break;
            case 1: //adminFake
                while(true){}
                break;
            case 2: //otherFake
                while(true){}
                break;
        }

    }

    private Utente randomUser()
    {
        PublicData pubD = new PublicData(new Name(randomString()), new Name(randomString()), new TaxCode(randomString()), new Nickname(randomString()), new Email(randomString()), new GregorianCalendar(1998, 2, 3), Gender.WOMAN);
        PrivateData priD = new PrivateData(new SurfaceAddress(randomString()), new SurfaceAddress("alatri"), new Nationality("Italiana"), new PhoneNumber("077152345678"));
        Utente us = new Utente(pubD, priD, new PW("12345"), new Roles(false, true), new Questions(new String[]{randomString(),randomString(),randomString(),randomString()}));
        users.add(us);
        return us;
    }

    private String randomString(){
        return gen.nextString();
    }

    private void registerFake(){

    }

    private void adminDeleteFake(){

    }

    private void otherFake(){

    }
}
