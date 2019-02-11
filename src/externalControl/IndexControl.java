package externalControl;

import control.FacadeSubSystem;
import interfaces.SystemInterface;

public class IndexControl{

    private static IndexControl instance;

    private SystemInterface sysInt = new FacadeSubSystem();

    private IndexControl(){
    }

    public static IndexControl getInstance(){
        if(instance == null)
            instance = new IndexControl();
        return instance;
    }

    public String changeIPDAO(String IP){
        try{
            sysInt.changeHost(IP);
            return "Successo";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
