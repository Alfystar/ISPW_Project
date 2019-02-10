package bean;

import entity.*;

import java.util.GregorianCalendar;

public class FactoryInfo{

    public static void main(String[] args){
        FactoryInfo testFact = new FactoryInfo();

        /*Creazione di un utente con i parametri di UserInfoRegister*/

        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy"), new Email("ema@gmail.com"), new GregorianCalendar(97, 7, 31), Gender.MAN);
        PrivateData priD = new PrivateData(new SurfaceAddress("Roma"), new SurfaceAddress("Termini"), new Nationality("IT"), new PhoneNumber("3334233142"));

        BasicUserInfo basic = testFact.createBasic(pubD);

        RestrictUserInfo restr = testFact.createRestrict(priD);

        System.out.println(basic.toString());
        System.out.println(restr.toString());

    }

    public BasicUserInfo createBasic(PublicData pubD){

        return new BasicUserInfo(pubD);
    }

    public RestrictUserInfo createRestrict(PrivateData priD){

        return new RestrictUserInfo(priD);
    }
}