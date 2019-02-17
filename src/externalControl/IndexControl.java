package externalControl;

import DAO.DAOClass;
import DAO.DaemonDAO;
import control.FacadeSubSystem;
import interfaces.SystemInterface;

public class IndexControl{

    private static IndexControl instance = null;

    private SystemInterface sysInt = new FacadeSubSystem();

    private IndexControl(){
    }

    public synchronized static IndexControl getInstance(){
        if(instance == null)
            instance = new IndexControl();
        return instance;
    }

    public String startDBAndIP(){
        try{
            DaemonDAO.getInstance();

            String IPtest = sysInt.getLastHost();
            //System.out.println("Controller: " + IPtest);
            return IPtest;
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String changeIPDAO(String IP){
        try{
            DAOClass daoClass = new DAOClass();
            sysInt.changeHost(IP);
            if(daoClass.testNet()) return "Successo";
            else return "Fallito";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
