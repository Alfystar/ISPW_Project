package entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PublicData{
    private Name name;
    private Name surname;
    private GregorianCalendar birthday;
    private Gender gender;
    private TaxCode fiscalCode;
    private SocialStatus socialStatus;
    private Avatar avatar;
    private Email email;
    private Nickname nickname;

    //Costruttore con i parametri di UserInfoRegister relativi a PublicData
    public PublicData(Name name, Name surname, TaxCode fiscalCode, Nickname nickname, Email email, GregorianCalendar birthday, Gender gender){
        /*passati*/
        this.name = new Name(name);
        this.surname = new Name(surname);
        this.fiscalCode = new TaxCode(fiscalCode);
        this.nickname = new Nickname(nickname);
        this.email = new Email(email);
        this.birthday = new GregorianCalendar(birthday.get(Calendar.YEAR), birthday.get(Calendar.MONTH), birthday.get(Calendar.DAY_OF_MONTH));
        this.gender = gender;

        /*start a default*/
        this.socialStatus = new SocialStatus();
        this.avatar = new Avatar();

    }

    //Costruttore con tutti i parametri possibili di PublicData
    public PublicData(Name name, Name surname, GregorianCalendar birthday, Gender gender, TaxCode fiscalCode, SocialStatus socialStatus, Avatar avatar, Email email, Nickname nickname){
        this.name = new Name(name);
        this.surname = new Name(surname);
        this.birthday = new GregorianCalendar(birthday.get(Calendar.YEAR), birthday.get(Calendar.MONTH), birthday.get(Calendar.DAY_OF_MONTH));
        this.gender = gender;
        this.fiscalCode = new TaxCode(fiscalCode);
        this.socialStatus = new SocialStatus(socialStatus);
        this.avatar = avatar;
        this.email = new Email(email);
        this.nickname = new Nickname(nickname);
    }

    //Costruttore che prende un PublicData e lo copia in un'altra istanza
    public PublicData(PublicData pubD){
        this.name = new Name(pubD.getName());
        this.surname = new Name(pubD.getSurname());
        this.birthday = new GregorianCalendar(pubD.getBirthday().get(Calendar.YEAR), pubD.getBirthday().get(Calendar.MONTH), pubD.getBirthday().get(Calendar.DAY_OF_MONTH));
        this.gender = pubD.getGender();
        this.fiscalCode = new TaxCode(pubD.getTC());
        this.socialStatus = new SocialStatus(pubD.getSocialStatus());
        this.avatar = pubD.getAvatar();
        this.email = new Email(pubD.getEmail());
        this.nickname = new Nickname(pubD.getNick());
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

    public TaxCode getTC(){
        return this.fiscalCode;
    }

    public SocialStatus getSocialStatus(){
        return this.socialStatus;
    }

    public Avatar getAvatar(){
        return this.avatar;
    }

    public Email getEmail(){
        return this.email;
    }

    public Nickname getNick(){
        return this.nickname;
    }

    private GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int days = Integer.parseInt(splitDate[2]);
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, days);
        return gc;

    }

    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }

    @Override
    public String toString(){
        String out;
        out = "Name:" + this.name.get() + "\n";
        out += "Surname:" + this.surname.get() + "\n";
        out += "Birthday:" + gregCalToString(this.birthday) + "\n";
        out += "Gender:" + this.gender.toString() + "\n";
        out += "TaxCode:" + this.fiscalCode.get() + "\n";
        out += "SocialStatus:" + this.socialStatus.get() + "\n";
        out += "ImageFile.Avatar:" + this.avatar.getAvatarName() + "\n";
        out += "Email:" + this.email.get() + "\n";
        out += "Nickname:" + this.nickname.get() + "\n";
        return out;
    }
}
