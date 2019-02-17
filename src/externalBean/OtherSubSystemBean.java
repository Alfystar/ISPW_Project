package externalBean;

import entity.Nickname;
import externalControl.OtherSubSystemControl;

public class OtherSubSystemBean{

    private String nickInput;
    private String tenantInput;
    private String renterInput;

    public OtherSubSystemBean(){

        this.nickInput = "";
        this.tenantInput = "";
        this.renterInput = "";
    }

    public String getNickInput(){
        return this.nickInput;
    }

    public void setNickInput(String nI){
        this.nickInput = nI;
    }

    public String getTenantInput(){ return this.tenantInput; }

    public void setTenantInput(String tI){
        this.tenantInput = tI;
    }

    public String getRenterInput(){
        return this.renterInput;
    }

    public void setRenterInput(String rI){
        this.renterInput = rI;
    }

    public String banUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.banUser(nickN);

    }

    public String unBanUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.unBanUser(nickN);

    }

    public String destroyUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.destroyUser(nickN);

    }

    public String[] ottieniPubDUtente(){

        if(this.nickInput.equals("")){

            String[] response = {"Inserire Nickname non nullo", null};
            return response;
        }

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.obtainPubData(nickN);

    }

    public String ottieniPriDUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.obtainPriData(nickN);

    }

    public String ottieniRuoliUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.obtainRoleUser(nickN);

    }

    public String ottieniStatoUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.obtainStatusUser(nickN);

    }

    public String modRuoliUtente(){

        if(this.nickInput.equals("")) return "Inserire Nickname non nullo";

        if(this.renterInput.equals("") && this.tenantInput.equals(""))
            return "Immettere almeno una modifica di ruolo";

        OtherSubSystemControl controller = OtherSubSystemControl.getInstance();

        Nickname nickN = new Nickname(this.nickInput);

        return controller.modifyRole(nickN, this.tenantInput, this.renterInput);

    }


}
