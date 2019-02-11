package externalBean;

import externalControl.IndexControl;

public class IndexBean{

    private String IPtext;

    public IndexBean(){
        this.IPtext = "";
    }

    public String getIPtext(){ return this.IPtext; }

    public void setIPtext(String IP){
        this.IPtext = IP;
    }

    public String changeIP(){
        IndexControl controller = IndexControl.getInstance();
        return controller.changeIPDAO(this.IPtext);

    }

    public String startDB(){
        IndexControl controller = IndexControl.getInstance();
        return controller.startDBAndIP();
    }

}
