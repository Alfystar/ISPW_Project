package bean;

import entity.*;

import java.util.GregorianCalendar;

public class UserInfoRegister{
    private Name name;
    private Name surname;
    private TaxCode cf;
    private Nickname nickname;
    private Email email;
    private GregorianCalendar birthday;
    private Gender gender;
    private Questionary answers;
    private PW pw;
    private SurfaceAddress cityOfBirth;

    //L'unico costruttore di UserInfoRegister deve avere tutti i parametri
    public UserInfoRegister(Name name, Name surname, TaxCode cf, Nickname nickname, Email email, GregorianCalendar birthday, Gender gender, Questionary answers, PW pw, SurfaceAddress cOfB){
        this.name = new Name(name);
        this.surname = new Name(surname);
        this.cf = new TaxCode(cf);
        this.nickname = new Nickname(nickname);
        this.email = new Email(email);
        this.birthday = birthday;
        this.gender = gender;
        this.answers = new Questionary(answers);
        this.pw = new PW(pw);
        this.cityOfBirth = new SurfaceAddress(cOfB);
    }

    public Name getName(){
        return this.name;
    }

    public Name getSurname(){
        return this.surname;
    }

    public TaxCode getCf(){
        return this.cf;
    }

    public Nickname getNickname(){
        return this.nickname;
    }

    public Email getEmail(){
        return this.email;
    }

    public GregorianCalendar getBirthday(){
        return this.birthday;
    }

    public Gender getGender(){
        return this.gender;
    }

    public Questionary getAnswers(){
        return this.answers;
    }

    public PW getPw(){
        return this.pw;
    }

    public SurfaceAddress getCityOfBirth(){return this.cityOfBirth;}
}
