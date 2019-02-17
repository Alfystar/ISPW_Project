package entity;


public class Utente{
    private BasicUserInfo pubD;
    private RestrictUserInfo prD;
    private PW pw;
    private UserStatus userStatus;
    private Roles roles;
    private Questionary questionary;


    public Utente(BasicUserInfo pubD, RestrictUserInfo prD, PW pw, Roles roles, Questionary questionary){
        this.pubD = (BasicUserInfo) pubD.clone();
        this.prD = (RestrictUserInfo) prD.clone();
        this.pw = new PW(pw);
        this.roles = new Roles(roles);
        this.questionary = new Questionary(questionary);
        this.userStatus = UserStatus.ACTIVE;
    }

    public Utente(BasicUserInfo pubD, RestrictUserInfo prD, PW pw, Roles roles, UserStatus userStatus, Questionary questionary){
        this.pubD = (BasicUserInfo) pubD.clone();
        this.prD = (RestrictUserInfo) prD.clone();
        this.pw = new PW(pw);
        this.roles = new Roles(roles);
        this.questionary = new Questionary(questionary);
        this.userStatus = userStatus;
    }

    public BasicUserInfo getPublic(){
        return this.pubD;
    }

    public RestrictUserInfo getPrivate(){
        return this.prD;
    }

    public Questionary getQuestionary(){
        return this.questionary;
    }

    public Roles getRole(){
        return this.roles;
    }

    public PW getPw(){
        return this.pw;
    }

    public UserStatus getStatus(){
        return this.userStatus;
    }

    public void setStatus(UserStatus newStatus){
        this.userStatus = newStatus;
    }

    public Boolean changePw(PW oldPw, PW newPw){
        if(oldPw.getPw().equals(this.pw.getPw())){
            this.pw.setPw(newPw.getPw());
            return true;
        }else{
            return false;
        }
    }

    public Boolean comparePw(PW password){
        return password.getPw().equals(this.pw.getPw());
    }

    public String printUser(){
        String out;
        out = "Utente nick: " + this.pubD.getNick().get() + ", Email:" + this.pubD.getEmail().get() + ", CF:" + this.pubD.getTC().get() + "\n";
        out += "\t##BasicUserInfo##\n" + this.pubD.toString();
        out += "\t##RestrictUserInfo##\n" + this.prD.toString();
        out += "\t##Ruolo##\n" + this.roles;
        out += "UserStatus:" + this.userStatus.toString() + "; PW:" + this.pw.getPw() + "\n";
        out += "\t**Answare at Question is:\n" + this.questionary.getAnswersList();
        return out;
    }

    @Override
    public String toString(){
        return "UserNick: " + this.pubD.getNick().get() + ", Email:" + this.pubD.getEmail().get() + ", CF:" + this.pubD.getTC().get() + "\n";
    }

    @Override
    public boolean equals(Object o){
        try{
            Utente u = (Utente) o;
            return (this.pubD.equals(u.pubD) && this.prD.equals(u.prD) &&
                    this.pw.equals(u.pw) && this.userStatus.equals(u.userStatus) &&
                    this.roles.equals(u.roles) && this.questionary.equals(u.questionary));
        }catch(ClassCastException e){
            return false;
        }
    }

}
