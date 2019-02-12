package externalBean;

import entity.Nickname;
import externalControl.ChangeDataControl;

public class ChangeDataBean{

    private String avatar;
    private String email;
    private String socialStatus;
    private String phoneNumber;
    private String address;
    private String nationality;
    private String oldPW;
    private String newPW;
    private String confirmPW;

    public ChangeDataBean(){

        this.avatar = "";
        this.email = "";
        this.socialStatus = "";
        this.phoneNumber = "";
        this.address = "";
        this.nationality = "";
        this.oldPW = "";
        this.newPW = "";
        this.confirmPW = "";
    }

    public String getAvatar(){
        return this.avatar;
    }
    public void setAvatar(String av){
        this.avatar = av;
    }

    public String getEmail(){ return this.email; }
    public void setEmail(String em){ this.email = em; }

    public String getSocialStatus(){
        return this.socialStatus;
    }
    public void setSocialStatus(String socStat){
        this.socialStatus = socStat;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phN){
        this.phoneNumber = phN;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String addr){
        this.address = addr;
    }

    public String getNationality(){
        return this.nationality;
    }
    public void setNationality(String nat){
        this.nationality = nat;
    }

    public String getOldPW(){
        return this.oldPW;
    }
    public void setOldPW(String oPW){
        this.oldPW = oPW;
    }

    public String getNewPW(){ return this.newPW; }
    public void setNewPW(String nPW){ this.newPW = nPW; }

    public String getConfirmPW(){
        return this.confirmPW;
    }
    public void setConfirmPW(String cPW){
        this.confirmPW = cPW;
    }

    public String validateChange(String nick){

        if(!this.newPW.equals(this.confirmPW)){
            return "Nuova password e conferma diverse";
        }

        ChangeDataControl controller = ChangeDataControl.getInstance();

        Nickname nickN = new Nickname(nick);

        return controller.changeData(nickN, this.avatar, this.email,
                this.socialStatus, this.phoneNumber, this.address,
                this.nationality, this.oldPW, this.newPW);
    }

    public String allowActions(String nick){
        ChangeDataControl controller = ChangeDataControl.getInstance();

        Nickname nickN = new Nickname(nick);

        return controller.verifyBan(nickN);
    }

}
