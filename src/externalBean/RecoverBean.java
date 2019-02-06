package externalBean;

import externalControl.RecoverControl;

import static java.lang.Boolean.FALSE;

public class RecoverBean {

    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String nickname;
    private String newPW;
    private String confirmPW;

    public RecoverBean() {
        this.question1 = "";
        this.question2 = "";
        this.question3 = "";
        this.question4 = "";
        this.nickname = "";
        this.newPW = "";
        this.confirmPW = "";
    }

    public void setQuestion1(String q1) {
        this.question1= q1;
    }
    public String getQuestion1() {
        return this.question1;
    }

    public void setQuestion2(String q2) { this.question2 = q2; }
    public String getQuestion2() {
        return this.question2;
    }

    public void setQuestion3(String q3) { this.question3 = q3; }
    public String getQuestion3() {
        return this.question3;
    }

    public void setQuestion4(String q4) {
        this.question4 = q4;
    }
    public String getQuestion4() {
        return this.question4;
    }

    public void setNickname(String nk) {
        this.nickname = nk;
    }
    public String getNickname() {
        return this.nickname;
    }

    public void setNewPW(String nPW) {
        this.newPW = nPW;
    }
    public String getNewPW() {
        return this.newPW;
    }

    public void setConfirmPW(String cPW) {
        this.confirmPW = cPW;
    }
    public String getConfirmPW() {
        return this.confirmPW;
    }

    public Boolean validateRecover() {

        if (this.question1.equals("") || this.question2.equals("") ||
                this.question3.equals("") || this.question4.equals("") ||
                this.nickname.equals("") || this.newPW.equals("") ||
                this.confirmPW.equals("")) {
            return FALSE;
        }
        //verifico di aver immesso la password correttamente entrambe le volte

        else if (!this.newPW.equals(this.confirmPW)){
            return FALSE;
        }

        RecoverControl controller = RecoverControl.getInstance();
        return controller.recover(this.question1, this.question2,
                                this.question3, this.question4,
                                this.nickname, this.newPW);
    }

}
