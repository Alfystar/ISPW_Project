package entity;

public class Utente {
    private PublicData pubD;
    private PrivateData prD;
    private PW pw;
    private UserStatus userStatus;
    private Roles roles;
    private Questions questions;

    public Utente(PublicData pubD, PrivateData prD, PW pw, Roles roles, Questions questions){
        this.pubD= new PublicData(pubD);
        this.prD= new PrivateData(prD);
        this.pw= new PW(pw);
        this.roles= new Roles(roles);
        this.questions= new Questions(questions);
        this.userStatus=UserStatus.ACTIVE;
    }


    public PublicData getPublic(){
        return this.pubD;
    }

    public PrivateData getPrivate(){
        return this.prD;
    }

    public Questions getQuestions(){
        return this.questions;
    }

    public Roles getRole(){
        return this.roles;
    }

    public UserStatus getStatus(){
        return this.userStatus;
    }

    public void setStatus(UserStatus newStatus){
        this.userStatus= newStatus;
    }

    public PW getPw(){
        return  this.pw;
    }

    public Boolean changePw(PW oldPw, PW newPw){
        if(oldPw.getPw().equals(this.pw.getPw())){
            this.pw.setPw(newPw.getPw());
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean comparePw(PW password){
        return password.getPw().equals(this.pw.getPw());
    }

    public String printUser()
    {
        String out;
        out="Utente nick: "+ this.pubD.getNickname().get()+", Email:"+this.pubD.getEmail().get()+", CF:"+ this.pubD.getTC().get()+"\n";
        out+="\t##PublicData##\n"+ this.pubD.toString();
        out+="\t##PrivateData##\n"+ this.prD.toString();
        out+="\t##Ruolo##\n"+this.roles;
        out+="UserStatus:"+this.userStatus.toString()+"; PW:"+ this.pw.getPw()+"\n";
        out+="\t**Answare at Question is:\n"+this.questions.getAnswersList();
        return out;
    }

    @Override
    public String toString()
    {
        return "UserNick: "+ this.pubD.getNickname().get()+", Email:"+this.pubD.getEmail().get()+", CF:"+ this.pubD.getTC().get()+"\n";
    }

}
