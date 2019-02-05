package externalBean;

import externalControl.LoginControl;

import static java.lang.Boolean.FALSE;

public class LoginBean {

    private String nickname;
    private String password;

    public LoginBean() {
        this.nickname = "";
        this.password = "";
    }

    public void setNickname(String nk) {
        this.nickname = nk;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean validateLogin() {

        if (this.nickname.equals("") || this.password.equals("")) {
            return FALSE;
        }

        LoginControl controller = LoginControl.getInstance();
        return controller.login(this.nickname, this.password);
    }
}
