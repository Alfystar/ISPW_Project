package externalBean;

import externalControl.LoginControl;

public class LoginBean{

    private String nickname;
    private String password;

    public LoginBean(){
        this.nickname = "";
        this.password = "";
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nk){
        this.nickname = nk;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String pwd){
        this.password = pwd;
    }

    public String validateLogin(){

        if(this.nickname.equals("") || this.password.equals("")){
            return "Inserire entrambi i campi";
        }

        LoginControl controller = LoginControl.getInstance();
        return controller.login(this.nickname, this.password);
    }
}
