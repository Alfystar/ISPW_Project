package entity;

import java.awt.*;
import java.awt.image.ImagingOpException;
import java.util.GregorianCalendar;

public class PublicData {
    private Name name;
    private Name surname;
    private GregorianCalendar birthday;
    private Gender gender;
    private TaxCode fiscalCode;
    private SocialStatus socialStatus;
    private ImagePath profileImage;
    private Email email;
    private Nickname nickname;

    //Costruttore con i parametri di UserInfoRegister relativi a PublicData
    public PublicData(Name name, Name surname, TaxCode fiscalCode, Nickname nickname, Email email, GregorianCalendar birthday, Gender gender){
        this.name= new Name(name);
        this.surname= new Name(surname);
        this.fiscalCode= new TaxCode(fiscalCode);
        this.nickname= new Nickname(nickname);
        this.email= new Email(email);
        this.birthday= new GregorianCalendar(birthday);
        this.gender= new Gender(gender);
    }
    //Costruttore con tutti i parametri possibili di PublicData
    public PublicData(Name name, Name surname, GregorianCalendar birthday, Gender gender, TaxCode fiscalCode, SocialStatus socialStatus, ImagePath profileImage, Email email, Nickname nickname){
        this.name= new Name(name);
        this.surname= new Name(surname);
        this.birthday= birthday;
        this.gender= gender;
        this.fiscalCode= new TaxCode(fiscalCode);
        this.socialStatus= new SocialStatus(socialStatus);
        this.profileImage= new ImagePath(profileImage);
        this.email= new Email(email);
        this.nickname= new Nickname(nickname);
    }

    public PublicData(PublicData pubD){
        this.name= new Name(name);
        this.surname= new Name(surname);
        this.birthday.setGregorianChange(pubD.birthday.getGregorianChange());
        this.gender= pubD.getGender();
        this.fiscalCode= new TaxCode(fiscalCode);
        this.socialStatus= new SocialStatus(socialStatus);
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
