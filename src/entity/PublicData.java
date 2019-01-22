package entity;

import java.awt.*;
import java.awt.image.ImagingOpException;
import java.util.GregorianCalendar;

public class PublicData {
    private Name name;
    private Name surname;
    private GregorianCalendar birthday= new GregorianCalendar();
    private Gender gender;
    private TaxCode fiscalCode;
    private SocialStatus socialStatus;
    private ImagePath profileImage= new ImagePath();
    private Email email;
    private Nickname nickname;

    //Costruttore con i parametri di UserInfoRegister relativi a PublicData
    public PublicData(Name name, Name surname, TaxCode fiscalCode, Nickname nickname, Email email, GregorianCalendar birthday, Gender gender){
        /*passati*/
        this.name= new Name(name);
        this.surname= new Name(surname);
        this.fiscalCode= new TaxCode(fiscalCode);
        this.nickname= new Nickname(nickname);
        this.email= new Email(email);
        this.birthday.setGregorianChange(birthday.getGregorianChange());
        this.gender= gender;

        /*start a default*/
        this.socialStatus= new SocialStatus();

    }
    //Costruttore con tutti i parametri possibili di PublicData
    public PublicData(Name name, Name surname, GregorianCalendar birthday, Gender gender, TaxCode fiscalCode, SocialStatus socialStatus, ImagePath profileImage, Email email, Nickname nickname){
        this.name= new Name(name);
        this.surname= new Name(surname);
        this.birthday.setGregorianChange(birthday.getGregorianChange());
        this.gender= gender;
        this.fiscalCode= new TaxCode(fiscalCode);
        this.socialStatus= new SocialStatus(socialStatus);
        this.profileImage= new ImagePath(profileImage);
        this.email= new Email(email);
        this.nickname= new Nickname(nickname);
    }
    //Costruttore che prende un PublicData e lo copia in un'altra istanza
    public PublicData(PublicData pubD){
        this.name= new Name(pubD.getName());
        this.surname= new Name(pubD.getName());
        this.birthday.setGregorianChange(pubD.birthday.getGregorianChange());
        this.gender= pubD.getGender();
        this.fiscalCode= new TaxCode(pubD.getFiscalCode());
        this.socialStatus= new SocialStatus(pubD.getSocialStatus());
        this.profileImage= new ImagePath(pubD.getProfileImage());
        this.email= new Email(pubD.getEmail());
        this.nickname= new Nickname(pubD.getNickname());
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

    @Override
    public String toString ()
    {
        String out;
        out="Name:"+this.name.get()+"\n";
        out+="Surname:"+this.surname.get()+"\n";
        out+="Birthday:"+this.birthday.toString()+"\n";
        out+="Gender:"+this.gender.toString()+"\n";
        out+="TaxCode:"+this.fiscalCode.get()+"\n";
        out+="SocialStatus:"+this.socialStatus.get()+"\n";
        out+="ImagePath:"+this.profileImage.get()+"\n";
        out+="Email:"+this.email.get()+"\n";
        out+="Nickname:"+this.nickname.get()+"\n";
        return out;
    }
}
