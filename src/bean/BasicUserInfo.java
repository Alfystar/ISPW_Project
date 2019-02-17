package bean;

import entity.*;

import java.util.GregorianCalendar;

public class BasicUserInfo{

    private PublicData publicData;

    /*Constructor*/

    public BasicUserInfo(PublicData pubDPass){
        this.publicData = (PublicData) pubDPass.clone();
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

        return this.publicData.getTC();
    }

    public SocialStatus getsocialStatus(){

        return this.publicData.getSocialStatus();
    }

    public Avatar getAvatar(){

        return this.publicData.getAvatar();
    }

    public Email getEmail(){

        return this.publicData.getEmail();
    }

    public Nickname getNickname(){

        return this.publicData.getNick();
    }

    @Override
    public String toString(){

        return this.publicData.toString();
    }

    @Override
    public boolean equals(Object o){
        try{
            return publicData.equals(((BasicUserInfo) o).publicData);
        }catch(ClassCastException e){
            return false;
        }
    }

}
