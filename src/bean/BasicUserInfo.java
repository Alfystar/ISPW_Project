package bean;

import entity.*;

import java.util.GregorianCalendar;

public class BasicUserInfo {

    private PublicData publicData;

    /*Constructor*/

    public BasicUserInfo(PublicData pubDPass){
        this.publicData = new PublicData(pubDPass);
    }

    /*Methods*/

    public Name getName(){
        return this.publicData.getName();
    }

    public Name getSurname(){

        return this.publicData.getSurname();
    }

    public GregorianCalendar getBirthday(){

        return this.publicData.getBirthday();
    }

    public Gender getGender(){

        return this.publicData.getGender();
    }

    public TaxCode getTaxCode(){

        return this.publicData.getFiscalCode();
    }

    public SocialStatus getsocialStatus(){

        return this.publicData.getSocialStatus();
    }

    public ImagePath getProfileImage(){

        return this.publicData.getProfileImage();
    }

    public Email getEmail(){

        return this.publicData.getEmail();
    }

    public Nickname getNickname(){

        return this.publicData.getNickname();
    }


}
