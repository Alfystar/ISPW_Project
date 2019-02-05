package externalBean;

import externalControl.RegisterControl;

import static java.lang.Boolean.FALSE;

public class RegisterBean {

    private String firstname;
    private String lastname;
    private String taxcode;
    private String nickname;
    private String email;
    private String bday;
    private String gender;
    private String password;
    private String question1;
    private String question2;
    private String question3;
    private String question4;

    public RegisterBean() {
        this.firstname = "";
        this.lastname = "";
        this.taxcode = "";
        this.nickname = "";
        this.email = "";
        this.bday = "";
        this.gender = "";
        this.password = "";
        this.question1 = "";
        this.question2 = "";
        this.question3 = "";
        this.question4 = "";
    }

    public void setFirstname(String fn) {
        this.firstname = fn;
    }
    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String ln) {
        this.lastname = ln;
    }
    public String getLastname() {
        return this.lastname;
    }

    public void setTaxcode(String tc) { this.taxcode = tc; }
    public String getTaxcode() {
        return this.taxcode;
    }

    public void setNickname(String nk) {
        this.nickname = nk;
    }
    public String getNickname() {
        return this.nickname;
    }

    public void setEmail(String em) {
        this.email = em;
    }
    public String getEmail() {
        return this.email;
    }

    public void setBday(String bd) {
        this.bday = bd;
    }
    public String getBday() {
        return this.bday;
    }

    public void setGender(String gen) {
        this.gender = gen;
    }
    public String getGender() {
        return this.gender;
    }

    public void setPassword(String pw) { this.password = pw;}
    public String getPassword() {
        return this.password;
    }

    public void setQuestion1(String q1) { this.question1 = q1; }
    public String getQuestion1() {
        return this.question1;
    }

    public void setQuestion2(String q2) {
        this.question2 = q2;
    }
    public String getQuestion2() {
        return this.question2;
    }

    public void setQuestion3(String q3) {
        this.question3 = q3;
    }
    public String getQuestion3() {
        return this.question3;
    }

    public void setQuestion4(String q4) {
        this.question4 = q4;
    }
    public String getQuestion4() {
        return this.question4;
    }

    public Boolean validateRegister() {

        if (this.firstname.equals("") || this.lastname.equals("") ||
            this.taxcode.equals("") || this.nickname.equals("") ||
            this.email.equals("") || this.bday.equals("") ||
            this.gender.equals("") || this.password.equals("") ||
            this.question1.equals("") || this.question2.equals("") ||
            this.question3.equals("") || this.question4.equals("") )
        {
            return FALSE;
        }
        RegisterControl controller = RegisterControl.getInstance();

        return controller.register(this.firstname, this.lastname, this.taxcode,
                                    this.nickname, this.email, this.bday,
                                    this.gender, this.password, this.question1,
                                    this.question2, this.question3, this.question4);
    }

}
