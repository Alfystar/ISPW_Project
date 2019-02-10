package externalBean;

import externalControl.RegisterControl;

public class RegisterBean{

    private String firstname;
    private String lastname;
    private String taxcode;
    private String nickname;
    private String email;
    private String bday;
    private String birthPlace;
    private String gender;
    private String password;
    private String question1;
    private String question2;
    private String question3;
    private String question4;

    public RegisterBean(){
        this.firstname = "";
        this.lastname = "";
        this.taxcode = "";
        this.nickname = "";
        this.email = "";
        this.bday = "";
        this.birthPlace = "";
        this.gender = "";
        this.password = "";
        this.question1 = "";
        this.question2 = "";
        this.question3 = "";
        this.question4 = "";
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String fn){
        this.firstname = fn;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String ln){
        this.lastname = ln;
    }

    public String getTaxcode(){
        return this.taxcode;
    }

    public void setTaxcode(String tc){ this.taxcode = tc; }

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nk){
        this.nickname = nk;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String em){
        this.email = em;
    }

    public String getBday(){
        return this.bday;
    }

    public void setBday(String bd){
        this.bday = bd;
    }

    public String getBirthPlace(){ return this.birthPlace; }

    public void setBirthPlace(String birPl){
        this.birthPlace = birPl;
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gen){
        this.gender = gen;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String pw){ this.password = pw;}

    public String getQuestion1(){
        return this.question1;
    }

    public void setQuestion1(String q1){ this.question1 = q1; }

    public String getQuestion2(){
        return this.question2;
    }

    public void setQuestion2(String q2){
        this.question2 = q2;
    }

    public String getQuestion3(){
        return this.question3;
    }

    public void setQuestion3(String q3){
        this.question3 = q3;
    }

    public String getQuestion4(){
        return this.question4;
    }

    public void setQuestion4(String q4){
        this.question4 = q4;
    }

    public String validateRegister(){

        if(this.firstname.equals("") || this.lastname.equals("") ||
                this.taxcode.equals("") || this.nickname.equals("") ||
                this.email.equals("") || this.bday.equals("") ||
                this.gender.equals("") || this.password.equals("") ||
                this.question1.equals("") || this.question2.equals("") ||
                this.question3.equals("") || this.question4.equals("")){
            return "Inserire tutti i campi (obbligatori)";
        }
        RegisterControl controller = RegisterControl.getInstance();

        return controller.register(this.firstname, this.lastname, this.taxcode,
                this.nickname, this.email, this.bday, this.birthPlace,
                this.gender, this.password, this.question1,
                this.question2, this.question3, this.question4);
    }

}
