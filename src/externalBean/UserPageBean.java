package externalBean;

import externalControl.UserPageControl;

public class UserPageBean {

public String[] getStringUsData(String nickN){

        UserPageControl controller = UserPageControl.getInstance();

        String[] tmp = {"add","right", "strings"};
        return tmp;
    }

    public void cancelUser(){

    }

}
