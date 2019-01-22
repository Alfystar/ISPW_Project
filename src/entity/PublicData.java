package entity;

import java.awt.*;
import java.awt.image.ImagingOpException;
import java.util.GregorianCalendar;

public class PublicData {
    private Name name= new Name();
    private Name surname= new Name();
    private GregorianCalendar birthday= new GregorianCalendar();
    private Gender gender;
    private TaxCode fiscalCode= new TaxCode();
    private SocialStatus socialStatus= new SocialStatus();
    private ImagePath profileImage= new ImagePath();
    private Email email= new Email();
    private Nickname nickname= new Nickname();

    //Costruttore con i parametri di UserInfoRegister relativi a PublicData
    public PublicData(Name name, Name surname, TaxCode fiscalCode, Nickname nickname, Email email, GregorianCalendar birthday, Gender gender){
        this.name.set(name.get());
        this.surname.set(surname.get());
        this.fiscalCode= fiscalCode;
        this.nickname= nickname;
        this.email= email;
        this.birthday= birthday;
        this.gender= gender;
    }
    //Costruttore con tutti i parametri possibili di PublicData
    public PublicData(Name name, Name surname, GregorianCalendar birthday, Gender gender, TaxCode fiscalCode, SocialStatus socialStatus, ImagePath profileImage, Email email, Nickname nickname){
        this.name= name;
        this.surname=surname;
        this.birthday= birthday;
        this.gender= gender;
        this.fiscalCode= fiscalCode;
        this.socialStatus= socialStatus;
        this.profileImage= profileImage;
        this.email= email;
        this.nickname= nickname;
    }

    public PublicData(PublicData pubD){
        this.name.set(pubD.getName().get());
        this.surname.set(pubD.getSurname().get());
        this.birthday.setGregorianChange(pubD.birthday.getGregorianChange());
        this.gender= pubD.getGender();
        this.fiscalCode.set(pubD.getFiscalCode().get());
        this.socialStatus.set(pubD.getSocialStatus().get());
        this.profileImage.set(pubD.getProfileImage().get());
        this.email.set(pubD.getEmail().get());
        this.nickname.set(pubD.getNickname().get());
    }

    public Name getName(){
        return this.name;
    }

    public Name getSurname(){
        return this.surname;
    }

    public GregorianCalendar getBirthday(){
        return this.birthday;
    }

    public Gender getGender(){
        return this.gender;
    }

    public TaxCode getFiscalCode(){
        return this.fiscalCode;
    }

    public SocialStatus getSocialStatus(){
        return this.socialStatus;
    }

    public ImagePath getProfileImage(){
        return this.profileImage;
    }

    public Email getEmail(){
        return this.email;
    }

    public Nickname getNickname(){
        return this.nickname;
    }
}
